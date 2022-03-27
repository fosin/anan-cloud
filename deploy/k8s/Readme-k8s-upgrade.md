# 3、升级kok8s版本

```shell script
#替换环境变量
sed -i 's/1.18.10/1.19.16/g' ~/.bash_profile
source ~/.bash_profile

```

## 3.1、查询当前 k8s 可用的所有版本

```shell script
# 在列表中找到最新的 1.x 版本号
# 该版本号格式为 1.x.y-0，其中x是此版本号，y 是最新的补丁
# 以下所有1.x.y替换成具体的版本，例如1.19.16-0
yum makecache fast && yum list --showduplicates kubeadm --disableexcludes=kubernetes

```

## 3.2、升级 控制平面 节点

### 3.2.1、升级第一个控制平面节点

```shell script
K8S_VERSION=1.19.16-0
#假设所有命令都以 root 身份执行
#每个控制平面 节点上执行如下命令，升级 kubeadm
yum install -y kubeadm-${K8S_VERSION} --disableexcludes=kubernetes

#执行命令，以验证升级结果
kubeadm version

```

### 3.2.2、在第一个控制平面节点上执行命令

```shell script
#请忽略错误 could not fetch a Kubernetes version from the internet: unable to get URL "https://dl.k8s.io/release/stable.txt"，
#在不能获得最新 kubernetes 版本列表的情况下，将使用 kubeadm 的版本作为升级的目标版本
#（在前面的步骤中，已经从 yum 仓库找到了最新 kubeadm 的版本）
kubeadm upgrade plan

# 替换 x 为最新补丁的版本号（kubeadm upgrade 同时会自动更新节点上的证书。如果不想更新证书，请使用参数 --certificate-renewal=false）
kubeadm upgrade apply v${K8S_VERSION} -y

```

## 3.3、升级其他控制平面节点

```shell script
#不需要执行 kubeadm upgrade plan
#此时执行的是 kubeadm upgrade node
kubeadm upgrade node

```

## 3.4、升级 kubelet 和 kubectl

### 3.4.1、在所有的 控制平面 节点上执行如下命令以升级 kubelet 和 kubectl

```shell script
# 替换 x 为最新补丁的版本号
yum install -y kubelet-${K8S_VERSION} kubectl-${K8S_VERSION} --disableexcludes=kubernetes

```

### 3.4.2、执行如下命令，以重启 kubelet

```shell script
## kuberntes服务nodeport端口，默认是3000-32767。但是某些场合下，这个限制并不适用。
## 在apiserver配置文件中command下添加    - --service-node-port-range=1-65535参数，修改后会自动生效，无需其他操作:
vim /etc/kubernetes/manifests/kube-apiserver.yaml
systemctl daemon-reload && systemctl restart kubelet && systemctl status kubelet

```

## 3.5、升级 worker 节点

    建议逐个升级 worker 节点，或者同一时间点只升级少量的 worker 节点，以避免集群出现资源紧缺的状况。

### 3.5.1、升级 kubeadm（在所有的 worker 节点上执行如下命令，升级 kubeadm）

```shell script
# 将 1.19.16-0 中的 x 替换为最新的补丁版本
yum install -y kubeadm-${K8S_VERSION} --disableexcludes=kubernetes

```

### 3.5.2、排空（drain）节点

```shell script
#执行以下命令，将节点标记为 不可调度的 并驱逐节点上所有的 Pod，
# 在可以执行 kubectl 命令的位置执行（通常是第一个 控制平面节点）
# $NODE 代表一个变量，实际执行时，请用您的节点名替换
kubectl drain $NODE --ignore-daemonsets
# 如果由于有状态Pod导致驱逐失败，只能删除本地数据，但是有风险，需自行评估
kubectl drain $NODE --ignore-daemonsets --delete-local-data
#输出结果如下所示：
node/local2 cordoned
WARNING: ignoring DaemonSet-managed Pods: kube-system/kube-flannel-ds-amd64-zlrbd, kube-system/kube-proxy-k7bvk
evicting pod "coredns-5644d7b6d9-m47fm"
pod/coredns-5644d7b6d9-m47fm evicted
node/local2 evicted
```

### 3.5.3、升级 kubelet 的配置

```shell script
kubeadm upgrade node
```

### 3.5.4、升级 kubelet 和 kubectl

```shell script
#在所有的 worker 节点执行命令
# 替换 x 为最新补丁的版本号
yum install -y kubelet-${K8S_VERSION} kubectl-${K8S_VERSION} --disableexcludes=kubernetes

#执行如下命令，以重启 kubelet
systemctl daemon-reload
systemctl restart kubelet
```

### 3.5.5、恢复（uncordon）节点

```shell script
#执行如下命令，使节点重新接受调度并投入使用：
kubectl uncordon $NODE
```

## 3.6、检查集群的状态

```shell script
#在所有节点的 kubelet 本升级以后，执行如下命令以验证所有节点都可用：
#STATUS 字段应该为 Ready，版本号也应该显示目标版本号。
kubectl get nodes -o wide
```

## 3.7、从错误状态中恢复

    #如果 kubeadm upgrade 执行过程中出现错误且未曾回滚，例如执行过程中意外关机，您可以再次执行 kubeadm upgrade。
    #该命令是 幂等 的，并将最终保证您能够达到最终期望的升级结果。
    #从失败状态中恢复时，请执行 kubeadm upgrade --force 命令，注意要使用集群的当前版本号。
