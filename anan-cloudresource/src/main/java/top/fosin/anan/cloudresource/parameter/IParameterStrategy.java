package top.fosin.anan.cloudresource.parameter;

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
    byte getType();

    /**
     * 获取参数类型解释
     *
     * @return 参数类型解释
     */
    String getTypeName();

    /**
     * 获取参数作用域
     *
     * @return 作用域
     */
    String getScope();
}
