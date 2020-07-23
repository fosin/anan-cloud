package com.github.fosin.anan;

import com.github.fosin.anan.core.banner.AnanBanner;
import com.github.fosin.anan.oauth2.annotation.EnableAnanResourceServer;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.loadbalancer.LoadBalancerEurekaAutoConfiguration;

/**
 * Description
 *
 * @author fosin
 */
@SpringBootApplication(exclude = LoadBalancerEurekaAutoConfiguration.class)
@EnableAdminServer
@EnableAnanResourceServer
public class AdminServerApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(AdminServerApplication.class)
                .banner(new AnanBanner("AnAn Admin Server"))
                .logStartupInfo(true)
                .run(args);
    }
}
