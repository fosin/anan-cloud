## 1、Docker Swarm集群环境部署
### 1.1、创建集群管理节点(当主机上存在多个ip时，需要指定MANAGER-IP)
    $ docker swarm init --advertise-addr 19168.137.8
    
    Swarm initialized: current node (jexina8uncvt29aeim6wcg05n) is now a manager.
    
    To add a worker to this swarm, run the following command:

    docker swarm join \
    --token SWMTKN-1-22p3xwtzd75l7e7h95x8lgsvb9efh9r4aix8ari0j32suhtua6-66lffpxnvywldww4rhysrexg9 \
    19168.137.8:2377

    To add a manager to this swarm, run 'docker swarm join-token manager' and follow the instructions.
    
    再次查看集群加入命令
    $ docker swarm join-token worker
    
    To add a worker to this swarm, run the following command:

    docker swarm join \
    --token SWMTKN-1-03ub9x4wqn78ma6jg3bjahf9siq9mi536bxk6t6zuk3ep7iwsb-0zuut5hf56qmwdpb3umvw53ip \
    19168.106.2:2377

### 1.2、使用创建管理节点时返回的命令在worker节点上都执行一遍加入swarm集群

### 1.3、创建swarm网络
    docker network create -d overlay --subnet=172.29.0.0/16 anan-overlay

### 1.4、环境部署
    拷贝docker文件夹到Linux服务器上，每个节点都要拷贝
    分配权限
        chmod 755 deploy/ -R
    
### 1.5、使用yml启动swarm集群
    启动基础中间件(mysql、redis、rabbitmq)
    docker stack deploy -c docker-stack-base.yml b
    
    启动服务(应用相关服务和nginx)
    docker stack deploy -c docker-stack-services.yml s
    
    启动elk日志收集分析(elasticsearch、logstash、kibana、filebeat)、分布式链路追踪zipkin
    docker stack deploy -c docker-stack-elk.yml e
    
    启动Prometheus监控(cadvisor、node-exporter、grafana、prometheus)
    docker stack deploy -c docker-stack-prometheus.yml p

### 1.6、停止集群中所有服务并删除容器
    docker stack rm s
    docker stack rm p
    docker stack rm e
    docker stack rm b
