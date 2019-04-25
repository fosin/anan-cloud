package com.github.fosin.anan.platform.service.inter;

import com.github.fosin.anan.jpa.service.ISimpleJpaService;
import com.github.fosin.anan.platform.dto.request.AnanOrganizationAuthCreateDto;
import com.github.fosin.anan.platform.dto.request.AnanOrganizationAuthRetrieveDto;
import com.github.fosin.anan.platform.dto.request.AnanOrganizationAuthUpdateDto;
import com.github.fosin.anan.platform.entity.AnanOrganizationAuthEntity;
import com.github.fosin.anan.platformapi.dto.RegisterDto;

import java.util.List;

/**
 * 系统机构授权表(anan_organization_auth)表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface AnanOrganizationAuthService extends ISimpleJpaService<AnanOrganizationAuthEntity, Long, AnanOrganizationAuthCreateDto, AnanOrganizationAuthRetrieveDto, AnanOrganizationAuthUpdateDto> {
    List<AnanOrganizationAuthEntity> findAllByVersionId(Long versionId);

    List<AnanOrganizationAuthEntity> findAllByOrganizId(Long organizId);

    Boolean register(RegisterDto registerDto);
}
