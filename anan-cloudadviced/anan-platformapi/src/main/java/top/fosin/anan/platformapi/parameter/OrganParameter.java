package top.fosin.anan.platformapi.parameter;

import top.fosin.anan.platformapi.service.inter.ParameterFeignService;
import top.fosin.anan.platformapi.service.AnanUserDetailService;

/**
 * @author fosin
 * @date 2019/5/13
 */
public class OrganParameter extends RemoteParameter {
    public OrganParameter(ParameterFeignService parameterService, AnanUserDetailService ananUserDetailService) {
        super(new OrganStrategy(ananUserDetailService), parameterService);
    }
}
