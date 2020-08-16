package com.github.fosin.anan.auth.config;

import com.github.fosin.anan.auth.security.AnanUserDetailsServiceImpl;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

/**
 * @author fosin
 */
//@Configuration
//@EnableWebSecurity
//@Order(1)
public class AnanWebSecurityConfigurer extends WebSecurityConfigurerAdapter {
    private static final String INTERNAL_SECRET_KEY = "INTERNAL_SECRET_KEY";
    private final AnanUserDetailsServiceImpl userDetailsService;
    private final DataSource dataSource;

    public AnanWebSecurityConfigurer(AnanUserDetailsServiceImpl userDetailsService, DataSource dataSource) {
        this.userDetailsService = userDetailsService;
        this.dataSource = dataSource;
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .antMatcher("/sso/**")
//                .authorizeRequests()
//                .antMatchers("/sso/login", "/sso/logout").permitAll()
//                .antMatchers(HttpMethod.OPTIONS).permitAll()
//                //除以上路径都需要验证
//                .anyRequest().authenticated()
//                .and()
//                .exceptionHandling()
//                .accessDeniedPage("/sso/login?error")
//                .and().csrf().disable()
//                .logout()
//                .clearAuthentication(true)
//                .logoutUrl("/sso/logout")
//                .logoutSuccessUrl("/sso/login?logout")
//                //指定在注销时是否使HttpSession无效。默认为true。
////                .invalidateHttpSession(true)
//                //允许在注销成功时指定要删除的cookie的名称。这是显式添加CookieClearingLogoutHandler的快捷方式。
////              .deleteCookies("")
//                .and()
//                //http参数中包含一个名为“remember-me”的参数，不管session是否过期，用户记录将会被记保存下来
//                .rememberMe()
//                //设置起效为2天
//                .rememberMeServices(rememberMeServices())
//                .and()
//                .formLogin()
//                //form表单密码参数名
//                .usernameParameter("usercode")
//                //form表单用户名参数名
//                .passwordParameter("password")
//                .loginPage("/sso/login")
//                .loginProcessingUrl("/sso/login")
//                .defaultSuccessUrl("/sso/index")
//                .failureUrl("/sso/login?error")
//                //permitall()方法允许为与基于表单的登录相关联的所有url授予所有用户访问权限。
//                .permitAll()
//        ;
//    }
//
//    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
////        auth.authenticationProvider(new AnanAuthenticationProvider());
//    }
//
//    @Override
//    public void configure(WebSecurity web) {
//        web.ignoring().antMatchers(
//                "/**/*.html",
//                "/**/*.css",
//                "/**/*.js",
//                "/**/*.woff",
//                "/**/*.woff2",
//                "/**/*.ttf",
//                "/**/*.map",
//                "/**/*.ico",
//                "/**/*.swf",
//                "/**/webjars/**",
//                "/**/images/**",
//                "/**/*.jpg",
//                "/**/*.png",
//                "/**/swagger-resources/**",
//                "/**/api-docs"
//        );
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }
//
//    /**
//     * 返回 RememberMeServices 实例，将用户rememberme信息存在数据库中
//     *
//     * @return RememberMeServices
//     */
//    @Bean
//    public RememberMeServices rememberMeServices() {
//        JdbcTokenRepositoryImpl rememberMeTokenRepository = new JdbcTokenRepositoryImpl();
//        // 此处需要设置数据源，否则无法从数据库查询验证信息
//        rememberMeTokenRepository.setDataSource(dataSource);
//
//        // 此处的 key 可以为任意非空值(null 或 "")，单必须和起前面
//        // rememberMeServices(RememberMeServices rememberMeServices).key(key)的值相同
//        PersistentTokenBasedRememberMeServices rememberMeServices =
//                new PersistentTokenBasedRememberMeServices(INTERNAL_SECRET_KEY, userDetailsService, rememberMeTokenRepository);
//
//        // 该参数不是必须的，默认值为 "remember-me", 但如果设置必须和页面复选框的 name 一致
//        rememberMeServices.setParameter("remember-me");
//        //默认验证时间48小时
//        rememberMeServices.setTokenValiditySeconds(172800);
//        return rememberMeServices;
//    }
}
