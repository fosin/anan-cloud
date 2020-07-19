package com.github.fosin.anan.platformapi.config;

import com.github.fosin.anan.oauth2.config.AnanAuthorityConfig;
import com.github.fosin.anan.oauth2.dto.AnanAuthorityDto;
import com.github.fosin.anan.platformapi.constant.ServiceConstant;
import com.github.fosin.anan.platformapi.service.inter.PermissionFeignService;
import com.github.fosin.anan.platformapi.entity.AnanPermissionEntity;
import com.github.fosin.anan.util.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    @ConditionalOnBean(PermissionFeignService.class)
    public AnanAuthorityConfig authorityConfigs(PermissionFeignService permissionService) {
        List<AnanAuthorityDto> authorityDtos = new ArrayList<>();
        List<AnanPermissionEntity> entities;
        if (StringUtil.hasText(appName) && !ServiceConstant.ANAN_AUTHSERVER.equals(appName)) {
            ResponseEntity<List<AnanPermissionEntity>> responseEntity = permissionService.findByAppName(appName);
            entities = responseEntity.getBody();

            Objects.requireNonNull(entities).forEach(entity -> {
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
                    AnanAuthorityDto authorityDto = new AnanAuthorityDto();
                    authorityDto.setPath(entity.getPath());
                    authorityDto.setMethod(httpMethods);
                    authorityDto.setAuthority(entity.getId() + "");
                    authorityDtos.add(authorityDto);
                }
            });
        }
        return new AnanAuthorityConfig(authorityDtos);
    }
}
