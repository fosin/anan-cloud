package top.fosin.anan.cloudresource.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.fosin.anan.cloudresource.service.ReactiveCurrentUserService;
import top.fosin.anan.security.resource.AnanSecurityProperties;

/**
 * @author fosin
 * @date 2020/9/2
 * @since 2.1.0
 */
@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
public class ReactiveResourceAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public ReactiveCurrentUserService reactiveCurrentUserService(AnanSecurityProperties ananSecurityProperties) {
        return new ReactiveCurrentUserService(ananSecurityProperties);
    }
}
