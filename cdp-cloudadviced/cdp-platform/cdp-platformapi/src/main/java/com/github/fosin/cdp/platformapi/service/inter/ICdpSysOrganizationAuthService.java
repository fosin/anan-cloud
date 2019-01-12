package com.github.fosin.cdp.platformapi.service.inter;

import com.github.fosin.cdp.jpa.service.ISimpleJpaService;
import com.github.fosin.cdp.platformapi.entity.CdpSysOrganizationAuthEntity;

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
}