package top.fosin.anan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import top.fosin.anan.core.banner.AnanBanner;
import top.fosin.anan.redis.annotation.EnableAnanRedis;
import top.fosin.anan.security.servlet.annotation.EnableAnanWebSecurity;

/**
 * @author fosin
 */
@SpringBootApplication
@EnableAnanRedis
@EnableAnanWebSecurity
@EnableJpaAuditing
@EnableFeignClients
@EnableTransactionManagement
public class PlatformServerApplication {
    public static void main(String[] args) {
        String banner = "Anan Platform Server";
        SpringApplication application = new SpringApplicationBuilder(PlatformServerApplication.class)
                .banner(new AnanBanner(banner))
                .logStartupInfo(true).build();
        application.setAllowBeanDefinitionOverriding(true);
        application.run(args);
    }
}
