package top.fosin.anan.platform.config;

import net.devh.boot.grpc.server.security.authentication.BearerAuthenticationReader;
import net.devh.boot.grpc.server.security.authentication.CompositeGrpcAuthenticationReader;
import net.devh.boot.grpc.server.security.authentication.GrpcAuthenticationReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import top.fosin.anan.cloudresource.entity.res.PermissionDTO;
import top.fosin.anan.platform.modules.permission.service.inter.PermissionService;
import top.fosin.anan.security.resource.AnanProgramAuthorities;
import top.fosin.anan.security.resource.AnanSecurityProperties;
import top.fosin.anan.security.resource.AuthorityPermission;
import top.fosin.anan.security.resource.SecurityConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fosin
 * @date 2018.12.17
 */
@Configuration
public class PlatformAutoConfiguration {
    @Value("${spring.application.name}")
    private final String applicationName = "anan-platformserver";

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * TODO
     *  如果bean名称是ananProgramAuthorities，则会出现源码跑无问题，编译打包运行报错：
     *  The bean 'ananProgramAuthorities', defined in class path resource [top/fosin/anan/cloudgateway/config/GatewayAutoConfiguration.class], could not be registered. A bean with that name has already been defined in class path resource [top/fosin/anan/security/configurer/AnanSecurityAutoConfiguration.class] and overriding is disabled.
     * Action: Consider renaming one of the beans or enabling overriding by setting
     * spring.main.allow-bean-definition-overriding=true
     * <p>
     * 即使设置了spring.main.allow-bean-definition-overriding=true还是会出现这个问题。
     *
     * @return AnanProgramAuthorities 编程权限
     */
    @Bean
    public AnanProgramAuthorities ananProgramAuthorities(PermissionService permissionService,
                                                         AnanSecurityProperties ananSecurityProperties) {
        List<AnanSecurityProperties.Authority> authorities = new ArrayList<>();
        // 方法4：GRPC
        if (StringUtils.hasText(applicationName)) {
            List<PermissionDTO> permissions = permissionService.findByServiceCode(applicationName);
            String authorityPrefix = ananSecurityProperties.getOauth2().getResourceServer().getAuthorityPrefix();
            permissions.forEach(p -> {
                String path = p.getPath();
                if (StringUtils.hasText(path)) {
                    AnanSecurityProperties.Authority authority = new AnanSecurityProperties.Authority();
                    authority.setPaths(path);
                    authority.setMethods(p.getMethod());
                    String permission = AuthorityPermission.hasAuthority.getName() + SecurityConstant.AUTHORITY_SPLIT
                            + authorityPrefix + p.getId();
                    authority.setPermission(permission);
                    authorities.add(authority);
                }
            });
        }
        return new AnanProgramAuthorities(authorities);
    }

//    @Bean
//    public AuthenticationManager authenticationManager() {
//        final List<AuthenticationProvider> providers = new ArrayList<>();
//        providers.add(...); // Possibly JwtAuthenticationProvider
//        return new ProviderManager(providers);
//    }

    @Bean
    public GrpcAuthenticationReader authenticationReader() {
        final List<GrpcAuthenticationReader> readers = new ArrayList<>();
        // The actual token class is dependent on your spring-security library (OAuth2/JWT/...)
        readers.add(new BearerAuthenticationReader(BearerTokenAuthenticationToken::new));
        return new CompositeGrpcAuthenticationReader(readers);
    }
}
