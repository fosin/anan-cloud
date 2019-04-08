package com.github.fosin.cdp.platformapi.parameter;

import com.github.fosin.cdp.platformapi.entity.CdpParameterEntity;

/**
 * Description:
 *
 * @author fosin
 * @date 2018.8.6
 */
public interface IParameterUtil {
    /**
     * 设置当前上下文相关的参数
     *
     * @param value       参数值
     * @param name        参数键
     * @param description 参数描述
     * @return 参数数据
     */
    CdpParameterEntity setParameter(String name, String value, String description);

    /**
     * 设置当前上下文相关的参数
     *
     * @param value       参数值
     * @param name        参数键
     * @param description 参数描述
     * @return 参数数据
     */
    CdpParameterEntity setParameter(String scope, String name, String value, String description);

    /**
     * 获取当前上下文的参数
     *
     * @param name 参数键
     * @return 参数值
     */
    String getParameter(String name);

    /**
     * 获取当前上下文相关的参数
     *
     * @param scope 机构的id
     * @param name  参数键
     * @return 参数值
     */
    String getParameter(String scope, String name);

    /**
     * 获取当前上下文相关的参数,如果没有该参数则创建一个
     *
     * @param name         参数键
     * @param defaultValue 如果没有找到，则取这个默认值
     * @param description  参数描述
     * @return 参数值
     */
    String getOrCreateParameter(String name, String defaultValue, String description);

    /**
     * 获取当前上下文相关的参数,如果没有该参数则创建一个
     *
     * @param scope        机构的id
     * @param name         参数键
     * @param defaultValue 如果没有找到，则取这个默认值
     * @param description  参数描述
     * @return 参数值
     */
    String getOrCreateParameter(String scope, String name, String defaultValue, String description);
}
