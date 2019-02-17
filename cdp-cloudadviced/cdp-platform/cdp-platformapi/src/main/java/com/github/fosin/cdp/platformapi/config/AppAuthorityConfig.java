package com.github.fosin.cdp.platformapi.config;

import com.github.fosin.cdp.platformapi.entity.CdpPermissionEntity;
import com.github.fosin.cdp.oauth2.config.CdpAuthorityConfig;
import com.github.fosin.cdp.oauth2.dto.CdpAuthorityDto;
import com.github.fosin.cdp.platformapi.service.inter.IPermissionService;
import com.github.fosin.cdp.util.StringUtil;
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
    public CdpAuthorityConfig authorityConfiggs(IPermissionService permissionService) {
        List<CdpAuthorityDto> authorityDtos = new ArrayList<>();
        List<CdpPermissionEntity> entities;
        if (StringUtil.hasText(appName)) {
            entities = permissionService.findByAppName(appName);
        } else {
            entities = (List<CdpPermissionEntity>) permissionService.findAll();
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
                CdpAuthorityDto authorityDto = new CdpAuthorityDto();
                authorityDto.setPath(entity.getPath());
                authorityDto.setMethod(httpMethods);
                authorityDto.setAuthority(entity.getId() + "");
                authorityDtos.add(authorityDto);
            }

        });
        return new CdpAuthorityConfig(authorityDtos);
    }
}
