package com.github.fosin.cdp.platformapi.parameter;

/**
 * 数据库参数类型
 *
 * @author fosin
 * @date 2019/3/28
 */
public enum ParameterType {

    /**
     * 机构参数
     */
    Organization(1),

    /**
     * 用户参数
     */
    User(2);

    private final int type;

    ParameterType(int type) {
        this.type = type;
    }

    public int getTypeValue() {
        return type;
    }

}
