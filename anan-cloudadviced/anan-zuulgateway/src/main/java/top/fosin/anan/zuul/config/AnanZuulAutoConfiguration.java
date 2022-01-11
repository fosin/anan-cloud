package top.fosin.anan.zuul.config;

import feign.Client;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger.web.SwaggerResource;
import top.fosin.anan.cloudresource.dto.res.AnanPermissionRespDto;
import top.fosin.anan.cloudresource.service.inter.PermissionFeignService;
import top.fosin.anan.security.resource.AnanProgramAuthorities;
import top.fosin.anan.security.resource.AnanSecurityProperties;
import top.fosin.anan.security.resource.AuthorityPermission;
import top.fosin.anan.security.resource.SecurityConstant;
import top.fosin.anan.swagger.config.AnanSwaggerResourcesProvider;
import top.fosin.anan.swagger.config.SwaggerProperties;

import java.util.ArrayList;
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
    @Primary
    public AnanProgramAuthorities ananProgramAuthorities(RouteLocator routeLocator,
                                                         PermissionFeignService permissionFeignService) {
        List<Route> routes = routeLocator.getRoutes();
        List<String> locations = routes.stream().map(Route::getLocation).collect(Collectors.toList());
        List<AnanPermissionRespDto> dtos = permissionFeignService.findByServiceCodes(locations).getBody();
        List<AnanSecurityProperties.Authority> authorities = new ArrayList<>();
        Objects.requireNonNull(dtos).forEach(dto -> {
            String entityPath = dto.getPath();
            if (StringUtils.hasText(entityPath)) {
                String joinPath = joinPath(getRouteFullPath(routes, dto.getServiceCode()), entityPath);
                String method = dto.getMethod();
                AnanSecurityProperties.Authority authority = new AnanSecurityProperties.Authority();
                authority.setPaths(joinPath);
                authority.setMethods(method);
                authority.setPermission(AuthorityPermission.hasAuthority.getName() + SecurityConstant.AUTHORITY_SPLIT + SecurityConstant.PREFIX_ROLE + dto.getId());
                authorities.add(authority);
            }
        });
        return new AnanProgramAuthorities(authorities);
    }

    private String getRouteFullPath(List<Route> routes, String serviceCode) {
        String fullPath = "";
        for (Route route : routes) {
            if (route.getLocation().equals(serviceCode)) {
                fullPath = route.getFullPath();
            }
        }
        return fullPath;
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

    /**
     * 解决：zuul默认使用ribbon做负载，但是springcloud2020使用自己的负载导致报错
     * class org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient cannot be cast to class org
     * .springframework.cloud.loadbalancer.blocking.client.BlockingLoadBalancerClient
     *
     * @return Client
     */
    @Bean
    public Client feignClient() {
        return new Client.Default(null, null);
    }
}
