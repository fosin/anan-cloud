package top.fosin.anan.cloudgateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.swagger.web.SwaggerResource;
import top.fosin.anan.cloudresource.constant.RequestPath;
import top.fosin.anan.cloudresource.constant.ServiceConstant;
import top.fosin.anan.cloudresource.constant.UrlPrefixConstant;
import top.fosin.anan.cloudresource.dto.res.AnanPermissionRespDto;
import top.fosin.anan.security.resource.AnanProgramAuthorities;
import top.fosin.anan.security.resource.AnanSecurityProperties;
import top.fosin.anan.security.resource.AuthorityPermission;
import top.fosin.anan.security.resource.SecurityConstant;
import top.fosin.anan.swagger.config.AnanSwaggerResourcesProvider;
import top.fosin.anan.swagger.config.SwaggerProperties;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author fosin
 * @date 2021.12.17
 */
@Configuration
@Slf4j
public class AnanGatewayAutoConfiguration {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    private final DiscoveryClient discoveryClient;
    @Value("${spring.application.name}")
    private final String applicationName = "anan-cloudgateway";

    public AnanGatewayAutoConfiguration(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    private ServiceInstance getServiceRandomInstance() {
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(ServiceConstant.ANAN_PLATFORMSERVER);
        Assert.isTrue(serviceInstances.size() > 0, "未找到服务的实例：" + ServiceConstant.ANAN_PLATFORMSERVER);
        Random random = new Random();
        int nextInt = random.nextInt(serviceInstances.size());
        return serviceInstances.get(nextInt);
    }

    @Retryable(value = {RemoteAccessException.class}, maxAttemptsExpression = "${retry.maxAttempts:5}",
            backoff = @Backoff(delayExpression = "${retry.backoff:1000}"))
    public List<AnanPermissionRespDto> getPermissionsByRest(List<String> hosts) throws URISyntaxException {
        ServiceInstance instance = getServiceRandomInstance();
        String scheme = instance.getScheme();
        URI uri = new URI(scheme == null ? "http" : scheme, null, instance.getHost(), instance.getPort(), "/" + UrlPrefixConstant.PERMISSION + RequestPath.SERVICE_CODES, null, null);
        RequestEntity<List<String>> request = RequestEntity
                .post(uri)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(hosts);
        ResponseEntity<List<AnanPermissionRespDto>> res = restTemplate()
                .exchange(uri.toString(), HttpMethod.POST, request, new ParameterizedTypeReference<>() {
                });
        return res.getBody();
    }

    @Recover
    public List<AnanPermissionRespDto> getPermissionsByRestRecover(RemoteAccessException exception, List<String> hosts) {
        throw exception;
    }

    /**
     * TODO
     *  如果bean名称是ananProgramAuthorities，则会出现源码跑无问题，编译打包运行报错：
     *  The bean 'ananProgramAuthorities', defined in class path resource [top/fosin/anan/cloudgateway/config/AnanGatewayAutoConfiguration.class], could not be registered. A bean with that name has already been defined in class path resource [top/fosin/anan/security/configurer/AnanSecurityAutoConfiguration.class] and overriding is disabled.
     * Action: Consider renaming one of the beans or enabling overriding by setting
     * spring.main.allow-bean-definition-overriding=true
     * <p>
     * 即使设置了spring.main.allow-bean-definition-overriding=true还是会出现这个问题。
     *
     * @return AnanProgramAuthorities 编程权限
     * @throws URISyntaxException 异常
     */
    @Bean
    @Primary
    public AnanProgramAuthorities ananProgramAuthoritiesNew() throws URISyntaxException {
        List<RouteDefinition> routes = ananGatewayProperties().getRoutes();
        List<String> hosts = routes.stream().map(route -> route.getUri().getHost()).collect(Collectors.toList());
        //List<AnanPermissionRespDto> dtos = permissionFeignService.findByServiceCodes(hosts).getBody();
        List<AnanPermissionRespDto> dtos = getPermissionsByRest(hosts);
        //List<AnanPermissionRespDto> dtos = webClientBuilder()
        //        .build().get()
        //        .uri("http://" + ServiceConstant.ANAN_PLATFORMSERVER + "/v1/permission/serviceCodes")
        //        .retrieve()
        //        .bodyToMono(new ParameterizedTypeReference<List<AnanPermissionRespDto>>() {})
        //        .block();
        List<AnanSecurityProperties.Authority> authorities = new ArrayList<>();
        Objects.requireNonNull(dtos).forEach(dto -> {
            String entityPath = dto.getPath();
            if (StringUtils.hasText(entityPath)) {
                String joinPath = tranformPath(joinPath(getRoutePath(routes, dto.getServiceCode()), entityPath),
                        "/*");
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

    @Bean
    @Primary
    @ConditionalOnMissingBean
    public AnanSwaggerResourcesProvider ananSwaggerResourcesProvider(SwaggerProperties swaggerProperties) {
        List<RouteDefinition> routes = ananGatewayProperties().getRoutes();
        String localPath = tranformPath(getRoutePath(routes, applicationName),"");
        List<SwaggerResource> swaggerResources = routes.stream().map(route -> {
            SwaggerResource swaggerResource = new SwaggerResource();
            swaggerResource.setName(route.getId());
            String location = tranformPath(getRoutePath(route).replace(localPath,""), "/v2/api-docs");
            swaggerResource.setLocation(location);
            swaggerResource.setSwaggerVersion(DocumentationType.SWAGGER_2.getVersion());
            return swaggerResource;
        }).collect(Collectors.toList());
        return new AnanSwaggerResourcesProvider(swaggerResources, swaggerProperties);
    }

    private String tranformPath(String s, String r) {
        return s.replace("/**", r)
                .replace("/{segment}", r)
                .replace("/*/*", r)
                .replace("/**/**", r);
    }

    private String getRoutePath(List<RouteDefinition> routes, String serviceCode) {
        String path = "";
        for (RouteDefinition route : routes) {
            if (route.getUri().getHost().equals(serviceCode)) {
                path = getRoutePath(route);
            }
        }
        return path;
    }

    private String getRoutePath(RouteDefinition route) {
        PredicateDefinition pd =
                route.getPredicates().stream().filter(p -> p.getName().equalsIgnoreCase("Path")).findFirst().orElseThrow();
        return pd.getArgs().values().stream().findFirst().orElseThrow();
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
    public GatewayProperties ananGatewayProperties() {
        return new GatewayProperties();
    }

}
