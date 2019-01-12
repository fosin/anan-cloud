package com.github.fosin.cdp.platformapi.service.inter;


import com.github.fosin.cdp.platformapi.dto.RegisterDto;
import com.github.fosin.cdp.platformapi.entity.CdpSysOrganizationEntity;
import com.github.fosin.cdp.core.exception.CdpServiceException;
import com.github.fosin.cdp.jpa.service.ISimpleJpaService;

import java.util.List;

/**
 * 2017/12/29.
 * Time:12:37
 *
 * @author fosin
 */
public interface IOrganizationService extends ISimpleJpaService<CdpSysOrganizationEntity, Long, CdpSysOrganizationEntity, CdpSysOrganizationEntity, CdpSysOrganizationEntity> {
    List<CdpSysOrganizationEntity> findAllByTopId(Long topId);

    List<CdpSysOrganizationEntity> findByPid(Long pid) throws CdpServiceException;

    List<CdpSysOrganizationEntity> findByCodeStartingWith(String code) throws CdpServiceException;

    Boolean register(RegisterDto registerDto);
}

