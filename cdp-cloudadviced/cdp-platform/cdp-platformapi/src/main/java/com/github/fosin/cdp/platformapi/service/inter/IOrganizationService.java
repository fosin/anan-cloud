package com.github.fosin.cdp.platformapi.service.inter;


import com.github.fosin.cdp.platformapi.dto.RegisterDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpOrganizationCreateDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpOrganizationRetrieveDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpOrganizationUpdateDto;
import com.github.fosin.cdp.platformapi.entity.CdpOrganizationEntity;
import com.github.fosin.cdp.core.exception.CdpServiceException;
import com.github.fosin.cdp.jpa.service.ISimpleJpaService;

import java.util.List;

/**
 * 2017/12/29.
 * Time:12:37
 *
 * @author fosin
 */
public interface IOrganizationService extends ISimpleJpaService<CdpOrganizationEntity, Long, CdpOrganizationCreateDto, CdpOrganizationRetrieveDto, CdpOrganizationUpdateDto> {
    List<CdpOrganizationEntity> findAllByTopId(Long topId);

    List<CdpOrganizationEntity> findByPid(Long pid);

    List<CdpOrganizationEntity> findByCodeStartingWith(String code);
}

