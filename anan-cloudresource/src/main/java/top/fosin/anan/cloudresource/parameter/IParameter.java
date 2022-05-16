package top.fosin.anan.cloudresource.parameter;

import cn.hutool.core.util.NumberUtil;
import org.springframework.util.Assert;
import top.fosin.anan.cloudresource.dto.res.ParameterRespDto;
import top.fosin.anan.core.util.DateTimeUtil;

import java.math.BigDecimal;
import java.util.Date;

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
    default ParameterRespDto setParameter(String name, String value, String description) {
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
    ParameterRespDto setParameter(String scope, String name, String value, String description);

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
     * @param name         参数键
     * @param defaultValue 如果没有找到，则取这个默认值
     * @param description  参数描述
     * @return int类型参数值
     */
    default int getOrCreateParameter(String name, int defaultValue, String description) {
        String value = this.getOrCreateParameter(this.getParameterStrategy().getScope(), name, String.valueOf(defaultValue), description);
        Assert.isTrue(NumberUtil.isInteger(value), this.getParameterStrategy().getTypeName()
                + "【" + name + "】不是int类型，请检查！");
        return Integer.parseInt(value);
    }

    /**
     * 获取当前上下文相关的参数,如果没有该参数则创建一个
     *
     * @param name         参数键
     * @param defaultValue 如果没有找到，则取这个默认值
     * @param description  参数描述
     * @return long类型参数值
     */
    default long getOrCreateParameter(String name, long defaultValue, String description) {
        String value = this.getOrCreateParameter(this.getParameterStrategy().getScope(), name, String.valueOf(defaultValue), description);
        Assert.isTrue(NumberUtil.isLong(value), this.getParameterStrategy().getTypeName()
                + "【" + name + "】不是long类型，请检查！");
        return Long.parseLong(value);
    }

    /**
     * 获取当前上下文相关的参数,如果没有该参数则创建一个
     *
     * @param name         参数键
     * @param defaultValue 如果没有找到，则取这个默认值
     * @param description  参数描述
     * @return double类型参数值
     */
    default double getOrCreateParameter(String name, double defaultValue, String description) {
        String value = this.getOrCreateParameter(this.getParameterStrategy().getScope(), name, String.valueOf(defaultValue), description);
        Assert.isTrue(NumberUtil.isDouble(value), this.getParameterStrategy().getTypeName()
                + "【" + name + "】不是double类型，请检查！");
        return Double.parseDouble(value);
    }

    /**
     * 获取当前上下文相关的参数,如果没有该参数则创建一个
     *
     * @param name         参数键
     * @param defaultValue 如果没有找到，则取这个默认值
     * @param description  参数描述
     * @return float类型参数值
     */
    default float getOrCreateParameter(String name, float defaultValue, String description) {
        String value = this.getOrCreateParameter(this.getParameterStrategy().getScope(), name, String.valueOf(defaultValue), description);
        Assert.isTrue(NumberUtil.isNumber(value), this.getParameterStrategy().getTypeName()
                + "【" + name + "】不是float类型，请检查！");
        return Float.parseFloat(value);
    }

    /**
     * 获取当前上下文相关的参数,如果没有该参数则创建一个
     *
     * @param name         参数键
     * @param defaultValue 如果没有找到，则取这个默认值
     * @param description  参数描述
     * @return BigDecimal类型参数值
     */
    default BigDecimal getOrCreateParameter(String name, BigDecimal defaultValue, String description) {
        String value = this.getOrCreateParameter(this.getParameterStrategy().getScope(), name, String.valueOf(defaultValue), description);
        Assert.isTrue(NumberUtil.isNumber(value), this.getParameterStrategy().getTypeName()
                + "【" + name + "】不是BigDecimal类型，请检查！");
        return NumberUtil.toBigDecimal(value);
    }

    /**
     * 获取当前上下文相关的参数,如果没有该参数则创建一个
     *
     * @param name         参数键
     * @param defaultValue 如果没有找到，则取这个默认值
     * @param description  参数描述
     * @return date类型参数值
     */
    default Date getOrCreateParameter(String name, Date defaultValue, String description) {
        String value = this.getOrCreateParameter(this.getParameterStrategy().getScope(), name, defaultValue.toString(),
                description);
        Assert.isTrue(DateTimeUtil.isValidDate(value), this.getParameterStrategy().getTypeName()
                + "【" + name + "】不是Date类型，请检查！");
        return DateTimeUtil.dateOf(value);
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
