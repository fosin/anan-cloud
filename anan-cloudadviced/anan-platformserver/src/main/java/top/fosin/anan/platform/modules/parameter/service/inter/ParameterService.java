package top.fosin.anan.platform.modules.parameter.service.inter;

import top.fosin.anan.cloudresource.entity.req.ParameterCreateDTO;
import top.fosin.anan.cloudresource.entity.res.ParameterDTO;
import top.fosin.anan.cloudresource.entity.req.ParameterUpdateDTO;
import top.fosin.anan.cloudresource.service.inter.base.ParameterBaseService;
import top.fosin.anan.jpa.service.ICreateJpaService;
import top.fosin.anan.jpa.service.IDeleteJpaService;
import top.fosin.anan.jpa.service.IRetrieveJpaService;
import top.fosin.anan.jpa.service.IUpdateJpaService;
import top.fosin.anan.platform.modules.parameter.po.Parameter;

/**
 * @author fosin
 * @date 2017/12/29
 */
public interface ParameterService extends
        ICreateJpaService<ParameterCreateDTO, ParameterDTO, Long, Parameter>,
        IRetrieveJpaService<ParameterDTO, Long, Parameter>,
        IUpdateJpaService<ParameterUpdateDTO, Long, Parameter>,
        IDeleteJpaService<Long, Parameter>, ParameterBaseService {
}
