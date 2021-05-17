package top.fosin.anan.cloudresource.parameter;

import top.fosin.anan.cloudresource.service.AnanUserDetailService;

/**
 * 服务参数
 * @author fosin
 * @date 2021/5/2
 */
public class ServiceStrategy implements IParameterStrategy {
    private final AnanUserDetailService ananUserDetailService;

    public ServiceStrategy(AnanUserDetailService ananUserDetailService) {
        this.ananUserDetailService = ananUserDetailService;
    }

    @Override
    public int getType() {
        return ParameterType.Service.getTypeValue();
    }

    @Override
    public String getScope() {
        return ananUserDetailService.getAnanOrganizId() + "";
    }
}
