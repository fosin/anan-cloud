package top.fosin.anan.cloudresource.parameter;

import top.fosin.anan.cloudresource.service.UserInfoService;

/**
 * 服务参数
 *
 * @author fosin
 * @date 2021/5/2
 */
public class ServiceStrategy implements IParameterStrategy {
    private final UserInfoService userInfoService;

    public ServiceStrategy(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
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
        return userInfoService.isSysAdminUser() ? null : userInfoService.getAnanOrganizId() + "";
    }
}
