package top.fosin.anan.platformapi.parameter;

import top.fosin.anan.platformapi.service.AnanUserDetailService;

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
