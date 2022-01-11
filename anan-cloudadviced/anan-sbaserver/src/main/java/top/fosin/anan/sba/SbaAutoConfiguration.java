package top.fosin.anan.sba;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author fosin
 * @date 2020/3/26
 * @since 2.0.0
 */
@Configuration
public class SbaAutoConfiguration {

    //@Bean
    //LoadBalancerInterceptor loadBalancerInterceptor(LoadBalancerClient loadBalance) {
    //    return new LoadBalancerInterceptor(loadBalance);
    //}

    @Bean
    @Primary
    @RefreshScope
    @ConfigurationProperties("spring.boot.admin")
    public AdminServerProperties adminServerProperties() {
        return new AdminServerProperties();
    }
}
