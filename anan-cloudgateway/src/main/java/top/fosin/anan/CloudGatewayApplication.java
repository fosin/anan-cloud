package top.fosin.anan;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;
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
@EnableFeignClients
//@EnableRedisHttpSession
public class CloudGatewayApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(CloudGatewayApplication.class)
                .banner(new AnanBanner("AnAn Cloud Gateway"))
                .logStartupInfo(true)
                .run(args);
    }
}
