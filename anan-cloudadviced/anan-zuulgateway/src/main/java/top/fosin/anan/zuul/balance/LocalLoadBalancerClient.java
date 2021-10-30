package top.fosin.anan.zuul.balance;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerRequest;

import java.net.URI;

/**
 * @author fosin
 * @date 2018.9.24
 */
public class LocalLoadBalancerClient implements LoadBalancerClient {
    @Override
    public <T> T execute(String serviceId, LoadBalancerRequest<T> request) {
        return null;
    }

    @Override
    public <T> T execute(String serviceId, ServiceInstance serviceInstance, LoadBalancerRequest<T> request) {
        return null;
    }

    @Override
    public URI reconstructURI(ServiceInstance instance, URI original) {
        return null;
    }

    @Override
    public ServiceInstance choose(String serviceId) {
        return null;
    }
}
