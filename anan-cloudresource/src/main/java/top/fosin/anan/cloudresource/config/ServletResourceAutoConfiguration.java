package top.fosin.anan.cloudresource.config;

import org.springframework.boot.actuate.autoconfigure.endpoint.web.CorsEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.web.server.ManagementPortType;
import org.springframework.boot.actuate.endpoint.ExposableEndpoint;
import org.springframework.boot.actuate.endpoint.web.*;
import org.springframework.boot.actuate.endpoint.web.annotation.ControllerEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.annotation.ServletEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;
import top.fosin.anan.cloudresource.service.CurrentUserService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author fosin
 * @date 2020/9/2
 * @since 2.1.0
 */
@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class ServletResourceAutoConfiguration {
    /**
     * 解决springboot升到2.6.x之后，使用actuator之后swagger会报错
     *
     * @param wes the web endpoints supplier
     * @param ses the servlet endpoints supplier
     * @param ces the controller endpoints supplier
     * @param emt the endpoint media types
     * @param cep the cors properties
     * @param wep the web endpoints properties
     * @param env the environment
     * @return the web mvc endpoint handler mapping
     */
    @Bean
    @ConditionalOnMissingBean
    public WebMvcEndpointHandlerMapping webMvcEndpointHandlerMapping(WebEndpointsSupplier wes
            , ServletEndpointsSupplier ses, ControllerEndpointsSupplier ces, EndpointMediaTypes emt
            , CorsEndpointProperties cep, WebEndpointProperties wep, Environment env) {
        List<ExposableEndpoint<?>> allEndpoints = new ArrayList<>();
        Collection<ExposableWebEndpoint> webEndpoints = wes.getEndpoints();
        allEndpoints.addAll(webEndpoints);
        allEndpoints.addAll(ses.getEndpoints());
        allEndpoints.addAll(ces.getEndpoints());
        String basePath = wep.getBasePath();
        EndpointMapping endpointMapping = new EndpointMapping(basePath);
        boolean shouldRegisterLinksMapping = wep.getDiscovery().isEnabled() && (StringUtils.hasText(basePath) || ManagementPortType.get(env).equals(ManagementPortType.DIFFERENT));
        return new WebMvcEndpointHandlerMapping(endpointMapping, webEndpoints, emt
                , cep.toCorsConfiguration(), new EndpointLinksResolver(
                allEndpoints, basePath), shouldRegisterLinksMapping, null);
    }

    @Bean
    @ConditionalOnMissingBean
    public CurrentUserService userInfoService() {
        return new CurrentUserService();
    }
}
