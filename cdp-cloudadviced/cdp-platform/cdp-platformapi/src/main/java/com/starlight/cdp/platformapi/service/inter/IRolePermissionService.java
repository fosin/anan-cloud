package com.starlight.cdp.platformapi.service.inter;


import com.starlight.cdp.mvc.service.ICrudBatchService;
import com.starlight.cdp.platformapi.entity.CdpSysRolePermissionEntity;

import java.util.Collection;
import java.util.List;

/**
 *  2017/12/29.
 * Time:12:37
 * @author fosin
 */
public interface IRolePermissionService extends ICrudBatchService<CdpSysRolePermissionEntity,Integer> {
    List<CdpSysRolePermissionEntity> findByRoleId(Integer roleId);

    long countByPermissionId(Integer permissionId);

    List<CdpSysRolePermissionEntity> updateInBatch(Integer roleId, Collection<CdpSysRolePermissionEntity> entities);
}
