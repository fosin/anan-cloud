package com.github.fosin.anan.zuul.balance;

import com.github.fosin.anan.core.util.IpUtil;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 * Description: 开发模式下优先找同IP的服务，其次找同网段的服务，优先使用该负载模式
 *
 * @author fosin
 * @date 2018.9.25
 */
@Slf4j
public class LocalLoadBalanceRule extends AbstractLoadBalancerRule {

    @Value("${spring.cloud.client.ipAddress}")
    private String ip;

//    public LocalLoadBalanceRule() {
//        super();
//    }

    @Override
    public Server choose(Object key) {
        ILoadBalancer lb = getLoadBalancer();

        if (lb == null) {
            log.warn("no load balancer");
            return null;
        }

        int count = 0;
        while (count++ < 10) {
            List<Server> reachableServers = lb.getReachableServers();
            List<Server> allServers = lb.getAllServers();
            int upCount = reachableServers.size();
            int serverCount = allServers.size();

            if ((upCount == 0) || (serverCount == 0)) {
                log.warn("No up servers available from load balancer: " + lb);
                return null;
            }

            for (Server server : allServers) {
                if (server == null) {
                    /* Transient. */
                    Thread.yield();
                    continue;
                }

                if (server.isAlive() && (server.isReadyToServe())) {
                    String host = server.getHost().toLowerCase();
                    if ("localhost".equals(host)) {
                        return (server);
                    }

                    //IPv4
                    if (IpUtil.isIPv4LiteralAddress(host)) {
                        //找同IP地址的
                        if (ip.equals(host)) {
                            return (server);
                        }
                        //找同网段的
                        if (ip.substring(0, ip.lastIndexOf(".")).equals(host.substring(0, host.lastIndexOf(".")))) {
                            return (server);
                        }
                    }
                    //IPv6
                    if (IpUtil.isIPv6LiteralAddress(host)) {
                        //找同IP地址的
                        if (ip.equals(host)) {
                            return (server);
                        }
                        //找同网段的
                        if (ip.substring(0, ip.lastIndexOf(":")).equals(host.substring(0, host.lastIndexOf(":")))) {
                            return (server);
                        }
                    }
                }
            }
        }
        if (count >= 10) {
            log.warn("No available alive servers after 10 tries from load balancer: "
                    + lb);
        }
        return null;
//        Optional<Server> server = getPredicate().chooseRoundRobinAfterFiltering(lb.getAllServers(), key);
//        if (server.isPresent()) {
//            return server.get();
//        } else {
//
//        }
    }

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }


}
