package com.github.fosin.anan;

import com.github.fosin.anan.core.banner.AnanBanner;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Description
 *
 * @author fosin
 */
@SpringBootApplication
@EnableAdminServer
public class AdminServerApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(AdminServerApplication.class)
                .banner(new AnanBanner("AnAn Admin Server"))
                .logStartupInfo(true)
                .run(args);
    }
}
