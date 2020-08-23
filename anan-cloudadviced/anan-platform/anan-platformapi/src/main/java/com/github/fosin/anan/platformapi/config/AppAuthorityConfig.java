package com.github.fosin.anan.platformapi.config;

import com.github.fosin.anan.platformapi.constant.ServiceConstant;
import com.github.fosin.anan.platformapi.entity.AnanPermissionEntity;
import com.github.fosin.anan.platformapi.service.inter.PermissionFeignService;
import com.github.fosin.anan.security.resource.AnanAuthorityPermission;
import com.github.fosin.anan.security.resource.AnanProgramAuthorities;
import com.github.fosin.anan.security.resource.AnanSecurityProperties;
import com.github.fosin.anan.util.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.*;

/**
 * Description:
 *
 * @author fosin
 * @date 2018.9.12
 */
@Configuration
public class AppAuthorityConfig {

    @Value("${spring.application.name}")
    private String appName;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Bean
    @ConditionalOnBean(PermissionFeignService.class)
    public AnanProgramAuthorities ananProgramAuthorities(PermissionFeignService permissionService) {
        List<AnanSecurityProperties.AnanAuthorityProperties> authorityDtos = new ArrayList<>();
        List<AnanPermissionEntity> entities;
        if (StringUtil.hasText(appName) && !ServiceConstant.ANAN_AUTHSERVER.equals(appName)) {
            ResponseEntity<List<AnanPermissionEntity>> responseEntity = permissionService.findByAppName(appName);
            entities = responseEntity.getBody();

            Objects.requireNonNull(entities).forEach(entity -> {
                if (StringUtil.hasText(entity.getPath())) {
                    String method = entity.getMethod();
                    List<HttpMethod> httpMethods = new ArrayList<>();
                    if (StringUtil.hasText(method)) {
                        String[] strings = method.split(",");
                        for (String string : strings) {
                            httpMethods.add(HttpMethod.resolve(string));
                        }
                    }
                    AnanSecurityProperties.AnanAuthorityProperties authorityDto = new AnanSecurityProperties.AnanAuthorityProperties();
                    authorityDto.setPaths(Collections.singletonList(entity.getPath()));
                    authorityDto.setMethods(httpMethods);
                    authorityDto.setPermission(AnanAuthorityPermission.hasAuthority.getName() + AnanAuthorityPermission.SPLITER + entity.getId());
                    authorityDtos.add(authorityDto);
                }
            });
        }
        return new AnanProgramAuthorities(authorityDtos);
    }
}
