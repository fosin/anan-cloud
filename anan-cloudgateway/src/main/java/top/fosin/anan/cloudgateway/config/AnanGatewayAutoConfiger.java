package top.fosin.anan.cloudgateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import top.fosin.anan.cloudgateway.filter.ChangePasswordGatewayFilter;
import top.fosin.anan.cloudgateway.filter.LoginGatewayFilter;

/**
 * @author fosin
 * @date 2018.12.17
 */
@Configuration
public class AnanGatewayAutoConfiger {

    @Bean
    @RefreshScope
    @ConfigurationProperties("spring.cloud.gateway")
    @Primary
    public GatewayProperties gatewayProperties() {
        return new GatewayProperties();
    }

    @Bean
    public LoginGatewayFilter loginGatewayFilter() {
        return new LoginGatewayFilter();
    }

    @Bean
    public ChangePasswordGatewayFilter changePasswordGatewayFilter() {
        return new ChangePasswordGatewayFilter();
    }

    @Bean
    LoadBalancerInterceptor loadBalancerInterceptor(LoadBalancerClient loadBalance) {
        return new LoadBalancerInterceptor(loadBalance);
    }

//    @Bean
//    RedisRateLimiter redisRateLimiter() {
//        return new RedisRateLimiter(1, 2);
//    }

//    @Bean
//    @Primary
//    @ConditionalOnMissingBean
//    public AnanSwaggerResourcesProvider ananSwaggerResourcesProvider(RouteLocator routeLocator, SwaggerProperties swaggerProperties) {
//        Flux<Route> routes = routeLocator.getRoutes();
//        List<SwaggerResource> swaggerResources = new ArrayList<>();
//        routes.toIterable().forEach(route -> {
//            SwaggerResource swaggerResource = new SwaggerResource();
//            swaggerResource.setName(route.getId());
//            swaggerResource.setLocation(route.getPredicate().toString().replace("**", "v2/api-docs"));
//            swaggerResource.setSwaggerVersion("2.0");
//            swaggerResources.add(swaggerResource);
//        });
//        return new AnanSwaggerResourcesProvider(swaggerResources, swaggerProperties);
//    }

    //    @Bean
//    @ConditionalOnProperty(prefix = "spring.profiles", name = "active", havingValue = "local")
//    public IRule localLoadBalanceRule() {
//        return new LocalLoadBalanceRule();
//    }
}
