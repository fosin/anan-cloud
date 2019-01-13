package com.github.fosin.cdp.platform.service.inter;

import com.github.fosin.cdp.jpa.service.ISimpleJpaService;
import com.github.fosin.cdp.platform.entity.CdpSysOrganizationAuthEntity;
import com.github.fosin.cdp.platformapi.dto.RegisterDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 系统机构授权表(cdp_sys_organization_auth)表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface ICdpSysOrganizationAuthService extends ISimpleJpaService<CdpSysOrganizationAuthEntity, Long, CdpSysOrganizationAuthEntity, CdpSysOrganizationAuthEntity, CdpSysOrganizationAuthEntity> {
    List<CdpSysOrganizationAuthEntity> findAllByVersionId(Long versionId);

    List<CdpSysOrganizationAuthEntity> findAllByOrganizId(Long organizId);

    @Transactional
    Boolean register(RegisterDto registerDto);
}