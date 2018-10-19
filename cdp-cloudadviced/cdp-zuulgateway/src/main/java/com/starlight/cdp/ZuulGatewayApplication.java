package com.starlight.cdp;

import com.netflix.loadbalancer.IRule;
import com.starlight.cdp.core.banner.CdpBanner;
import com.starlight.cdp.oauth2.annotation.EnableCdpOauth2;
import com.starlight.cdp.swagger.config.CdpSwaggerResourcesProvider;
import com.starlight.cdp.swagger.annotation.EnableCdpSwagger2;
import com.starlight.cdp.zuulgateway.balance.LocalLoadBalanceRule;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import springfox.documentation.swagger.web.SwaggerResource;

import java.util.ArrayList;
import java.util.List;

/**
 * Description
 *
 * @author fosin
 */
@SpringCloudApplication
@EnableZuulProxy
@EnableHystrixDashboard
@EnableCdpSwagger2
@EnableCdpOauth2
@EnableWebSecurity
//@EnableTurbineStream
//@EnableRedisHttpSession(maxInactiveIntervalInSeconds=60)
public class ZuulGatewayApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ZuulGatewayApplication.class)
                .banner(new CdpBanner("CDP Zuul Gateway"))
                .logStartupInfo(true)
                .run(args);
    }

    @Bean
    @RefreshScope
    @ConfigurationProperties("zuul")
    @Primary
    public ZuulProperties zuulProperties() {
        return new ZuulProperties();
    }

    @Bean
    LoadBalancerInterceptor loadBalancerInterceptor(LoadBalancerClient loadBalance) {
        return new LoadBalancerInterceptor(loadBalance);
    }

    @Bean
    @Primary
    @ConditionalOnMissingBean
    public CdpSwaggerResourcesProvider cdpSwaggerResourcesProvider(RouteLocator routeLocator) {
        List<Route> routes = routeLocator.getRoutes();
        List<SwaggerResource> swaggerResources = new ArrayList<>();
        routes.forEach(route -> {
            SwaggerResource swaggerResource = new SwaggerResource();
            swaggerResource.setName(route.getId());
            swaggerResource.setLocation(route.getFullPath().replace("**", "v2/api-docs"));
            swaggerResource.setSwaggerVersion("2.0");
            swaggerResources.add(swaggerResource);
        });
        return new CdpSwaggerResourcesProvider(swaggerResources);
    }

//    @Bean
//    @ConditionalOnProperty(prefix = "spring.profiles", name = "active", havingValue = "local")
//    public IRule localLoadBalanceRule() {
//        return new LocalLoadBalanceRule();
//    }
}
