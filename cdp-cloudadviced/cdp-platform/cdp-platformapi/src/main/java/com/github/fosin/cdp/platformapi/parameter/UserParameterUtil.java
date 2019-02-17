package com.github.fosin.cdp.platformapi.parameter;

import com.github.fosin.cdp.platformapi.entity.CdpParameterEntity;
import com.github.fosin.cdp.platformapi.entity.CdpUserEntity;
import com.github.fosin.cdp.platformapi.util.LoginUserUtil;
import com.github.fosin.cdp.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

/**
 * Description:
 *
 * @author fosin
 * @date 2018.8.6
 */
@Slf4j
public class UserParameterUtil extends AbstractParameterUtil {
    public static final Integer TYPE = 2;

    public static CdpParameterEntity setParameter(String name, String value, String description) {
        return setParameter(getScope(), name, value, description);
    }

    public static CdpParameterEntity setParameter(String scope, String name, String value, String description) {
        return ParameterUtil.setParameter(TYPE, scope, name, value, description);
    }


    public static String getParameter(String name) {
        return getParameter(getScope(), name);
    }

    public static String getParameter(String scope, String name) {
        CdpParameterEntity parameter = ParameterUtil.getParameter(TYPE, scope, name);
        String info = "没有从参数[" + "type:" + TYPE + " scope:" + scope + " name:" + name + "]中查询到参数";
        log.debug(info);
        Assert.isTrue(parameter != null && parameter.getId() != null, info);
        return getValue(parameter);
    }

    /**
     * 得到机构链中最接近的参数
     *
     * @param name 参数名称CdpParameterEntity.name
     * @return 参数
     */
    public static String getNearestParameter(String name) {
        return getNearestParameter(getScope(), name);
    }

    /**
     * 得到用户链中最接近的参数
     *
     * @param scope 机构ID CdpParameterEntity.scope
     * @param name  参数名称 CdpParameterEntity.name
     * @return 参数
     */
    public static String getNearestParameter(String scope, String name) {
        CdpParameterEntity parameter = ParameterUtil.getParameter(TYPE, scope, name);
        String value = getValue(parameter);
        if (StringUtil.isEmpty(scope)) {
            String info = "没有从参数[" + "type:" + TYPE + " scope:" + scope + " name:" + name + "]中查询到参数";
            log.debug(info);
            Assert.isTrue(parameter != null && parameter.getId() != null, info);
            return value;
        }
        //parameter为空表示没有参数记录，则空域的参数
        if (parameter == null) {
            parameter = ParameterUtil.getParameter(TYPE, "", name);
            String info = "没有从参数[" + "type:" + TYPE + " scope:" + scope + " name:" + name + "]中查询到参数";
            log.debug(info);
            Assert.isTrue(parameter != null && parameter.getId() != null, info);
            value = parameter.getValue();
        }
        return value;
    }

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
        CdpUserEntity user = LoginUserUtil.getUser();
        return user.getId() + "";
    }
}
