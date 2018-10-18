package com.starlight.cdp;

import com.starlight.cdp.core.banner.CDPBanner;
import com.starlight.cdp.oauth2.annotation.EnableCdpOauth2;
import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Description
 *
 * @author fosin
 */
@SpringBootApplication
@EnableAdminServer
@EnableTurbine
@EnableCdpOauth2
@EnableWebSecurity
public class AdminServerApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(AdminServerApplication.class)
                .banner(new CDPBanner("CDP Admin Server"))
                .logStartupInfo(true)
                .run(args);
    }

    @Bean
    LoadBalancerInterceptor loadBalancerInterceptor(LoadBalancerClient loadBalance) {
        return new LoadBalancerInterceptor(loadBalance);
    }

    //    @Bean
//    @RefreshScope
//    @ConfigurationProperties("spring.boot.admin")
//    public AdminServerProperties   adminServerProperties(){
//        return new AdminServerProperties();
//    }
}
