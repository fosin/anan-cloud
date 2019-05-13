package com.github.fosin.anan.platformapi.parameter;

/**
 * 参数策略
 *
 * @author fosin
 * @date 2019/5/13
 */
public interface IParameterStrategy {
    /**
     * 获取参数类型
     *
     * @return 参数类型
     */
    int getType();

    /**
     * 获取参数作用域
     *
     * @return 作用域
     */
    String getScope();
}
