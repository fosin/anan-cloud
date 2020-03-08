package com.github.fosin.anan.platform.service.inter;


import com.github.fosin.anan.jpa.service.ISimpleJpaService;
import com.github.fosin.anan.pojo.dto.request.AnanOrganizationCreateDto;
import com.github.fosin.anan.pojo.dto.request.AnanOrganizationRetrieveDto;
import com.github.fosin.anan.pojo.dto.request.AnanOrganizationUpdateDto;
import com.github.fosin.anan.platformapi.entity.AnanOrganizationEntity;

import java.util.List;

/**
 * 2017/12/29.
 * Time:12:37
 *
 * @author fosin
 */
public interface OrganizationService extends ISimpleJpaService<AnanOrganizationEntity, Long, AnanOrganizationCreateDto, AnanOrganizationRetrieveDto, AnanOrganizationUpdateDto> {
    List<AnanOrganizationEntity> findAllByTopId(Long topId);

    List<AnanOrganizationEntity> findByPid(Long pid);

    List<AnanOrganizationEntity> findByCodeStartingWith(String code);
}

