package com.github.fosin.cdp.platformapi.service.inter;


import com.github.fosin.cdp.jpa.service.batch.ICrudBatchJpaService;
import com.github.fosin.cdp.platformapi.entity.CdpSysVersionPermissionEntity;

import java.util.List;

/**
 * 系统版本权限表(cdp_sys_version_permission)表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface ICdpSysVersionPermissionService extends ICrudBatchJpaService<CdpSysVersionPermissionEntity, Long, Long> {
    List<CdpSysVersionPermissionEntity> findByVersionId(Long versionId);

    long countByPermissionId(Long permissionId);

}