package com.github.fosin.cdp.platformapi.service.inter;

import com.github.fosin.cdp.platformapi.dto.request.CdpParameterCreateDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpParameterRetrieveDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpParameterUpdateDto;
import com.github.fosin.cdp.platformapi.entity.CdpParameterEntity;
import com.github.fosin.cdp.core.exception.CdpServiceException;
import com.github.fosin.cdp.jpa.service.ISimpleJpaService;

/**
 * 2017/12/29.
 * Time:12:30
 *
 * @author fosin
 */
public interface IParameterService extends ISimpleJpaService<CdpParameterEntity, Long, CdpParameterCreateDto, CdpParameterRetrieveDto, CdpParameterUpdateDto> {
    CdpParameterEntity findByTypeAndScopeAndName(Integer type, String scope, String name);

    boolean applyChange(Long id);

    boolean applyChanges();
}
