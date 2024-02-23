package top.fosin.anan.cloudgateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.reactive.WebFluxProperties;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.boot.web.server.Cookie;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseCookie;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.session.WebSessionIdResolver;
import top.fosin.anan.cloudgateway.service.CustomOidcReactiveOAuth2UserService;
import top.fosin.anan.cloudgateway.service.CustomReactiveOAuth2UserService;
import top.fosin.anan.security.resource.AnanSecurityProperties;

import java.util.List;

/**
 * @author fosin
 * @date 2021.12.17
 */
@Configuration
@Slf4j
public class GatewayAutoConfiguration {
    private final ServerProperties serverProperties;
    private final WebFluxProperties webFluxProperties;
    private final AnanSecurityProperties ananSecurityProperties;
    @Value("${spring.application.name}")
    private String applicationName = "anan-cloudgateway";

    public GatewayAutoConfiguration(ServerProperties serverProperties,
                                    WebFluxProperties webFluxProperties, AnanSecurityProperties ananSecurityProperties) {
        this.serverProperties = serverProperties;
        this.webFluxProperties = webFluxProperties;
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

//    @Bean
//    @Primary
//    @ConditionalOnMissingBean
//    public AnanSwaggerResourcesProvider ananSwaggerResourcesProvider(SwaggerProperties swaggerProperties) {
//        List<RouteDefinition> routes = ananGatewayProperties().getRoutes();
//        String localPath = tranformPath(getRoutePath(routes, applicationName), "");
//        List<SwaggerResource> swaggerResources = routes.stream().map(route -> {
//            SwaggerResource swaggerResource = new SwaggerResource();
//            swaggerResource.setName(route.getId());
//            String location = tranformPath(getRoutePath(route).replace(localPath, ""), "/v3/api-docs");
//            swaggerResource.setLocation(location);
//            swaggerResource.setSwaggerVersion(DocumentationType.OAS_30.getVersion());
//            return swaggerResource;
//        }).collect(Collectors.toList());
//        return new AnanSwaggerResourcesProvider(swaggerResources, swaggerProperties);
//    }

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
//        WebFluxProperties.Cookie cookie = this.webFluxProperties.getSession().getCookie();
//        if (cookie.getSameSite() != null) {
//            return cookie.getSameSite().attribute();
//        }
        return null;
    }
}
