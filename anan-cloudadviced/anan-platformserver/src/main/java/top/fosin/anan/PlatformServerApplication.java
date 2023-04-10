package top.fosin.anan;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import top.fosin.anan.core.banner.AnanBanner;
import top.fosin.anan.redis.annotation.EnableAnanRedis;
import top.fosin.anan.swagger.annotation.EnableAnanSwagger2;

/**
 * @author fosin
 */
@SpringBootApplication
@EnableAnanRedis
@EnableAnanSwagger2
@EnableJpaAuditing
@EnableFeignClients
@EnableTransactionManagement
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 60 * 60 * 24)
public class PlatformServerApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(PlatformServerApplication.class)
                .banner(new AnanBanner("Anan Platform Server"))
                .logStartupInfo(true)
                .run(args);
    }
}
