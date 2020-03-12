package com.github.fosin.anan.platformapi.parameter;

import com.github.fosin.anan.platformapi.service.inter.ParameterFeignService;

/**
 * @author fosin
 * @date 2019/5/13
 */
public class UserParameter extends RemoteParameter {
    public UserParameter(ParameterFeignService parameterService) {
        super(new UserStrategy(), parameterService);
    }
}
