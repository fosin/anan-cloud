package top.fosin.anan.zuul.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger.web.SwaggerResource;
import top.fosin.anan.cloudresource.dto.res.AnanPermissionRespDto;
import top.fosin.anan.cloudresource.service.inter.PermissionFeignService;
import top.fosin.anan.security.resource.AnanAuthorityPermission;
import top.fosin.anan.security.resource.AnanProgramAuthorities;
import top.fosin.anan.security.resource.AnanSecurityConstant;
import top.fosin.anan.security.resource.AnanSecurityProperties;
import top.fosin.anan.swagger.config.AnanSwaggerResourcesProvider;
import top.fosin.anan.swagger.config.SwaggerProperties;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author fosin
 * @date 2018.12.17
 */
@Configuration
public class AnanZuulAutoConfiguration {

    @Bean
    @Lazy
    @ConditionalOnBean(PermissionFeignService.class)
    public AnanProgramAuthorities ananProgramAuthorities(RouteLocator routeLocator, PermissionFeignService permissionService) {
        List<Route> routes = routeLocator.getRoutes();
        List<AnanSecurityProperties.AnanAuthorityProperties> propertiesList = new ArrayList<>();
        routes.forEach(route -> {
            String fullPath = route.getFullPath();
            String location = route.getLocation();

            List<AnanPermissionRespDto> dtos = permissionService.findByServiceCode(location).getBody();
            Objects.requireNonNull(dtos).forEach(dto -> {
                String entityPath = dto.getPath();
                if (StringUtils.hasText(entityPath)) {
                    String method = dto.getMethod();
                    List<HttpMethod> httpMethods = new ArrayList<>();
                    if (StringUtils.hasText(method)) {
                        String[] strings = method.split(",");
                        for (String string : strings) {
                            httpMethods.add(HttpMethod.resolve(string));
                        }
                    }
                    Long id = dto.getId();
                    String joinPath = joinPath(fullPath, entityPath);
                    Assert.isTrue(id != null && id > 0, "无效的权限ID：" + id);
                    String authority = AnanSecurityConstant.PREFIX_ROLE + id;
                    AnanSecurityProperties.AnanAuthorityProperties authorityProperties = new AnanSecurityProperties.AnanAuthorityProperties();
                    authorityProperties.setPaths(Collections.singletonList(joinPath));
                    authorityProperties.setMethods(httpMethods);
                    authorityProperties.setPermission(AnanAuthorityPermission.hasAuthority.getName() + AnanAuthorityPermission.SPLITER + authority);
                    propertiesList.add(authorityProperties);
                }
            });
        });
        return new AnanProgramAuthorities(propertiesList);
    }

    private String joinPath(String prefixPath, String suffixPath) {
        boolean suffixFlag = suffixPath.startsWith("/");
        boolean prefixFlag = prefixPath.endsWith("/");
        String finalPath;
        if (suffixFlag) {
            if (prefixFlag) {
                finalPath = prefixPath + suffixPath.substring(1);
            } else {
                finalPath = prefixPath + suffixPath;
            }
        } else {
            if (prefixFlag) {
                finalPath = prefixPath + suffixPath;
            } else {
                finalPath = prefixPath + "/" + suffixPath;
            }
        }

        return finalPath;
    }

    @Bean
    @RefreshScope
    @Primary
    public ZuulProperties zuulProperties() {
        return new ZuulProperties();
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
    public AnanSwaggerResourcesProvider ananSwaggerResourcesProvider(RouteLocator routeLocator, SwaggerProperties swaggerProperties) {
        List<Route> routes = routeLocator.getRoutes();
        List<SwaggerResource> swaggerResources = routes.stream().map(route -> {
            SwaggerResource swaggerResource = new SwaggerResource();
            swaggerResource.setName(route.getId());
            swaggerResource.setLocation(route.getFullPath().replace("**", "v2/api-docs"));
            swaggerResource.setSwaggerVersion("2.0");
            return swaggerResource;
        }).collect(Collectors.toList());
        return new AnanSwaggerResourcesProvider(swaggerResources, swaggerProperties);
    }

    //    @Bean
//    @ConditionalOnProperty(prefix = "spring.profiles", name = "active", havingValue = "local")
//    public IRule localLoadBalanceRule() {
//        return new LocalLoadBalanceRule();
//    }

}
