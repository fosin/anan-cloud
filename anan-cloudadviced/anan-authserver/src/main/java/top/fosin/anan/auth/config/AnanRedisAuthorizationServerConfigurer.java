package top.fosin.anan.auth.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import top.fosin.anan.auth.security.AnanTokenServices;
import top.fosin.anan.auth.security.AnanUserDetailsServiceImpl;

import javax.sql.DataSource;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author fosin
 * @date 2017/12/28
 */
//@Configuration
//@EnableAuthorizationServer
@SuppressWarnings("SpringConfigurationProxyMethods")
@AllArgsConstructor
public class AnanRedisAuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {
    private final AuthenticationManager authenticationManager;
    private final DataSource dataSource;
    private final AnanUserDetailsServiceImpl userDetailsService;
    private final RedisConnectionFactory redisConnectionFactory;
    private final PasswordEncoder passwordEncoder;

    /**
     * 用来配置客户端详情服务（ClientDetailsService），
     * 客户端详情信息在这里进行初始化，你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息。
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService());
    }


    /**
     * 用来配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)。
     * 配置身份认证器，配置认证方式，TokenStore，TokenGranter，OAuth2RequestFactory
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        endpoints.tokenStore(redisTokenStore())
                .tokenEnhancer(tokenEnhancer())
                .userDetailsService(userDetailsService)
                .authenticationManager(authenticationManager)
//                .authenticationManager(new AuthenticationManager() {
//                    @Override
//                    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//                        return authenticationProvider().authenticate(authentication);
//                    }
//                })
                .tokenServices(defaultTokenServices())
                //目前spring提供了内存和数据库两种方式存储授权码Authentication code，
                // 要实现多个实例之间共享可以选在将AuthenTicationCode存储在数据中
                .authorizationCodeServices(authorizationCodeServices())
        ;
    }

    /**
     * 对应于配置AuthorizationServer安全认证的相关信息，创建ClientCredentialsTokenEndpointFilter核心过滤器
     * 用来配置令牌端点(Token Endpoint)的安全约束.
     * 配置oauth认证安全策略 从表单提交经过OAuth认证
     *
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                //url:/oauth/token_key,exposes public key for token verification if using JWT tokens
                .tokenKeyAccess("permitAll()")
                //url:/oauth/check_token allow check token
                .checkTokenAccess("isAuthenticated()")
                //允许表单认证
                .allowFormAuthenticationForClients();
    }

    /**
     * @return DefaultTokenServices
     */
//    @Primary
    @Bean
    public AnanTokenServices defaultTokenServices() {
        AnanTokenServices tokenServices = new AnanTokenServices();
        tokenServices.setTokenStore(redisTokenStore());
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setClientDetailsService(clientDetailsService());
        // token有效期自定义设置，默认12小时
        tokenServices.setAccessTokenValiditySeconds(60 * 60 * 12);
        //默认3天，这里修改
        tokenServices.setRefreshTokenValiditySeconds(60 * 60 * 72);
        tokenServices.setTokenEnhancer(tokenEnhancer());
        return tokenServices;
    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    /**
     * 配置JDBC方式的认证方式
     *
     * @return
     */
    @Bean
    public JdbcClientDetailsService clientDetailsService() {
        JdbcClientDetailsService clientDetailsService = new JdbcClientDetailsService(dataSource);
        clientDetailsService.setPasswordEncoder(passwordEncoder);
        return clientDetailsService;
    }

//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userDetailsService);
//        authenticationProvider.setPasswordEncoder(passwordEncoder);
//        return authenticationProvider;
//    }

    /**
     * redis存储方式
     *
     * @return
     */
    @Bean
    public RedisTokenStore redisTokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    /**
     * 设置返回的token的默认格式，增加code和msg两个返回值，方便前对
     *
     * @return TokenEnhancer
     */
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return (accessToken, authentication) -> {
            if (accessToken instanceof DefaultOAuth2AccessToken) {
                DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
                Map<String, Object> additionalInformation = new LinkedHashMap<String, Object>();
//                    additionalInformation.put("code",ResultCode.SUCCESS.getCode());
//                    additionalInformation.put("msg",ResultCode.SUCCESS.getMsg());
                token.setAdditionalInformation(additionalInformation);
            }
            return accessToken;
        };
    }
    /**
     * 这是一个基于JDBC的实现版本，令牌会被保存进关系型数据库。
     * 使用这个版本的实现时，你可以在不同的服务器之间共享令牌信息，
     * 使用这个版本的时候请注意把"spring-jdbc"这个依赖加入到你的classpath当中。
     */
//    @Bean
//    public JdbcTokenStore jdbcTokenStore(){
//        return new JdbcTokenStore(dataSource);
//    }

    /**
     * 这个版本的实现是被默认采用的，它可以完美的工作在单服务器上（即访问并发量压力不大的情况下，并且它在失败的时候不会进行备份），
     * 大多数的项目都可以使用这个版本的实现来进行尝试，你可以在开发的时候使用它来进行管理，因为不会被保存到磁盘中，所以更易于调试。
     * @return
     */
//    @Bean
//    public InMemoryTokenStore inMemoryTokenStore() {
//        return new InMemoryTokenStore();
//    }

}
