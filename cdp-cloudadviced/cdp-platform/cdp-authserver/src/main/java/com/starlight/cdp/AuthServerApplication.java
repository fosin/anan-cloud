package com.starlight.cdp;

import com.starlight.cdp.core.banner.CdpBanner;
import com.starlight.cdp.oauth2.annotation.EnableCdpOauth2;
import com.starlight.cdp.swagger.annotation.EnableCdpSwagger2;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Description
 * @author fosin
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableCaching
@EnableRedisHttpSession(maxInactiveIntervalInSeconds=60)
@EnableCdpSwagger2
@EnableCdpOauth2
public class AuthServerApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(AuthServerApplication.class)
                .banner(new CdpBanner("CDP Auth Server"))
                .logStartupInfo(true)
                .run(args);
    }
}
