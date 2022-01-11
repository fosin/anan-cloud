package top.fosin.anan.cloudresource.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import top.fosin.anan.cloudresource.service.AnanReactiveUserDetailService;
import top.fosin.anan.cloudresource.service.AnanUserDetailService;
import top.fosin.anan.security.resource.JwtDecoderCondition;

/**
 * @author fosin
 * @date 2020/9/2
 * @since 2.1.0
 */
@Configuration
public class ResourceAutoConfiguration {

    @Bean
    //由于JwtDecoder和AnanUserDetailService的加载顺序问题导致ConditionalOnBean注解不能正常工作
    //@ConditionalOnBean(JwtDecoder.class)
    @Conditional(JwtDecoderCondition.class)
    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
    @ConditionalOnMissingBean
    public AnanUserDetailService ananUserDetailService(JwtDecoder jwtDecoder) {
        return new AnanUserDetailService(jwtDecoder);
    }

    @Bean
    //由于JwtDecoder和AnanUserDetailUtil的加载顺序问题导致ConditionalOnBean注解不能正常工作
    //原因：在spring ioc的过程中，优先解析@Component，@Service，@Controller注解的类。其次解析配置类，也就是@Configuration标注的类。最后开始解析配置类中定义的bean。
    //@ConditionalOnBean(ReactiveJwtDecoder.class)
    @Conditional(JwtDecoderCondition.class)
    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
    @ConditionalOnMissingBean
    public AnanReactiveUserDetailService ananReactiveUserDetailService(ReactiveJwtDecoder jwtDecoder) {
        return new AnanReactiveUserDetailService(jwtDecoder);
    }

}
