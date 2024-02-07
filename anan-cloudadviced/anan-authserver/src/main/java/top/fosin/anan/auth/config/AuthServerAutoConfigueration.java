package top.fosin.anan.auth.config;

import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.authorization.token.*;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;
import org.springframework.session.security.web.authentication.SpringSessionRememberMeServices;
import org.springframework.web.client.RestTemplate;
import top.fosin.anan.cloudresource.service.inter.rpc.UserRpcService;
import top.fosin.anan.security.resource.AnanSecurityProperties;

/**
 * @author fosin
 * @date 2020/9/5
 * @since 2.1.0
 */
@Configuration
public class AuthServerAutoConfigueration {

    /**
     * 解决springboot升到2.6.x之后，使用actuator之后swagger会报错
     *
     * @return BeanPostProcessor
     */
//    @Bean
//    public BeanPostProcessor springfoxHandlerProviderBeanPostProcessor() {
//        return new BeanPostProcessor() {
//
//            @Override
//            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//                if (bean instanceof WebMvcRequestHandlerProvider || bean instanceof WebFluxRequestHandlerProvider) {
//                    customizeSpringfoxHandlerMappings(getHandlerMappings(bean));
//                }
//                return bean;
//            }
//
//            private <T extends RequestMappingInfoHandlerMapping> void customizeSpringfoxHandlerMappings(List<T> mappings) {
//                List<T> copy = mappings.stream()
//                        .filter(mapping -> mapping.getPatternParser() == null)
//                        .collect(Collectors.toList());
//                mappings.clear();
//                mappings.addAll(copy);
//            }
//
//            @SuppressWarnings("unchecked")
//            private List<RequestMappingInfoHandlerMapping> getHandlerMappings(Object bean) {
//                try {
//                    Field field = ReflectionUtils.findField(bean.getClass(), "handlerMappings");
//                    field.setAccessible(true);
//                    return (List<RequestMappingInfoHandlerMapping>) field.get(bean);
//                } catch (IllegalArgumentException | IllegalAccessException e) {
//                    throw new IllegalStateException(e);
//                }
//            }
//        };
//    }

    /**
     * 加载国际化认证提示信息
     *
     * @return ReloadableResourceBundleMessageSource
     */
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        //加载org/springframework/security包下的中文提示信息 配置文件
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public DefaultOidcUserInfoMapper defaultOidcUserInfoMapper() {
        return new DefaultOidcUserInfoMapper();
    }

    @Bean
    public OAuth2TokenGenerator<?> tokenGenerator(JWKSource<SecurityContext> jwkSource,
                                                  UserRpcService userRpcService,
                                                  AnanSecurityProperties ananSecurityProperties) {
        JwtEncoder jwtEncoder = new NimbusJwtEncoder(jwkSource);
        JwtGenerator jwtGenerator = new JwtGenerator(jwtEncoder);
        jwtGenerator.setJwtCustomizer(new JwtOAuth2TokenCustomizer(userRpcService, ananSecurityProperties));
        OAuth2AccessTokenGenerator accessTokenGenerator = new OAuth2AccessTokenGenerator();
        OAuth2RefreshTokenGenerator refreshTokenGenerator = new OAuth2RefreshTokenGenerator();
        return new DelegatingOAuth2TokenGenerator(jwtGenerator, accessTokenGenerator, refreshTokenGenerator);
    }

    /**
     * 搭配Spring Session使用时，需要配置该bean
     */
    @Bean
    @ConditionalOnProperty(prefix = "anan.security.sso.remember-me", name = "enabled", havingValue = "true")
    public RememberMeServices rememberMeServices(AnanSecurityProperties ananSecurityProperties) {
        AnanSecurityProperties.RememberMe rememberMe = ananSecurityProperties.getSso().getRememberMe();
        SpringSessionRememberMeServices rememberMeServices = new SpringSessionRememberMeServices();
        rememberMeServices.setValiditySeconds(rememberMe.getTokenValiditySeconds());
        rememberMeServices.setRememberMeParameterName(rememberMe.getParameterName());
        rememberMeServices.setAlwaysRemember(rememberMe.getAlwaysRemember());
        return rememberMeServices;
    }

    /**
     * 启用anan.security.sso.remember-me.maximum-sessions参数来限制同一用户会话数量，实现一个用户能同时在几个客户端登陆系统
     */
    @Bean
    @ConditionalOnExpression("${anan.security.sso.session.maximum-sessions:0} > 0")
    public SpringSessionBackedSessionRegistry<?> sessionRegistry(FindByIndexNameSessionRepository<?> sessionRepository) {
        return new SpringSessionBackedSessionRegistry<>(sessionRepository);
    }

    //@Bean
    //public HttpSessionRequestCache requestCache() {
    //    return new HttpSessionRequestCache();
    //}

//    @Bean
//    public ObjectMapper objectMapper() {
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.activateDefaultTyping(
//                LaissezFaireSubTypeValidator.instance,
//                ObjectMapper.DefaultTyping.NON_FINAL,
//                JsonTypeInfo.As.PROPERTY);
//        mapper.registerModule(new CoreJackson2Module());
//        mapper.registerModule(new WebJackson2Module());
//        mapper.registerModule(new WebServerJackson2Module());
//        mapper.registerModule(new WebServletJackson2Module());
//        mapper.registerModule(new OAuth2ClientJackson2Module());
//        mapper.registerModule(new JavaTimeModule());
//        mapper.registerModule(new CustomJackson2Module());
//        return mapper;
//    }
}
