package com.github.fosin.anan.platform.service.inter;

import com.github.fosin.anan.pojo.dto.request.AnanParameterCreateDto;
import com.github.fosin.anan.pojo.dto.request.AnanParameterRetrieveDto;
import com.github.fosin.anan.pojo.dto.request.AnanParameterUpdateDto;
import com.github.fosin.anan.platformapi.entity.AnanParameterEntity;
import com.github.fosin.anan.jpa.service.ISimpleJpaService;

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
