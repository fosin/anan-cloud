# 更新k8s证书

## 1、各个证书过期时间
    for item in `find /etc/kubernetes/pki -maxdepth 2 -name "*.crt"`;do openssl x509 -in $item -text -noout| grep Not;echo ======================$item===============;done
    
    #证书默认有效期
    /etc/kubernetes/pki/apiserver.crt                #1年有效期
    /etc/kubernetes/pki/front-proxy-ca.crt           #10年有效期
    /etc/kubernetes/pki/ca.crt                       #10年有效期
    /etc/kubernetes/pki/apiserver-etcd-client.crt    #1年有效期
    /etc/kubernetes/pki/front-proxy-client.crt       #1年有效期
    /etc/kubernetes/pki/etcd/server.crt              #1年有效期
    /etc/kubernetes/pki/etcd/ca.crt                  #10年有效期
    /etc/kubernetes/pki/etcd/peer.crt                #1年有效期
    /etc/kubernetes/pki/etcd/healthcheck-client.crt  #1年有效期
    /etc/kubernetes/pki/apiserver-kubelet-client.crt #1年有效期

## 2、备份原有证书
    cp -rp /etc/kubernetes /etc/kubernetes.bak

## 3、备份etcd数据目录
    cp -r /var/lib/etcd /var/lib/etcd.bak

## 4、更新master节点相关证书

### 4.1、在master节点上执行
    kubeadm alpha certs renew all

### 4.2、在master节点上将/etc/kubernetes目录下的所有配置文件备份，然后执行：
    kubeadm alpha kubeconfig user --client-name=admin
    kubeadm alpha kubeconfig user --org system:masters --client-name kubernetes-admin  > /etc/kubernetes/admin.conf
    kubeadm alpha kubeconfig user --client-name system:kube-controller-manager > /etc/kubernetes/controller-manager.conf
    kubeadm alpha kubeconfig user --org system:nodes --client-name system:node:$(hostname) > /etc/kubernetes/kubelet.conf
    kubeadm alpha kubeconfig user --client-name system:kube-scheduler > /etc/kubernetes/scheduler.conf

### 4.3、将新生成的admin配置文件覆盖掉原本的admin文件
    mv $HOME/.kube/config $HOME/.kube/config.old
    cp -i /etc/kubernetes/admin.conf $HOME/.kube/config

    如果出现Unable to connect to the server: x509: certificate is valid for xxxx, not xxxx的报错，
    就需要修改/etc/kubernetes/目录下的所有conf文件中的cluster.server字段，将其改为master的kubelet监听的ip。
    同时重新覆盖/root/.kube/config文件。这种问题发生在多网卡的情况下。
    sed -i 's/100.100/172.16/g' /etc/kubernetes/*.conf

### 4.4、在所有Master上执行重启kube-apiserver,kube-controller,kube-scheduler,etcd这4个容器，使证书生效
    docker ps -a |grep -E 'k8s_kube-apiserver|k8s_kube-controller-manager|k8s_kube-scheduler|k8s_etcd_etcd' | awk -F ' ' '{print $1}' |xargs docker restart

## 5、更新kubelet相关证书

    kubelet的证书通过修改kubelet启动参数配置为自动更新。

### 5.1、首先，在master节点上，修改controller manager组件的参数，增加：

    kube-controller-manager --experimental-cluster-signing-duration=87600h  --feature-gates=RotateKubeletClientCertificate=true
    这两个参数。
    
    对于controller manager运行在静态pod中的情况，在/etc/kubernets/manifests目录下编辑controller manager的yaml文件，在启动命令下增加这两项参数。
    
    然后，将此yaml文件移出/etc/kubernets/manifests目录，等几秒钟，再移回来，controller manager的静态pod就会自动重启。
    
    注意，不要直接通过kubectl edit修改静态pod。
    
    对于controller manager运行在物理机上的情况，与下面kubelet的操作类似。

### 5.2、在所有节点上，修改kubelet的systemctl的启动命令。在CentOS中位于/etc/systemd/system/kubelet.service.d中，增加：

    kubelet --feature-gates=RotateKubeletClientCertificate=true  --cert-dir=/var/lib/kubelet/pki --rotate-certificates  --rotate-server-certificates
    四项参数。
    
    然后执行：
    
    systemctl daemon-reload
    systemctl restart kubelet
    kubelet正常运行了，则证书更新完成。
## 6、更新csr资源

    执行完以上两步后，集群可能会出现日志无法查看的情况。执行kubectl logs时，报：
    
    Error from server: Get https://xxx:10250/containerLogs/xxx remote error: tls: internal error
    这里需要认证一下csr，即certificatesigningrequests，是kubelet向master节点发送的证书认证请求。
    
    执行kubectl get csr，发现有大量的csr处于Pending状态。将这些csr认证通过：
    
    kubectl certificate approve [name]
    一一更新后，再查看状态时变为Approved,Issued。此后，再执行kubectl logs就正常了。

## 7、更新secret

    集群中与etcd相关的secret会用到etcd的证书作为字段。这些secret需要根据新生成的etcd证书进行更新。
    
    用kubectl get secret -n kube-system | grep etcd找出所有与etcd相关的secret资源，将里面的xxx.crt和xxx.key字段分别替换为位于/etc/kubernetes/pki/etcd下的，server.crt和server.key经过base64加密后的字符串。
    
    之后，重启etcd和使用到secret的各组件即可。

## 8、重启apiserver
    docker ps -a |grep -E 'k8s_kube-apiserver' | awk -F ' ' '{print $1}' |xargs docker restart
