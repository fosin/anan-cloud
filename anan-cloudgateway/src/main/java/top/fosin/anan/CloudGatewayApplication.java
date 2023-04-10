package top.fosin.anan;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.session.data.redis.config.annotation.web.server.EnableRedisWebSession;
import top.fosin.anan.core.banner.AnanBanner;
import top.fosin.anan.security.reactive.annotation.EnableAnanWebFluxSecurity;
import top.fosin.anan.swagger.annotation.EnableAnanSwagger2;

/**
 * Spring Cloud Gateway
 *
 * @author fosin
 * @date 2019/5/5
 */
@SpringBootApplication
@EnableAnanSwagger2
@EnableAnanWebFluxSecurity
@EnableRedisWebSession(maxInactiveIntervalInSeconds = 60 * 60 * 24)
@EnableRetry
public class CloudGatewayApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(CloudGatewayApplication.class)
                .banner(new AnanBanner("Anan Cloud Gateway"))
                .logStartupInfo(true)
                .run(args);
    }
}
