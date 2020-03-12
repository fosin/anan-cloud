package com.github.fosin.anan.platformapi.parameter;

import com.github.fosin.anan.platformapi.service.inter.ParameterFeignService;

/**
 * @author fosin
 * @date 2019/5/13
 */
public class OrganParameter extends RemoteParameter {
    public OrganParameter(ParameterFeignService parameterService) {
        super(new OrganStrategy(), parameterService);
    }
}
