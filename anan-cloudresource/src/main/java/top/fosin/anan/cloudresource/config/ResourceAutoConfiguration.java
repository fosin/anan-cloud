package top.fosin.anan.cloudresource.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import top.fosin.anan.cloudresource.entity.res.PermissionDTO;
import top.fosin.anan.cloudresource.service.inter.rpc.PermissionRpcService;
import top.fosin.anan.security.resource.AnanProgramAuthorities;
import top.fosin.anan.security.resource.AnanSecurityProperties;
import top.fosin.anan.security.resource.AuthorityPermission;
import top.fosin.anan.security.resource.SecurityConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fosin
 * @date 2020/9/2
 * @since 2.1.0
 */
@Configuration
public class ResourceAutoConfiguration {
    @Value("${spring.application.name}")
    private String applicationName = "";

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
    @ConditionalOnMissingBean
    public AnanProgramAuthorities ananProgramAuthorities(PermissionRpcService permissionRpcService,
                                                         AnanSecurityProperties ananSecurityProperties) {
        List<AnanSecurityProperties.Authority> authorities = new ArrayList<>();
        // 方法4：GRPC
        if (StringUtils.hasText(this.applicationName)) {
            List<PermissionDTO> permissions = permissionRpcService.findByServiceCode(this.applicationName);
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
}
