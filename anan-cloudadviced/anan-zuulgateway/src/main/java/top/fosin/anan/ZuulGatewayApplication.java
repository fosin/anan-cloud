package top.fosin.anan;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import top.fosin.anan.core.banner.AnanBanner;
import top.fosin.anan.redis.annotation.EnableAnanRedis;
import top.fosin.anan.security.servlet.annotation.EnableAnanWebSecurity;
import top.fosin.anan.swagger.annotation.EnableAnanSwagger2;

/**
 * @author fosin
 */
@SpringBootApplication
@EnableCircuitBreaker
@EnableZuulProxy //启用RateLimit
@EnableHystrixDashboard
@EnableAnanRedis
@EnableAnanSwagger2
@EnableAnanWebSecurity
@EnableFeignClients
public class ZuulGatewayApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ZuulGatewayApplication.class)
                .banner(new AnanBanner("Anan Zuul Gateway"))
                .logStartupInfo(true)
                .run(args);
    }
}
