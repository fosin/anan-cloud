package com.github.fosin.cdp.platformapi.service.inter;

import com.github.fosin.cdp.platformapi.dto.request.CdpSysParameterCreateDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpSysParameterRetrieveDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpSysParameterUpdateDto;
import com.github.fosin.cdp.platformapi.entity.CdpSysParameterEntity;
import com.github.fosin.cdp.core.exception.CdpServiceException;
import com.github.fosin.cdp.jpa.service.ISimpleJpaService;

/**
 * 2017/12/29.
 * Time:12:30
 *
 * @author fosin
 */
public interface IParameterService extends ISimpleJpaService<CdpSysParameterEntity, Long, CdpSysParameterCreateDto, CdpSysParameterRetrieveDto, CdpSysParameterUpdateDto> {
    CdpSysParameterEntity findByTypeAndScopeAndName(Integer type, String scope, String name);

    boolean applyChange(Long id);

    boolean applyChanges();
}
