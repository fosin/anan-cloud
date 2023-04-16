package top.fosin.anan;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import top.fosin.anan.core.banner.AnanBanner;
import top.fosin.anan.redis.annotation.EnableAnanRedis;
import top.fosin.anan.security.servlet.annotation.EnableAnanWebSecurity;
import top.fosin.anan.swagger.annotation.EnableAnanSwagger2;

/**
 * @author fosin
 */
@SpringBootApplication
@EnableAnanRedis
@EnableAnanSwagger2
@EnableAnanWebSecurity
@EnableFeignClients
public class AuthServerApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(AuthServerApplication.class)
                .banner(new AnanBanner("Anan Auth Server"))
                .logStartupInfo(true)
                .run(args);
    }

}
