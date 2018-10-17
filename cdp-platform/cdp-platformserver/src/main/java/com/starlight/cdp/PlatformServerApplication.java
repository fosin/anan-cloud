package com.starlight.cdp;

import com.starlight.cdp.core.banner.CDPBanner;
import com.starlight.cdp.oauth2.annotation.EnableCdpOauth2;
import com.starlight.cdp.swagger.annotation.EnableCdpSwagger2;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Description
 *
 * @author fosin
 */
@SpringBootApplication
@EnableCaching
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 60)
@EnableCdpSwagger2
@EnableCdpOauth2
@EnableWebSecurity
public class PlatformServerApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(PlatformServerApplication.class)
                .banner(new CDPBanner("CDP Platform Server"))
                .logStartupInfo(true)
                .run(args);
    }

    @Bean
    @ConditionalOnMissingBean(LoadBalancerInterceptor.class)
    public LoadBalancerInterceptor loadBalancerInterceptor(LoadBalancerClient loadBalance) {
        return new LoadBalancerInterceptor(loadBalance);
    }
}
