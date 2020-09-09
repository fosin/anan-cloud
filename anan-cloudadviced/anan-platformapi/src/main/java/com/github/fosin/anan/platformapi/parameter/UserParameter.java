package com.github.fosin.anan.platformapi.parameter;

import com.github.fosin.anan.platformapi.service.inter.ParameterFeignService;
import com.github.fosin.anan.platformapi.service.AnanUserDetailService;

/**
 * @author fosin
 * @date 2019/5/13
 */
public class UserParameter extends RemoteParameter {
    public UserParameter(ParameterFeignService parameterService, AnanUserDetailService ananUserDetailService) {
        super(new UserStrategy(ananUserDetailService), parameterService);
    }
}
