package top.fosin.anan.sba;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
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
//    /**
//     * 解决springboot升到2.6.x之后，使用actuator之后swagger会报错
//     *
//     * @return BeanPostProcessor
//     */
//    @Bean
//    public BeanPostProcessor springfoxHandlerProviderBeanPostProcessor() {
//        return new BeanPostProcessor() {
//
//            @Override
//            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//                if (bean instanceof WebMvcRequestHandlerProvider || bean instanceof WebFluxRequestHandlerProvider) {
//                    customizeSpringfoxHandlerMappings(getHandlerMappings(bean));
//                }
//                return bean;
//            }
//
//            private <T extends RequestMappingInfoHandlerMapping> void customizeSpringfoxHandlerMappings(List<T> mappings) {
//                List<T> copy = mappings.stream()
//                        .filter(mapping -> mapping.getPatternParser() == null)
//                        .collect(Collectors.toList());
//                mappings.clear();
//                mappings.addAll(copy);
//            }
//
//            @SuppressWarnings("unchecked")
//            private List<RequestMappingInfoHandlerMapping> getHandlerMappings(Object bean) {
//                try {
//                    Field field = ReflectionUtils.findField(bean.getClass(), "handlerMappings");
//                    field.setAccessible(true);
//                    return (List<RequestMappingInfoHandlerMapping>) field.get(bean);
//                } catch (IllegalArgumentException | IllegalAccessException e) {
//                    throw new IllegalStateException(e);
//                }
//            }
//        };
//    }
    //@Bean
    //LoadBalancerInterceptor loadBalancerInterceptor(LoadBalancerClient loadBalance) {
    //    return new LoadBalancerInterceptor(loadBalance);
    //}

    @Bean
    @Primary
    @RefreshScope
    public AdminServerProperties adminServerProperties() {
        return new AdminServerProperties();
    }
}
