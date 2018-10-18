package com.starlight.cdp.platformapi.service.inter;

import com.starlight.cdp.core.exception.CdpServiceException;
import com.starlight.cdp.mvc.service.ISimpleService;
import com.starlight.cdp.platformapi.entity.CdpSysParameterEntity;

/**
 *  2017/12/29.
 * Time:12:30
 * @author fosin
 */
public interface IParameterService extends ISimpleService<CdpSysParameterEntity,Integer> {
    CdpSysParameterEntity findByTypeAndScopeAndName(Integer type, String scope, String name);

    boolean applyChange(Integer id) throws CdpServiceException;

    boolean applyChanges() throws CdpServiceException;
}
