package com.starlight.cdp;

import com.starlight.cdp.core.banner.CDPBanner;
import com.starlight.cdp.oauth2.annotation.EnableCdpOauth2;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import zipkin.server.internal.EnableZipkinServer;

/**
 * Description
 *
 * @author fosin
 */
@SpringBootApplication
@EnableZipkinServer
@EnableCdpOauth2
@EnableWebSecurity
public class SleuthServerApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(SleuthServerApplication.class)
                .banner(new CDPBanner("CDP Sleuth Server"))
                .logStartupInfo(true)
                .run(args);
    }
}
