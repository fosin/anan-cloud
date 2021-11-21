package top.fosin.anan.cloudresource.parameter;

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
    User(2),

    /**
     * 服务参数
     */
    Service(3);

    private final int type;

    ParameterType(int type) {
        this.type = type;
    }

    public int getTypeValue() {
        return type;
    }

    public String getTypeName() {
        switch (type) {
            case 1:
                return "机构参数";
            case 2:
                return "用户参数";
            case 3:
                return "服务参数";
            default:
                return "";
        }
    }
}
