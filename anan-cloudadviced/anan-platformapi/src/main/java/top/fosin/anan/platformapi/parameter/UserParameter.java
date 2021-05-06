package top.fosin.anan.platformapi.parameter;

import top.fosin.anan.platformapi.service.inter.ParameterFeignService;
import top.fosin.anan.platformapi.service.AnanUserDetailService;

/**
 * 用户参数
 *
 * @author fosin
 * @date 2019/5/13
 */
public class UserParameter extends RemoteParameter {
    public UserParameter(ParameterFeignService parameterService, AnanUserDetailService ananUserDetailService) {
        super(new UserStrategy(ananUserDetailService), parameterService);
    }
}
