package com.github.fosin.cdp.zuulgateway.config;

import com.github.fosin.cdp.swagger.config.CdpSwaggerResourcesProvider;
import com.github.fosin.cdp.swagger.spring4all.SwaggerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.swagger.web.SwaggerResource;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author fosin
 * @date 2018.12.17
 */
@Configuration
public class CdpZuulAutoConfiger {

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
    public CdpSwaggerResourcesProvider cdpSwaggerResourcesProvider(RouteLocator routeLocator, SwaggerProperties swaggerProperties) {
        List<Route> routes = routeLocator.getRoutes();
        List<SwaggerResource> swaggerResources = new ArrayList<>();
        routes.forEach(route -> {
            SwaggerResource swaggerResource = new SwaggerResource();
            swaggerResource.setName(route.getId());
            swaggerResource.setLocation(route.getFullPath().replace("**", "v2/api-docs"));
            swaggerResource.setSwaggerVersion("2.0");
            swaggerResources.add(swaggerResource);
        });
        return new CdpSwaggerResourcesProvider(swaggerResources, swaggerProperties);
    }

    //    @Bean
//    @ConditionalOnProperty(prefix = "spring.profiles", name = "active", havingValue = "local")
//    public IRule localLoadBalanceRule() {
//        return new LocalLoadBalanceRule();
//    }
}
