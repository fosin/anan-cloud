package top.fosin.anan.cloudgateway.balance;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerRequest;
import org.springframework.cloud.client.loadbalancer.Request;

import java.io.IOException;
import java.net.URI;

/**
 * @author fosin
 * @date 2018.9.24
 */
public class LocalLoadBalancerClient implements LoadBalancerClient {
    @Override
    public <T> T execute(String serviceId, LoadBalancerRequest<T> request) throws IOException {
        return null;
    }

    @Override
    public <T> T execute(String serviceId, ServiceInstance serviceInstance, LoadBalancerRequest<T> request) throws IOException {
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

    @Override
    public <T> ServiceInstance choose(String serviceId, Request<T> request) {
        return null;
    }

    //public URI reconstructURI(ServiceInstance instance, URI original) {
    //    Assert.notNull(instance, "instance can not be null");
    //    String serviceId = instance.getServiceId();
    //    RibbonLoadBalancerContext context = this.clientFactory.getLoadBalancerContext(serviceId);
    //    URI uri;
    //    Server server;
    //    if (instance instanceof LocalLoadBalancerClient.RibbonServer) {
    //        LocalLoadBalancerClient.RibbonServer ribbonServer = (LocalLoadBalancerClient.RibbonServer)instance;
    //        server = ribbonServer.getServer();
    //        uri = RibbonUtils.updateToSecureConnectionIfNeeded(original, ribbonServer);
    //    } else {
    //        server = new Server(instance.getScheme(), instance.getHost(), instance.getPort());
    //        IClientConfig clientConfig = this.clientFactory.getClientConfig(serviceId);
    //        ServerIntrospector serverIntrospector = this.serverIntrospector(serviceId);
    //        uri = RibbonUtils.updateToSecureConnectionIfNeeded(original, clientConfig, serverIntrospector, server);
    //    }
    //
    //    return context.reconstructURIWithServer(server, uri);
    //}
    //
    //public ServiceInstance choose(String serviceId) {
    //    return this.choose(serviceId, (Object)null);
    //}
    //
    //public ServiceInstance choose(String serviceId, Object hint) {
    //    Server server = this.getServer(this.getLoadBalancer(serviceId), hint);
    //    return server == null ? null : new LocalLoadBalancerClient.RibbonServer(serviceId, server, this.isSecure(server, serviceId), this.serverIntrospector(serviceId).getMetadata(server));
    //}
    //
    //public <T> T execute(String serviceId, LoadBalancerRequest<T> request) throws IOException {
    //    return this.execute(serviceId, request, null);
    //}
    //
    //public <T> T execute(String serviceId, LoadBalancerRequest<T> request, Object hint) throws IOException {
    //    ILoadBalancer loadBalancer = this.getLoadBalancer(serviceId);
    //    Server server = this.getServer(loadBalancer, hint);
    //    if (server == null) {
    //        throw new IllegalStateException("No instances available for " + serviceId);
    //    } else {
    //        LocalLoadBalancerClient.RibbonServer ribbonServer = new LocalLoadBalancerClient.RibbonServer(serviceId, server, this.isSecure(server, serviceId), this.serverIntrospector(serviceId).getMetadata(server));
    //        return this.execute(serviceId, (ServiceInstance)ribbonServer, (LoadBalancerRequest)request);
    //    }
    //}
    //
    //public <T> T execute(String serviceId, ServiceInstance serviceInstance, LoadBalancerRequest<T> request) throws IOException {
    //    Server server = null;
    //    if (serviceInstance instanceof LocalLoadBalancerClient.RibbonServer) {
    //        server = ((LocalLoadBalancerClient.RibbonServer)serviceInstance).getServer();
    //    }
    //
    //    if (server == null) {
    //        throw new IllegalStateException("No instances available for " + serviceId);
    //    } else {
    //        RibbonLoadBalancerContext context = this.clientFactory.getLoadBalancerContext(serviceId);
    //        RibbonStatsRecorder statsRecorder = new RibbonStatsRecorder(context, server);
    //
    //        try {
    //            T returnVal = request.apply(serviceInstance);
    //            statsRecorder.recordStats(returnVal);
    //            return returnVal;
    //        } catch (IOException var8) {
    //            statsRecorder.recordStats(var8);
    //            throw var8;
    //        } catch (Exception var9) {
    //            statsRecorder.recordStats(var9);
    //            ReflectionUtils.rethrowRuntimeException(var9);
    //            return null;
    //        }
    //    }
    //}
    //
    //private ServerIntrospector serverIntrospector(String serviceId) {
    //    ServerIntrospector serverIntrospector = (ServerIntrospector)this.clientFactory.getInstance(serviceId, ServerIntrospector.class);
    //    if (serverIntrospector == null) {
    //        serverIntrospector = new DefaultServerIntrospector();
    //    }
    //
    //    return (ServerIntrospector)serverIntrospector;
    //}
    //
    //private boolean isSecure(Server server, String serviceId) {
    //    IClientConfig config = this.clientFactory.getClientConfig(serviceId);
    //    ServerIntrospector serverIntrospector = this.serverIntrospector(serviceId);
    //    return RibbonUtils.isSecure(config, serverIntrospector, server);
    //}
    //
    //protected Server getServer(String serviceId) {
    //    return this.getServer(this.getLoadBalancer(serviceId), (Object)null);
    //}
    //
    //protected Server getServer(ILoadBalancer loadBalancer) {
    //    return this.getServer(loadBalancer, (Object)null);
    //}
    //
    //protected Server getServer(ILoadBalancer loadBalancer, Object hint) {
    //    return loadBalancer == null ? null : loadBalancer.chooseServer(hint != null ? hint : "default");
    //}
    //
    //protected ILoadBalancer getLoadBalancer(String serviceId) {
    //    return this.clientFactory.getLoadBalancer(serviceId);
    //}
}
