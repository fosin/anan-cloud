package top.fosin.anan.cloudresource.type;

public enum RpcCallStrategy {
    /**
     * 使用GRPC服务
     */
    GRPC(1),

    /**
     * 使用Feign服务
     */
    FEIGN(2);

    private final int type;

    RpcCallStrategy(int type) {
        this.type = type;
    }

    public int getTypeValue() {
        return type;
    }
}
