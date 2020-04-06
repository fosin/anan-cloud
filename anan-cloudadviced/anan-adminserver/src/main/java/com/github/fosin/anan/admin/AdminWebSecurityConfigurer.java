package com.github.fosin.anan.admin;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 必须使用Order注解，否则报错//@Order on WebSecurityConfigurers must be unique. Order of 100 was already used on com.github.fosin.anan.admin.AdminWebSecurityConfigurer$$EnhancerBySpringCGLIB$$a337b3be@349686e8, so it cannot be used on org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2SsoDefaultConfiguration$$EnhancerBySpringCGLIB$$ebdb7bee@9120cb5 too.
 * //	at org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration.setFilterChainProxySecurityConfigurer(WebSecurityConfiguration.java:148)
 *
 * @author fosin
 */
//@Configuration
//@EnableWebSecurity
public class AdminWebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/login/**").permitAll()
                .anyRequest().authenticated()
        ;
    }
}
