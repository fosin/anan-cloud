package top.fosin.anan;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import top.fosin.anan.core.banner.AnanBanner;

/**
 * @author fosin
 */
@SpringBootApplication
@EnableAdminServer
//使用K8S作为服务发现需要启用该注解，否则在sbaserver之后启动的应用发现不了
@EnableScheduling
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 60 * 60 * 24)
public class SbaServerApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(SbaServerApplication.class)
                .banner(new AnanBanner("Anan SBA Server"))
                .logStartupInfo(true)
                .run(args);
    }
}
