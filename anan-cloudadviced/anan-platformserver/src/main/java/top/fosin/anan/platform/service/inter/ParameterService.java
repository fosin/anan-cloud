package top.fosin.anan.platform.service.inter;

import java.util.Collection;
import java.util.List;

import top.fosin.anan.cloudresource.dto.req.AnanParameterReqDto;
import top.fosin.anan.cloudresource.dto.req.AnanParameterReqDto;
import top.fosin.anan.cloudresource.dto.req.AnanParameterReqDto;
import top.fosin.anan.cloudresource.dto.res.AnanParameterRespDto;
import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platform.entity.AnanParameterEntity;

/**
 * @author fosin
 * @date 2017/12/29
 *
 */
public interface ParameterService extends ISimpleJpaService<AnanParameterEntity, AnanParameterRespDto,
        Long, AnanParameterReqDto, AnanParameterReqDto, AnanParameterReqDto> {
    void cancelDelete(Collection<Long> ids);

    AnanParameterRespDto getParameter(Integer type, String scope, String name);

    AnanParameterRespDto getNearestParameter(int type, String scope, String name);

    AnanParameterRespDto getOrCreateParameter(int type, String scope, String name, String defaultValue, String description);

    Boolean applyChange(Long id);

    Boolean applyChangeAll();

    Boolean applyChanges(List<Long> ids);
}
