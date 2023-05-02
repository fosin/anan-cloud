package top.fosin.anan.cloudresource.parameter;

import top.fosin.anan.cloudresource.service.CurrentUserService;

/**
 * 用户参数
 *
 * @author fosin
 * @date 2019/5/13
 */
public class UserStrategy implements IParameterStrategy {
    private final CurrentUserService currentUserService;

    public UserStrategy(CurrentUserService currentUserService) {
        this.currentUserService = currentUserService;
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
        return currentUserService.isSysAdminUser() ? null : currentUserService.getAnanUser().getId() + "";
    }
}
