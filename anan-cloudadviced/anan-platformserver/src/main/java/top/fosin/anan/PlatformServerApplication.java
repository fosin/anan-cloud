package top.fosin.anan;

import top.fosin.anan.core.banner.AnanBanner;
import top.fosin.anan.redis.annotation.EnableAnanRedis;
import top.fosin.anan.swagger.annotation.EnableAnanSwagger2;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author fosin
 */
@SpringBootApplication
@EnableAnanRedis
@EnableAnanSwagger2
@EnableJpaAuditing
@EnableCircuitBreaker
@EnableFeignClients
@EnableTransactionManagement
public class PlatformServerApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(PlatformServerApplication.class)
                .banner(new AnanBanner("AnAn Platform Server"))
                .logStartupInfo(true)
                .run(args);
    }
}
