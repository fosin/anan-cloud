package top.fosin.anan.auth.config;

import top.fosin.anan.auth.security.AnanTokenServices;
import top.fosin.anan.security.resource.AnanSecurityProperties;
import org.springframework.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import javax.sql.DataSource;
import java.security.KeyPair;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 2017/12/28.
 * Time:11:02
 *
 * @author fosin
 */
@Configuration
@EnableAuthorizationServer
@AllArgsConstructor
public class AnanJwtAuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {
    private final AuthenticationManager authenticationManager;
    private final DataSource dataSource;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final AnanSecurityProperties ananSecurityProperties;

    /**
     * 用来配置客户端详情服务（ClientDetailsService），
     * 客户端详情信息在这里进行初始化，你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息。
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService());
    }


    /**
     * 用来配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)。
     * 配置身份认证器，配置认证方式，TokenStore，TokenGranter，OAuth2RequestFactory
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        //设置自动配置的tokenStore，实际设置为JwtTokenStore的Bean
        endpoints.tokenStore(tokenStore())
                //为让Grant Type支持password需设置authenticationManager
                .authenticationManager(authenticationManager)
                //设置自动配置的accessTokenConverter，实际设置为JwtAccessTokenConverter的Bean
                .accessTokenConverter(jwtAccessTokenConverter())
                //当Grant Type为refresh_token时，需设置userDetailsService
                .userDetailsService(userDetailsService)
                //以下两个方法启用会导致jwt不能生产token
//                .tokenEnhancer(tokenEnhancer())
//                .tokenServices(defaultTokenServices())
                //目前spring提供了内存和数据库两种方式存储授权码Authentication code，
                // 要实现多个实例之间共享可以选在将AuthenTicationCode存储在数据中
                .authorizationCodeServices(authorizationCodeServices())
        ;
    }

    private AuthorizationCodeServices authorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    /**
     * 配置JDBC方式的认证方式
     */
    private ClientDetailsService clientDetailsService() {
        JdbcClientDetailsService clientDetailsService = new JdbcClientDetailsService(dataSource);
        clientDetailsService.setPasswordEncoder(passwordEncoder);
        return clientDetailsService;
    }

    /**
     * 自定义Token生成规则
     *
     * @return DefaultTokenServices
     */
    @Primary
    @Bean
    public DefaultTokenServices defaultTokenServices() {
        AnanTokenServices tokenServices = new AnanTokenServices();
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setTokenEnhancer(tokenEnhancer());
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setAuthenticationManager(authenticationManager);
        tokenServices.setClientDetailsService(clientDetailsService());
        //访问token有效期自定义设置，默认12小时
        tokenServices.setAccessTokenValiditySeconds(60 * 60 * 12);
        //刷新Token默认3天
        tokenServices.setRefreshTokenValiditySeconds(60 * 60 * 72);
        return tokenServices;
    }

    @Bean
    public DefaultUserAuthenticationConverter defaultUserAuthenticationConverter() {
        return new AnanUserAuthenticationConverter(userDetailsService);
    }

    /**
     * 这个版本的全称是 JSON Web Token（JWT），它可以把令牌相关的数据进行编码（因此对于后端服务来说，
     * 它不需要进行存储，这将是一个重大优势），但是它有一个缺点，那就是撤销一个已经授权令牌将会非常困难，
     * 所以它通常用来处理一个生命周期较短的令牌以及撤销刷新令牌（refresh_token）。
     * 另外一个缺点就是这个令牌占用的空间会比较大，如果你加入了比较多用户凭证信息。
     * JwtTokenStore 不会保存任何数据，但是它在转换令牌值以及授权信息方面与 DefaultTokenServices
     * 所扮演的角色是一样的。
     *
     * @return jwtTokenStore
     */
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    private KeyPair keyPair() {
        AnanSecurityProperties.Jwt jwt = ananSecurityProperties.getJwt();
        return new KeyStoreKeyFactory
                (jwt.getKeyStore(),
                        jwt.getKeyStorePassword().toCharArray())
                .getKeyPair(jwt.getKeyAlias(),
                        jwt.getKeyPassword().toCharArray());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        AnanSecurityProperties.Jwt jwt = ananSecurityProperties.getJwt();
        KeyPair keyPair = keyPair();
        if (keyPair != null) {
            converter.setKeyPair(keyPair);
        } else {
            String keyValue = jwt.getPublicKeyValue();
            if (StringUtils.hasText(keyValue)) {
                converter.setSigningKey(keyValue);
            }
        }

        //配置自定义的CustomUserAuthenticationConverter
        DefaultAccessTokenConverter accessTokenConverter = (DefaultAccessTokenConverter) converter.getAccessTokenConverter();
        accessTokenConverter.setUserTokenConverter(defaultUserAuthenticationConverter());
        return converter;
    }

    /**
     * 设置返回的token的默认格式，增加code和msg两个返回值
     *
     * @return TokenEnhancer
     */
    public TokenEnhancer tokenEnhancer() {
        return (accessToken, authentication) -> {
            if (accessToken instanceof DefaultOAuth2AccessToken) {
                DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
                Map<String, Object> additionalInformation = new LinkedHashMap<>();
//                    additionalInformation.put("code",ResultCode.SUCCESS.getCode());
//                    additionalInformation.put("msg",ResultCode.SUCCESS.getMsg());
                token.setAdditionalInformation(additionalInformation);
            }
            return accessToken;
        };


    }

    /**
     * 对应于配置AuthorizationServer安全认证的相关信息，创建ClientCredentialsTokenEndpointFilter核心过滤器
     * 用来配置令牌端点(Token Endpoint)的安全约束.
     * 配置oauth认证安全策略 从表单提交经过OAuth认证
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {

        security
                //url:/oauth/token_key,exposes public key for token verification if using JWT tokens
                .tokenKeyAccess("isAuthenticated()")
                //url:/oauth/check_token allow check token
                .checkTokenAccess("isAuthenticated()")
                .realm("fosin")
                //允许表单认证
                .allowFormAuthenticationForClients()
                .passwordEncoder(passwordEncoder)
        ;
    }

//    /**
//     *
//     * redis存储方式
//     *
//     * @return redisTokenStore
//     */
//    @Bean
//    public RedisTokenStore redisTokenStore() {
//        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
//        redisTokenStore.setPrefix(RedisConstant.ANAN_AUTHSERVER);
//        return redisTokenStore;
//    }

    //    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userDetailsService);
//        authenticationProvider.setPasswordEncoder(passwordEncoder);
//        return authenticationProvider;
//    }

//    /**
//     *
//     * 这是一个基于JDBC的实现版本，令牌会被保存进关系型数据库。
//     * 使用这个版本的实现时，你可以在不同的服务器之间共享令牌信息，
//     * 使用这个版本的时候请注意把"spring-jdbc"这个依赖加入到你的classpath当中。
//     */
//    @Bean
//    public JdbcTokenStore jdbcTokenStore(){
//        return new JdbcTokenStore(dataSource);
//    }

//    /**
//     * 这个版本的实现是被默认采用的，它可以完美的工作在单服务器上（即访问并发量压力不大的情况下，并且它在失败的时候不会进行备份），
//     * 大多数的项目都可以使用这个版本的实现来进行尝试，你可以在开发的时候使用它来进行管理，因为不会被保存到磁盘中，所以更易于调试。
//     * @return
//     */
//    @Bean
//    public InMemoryTokenStore inMemoryTokenStore() {
//        return new InMemoryTokenStore();
//    }

}
