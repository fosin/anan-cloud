package top.fosin.anan.cloudresource.parameter;

import top.fosin.anan.cloudresource.service.AnanUserDetailService;
import top.fosin.anan.cloudresource.service.inter.ParameterFeignService;

/**
 * 服务参数
 *
 * @author fosin
 * @date 2021/5/2
 */
public class ServiceParameter extends RemoteParameter {
    public ServiceParameter(ParameterFeignService parameterService, AnanUserDetailService ananUserDetailService) {
        super(new ServiceStrategy(ananUserDetailService), parameterService);
    }
}
