package top.fosin.anan.platform.modules.pub.service.inter;

import top.fosin.anan.cloudresource.dto.req.ParameterReqDto;
import top.fosin.anan.cloudresource.dto.res.ParameterRespDto;
import top.fosin.anan.cloudresource.service.inter.base.ParameterBaseService;
import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platform.modules.pub.po.Parameter;

/**
 * @author fosin
 * @date 2017/12/29
 */
public interface ParameterService extends ISimpleJpaService<ParameterReqDto, ParameterRespDto, Long, Parameter>, ParameterBaseService {
}
