package top.fosin.anan.cloudgateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.reactive.WebFluxProperties;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.boot.web.server.Cookie;
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
import org.springframework.http.ResponseCookie;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.session.WebSessionIdResolver;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.swagger.web.SwaggerResource;
import top.fosin.anan.cloudgateway.service.CustomOidcReactiveOAuth2UserService;
import top.fosin.anan.cloudgateway.service.CustomReactiveOAuth2UserService;
import top.fosin.anan.cloudresource.constant.ServiceConstant;
import top.fosin.anan.cloudresource.entity.res.PermissionDTO;
import top.fosin.anan.cloudresource.service.inter.rpc.PermissionRpcService;
import top.fosin.anan.security.resource.AnanProgramAuthorities;
import top.fosin.anan.security.resource.AnanSecurityProperties;
import top.fosin.anan.security.resource.AuthorityPermission;
import top.fosin.anan.security.resource.SecurityConstant;
import top.fosin.anan.swagger.config.AnanSwaggerResourcesProvider;
import top.fosin.anan.swagger.config.SwaggerProperties;

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
public class GatewayAutoConfiguration {
    private final DiscoveryClient discoveryClient;
    private final ServerProperties serverProperties;
    private final WebFluxProperties webFluxProperties;
    @Value("${spring.application.name}")
    private final String applicationName = "anan-cloudgateway";
    private final PermissionRpcService permissionRpcService;
    private final AnanSecurityProperties ananSecurityProperties;

    public GatewayAutoConfiguration(DiscoveryClient discoveryClient, ServerProperties serverProperties,
                                    WebFluxProperties webFluxProperties, PermissionRpcService permissionRpcService, AnanSecurityProperties ananSecurityProperties) {
        this.discoveryClient = discoveryClient;
        this.serverProperties = serverProperties;
        this.webFluxProperties = webFluxProperties;
        this.permissionRpcService = permissionRpcService;
        this.ananSecurityProperties = ananSecurityProperties;
    }

    //    @Bean
//    public ServerRequestCache serverRequestCache() {
//        return new CustomWebSessionServerRequestCache();
//    }
    @Bean
    public CustomReactiveOAuth2UserService customReactiveOAuth2UserService() {
        return new CustomReactiveOAuth2UserService(ananSecurityProperties);
    }

