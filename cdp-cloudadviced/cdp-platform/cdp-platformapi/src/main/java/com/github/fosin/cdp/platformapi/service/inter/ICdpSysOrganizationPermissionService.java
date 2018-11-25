package com.github.fosin.cdp.platformapi.service.inter;

import com.github.fosin.cdp.mvc.service.ICrudBatchService;
import com.github.fosin.cdp.platformapi.entity.CdpSysOrganizationPermissionEntity;
import com.github.fosin.cdp.mvc.service.ISimpleService;
import com.github.fosin.cdp.platformapi.entity.CdpSysVersionPermissionEntity;

import java.util.Collection;
import java.util.List;

/**
 * 系统机构权限表(cdp_sys_organization_permission)表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface ICdpSysOrganizationPermissionService extends ICrudBatchService<CdpSysOrganizationPermissionEntity, Long> {
    List<CdpSysOrganizationPermissionEntity> findByOrganizId(Long organizId);

    long countByPermissionId(Long permissionId);

    List<CdpSysOrganizationPermissionEntity> updateInBatch(Long organizId, Collection<CdpSysOrganizationPermissionEntity> entities);
}