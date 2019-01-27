package com.github.fosin.cdp.platformapi.service.inter;


import com.github.fosin.cdp.platformapi.dto.request.CdpSysUserPermissionCreateDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpSysUserPermissionRetrieveDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpSysUserPermissionUpdateDto;
import com.github.fosin.cdp.platformapi.entity.CdpSysUserPermissionEntity;
import com.github.fosin.cdp.jpa.service.batch.ICrudBatchJpaService;

import java.util.List;

/**
 * 2017/12/29.
 * Time:12:37
 *
 * @author fosin
 */
public interface IUserPermissionService extends ICrudBatchJpaService<CdpSysUserPermissionEntity, Long, Long, CdpSysUserPermissionCreateDto, CdpSysUserPermissionRetrieveDto, CdpSysUserPermissionUpdateDto> {
    List<CdpSysUserPermissionEntity> findByUserIdAndOrganizId(Long userId, Long organizId);

    List<CdpSysUserPermissionEntity> findByUserId(Long userId);

    long countByPermissionId(Long permissionId);
}
