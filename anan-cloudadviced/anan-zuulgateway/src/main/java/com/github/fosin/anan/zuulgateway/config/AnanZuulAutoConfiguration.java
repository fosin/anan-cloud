package com.github.fosin.anan.zuulgateway.config;

import com.github.fosin.anan.oauth2.resource.AnanResourceServerProperties;
import com.github.fosin.anan.swagger.config.AnanSwaggerResourcesProvider;
import com.github.fosin.anan.swagger.spring4all.SwaggerProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;
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
public class AnanZuulAutoConfiguration {

    @Bean
    @RefreshScope
    @Primary
    public ZuulProperties zuulProperties() {
        return new ZuulProperties();
    }

    @Bean
    @RefreshScope
    @Primary
    public AnanResourceServerProperties ananResourceServerProperties() {
        return new AnanResourceServerProperties();
    }


    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    LoadBalancerInterceptor loadBalancerInterceptor(LoadBalancerClient loadBalance) {
        return new LoadBalancerInterceptor(loadBalance);
    }

    @Bean
    @Primary
    @ConditionalOnMissingBean
    public AnanSwaggerResourcesProvider ananSwaggerResourcesProvider(RouteLocator routeLocator, SwaggerProperties swaggerProperties) {
        List<Route> routes = routeLocator.getRoutes();
        List<SwaggerResource> swaggerResources = new ArrayList<>();
        routes.forEach(route -> {
            SwaggerResource swaggerResource = new SwaggerResource();
            swaggerResource.setName(route.getId());
            swaggerResource.setLocation(route.getFullPath().replace("**", "v2/api-docs"));
            swaggerResource.setSwaggerVersion("2.0");
            swaggerResources.add(swaggerResource);
        });
        return new AnanSwaggerResourcesProvider(swaggerResources, swaggerProperties);
    }

    //    @Bean
//    @ConditionalOnProperty(prefix = "spring.profiles", name = "active", havingValue = "local")
//    public IRule localLoadBalanceRule() {
//        return new LocalLoadBalanceRule();
//    }
}
