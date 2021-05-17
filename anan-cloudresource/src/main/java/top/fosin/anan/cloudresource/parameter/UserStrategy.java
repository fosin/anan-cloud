package top.fosin.anan.cloudresource.parameter;

import top.fosin.anan.cloudresource.service.AnanUserDetailService;

/**
 * 用户参数
 *
 * @author fosin
 * @date 2019/5/13
 */
public class UserStrategy implements IParameterStrategy {
    private final AnanUserDetailService ananUserDetailService;

    public UserStrategy(AnanUserDetailService ananUserDetailService) {
        this.ananUserDetailService = ananUserDetailService;
    }

    @Override
    public int getType() {
        return ParameterType.User.getTypeValue();
    }

    @Override
    public String getScope() {
        return ananUserDetailService.getAnanUserId() + "";
    }
}
