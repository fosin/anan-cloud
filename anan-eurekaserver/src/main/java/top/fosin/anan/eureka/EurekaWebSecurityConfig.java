package top.fosin.anan.eureka;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * @author fosin
 */
@Configuration
@EnableWebSecurity
public class EurekaWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        //忽略css.jq.img等文件
        web.ignoring().antMatchers("/**/*.html", "/**/*.css", "/img/**", "/**/*.js", "/third-party/**", "/**/*.ttf", "/**/*.woff", "/**/*.png");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and().csrf().disable() //HTTP with Disable CSRF
                .cors().and()
                .authorizeRequests() //Authorize Request Configuration
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest().authenticated() //除以上路径都需要验证
                .and()
                .headers().frameOptions().disable()
                .and()
                .httpBasic()
        ;

    }
}
