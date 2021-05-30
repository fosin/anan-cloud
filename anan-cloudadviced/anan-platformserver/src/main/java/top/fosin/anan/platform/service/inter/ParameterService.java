package top.fosin.anan.platform.service.inter;

import top.fosin.anan.cloudresource.dto.req.AnanParameterCreateDto;
import top.fosin.anan.cloudresource.dto.req.AnanParameterRetrieveDto;
import top.fosin.anan.cloudresource.dto.req.AnanParameterUpdateDto;
import top.fosin.anan.cloudresource.dto.res.AnanParameterRespDto;
import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.jpa.service.IStatusJpaService;
import top.fosin.anan.model.dto.StatusDto;
import top.fosin.anan.platform.entity.AnanParameterEntity;

/**
 * @author fosin
 * @date 2017/12/29
 *
 */
public interface ParameterService extends ISimpleJpaService<AnanParameterEntity, AnanParameterRespDto,
        Long, AnanParameterCreateDto, AnanParameterRetrieveDto, AnanParameterUpdateDto>,
        IStatusJpaService<AnanParameterEntity, Long, Integer, StatusDto<Long, Integer>> {
    AnanParameterRespDto getParameter(Integer type, String scope, String name);

    AnanParameterRespDto getNearestParameter(int type, String scope, String name);

    AnanParameterRespDto getOrCreateParameter(int type, String scope, String name, String defaultValue, String description);

    Boolean applyChange(Long id);

    Boolean applyChanges();
}
