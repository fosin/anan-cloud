# Helm部署指南
```shell script
#安装helm
ntpdate ntp1.aliyun.com
#如果wget下载没有进度，使用浏览器下载后手动上传
wget https://get.helm.sh/helm-v3.2.0-linux-amd64.tar.gz --user-agent="Mozilla/5.0 (X11;U;Linux i686;en-US;rv:1.9.0.3) Geco/2008092416 Firefox/3.0.3"  --no-check-certificate
tar -zxvf helm-v3.2.0-linux-amd64.tar.gz && mv linux-amd64/helm /usr/local/bin/ && rm -rf linux-amd64

#设置helm命令补全
yum install bash-completion -y
source /usr/share/bash-completion/bash_completion
echo 'source <(helm completion bash)' >> ~/.bashrc

#设置阿里的镜像仓库
helm repo add stable https://kubernetes-charts.storage.googleapis.com
helm repo add aliyun https://kubernetes.oss-cn-hangzhou.aliyuncs.com/charts

#卸载helm软件
rm -rf /usr/local/bin/helm

```
