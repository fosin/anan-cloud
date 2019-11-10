package com.github.fosin.anan;

import com.github.fosin.anan.core.banner.AnanBanner;
import com.github.fosin.anan.oauth2.annotation.EnableAnanOauth2;
import com.github.fosin.anan.swagger.annotation.EnableAnanSwagger2;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * Spring Cloud Gateway
 *
 * @author fosin
 * @date 2019/5/5
 */
@SpringCloudApplication
@EnableAnanSwagger2
@EnableAnanOauth2
//@EnableWebSecurity
//@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 3600)
public class CloudGatewayApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(CloudGatewayApplication.class)
                .banner(new AnanBanner("AnAn Cloud Gateway"))
                .logStartupInfo(true)
                .run(args);
    }

}
