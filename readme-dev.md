# 开发指南(待补充)

## 1、目录结构介绍

## 2、模块介绍

## 3、切换Profile

## 4、启动顺序

## 5、使用jasper加密配置文件
### input是需要加密的密码、password是加密的密钥、algorithm是加密算法
### 获取加密的密码
java -cp E:\Tools\Apache\Maven\repository\org\jasypt\jasypt\1.9.3\jasypt-1.9.3.jar org.jasypt.intf.cli.JasyptPBEStringEncryptionCLI input="tcFuture!20210911" password=J6B8wrE8lrjpClFlXfHn  algorithm=PBEWithMD5AndDES

### 解密密码
java -cp E:\Tools\Apache\Maven\repository\org\jasypt\jasypt\1.9.3\jasypt-1.9.3.jar org.jasypt.intf.cli.JasyptPBEStringDecryptionCLI input=svOoGRZ5qlC1bRGGh+7YwA== password=oF7tVrdfjrbQ0NfSsRL3 algorithm=PBEWithMD5AndDES
