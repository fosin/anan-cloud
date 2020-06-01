# 部署⼀个Kubernetes Dashboard
## 1. 为dashboard⽹站⽣成证书
     mkdir certs
     
     openssl req -nodes -newkey rsa:2048 -keyout certs/dashboard.key -out certs/dashboard.csr -subj "/C=/ST=/L=/O=/OU=/CN=kubernetes-dashboard"

     openssl x509 -req -sha256 -days 10000 -in certs/dashboard.csr -signkey certs/dashboard.key -out certs/dashboard.crt

## 2.为dashboard证书⽣成secrest
    kubectl create secret generic kubernetes-dashboard-certs --from-file=certs

## 4. 接下来就可以看到kubernetes原⽣dashboard了！
    接下来就可以看到dashboard的访问，使⽤EXTERNAL-IP登录浏览器
    
## 5. 方式一：获取token登录
    kubectl get secret $(kubectl get secret | grep anan | awk '{print $1}') -o jsonpath={.data.token}|base64 -d

## 6. 方式二：获取kubeconfig文件登录
      
      DASH_TOKEN=$(kubectl get secret $(kubectl get secret | grep anan | awk '{print $1}') -o jsonpath={.data.token}|base64 -d)
      
      kubectl config set-cluster kubernetes --server=192.168.0.3:6443 --kubeconfig=/root/dashboard-anan.conf
      
      kubectl config set-credentials anan --token=$DASH_TOKEN --kubeconfig=/root/dashboard-anan.conf
      
      kubectl config set-context anan@kubernetes --cluster=kubernetes --user=anan --kubeconfig=/root/dashboard-anan.conf
      
      kubectl config use-context anan@kubernetes --kubeconfig=/root/dashboard-anan.conf
      
      生成的dashboard-anan.conf即可用于登录dashboard
