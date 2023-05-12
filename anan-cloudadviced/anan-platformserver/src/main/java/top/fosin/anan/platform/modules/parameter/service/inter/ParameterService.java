package top.fosin.anan.platform.modules.parameter.service.inter;

import top.fosin.anan.cloudresource.entity.req.ParameterReqDTO;
import top.fosin.anan.cloudresource.entity.res.ParameterRespDTO;
import top.fosin.anan.cloudresource.service.inter.base.ParameterBaseService;
import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platform.modules.parameter.po.Parameter;

/**
 * @author fosin
 * @date 2017/12/29
 */
public interface ParameterService extends ISimpleJpaService<ParameterReqDTO, ParameterRespDTO, Long, Parameter>, ParameterBaseService {
}
