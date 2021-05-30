package top.fosin.anan.cloudresource.parameter;

import top.fosin.anan.cloudresource.service.AnanUserDetailService;
import top.fosin.anan.cloudresource.service.inter.ParameterFeignService;

/**
 * 机构参数
 *
 * @author fosin
 * @date 2019/5/13
 */
public class OrganParameter extends RemoteParameter {
    public OrganParameter(ParameterFeignService parameterService, AnanUserDetailService ananUserDetailService) {
        super(new OrganStrategy(ananUserDetailService), parameterService);
    }
}
