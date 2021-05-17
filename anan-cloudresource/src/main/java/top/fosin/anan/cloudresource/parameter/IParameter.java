package top.fosin.anan.cloudresource.parameter;

import top.fosin.anan.cloudresource.dto.res.AnanParameterRespDto;

/**
 * @author fosin
 * @date 2018.8.6
 */
public interface IParameter {
    /**
     * 设置当前参数
     *
     * @param name        参数键
     * @param value       参数值
     * @param description 参数描述
     * @return 参数数据
     */
    default AnanParameterRespDto setParameter(String name, String value, String description) {
        return setParameter(this.getParameterStrategy().getScope(), name, value, description);
    }

    /**
     * 设置当前参数
     *
     * @param name        参数键
     * @param value       参数值
     * @param description 参数描述
     * @return 参数数据
     */
    AnanParameterRespDto setParameter(String scope, String name, String value, String description);

    /**
     * 获取当前参数
     *
     * @param name 参数键
     * @return 参数值
     */
    default String getParameter(String name) {
        return getParameter(this.getParameterStrategy().getScope(), name);
    }

    /**
     * 获取当前参数
     *
     * @param scope 作用域(如果是机构参数，则是机构ID，如果是用户参数则是用户ID等)
     * @param name  参数键
     * @return 参数值
     */
    String getParameter(String scope, String name);

    /**
     * 获取最近的参数（逐级从当前参数依次向上查找）
     *
     * @param name 参数键
     * @return 参数值
     */
    default String getNearestParameter(String name) {
        return getNearestParameter(this.getParameterStrategy().getScope(), name);
    }

    /**
     * 获取最近的参数（逐级从当前参数依次向上查找）
     *
     * @param scope 作用域(如果是机构参数，则是机构ID，如果是用户参数则是用户ID等)
     * @param name  参数键
     * @return 参数值
     */
    String getNearestParameter(String scope, String name);

    /**
     * 获取当前上下文相关的参数,如果没有该参数则创建一个
     *
     * @param name         参数键
     * @param defaultValue 如果没有找到，则取这个默认值
     * @param description  参数描述
     * @return 参数值
     */
    default String getOrCreateParameter(String name, String defaultValue, String description) {
        return this.getOrCreateParameter(this.getParameterStrategy().getScope(), name, defaultValue, description);
    }

    /**
     * 获取当前上下文相关的参数,如果没有该参数则创建一个
     *
     * @param scope        作用域(如果是机构参数，则是机构ID，如果是用户参数则是用户ID等)
     * @param name         参数键
     * @param defaultValue 如果没有找到，则取这个默认值
     * @param description  参数描述
     * @return 参数值
     */
    String getOrCreateParameter(String scope, String name, String defaultValue, String description);

    /**
     * 获取参数策略
     *
     * @return 策略
     */
    IParameterStrategy getParameterStrategy();

}
