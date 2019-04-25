package com.github.fosin.anan.platformapi.parameter;

import com.github.fosin.anan.platformapi.entity.AnanParameterEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * 机构参数
 *
 * @author fosin
 * @date 2018.8.6
 */
@Component
@Slf4j
public class OrganizParameterUtil extends AbstractParameterUtil {

    public static AnanParameterEntity setParameter(String name, String value, String description) {
        return setParameter(ParameterUtil.getScope(ParameterType.Organization), name, value, description);
    }

    public static AnanParameterEntity setParameter(String scope, String name, String value, String description) {
        return ParameterUtil.setParameter(ParameterType.Organization, scope, name, value, description);
    }

    public static String getParameter(String name) {
        return getParameter(ParameterUtil.getScope(ParameterType.Organization), name);
    }

    public static String getParameter(String scope, String name) {
        AnanParameterEntity parameter = ParameterUtil.getParameter(ParameterType.Organization, scope, name);
        String info = "没有从参数[" + "type:" + ParameterType.Organization + " scope:" + scope + " name:" + name + "]中查询到参数";
        log.debug(info);
        Assert.isTrue(parameter != null && parameter.getId() != null, info);
        return getValue(parameter);
    }

    /**
     * 查询参数，如果没有找到参数数据则自动创建一条
     *
     * @param name         数名称 AnanParameterEntity.name
     * @param defaultValue 参数默认值
     * @param description  参数描述
     * @return 参数值
     */
    public static String getOrCreateParameter(String name, String defaultValue, String description) {
        return getOrCreateParameter(ParameterUtil.getScope(ParameterType.Organization), name, defaultValue, description);
    }


    public static String getOrCreateParameter(String scope, String name, String defaultValue, String description) {
        return ParameterUtil.getOrCreateParameter(ParameterType.Organization, scope, name, defaultValue, description);
    }

}
