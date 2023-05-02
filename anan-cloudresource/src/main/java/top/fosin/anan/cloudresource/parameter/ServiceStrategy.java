package top.fosin.anan.cloudresource.parameter;

import top.fosin.anan.cloudresource.service.CurrentUserService;

/**
 * 服务参数
 *
 * @author fosin
 * @date 2021/5/2
 */
public class ServiceStrategy implements IParameterStrategy {
    private final CurrentUserService currentUserService;

    public ServiceStrategy(CurrentUserService currentUserService) {
        this.currentUserService = currentUserService;
    }

    @Override
    public int getType() {
        return ParameterType.Service.getTypeValue();
    }

    @Override
    public String getTypeName() {
        return ParameterType.Service.getTypeName();
    }

    @Override
    public String getScope() {
        return currentUserService.isSysAdminUser() ? null : currentUserService.getAnanOrganizId() + "";
    }
}
