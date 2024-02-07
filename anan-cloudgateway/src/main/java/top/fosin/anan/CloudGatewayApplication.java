package top.fosin.anan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.retry.annotation.EnableRetry;
import top.fosin.anan.core.banner.AnanBanner;
import top.fosin.anan.security.reactive.annotation.EnableAnanWebFluxSecurity;

/**
 * Spring Cloud Gateway
 *
 * @author fosin
 * @date 2019/5/5
 */
@SpringBootApplication
//@EnableAnanSwagger2
@EnableAnanWebFluxSecurity
@EnableRetry
public class CloudGatewayApplication {
    public static void main(String[] args) {
        String banner = "Anan Cloud Gateway";
        SpringApplication application = new SpringApplicationBuilder(CloudGatewayApplication.class)
                .banner(new AnanBanner(banner))
                .logStartupInfo(true).build();
        application.setAllowBeanDefinitionOverriding(true);
        application.run(args);
    }
}
