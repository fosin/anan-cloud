package com.github.fosin.cdp;

import com.github.fosin.cdp.core.banner.CdpBanner;
import com.github.fosin.cdp.oauth2.annotation.EnableCdpOauth2;
import de.codecentric.boot.admin.server.config.AdminServerProperties;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * Description
 *
 * @author fosin
 */
@SpringBootApplication
@EnableAdminServer
@EnableCdpOauth2
@EnableWebSecurity
public class AdminServerApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(AdminServerApplication.class)
                .banner(new CdpBanner("CDP Admin Server"))
                .logStartupInfo(true)
                .run(args);
    }

    @Bean
    LoadBalancerInterceptor loadBalancerInterceptor(LoadBalancerClient loadBalance) {
        return new LoadBalancerInterceptor(loadBalance);
    }

    @Bean
    @Primary
    @RefreshScope
    @ConfigurationProperties("spring.boot.admin")
    public AdminServerProperties adminServerProperties() {
        return new AdminServerProperties();
    }
}
