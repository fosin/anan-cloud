package top.fosin.anan.cloudresource.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import top.fosin.anan.cloudresource.service.AnanUserDetailService;
import top.fosin.anan.security.config.JwtDecoderCondition;

/**
 * @author fosin
 * @date 2020/9/2
 * @since 2.1.0
 */
@Configuration
public class ResourceAutoConfiguration {

    @Bean
    //由于JwtDecoder和AnanUserDetailUtil的加载顺序问题导致ConditionalOnBean注解不能正常工作
//    @ConditionalOnBean(JwtDecoder.class)
    @Conditional(JwtDecoderCondition.class)
    public AnanUserDetailService ananUserDetailUtil(JwtDecoder jwtDecoder) {
        return new AnanUserDetailService(jwtDecoder);
    }

}
