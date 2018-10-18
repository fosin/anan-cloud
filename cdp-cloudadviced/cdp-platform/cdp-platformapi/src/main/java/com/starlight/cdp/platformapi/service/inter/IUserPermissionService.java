package com.starlight.cdp.platformapi.service.inter;


import com.starlight.cdp.mvc.service.ICrudBatchService;
import com.starlight.cdp.platformapi.entity.CdpSysUserPermissionEntity;

import java.util.List;

/**
 *  2017/12/29.
 * Time:12:37
 * @author fosin
 */
public interface IUserPermissionService extends ICrudBatchService<CdpSysUserPermissionEntity,Integer> {
    List<CdpSysUserPermissionEntity> findByUserIdAndOrganizId(Integer userId, Integer organizId);

    List<CdpSysUserPermissionEntity> findByUserId(Integer userId);

    long countByPermissionId(Integer permissionId);

    List<CdpSysUserPermissionEntity> updateInBatch(Integer userId, Iterable<CdpSysUserPermissionEntity> entities);
}
