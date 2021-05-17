package top.fosin.anan.cloudresource.parameter;

import top.fosin.anan.cloudresource.service.inter.ParameterFeignService;
import top.fosin.anan.cloudresource.service.AnanUserDetailService;

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
