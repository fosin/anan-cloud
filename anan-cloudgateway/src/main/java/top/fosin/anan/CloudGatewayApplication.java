package top.fosin.anan;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import top.fosin.anan.core.banner.AnanBanner;
import top.fosin.anan.swagger.annotation.EnableAnanSwagger2;

/**
 * Spring Cloud Gateway
 *
 * @author fosin
 * @date 2019/5/5
 */
@SpringCloudApplication
@EnableAnanSwagger2 //TODO 因为Swagger暂不支持webflux项目，所以Gateway里不能配置SwaggerConfig，也就是说Gateway无法提供自身API。
//@EnableAnanResourceServer //TODO 由于Oauth2依赖Webmvc模块，这和webflux冲突
@EnableWebSecurity
//@EnableWebFluxSecurity
//@EnableRedisHttpSession
public class CloudGatewayApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(CloudGatewayApplication.class)
                .banner(new AnanBanner("AnAn Cloud Gateway"))
                .logStartupInfo(true)
                .run(args);
    }
}
