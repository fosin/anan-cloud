package top.fosin.anan.platform.service.inter;

import top.fosin.anan.cloudresource.dto.request.AnanParameterCreateDto;
import top.fosin.anan.cloudresource.dto.request.AnanParameterRetrieveDto;
import top.fosin.anan.cloudresource.dto.request.AnanParameterUpdateDto;
import top.fosin.anan.platformapi.entity.AnanParameterEntity;
import top.fosin.anan.jpa.service.ISimpleJpaService;

/**
 * 2017/12/29.
 * Time:12:30
 *
 * @author fosin
 */
public interface ParameterService extends ISimpleJpaService<AnanParameterEntity, Long, AnanParameterCreateDto, AnanParameterRetrieveDto, AnanParameterUpdateDto> {
    AnanParameterEntity getParameter(Integer type, String scope, String name);

    AnanParameterEntity getNearestParameter(int type, String scope, String name);

    AnanParameterEntity getOrCreateParameter(int type, String scope, String name, String defaultValue, String description);

    Boolean applyChange(Long id);

    Boolean applyChanges();
}
