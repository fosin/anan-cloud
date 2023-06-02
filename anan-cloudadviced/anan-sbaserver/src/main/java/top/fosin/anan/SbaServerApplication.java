package top.fosin.anan;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import top.fosin.anan.core.banner.AnanBanner;

/**
 * @author fosin
 */
@SpringBootApplication
@EnableAdminServer
//使用K8S作为服务发现需要启用该注解，否则在sbaserver之后启动的应用发现不了
@EnableScheduling
public class SbaServerApplication {
    public static void main(String[] args) {
        String banner = "Anan SBA Gateway";
        SpringApplication application = new SpringApplicationBuilder(SbaServerApplication.class)
                .banner(new AnanBanner(banner))
                .logStartupInfo(true).build();
        application.setAllowBeanDefinitionOverriding(true);
        application.run(args);
    }
}
