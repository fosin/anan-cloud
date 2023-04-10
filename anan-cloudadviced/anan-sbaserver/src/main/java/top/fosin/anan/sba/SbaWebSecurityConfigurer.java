package top.fosin.anan.sba;

/**
 * @author fosin
 * @date 2021/10/12
 * @since 2.0.0
 * TODO 当反向代理地址是非标端口时，登陆后地址跳转后的端口丢失问题（待解决）
 */
//@Configuration(proxyBeanMethods = false)
public class SbaWebSecurityConfigurer //extends WebSecurityConfigurerAdapter
{
    //
    //private final AdminServerProperties adminServer;
    //private final AdminServerUiProperties adminServerUi;
    //private final SecurityProperties security;
    //@Value("${server.port}")
    //private int serverPort;
    //
    //private PortMapper portMapper;
    //
    //public SbaWebSecurityConfigurer(AdminServerProperties adminServer, AdminServerUiProperties adminServerUi, SecurityProperties security) {
    //    this.adminServer = adminServer;
    //    this.security = security;
    //    this.adminServerUi = adminServerUi;
    //}
    //
    //@Override
    //protected void configure(HttpSecurity http) throws Exception {
    //    this.portMapper = portMapper();
    //    SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
    //    successHandler.setTargetUrlParameter("redirectTo");
    //    successHandler.setDefaultTargetUrl(this.adminServer.path("/"));
    //    successHandler.setAlwaysUseDefaultTargetUrl(true);
    //    http.authorizeRequests(
    //                    (authorizeRequests) -> authorizeRequests.antMatchers(this.adminServer.path("/assets/**")).permitAll()
    //                            .antMatchers(this.adminServer.path("/login")).permitAll()
    //                            .antMatchers("/actuator/info").permitAll()
    //                            .antMatchers("/actuator/health").permitAll()
    //                            .antMatchers(HttpMethod.OPTIONS, "/actuator/**").permitAll()
    //                            .anyRequest().authenticated()
    //            ).formLogin((formLogin) -> formLogin.loginPage(this.adminServer.path("/login")).successHandler(successHandler))
    //            .logout((logout) -> logout.logoutUrl(this.adminServer.path("/logout")).logoutSuccessUrl(this.adminServer.path("/login?logout")))
    //            .httpBasic(Customizer.withDefaults())
    //            .csrf((csrf) -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
    //                    .ignoringRequestMatchers(
    //                            new AntPathRequestMatcher(this.adminServer.path("/instances"),
    //                                    HttpMethod.POST.toString()),
    //                            new AntPathRequestMatcher(this.adminServer.path("/instances/*"),
    //                                    HttpMethod.DELETE.toString()),
    //                            new AntPathRequestMatcher(this.adminServer.path("/actuator/**"))
    //                    ))
    //            .rememberMe((rememberMe) -> rememberMe.key(UUID.randomUUID().toString()).tokenValiditySeconds(1209600))
    //            .headers().frameOptions().sameOrigin()
    //            .and().requestCache().requestCache(requestCache())
    //            .and()
    //             .portMapper(configurer -> configurer.portMapper(portMapper))
    //            .exceptionHandling(configurer -> {
    //                LoginUrlAuthenticationEntryPoint entryPoint = new LoginUrlAuthenticationEntryPoint(this.adminServer.path("/login"));
    //                entryPoint.setPortMapper(portMapper);
    //                PortResolverImpl portResolver = new PortResolverImpl();
    //                portResolver.setPortMapper(portMapper);
    //                entryPoint.setPortResolver(portResolver);
    //                configurer.authenticationEntryPoint(entryPoint);
    //            })
    //    ;
    //}
    //
    //// Required to provide UserDetailsService for "remember functionality"
    //@Override
    //protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //    auth.inMemoryAuthentication().withUser(security.getUser().getName())
    //            .password("{noop}" + security.getUser().getPassword()).roles("USER");
    //}
    //
    //private PortMapper portMapper() {
    //    PortMapperImpl portMapper = new PortMapperImpl();
    //
    //    String publicUrl = adminServerUi.getPublicUrl();
    //    if (StringUtils.hasText(publicUrl)) {
    //        UriComponents publicComponents = UriComponentsBuilder.fromUriString(publicUrl).build();
    //        int redrectPort = publicComponents.getPort();
    //        Map<String, String> mappings = Maps.newHashMap();
    //        mappings.put(Integer.toString(serverPort), Integer.toString(redrectPort));
    //        mappings.put("443", Integer.toString(redrectPort));
    //        portMapper.setPortMappings(mappings);
    //    }
    //
    //    return portMapper;
    //}
    //
    //private RequestCache requestCache() {
    //    HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
    //    requestCache.setPortResolver(portResolver());
    //    return requestCache;
    //}
    //
    //private PortResolverImpl portResolver() {
    //    PortResolverImpl portResolver = new PortResolverImpl();
    //    portResolver.setPortMapper(portMapper);
    //    return portResolver;
    //}
}
