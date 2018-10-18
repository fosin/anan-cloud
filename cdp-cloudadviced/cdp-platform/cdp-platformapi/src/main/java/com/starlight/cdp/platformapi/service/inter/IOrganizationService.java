package com.starlight.cdp.platformapi.service.inter;


import com.starlight.cdp.core.exception.CdpServiceException;
import com.starlight.cdp.mvc.service.ISimpleService;
import com.starlight.cdp.platformapi.entity.CdpSysOrganizationEntity;

import java.util.List;

/**
 *  2017/12/29.
 * Time:12:37
 * @author fosin
 */
public interface IOrganizationService extends ISimpleService<CdpSysOrganizationEntity,Integer> {
    List<CdpSysOrganizationEntity> findByPid(Integer pid) throws CdpServiceException;

    List<CdpSysOrganizationEntity> findByCodeStartingWith(String code) throws CdpServiceException;
}

