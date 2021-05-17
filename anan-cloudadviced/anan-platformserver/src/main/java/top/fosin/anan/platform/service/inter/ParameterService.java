package top.fosin.anan.platform.service.inter;

import top.fosin.anan.cloudresource.dto.req.AnanParameterCreateDto;
import top.fosin.anan.cloudresource.dto.req.AnanParameterRetrieveDto;
import top.fosin.anan.cloudresource.dto.req.AnanParameterUpdateDto;
import top.fosin.anan.cloudresource.dto.res.AnanParameterRespDto;
import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platform.entity.AnanParameterEntity;

/**
 * 2017/12/29.
 * Time:12:30
 *
 * @author fosin
 */
public interface ParameterService extends ISimpleJpaService<AnanParameterEntity, AnanParameterRespDto,
        Long, AnanParameterCreateDto, AnanParameterRetrieveDto, AnanParameterUpdateDto> {
    AnanParameterRespDto getParameter(Integer type, String scope, String name);

    AnanParameterRespDto getNearestParameter(int type, String scope, String name);

    AnanParameterRespDto getOrCreateParameter(int type, String scope, String name, String defaultValue, String description);

    Boolean applyChange(Long id);

    Boolean applyChanges();
}
