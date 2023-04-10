package top.fosin.anan.cloudresource.parameter;

import top.fosin.anan.cloudresource.service.UserInfoService;

/**
 * 用户参数
 *
 * @author fosin
 * @date 2019/5/13
 */
public class UserStrategy implements IParameterStrategy {
    private final UserInfoService userInfoService;

    public UserStrategy(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @Override
    public int getType() {
        return ParameterType.User.getTypeValue();
    }

    @Override
    public String getTypeName() {
        return ParameterType.User.getTypeName();
    }

    @Override
    public String getScope() {
        return userInfoService.isSysAdminUser() ? null : userInfoService.getAnanUser().getId() + "";
    }
}
