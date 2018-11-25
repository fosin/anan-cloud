package com.github.fosin.cdp.platformapi.service.inter;

import com.github.fosin.cdp.mvc.service.ICrudBatchService;
import com.github.fosin.cdp.platformapi.entity.CdpSysRolePermissionEntity;
import com.github.fosin.cdp.platformapi.entity.CdpSysVersionPermissionEntity;
import com.github.fosin.cdp.mvc.service.ISimpleService;

import java.util.Collection;
import java.util.List;

/**
 * 系统版本权限表(cdp_sys_version_permission)表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface ICdpSysVersionPermissionService extends ICrudBatchService<CdpSysVersionPermissionEntity, Long> {
    List<CdpSysVersionPermissionEntity> findByVersionId(Long versionId);

    long countByPermissionId(Long permissionId);

    List<CdpSysVersionPermissionEntity> updateInBatch(Long versionId, Collection<CdpSysVersionPermissionEntity> entities);

}