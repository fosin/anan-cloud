package com.starlight.cdp.platformapi.parameter;

import com.starlight.cdp.platformapi.entity.CdpSysOrganizationEntity;
import com.starlight.cdp.platformapi.entity.CdpSysParameterEntity;
import com.starlight.cdp.platformapi.entity.CdpSysUserEntity;
import com.starlight.cdp.platformapi.service.inter.IOrganizationService;
import com.starlight.cdp.platformapi.util.LoginUserUtil;
import com.starlight.cdp.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Description:
 *
 * @author fosin
 * @date 2018.8.6
 */
@Component
public class OrganizParameterUtil extends AbstractParameterUtil {
    public static final Integer TYPE = 1;
    private static IOrganizationService organizationService;

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public OrganizParameterUtil(IOrganizationService organizationService) {
        OrganizParameterUtil.organizationService = organizationService;
    }

    public static CdpSysParameterEntity setParameter(String name, String value, String description) {
        return setParameter(getScope(), name, value, description);
    }

    public static CdpSysParameterEntity setParameter(String scope, String name, String value, String description) {
        return ParameterUtil.setParameter(TYPE, scope, name, value, description);
    }

    public static String getParameter(String name) {
        return getParameter(getScope(), name);
    }


    public static String getParameter(String scope, String name) {
        CdpSysParameterEntity parameterEntity = ParameterUtil.getParameter(TYPE, scope, name);
        Assert.notNull(parameterEntity, "没有从参数[" + "type:" + TYPE + " scope:" + scope + " name:" + name + "]中查询到参数");
        return getValue(parameterEntity);
    }

    /**
     * 得到机构链中最接近的参数
     *
     * @param name 参数名称CdpSysParameterEntity.name
     * @return 参数
     */
    public static String getNearestParameter(String name) {
        return getNearestParameter(getScope(), name);
    }

    /**
     * 得到机构链中最接近的参数
     *
     * @param scope 机构ID CdpSysParameterEntity.scope
     * @param name  参数名称 CdpSysParameterEntity.name
     * @return 参数
     */
    public static String getNearestParameter(String scope, String name) {
        CdpSysParameterEntity parameter = ParameterUtil.getParameter(TYPE, scope, name);
        String value = getValue(parameter);
        if (StringUtil.isEmpty(scope)) {
            Assert.notNull(parameter, "没有从参数[" + "type:" + TYPE + " scope:" + scope + " name:" + name + "]中查询到参数");
            return value;
        }
        //parameter为空表示没有参数记录，则依次向上找父机构的参数
        if (parameter.getId() == null || parameter.getId() < 1) {
            Integer id = Integer.parseInt(scope);
            CdpSysOrganizationEntity entity = organizationService.findOne(id);
            if (entity == null || entity.getLevel() == 0) {
                value = getNearestParameter("", name);
            } else {
                value = getNearestParameter(entity.getpId() + "", name);
            }
        }
        return value;
    }

    /**
     * 查询参数，如果没有找到参数数据则自动创建一条
     *
     * @param name         数名称 CdpSysParameterEntity.name
     * @param defaultValue 参数默认值
     * @param description  参数描述
     * @return 参数值
     */
    public static String getOrCreateParameter(String name, String defaultValue, String description) {
        return getOrCreateParameter(getScope(), name, defaultValue, description);
    }


    public static String getOrCreateParameter(String scope, String name, String defaultValue, String description) {
        String value;
        try {
            value = getNearestParameter(scope, name);
        } catch (Exception e) {// 报异常说明没有找到任何相关参数，则需要创建一个无域参数，这样默认所有机构共享这一个参数，如果需要设置机构个性化参数则需要在前端手动创建
            value = getValue(ParameterUtil.setParameter(TYPE, "", name, defaultValue, description));
        }
        return value;
    }

    private static String getScope() {
        CdpSysUserEntity user = LoginUserUtil.getUser();
        return user.getOrganizId() + "";
    }
}
