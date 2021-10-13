package top.fosin.anan;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import top.fosin.anan.core.banner.AnanBanner;

/**
 * @author fosin
 */
@SpringBootApplication
@EnableAdminServer
public class SbaServerApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(SbaServerApplication.class)
                .banner(new AnanBanner("AnAn SBA Server"))
                .logStartupInfo(true)
                .run(args);
    }
}
