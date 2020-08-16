package com.github.fosin.anan.platform.config;

import com.github.fosin.anan.security.config.AnanSecurityProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

/**
 * Description:
 *
 * @author fosin
 * @date 2018.12.17
 */
@Configuration
public class AnanPlatformAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean(LoadBalancerInterceptor.class)
    public LoadBalancerInterceptor loadBalancerInterceptor(LoadBalancerClient loadBalance) {
        return new LoadBalancerInterceptor(loadBalance);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    @RefreshScope
    @Primary
    public AnanSecurityProperties ananSecurityProperties() {
        return new AnanSecurityProperties();
    }
}
