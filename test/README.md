# Jmeter压测

## 1、部署Jmeter

### 安装jdk1.8及以上

### 安装jmeter

```shell script
<https://jmeter.apache.org/download_jmeter.cgi>
例如下载apache-jmeter-5.3.zip<https://mirrors.bfsu.edu.cn/apache//jmeter/binaries/apache-jmeter-5.3.zip>
cd /opt
unzip apache-jmeter-5.3.zip

#开启远程服务ssl
cat apache-jmeter-5.3/bin/jmeter.properties | grep server.rmi.ssl.disable
sed -i 's/#server.rmi.ssl.disable false/server.rmi.ssl.disable true/g' apache-jmeter-5.3/bin/jmeter.properties

#添加6台负载机 
cat apache-jmeter-5.3/bin/jmeter.properties | grep remote_hosts
sed -i 's/#remote_hosts 127.0.0.1/remote_hosts 172.24.125.175:1099,172.24.125.176:1099/g' apache-jmeter-5.3/bin/jmeter.properties

#修改测试报告
cat apache-jmeter-5.3/bin/jmeter.properties | grep jmeter.save.saveservice.output_format
sed -i 's/#jmeter.save.saveservice.output_format=csv/jmeter.save.saveservice.output_format=csv/g' apache-jmeter-5.3/bin/jmeter.properties

#在/tmp目录新建2个文件夹report和result用于存储测试报告
mkdir /tmp/report /tmp/result

```

## 2、压测Jmeter

### #上传jmx文件到/tmp目录下

备注 因为每次运行命令前,必须要清空result和report文件,否则无法运行命令

### 启动命令

参数 n 非gui运行 t 指定测试脚本 R 指定多少个 agent 启动并参与测试 r 代表全部 agent 启动并参与测试 l 生成测试结果文件

```shell script
不调用负载机
jmeter -n -t  /tmp/anan.jmx  -l /tmp/result/anan.jtl -e -o /tmp/report

调用所有负载机
jmeter -n -t /tmp/anan.jmx -r -l /tmp/result/anan.jtl -e -o /tmp/report

调用指定负载机
jmeter -n -t /tmp/anan.jmx -R 172.24.125.175 -l /tmp/result/anan.jtl -e -o /tmp/report

```

### 查看日志

```shell script
tailf jmeter.log

tailf jmeter-server.log

```

## 3、查看报告

### 将linux下的report导出到windows,查看index.html测试报告


