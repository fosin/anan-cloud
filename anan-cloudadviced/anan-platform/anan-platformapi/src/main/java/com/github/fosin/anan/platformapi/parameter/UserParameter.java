package com.github.fosin.anan.platformapi.parameter;

import com.github.fosin.anan.platformapi.service.inter.ParameterFeignService;
import com.github.fosin.anan.pojo.util.AnanUserDetailUtil;

/**
 * @author fosin
 * @date 2019/5/13
 */
public class UserParameter extends RemoteParameter {
    public UserParameter(ParameterFeignService parameterService, AnanUserDetailUtil ananUserDetailUtil) {
        super(new UserStrategy(ananUserDetailUtil), parameterService);
    }
}
