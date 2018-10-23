package com.github.fosin.cdp;

import com.github.fosin.cdp.core.banner.CdpBanner;
import com.github.fosin.cdp.oauth2.annotation.EnableCdpOauth2;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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
                .banner(new CdpBanner("CDP Sleuth Server"))
                .logStartupInfo(true)
                .run(args);
    }
}
