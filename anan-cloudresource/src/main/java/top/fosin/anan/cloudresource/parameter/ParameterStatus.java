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
    Normal((byte) 0),

    /**
     * 修改状态
     */
    Modified((byte) 1),

    /**
     * 删除状态
     */
    Deleted((byte) 2);

    private final byte type;

    ParameterStatus(byte type) {
        this.type = type;
    }

    public byte getTypeValue() {
        return type;
    }

    public static ParameterStatus valueOf(byte type) {    //手写的从byte到enum的转换函数
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
