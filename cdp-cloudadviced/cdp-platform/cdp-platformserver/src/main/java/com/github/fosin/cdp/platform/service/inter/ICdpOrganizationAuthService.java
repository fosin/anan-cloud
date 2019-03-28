package com.github.fosin.cdp.platform.service.inter;

import com.github.fosin.cdp.jpa.service.ISimpleJpaService;
import com.github.fosin.cdp.platformapi.dto.request.CdpOrganizationAuthCreateDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpOrganizationAuthRetrieveDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpOrganizationAuthUpdateDto;
import com.github.fosin.cdp.platformapi.entity.CdpOrganizationAuthEntity;
import com.github.fosin.cdp.platformapi.dto.RegisterDto;

import java.util.List;

/**
 * 系统机构授权表(cdp_organization_auth)表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface ICdpOrganizationAuthService extends ISimpleJpaService<CdpOrganizationAuthEntity, Long, CdpOrganizationAuthCreateDto, CdpOrganizationAuthRetrieveDto, CdpOrganizationAuthUpdateDto> {
    List<CdpOrganizationAuthEntity> findAllByVersionId(Long versionId);

    List<CdpOrganizationAuthEntity> findAllByOrganizId(Long organizId);

    Boolean register(RegisterDto registerDto);
}
