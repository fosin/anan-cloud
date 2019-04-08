package com.github.fosin.cdp.platformapi.parameter;

import com.github.fosin.cdp.platformapi.dto.request.CdpParameterCreateDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpParameterRetrieveDto;
import com.github.fosin.cdp.platformapi.entity.CdpParameterEntity;
import com.github.fosin.cdp.platformapi.service.inter.ParameterFeignService;
import com.github.fosin.cdp.platformapi.util.LoginUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Description: 数据库参数工具类
 *
 * @author fosin
 * @date 2018.8.1
 */
@Component
@Slf4j
public class ParameterUtil extends AbstractParameterUtil {
    private static ParameterFeignService parameterService;

    @Autowired
    public ParameterUtil(ParameterFeignService parameterService) {
        ParameterUtil.parameterService = parameterService;
    }

    public static String getOrCreateParameter(ParameterType type, String scope, String name, String defaultValue, String description) {
        CdpParameterRetrieveDto createEntity = new CdpParameterRetrieveDto();
        createEntity.setScope(scope);
        createEntity.setType(type.getTypeValue());
        createEntity.setName(name);
        createEntity.setValue(defaultValue);
        createEntity.setDefaultValue(defaultValue);
        createEntity.setDescription(description);
        return parameterService.getOrCreateParameter(createEntity).getBody();
    }

    public static String getOrCreateParameter(ParameterType type, String name, String defaultValue, String description) {
        return getOrCreateParameter(type, getScope(type), name, defaultValue, description);
    }

    public static CdpParameterEntity getParameter(ParameterType type, String scope, String name) {
        return parameterService.getParameter(type.getTypeValue(), scope, name).getBody();
    }

    public static CdpParameterEntity getParameter(ParameterType type, String name) {
        return getParameter(type, getScope(type), name);
    }

    public static CdpParameterEntity setParameter(ParameterType type, String name, String value, String description) {
        return setParameter(type, getScope(type), name, value, description);
    }

    public static synchronized CdpParameterEntity setParameter(ParameterType type, String scope, String name, String value, String description) {
        CdpParameterCreateDto createDto = new CdpParameterCreateDto();
        createDto.setValue(value);
        createDto.setType(type.getTypeValue());
        createDto.setScope(scope);
        createDto.setName(name);
        createDto.setDescription(description);
        return parameterService.create(createDto).getBody();

    }

    public static String getScope(ParameterType type) {
        switch (type) {
            case Organization:
                return LoginUserUtil.getUser().getOrganizId() + "";
            case User:
                return LoginUserUtil.getUser().getId() + "";
            default:
        }
        return null;
    }

    public static ParameterFeignService getParameterService() {
        return parameterService;
    }
}
