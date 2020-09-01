package com.github.fosin.anan.platformapi.parameter;

import com.github.fosin.anan.platformapi.service.inter.ParameterFeignService;
import com.github.fosin.anan.cloudresource.util.AnanUserDetailUtil;

/**
 * @author fosin
 * @date 2019/5/13
 */
public class OrganParameter extends RemoteParameter {
    public OrganParameter(ParameterFeignService parameterService, AnanUserDetailUtil ananUserDetailUtil) {
        super(new OrganStrategy(ananUserDetailUtil), parameterService);
    }
}
