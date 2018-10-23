package com.github.fosin.cdp.platformapi.parameter;

import com.github.fosin.cdp.core.exception.CdpServiceException;
import com.github.fosin.cdp.platformapi.entity.CdpSysParameterEntity;
import com.github.fosin.cdp.platformapi.service.inter.IParameterService;
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
public class ParameterUtil {
    private static IParameterService parameterService;

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public ParameterUtil(IParameterService parameterService) {
        ParameterUtil.parameterService = parameterService;
    }

    public static CdpSysParameterEntity getOrCreateParameter(Integer type, String scope, String name, String defaultValue, String description) throws RuntimeException {
        CdpSysParameterEntity parameterEntity = parameterService.findByTypeAndScopeAndName(type, scope, name);
        if (parameterEntity.getId() == null || parameterEntity.getId() < 1) {
            parameterEntity = setParameter(type, scope, name, defaultValue, description);
        }
        return parameterEntity;
    }

    public static CdpSysParameterEntity getParameter(Integer type, String scope, String name) throws RuntimeException {
        return parameterService.findByTypeAndScopeAndName(type, scope, name);
    }

    public static synchronized CdpSysParameterEntity setParameter(Integer type, String scope, String name, String value, String description) throws RuntimeException {
        CdpSysParameterEntity entity = getParameter(type, scope, name);
        if (value == null) {
            value = "";
        }
        if (entity == null) {
            entity = new CdpSysParameterEntity();
            entity.setScope(scope);
            entity.setType(type);
            entity.setName(name);
            entity.setValue(value);
            entity.setDefaultValue(value);
            entity.setDescription(description);
            try {
                return parameterService.create(entity);
            } catch (CdpServiceException e) {
                throw new RuntimeException(e);
            }
        }
        entity.setValue(value);
        try {
            return parameterService.update(entity);
        } catch (CdpServiceException e) {
            throw new RuntimeException(e);
        }
    }

    public static IParameterService getParameterService() {
        return parameterService;
    }
}
