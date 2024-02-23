package top.fosin.anan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import top.fosin.anan.core.banner.AnanBanner;
import top.fosin.anan.redis.annotation.EnableAnanRedis;
import top.fosin.anan.security.servlet.annotation.EnableAnanWebSecurity;

/**
 * @author fosin
 */
@SpringBootApplication
@EnableAnanRedis
@EnableAnanWebSecurity
@EnableFeignClients
public class AuthServerApplication {
    public static void main(String[] args) {
        String banner = "Anan Auth Server";
        SpringApplication application = new SpringApplicationBuilder(AuthServerApplication.class)
                .banner(new AnanBanner(banner))
                .logStartupInfo(true).build();
        application.setAllowBeanDefinitionOverriding(true);
        application.run(args);
    }

}
