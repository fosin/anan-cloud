package com.starlight.cdp.platformapi.config;

import com.starlight.cdp.oauth2.config.AuthorityConfig;
import com.starlight.cdp.oauth2.dto.AuthorityDto;
import com.starlight.cdp.platformapi.entity.CdpSysPermissionEntity;
import com.starlight.cdp.platformapi.service.inter.IPermissionService;
import com.starlight.cdp.util.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import java.util.ArrayList;
import java.util.List;

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

    @Bean
    @ConditionalOnBean(IPermissionService.class)
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public AuthorityConfig authorityConfiggs(IPermissionService permissionService) {
        List<AuthorityDto> authorityDtos = new ArrayList<>();
        List<CdpSysPermissionEntity> entities;
        if (StringUtil.hasText(appName)) {
            entities = permissionService.findByAppName(appName);
        } else {
            entities = (List<CdpSysPermissionEntity>) permissionService.findAll();
        }
        entities.forEach(entity -> {
            if (StringUtil.hasText(entity.getPath())) {
                String method = entity.getMethod();
                HttpMethod[] httpMethods = new HttpMethod[0];
                if (StringUtil.hasText(method)) {
                    String[] strings = method.split(",");
                    httpMethods = new HttpMethod[strings.length];
                    for (int i = 0; i < strings.length; i++) {
                        httpMethods[i] = HttpMethod.resolve(strings[i]);
                    }
                }
                AuthorityDto authorityDto = new AuthorityDto();
                authorityDto.setPath(entity.getPath());
                authorityDto.setMethod(httpMethods);
                authorityDto.setAuthority(entity.getId() + "");
                authorityDtos.add(authorityDto);
            }

        });
        return new AuthorityConfig(authorityDtos);
    }
}
