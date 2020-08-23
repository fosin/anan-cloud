package com.github.fosin.anan;

import com.github.fosin.anan.core.banner.AnanBanner;
import com.github.fosin.anan.platformapi.config.EnableFeignOAuth2Client;
import com.github.fosin.anan.redis.annotation.EnableAnanRedis;
import com.github.fosin.anan.security.annotation.EnableAnanSecurityOauth2;
import com.github.fosin.anan.swagger.annotation.EnableAnanSwagger2;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.loadbalancer.LoadBalancerEurekaAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author fosin
 * TODO eureka.client.enabled=false，还是会执行LoadBalancerEurekaAutoConfiguration，这个bug在SR7会解决
 */
@SpringBootApplication(exclude = {LoadBalancerEurekaAutoConfiguration.class})
@EnableAnanRedis
@EnableAnanSwagger2
@EnableAnanSecurityOauth2
@EnableJpaAuditing
@EnableCircuitBreaker
@EnableFeignOAuth2Client
@EnableTransactionManagement
public class PlatformServerApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(PlatformServerApplication.class)
                .banner(new AnanBanner("AnAn Platform Server"))
                .logStartupInfo(true)
                .run(args);
    }
}
