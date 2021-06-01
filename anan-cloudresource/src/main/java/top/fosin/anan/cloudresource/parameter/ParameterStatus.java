package top.fosin.anan.cloudresource.parameter;

/**
 * 参数状态
 *
 * @author fosin
 * @date 2021-5-31
 */
public enum ParameterStatus {

    /**
     * 正常状态
     */
    Normal(0),

    /**
     * 修改状态
     */
    Modified(1),

    /**
     * 删除状态
     */
    Deleted(2);

    private final int type;

    ParameterStatus(int type) {
        this.type = type;
    }

    public int getTypeValue() {
        return type;
    }

    public static ParameterStatus valueOf(int type) {    //手写的从int到enum的转换函数
        switch (type) {
            case 0:
                return Normal;
            case 1:
                return Modified;
            case 2:
                return Deleted;
            default:
                return null;
        }
    }
}
