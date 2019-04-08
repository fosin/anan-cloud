package com.github.fosin.cdp.platform.util;

import com.github.fosin.cdp.platform.service.inter.ParameterService;
import com.github.fosin.cdp.platformapi.entity.CdpParameterEntity;
import com.github.fosin.cdp.platformapi.parameter.AbstractParameterUtil;
import com.github.fosin.cdp.platformapi.parameter.ParameterType;
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
public class LocalParameterUtil extends AbstractParameterUtil {
    private static ParameterService parameterService;

    @Autowired
    public LocalParameterUtil(ParameterService parameterService) {
        LocalParameterUtil.parameterService = parameterService;
    }

    public static String getOrCreateParameter(ParameterType type, String scope, String name, String defaultValue, String description) {
        return getValue(parameterService.getOrCreateParameter(type.getTypeValue(), scope, name, defaultValue, description));
    }

    public static String getOrCreateParameter(ParameterType type, String name, String defaultValue, String description) {
        return getOrCreateParameter(type, getScope(type), name, defaultValue, description);
    }

    public static CdpParameterEntity getParameter(ParameterType type, String scope, String name) {
        return parameterService.getNearestParameter(type.getTypeValue(), scope, name);
    }

    public static CdpParameterEntity getParameter(ParameterType type, String name) {
        return getParameter(type, getScope(type), name);
    }

    public static String getScope(ParameterType type) {
        return com.github.fosin.cdp.platformapi.parameter.ParameterUtil.getScope(type);
    }

    public static ParameterService getParameterService() {
        return parameterService;
    }
}