    @Bean
    public CustomOidcReactiveOAuth2UserService customOidcReactiveOAuth2UserService() {
        return new CustomOidcReactiveOAuth2UserService(ananSecurityProperties);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    //@Bean
    //public LoadBalancerRequestFactory loadBalancerRequestFactory(LoadBalancerClient loadBalancerClient) {
    //    return new LoadBalancerRequestFactory(loadBalancerClient);
    //}

    //@Bean
    //public LoadBalancerClient blockingLoadBalancerClient(ReactiveLoadBalancer.Factory<ServiceInstance> loadBalancerClientFactory) {
    //    return new CustomBlockingLoadBalancerClient(loadBalancerClientFactory);
    //}
    //@Lazy // 重点：这里必须使用@Lazy
    //@Autowired
    //private PermissionFeignService permissionFeignService;
    //
    //@Async // 重点：这里必须在异步线程中执行，执行结果返回Future
    //public Future<List<PermissionDTO>> findByServiceCodes(List<String> hosts,String apiVersion) {
    //    List<PermissionDTO> dtos = permissionFeignService.findByServiceCodes(hosts, apiVersion).getData();
    //    return new AsyncResult<>(dtos);
    //}

    //@Bean
    //@LoadBalanced
    //public WebClient.Builder webBuilder() {
    //    return WebClient.builder();
    //}

    private ServiceInstance getServiceRandomInstance() {
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(ServiceConstant.ANAN_PLATFORMSERVER);
        Assert.isTrue(serviceInstances.size() > 0, "未找到服务的实例：" + ServiceConstant.ANAN_PLATFORMSERVER);
        Random random = new Random();
        int nextInt = random.nextInt(serviceInstances.size());
        return serviceInstances.get(nextInt);
    }

    @Retryable(value = {RemoteAccessException.class}, maxAttemptsExpression = "${retry.maxAttempts:5}",
            backoff = @Backoff(delayExpression = "${retry.backoff:1000}"))
    public List<PermissionDTO> getPermissionsByRest(List<String> hosts) throws URISyntaxException {
        List<PermissionDTO> dtos;
        // 方法4：GRPC
        dtos = permissionRpcService.findByServiceCodes(hosts);

        //方式1，feign
        //List<PermissionDTO> dtos = permissionFeignService.findByServiceCodes(hosts, PathPrefixConstant.API_VERSION_NAME).getData();
        //List<PermissionDTO> dtos = findByServiceCodes(hosts,PathPrefixConstant.API_VERSION_NAME).get();

        //方式2：restTemplate调用
//        ServiceInstance instance = getServiceRandomInstance();
//        String scheme = instance.getScheme();
//        URI uri = new URI(scheme == null ? "http" : scheme, null, instance.getHost(), instance.getPort(), "/" + PathPrefixConstant.PERMISSION + PathSuffixConstant.SERVICE_CODES, PathPrefixConstant.DEFAULT_VERSION_PARAM, null);
//
//        RequestEntity<List<String>> request = RequestEntity
//                .post(uri)
//                .accept(MediaType.APPLICATION_JSON)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(hosts);
//        ResponseEntity<MultResult<PermissionDTO>> res = restTemplate()
//                .exchange(uri, HttpMethod.POST, request, new ParameterizedTypeReference<>() {
//                });
        //使用服务名调用，由于restTemplate的bean的
        //URI uri = new URI("http://" + ServiceConstant.ANAN_PLATFORMSERVER + "/" + PathPrefixConstant.PERMISSION + PathSuffixConstant.SERVICE_CODES + "?" + PathPrefixConstant.DEFAULT_VERSION_PARAM);
        //RequestEntity<List<String>> request = RequestEntity
        //        .post(uri)
        //        .accept(MediaType.APPLICATION_JSON)
        //        .contentType(MediaType.APPLICATION_JSON)
        //        .body(hosts);
        //ResponseEntity<MultResult<PermissionDTO>>  res = restTemplate()
        //        .exchange(uri, HttpMethod.POST, request, new ParameterizedTypeReference<>() {});

//        dtos = Objects.requireNonNull(res.getBody()).getData();

        //方式3：webClient
        //MultResult<PermissionDTO> multResult = webBuilder().baseUrl("lb://" + ServiceConstant.ANAN_PLATFORMSERVER).build()
        //        .post()
        //        .uri(uriBuilder -> uriBuilder.path("/" + PathPrefixConstant.PERMISSION + PathSuffixConstant.SERVICE_CODES)
        //                .queryParam(PathPrefixConstant.API_VERSION_NAME, PathPrefixConstant.API_VERSION_VALUE).build())
        //         .contentType(MediaType.APPLICATION_JSON)
        //         .accept(MediaType.APPLICATION_JSON)
        //        .bodyValue(hosts)
        //        .exchangeToMono(clientResponse -> clientResponse.bodyToMono(new ParameterizedTypeReference<MultResult<PermissionDTO>>() {}))
        //        .doOnError(WebClientResponseException.class, err -> {
        //            log.info("ERROR status:{},msg:{}",err.getRawStatusCode(),err.getResponseBodyAsString());
        //            throw new RuntimeException(err.getMessage());
        //        })
        //.block();

        //ServiceInstance instance = getServiceRandomInstance();
        //String scheme = instance.getScheme();
        //URI uri = new URI(scheme == null ? "http" : scheme, null, instance.getHost(), instance.getPort(), "/" + PathPrefixConstant.PERMISSION + PathSuffixConstant.SERVICE_CODES, PathPrefixConstant.DEFAULT_VERSION_PARAM, null);
        //MultResult<PermissionDTO> multResult = webBuilder()
        //        .build().post()
        //        .uri(uri)
        //        .contentType(MediaType.APPLICATION_JSON)
        //        .accept(MediaType.APPLICATION_JSON)
        //        .bodyValue(hosts)
        //        .retrieve()
        //        .bodyToMono(new ParameterizedTypeReference<MultResult<PermissionDTO>>() {})
        //        .doOnError(throwable -> log.error(throwable.getMessage()))
        //        .block();

        //List<PermissionDTO> dtos = multResult.getData();

        return dtos;

    }

    @Recover
    public List<PermissionDTO> getPermissionsByRestRecover(RemoteAccessException exception, List<String> hosts) {
        throw exception;
    }

    /**
     * TODO
     *  如果bean名称是ananProgramAuthorities，则会出现源码跑无问题，编译打包运行报错：
     *  The bean 'ananProgramAuthorities', defined in class path resource [top/fosin/anan/cloudgateway/config/GatewayAutoConfiguration.class], could not be registered. A bean with that name has already been defined in class path resource [top/fosin/anan/security/configurer/AnanSecurityAutoConfiguration.class] and overriding is disabled.
     * Action: Consider renaming one of the beans or enabling overriding by setting
     * spring.main.allow-bean-definition-overriding=true
     * <p>
     * 即使设置了spring.main.allow-bean-definition-overriding=true还是会出现这个问题。
     *
     * @return AnanProgramAuthorities 编程权限
     */
    @Bean
    public AnanProgramAuthorities ananProgramAuthorities() throws URISyntaxException {
        List<RouteDefinition> routes = ananGatewayProperties().getRoutes();
        List<String> hosts = routes.stream().map(route -> route.getUri().getHost()).collect(Collectors.toList());
        List<PermissionDTO> permissions = getPermissionsByRest(hosts);
        String authorityPrefix = ananSecurityProperties.getOauth2().getResourceServer().getAuthorityPrefix();
        List<AnanSecurityProperties.Authority> authorities = new ArrayList<>();
        Objects.requireNonNull(permissions).forEach(p -> {
            String path = p.getPath();
            if (StringUtils.hasText(path)) {
                String joinPath = tranformPath(joinPath(getRoutePath(routes, p.getServiceCode()), path),
                        "");
                String method = p.getMethod();
                AnanSecurityProperties.Authority authority = new AnanSecurityProperties.Authority();
                authority.setPaths(joinPath);
                authority.setMethods(method);
                String permission = AuthorityPermission.hasAuthority.getName() + SecurityConstant.AUTHORITY_SPLIT
                        + authorityPrefix + p.getId();
                authority.setPermission(permission);
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
        String localPath = tranformPath(getRoutePath(routes, applicationName), "");
        List<SwaggerResource> swaggerResources = routes.stream().map(route -> {
            SwaggerResource swaggerResource = new SwaggerResource();
            swaggerResource.setName(route.getId());
            String location = tranformPath(getRoutePath(route).replace(localPath, ""), "/v3/api-docs");
            swaggerResource.setLocation(location);
            swaggerResource.setSwaggerVersion(DocumentationType.OAS_30.getVersion());
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

    @Bean
    public WebSessionIdResolver webSessionIdResolver() {
        CookieWebSessionIdResolver resolver = new CookieWebSessionIdResolver();
        String name = serverProperties.getReactive().getSession().getCookie().getName();

        //如果设置了cookie名称，这里需要同步设置
        if (StringUtils.hasText(name)) {
            resolver.setCookieName(name);
        }

        resolver.addCookieInitializer(this::initializeCookie);

        return resolver;
    }

    private void initializeCookie(ResponseCookie.ResponseCookieBuilder builder) {
        Cookie cookie = this.serverProperties.getReactive().getSession().getCookie();
        PropertyMapper map = PropertyMapper.get().alwaysApplyingWhenNonNull();
        map.from(cookie::getDomain).to(builder::domain);
        map.from(cookie::getPath).to(builder::path);
        map.from(cookie::getHttpOnly).to(builder::httpOnly);
        map.from(cookie::getSecure).to(builder::secure);
        map.from(cookie::getMaxAge).to(builder::maxAge);
        map.from(getSameSite(cookie)).to(builder::sameSite);
    }

    private String getSameSite(Cookie properties) {
        if (properties.getSameSite() != null) {
            return properties.getSameSite().attributeValue();
        }
        WebFluxProperties.Cookie cookie = this.webFluxProperties.getSession().getCookie();
        if (cookie.getSameSite() != null) {
            return cookie.getSameSite().attribute();
        }
        return null;
    }
}
