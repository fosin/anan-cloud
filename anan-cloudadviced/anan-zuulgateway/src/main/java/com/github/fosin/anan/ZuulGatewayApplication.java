package com.github.fosin.anan;

import com.github.fosin.anan.core.banner.AnanBanner;
import com.github.fosin.anan.oauth2.annotation.EnableAnanOauth2;
import com.github.fosin.anan.swagger.annotation.EnableAnanSwagger2;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Description
 *
 * @author fosin
 */
@SpringCloudApplication
@EnableZuulProxy
@EnableHystrixDashboard
@EnableAnanSwagger2
@EnableAnanOauth2
@EnableWebSecurity
//@EnableTurbineStream
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 3600)
public class ZuulGatewayApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ZuulGatewayApplication.class)
                .banner(new AnanBanner("AnAn Zuul Gateway"))
                .logStartupInfo(true)
                .run(args);
    }
}
