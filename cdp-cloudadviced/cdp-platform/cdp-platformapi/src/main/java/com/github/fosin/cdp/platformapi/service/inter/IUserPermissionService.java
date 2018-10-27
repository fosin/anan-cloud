package com.github.fosin.cdp.platformapi.service.inter;


import com.github.fosin.cdp.platformapi.entity.CdpSysUserPermissionEntity;
import com.github.fosin.cdp.mvc.service.ICrudBatchService;

import java.util.List;

/**
 *  2017/12/29.
 * Time:12:37
 * @author fosin
 */
public interface IUserPermissionService extends ICrudBatchService<CdpSysUserPermissionEntity,Integer> {
    List<CdpSysUserPermissionEntity> findByUserIdAndOrganizId(Long userId, Long organizId);

    List<CdpSysUserPermissionEntity> findByUserId(Long userId);

    long countByPermissionId(Long permissionId);

    List<CdpSysUserPermissionEntity> updateInBatch(Long userId, Iterable<CdpSysUserPermissionEntity> entities);
}
