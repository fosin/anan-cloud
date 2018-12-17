package com.github.fosin.cdp;

import com.github.fosin.cdp.core.banner.CdpBanner;
import com.github.fosin.cdp.oauth2.annotation.EnableCdpOauth2;
import com.github.fosin.cdp.swagger.annotation.EnableCdpSwagger2;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * Description
 *
 * @author fosin
 */
@SpringCloudApplication
@EnableZuulProxy
@EnableHystrixDashboard
@EnableCdpSwagger2
@EnableCdpOauth2
@EnableWebSecurity
//@EnableTurbineStream
//@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 3600)
public class ZuulGatewayApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ZuulGatewayApplication.class)
                .banner(new CdpBanner("CDP Zuul Gateway"))
                .logStartupInfo(true)
                .run(args);
    }

}
