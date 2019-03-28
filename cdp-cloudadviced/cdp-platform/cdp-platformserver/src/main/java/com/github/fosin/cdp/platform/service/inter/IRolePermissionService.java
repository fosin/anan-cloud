package com.github.fosin.cdp.platform.service.inter;


import com.github.fosin.cdp.jpa.service.batch.ICrudBatchJpaService;
import com.github.fosin.cdp.platformapi.dto.request.CdpRolePermissionCreateDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpRolePermissionRetrieveDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpRolePermissionUpdateDto;
import com.github.fosin.cdp.platformapi.entity.CdpRolePermissionEntity;

import java.util.Collection;
import java.util.List;

/**
 * 2017/12/29.
 * Time:12:37
 *
 * @author fosin
 */
public interface IRolePermissionService extends ICrudBatchJpaService<CdpRolePermissionEntity, Long, Long, CdpRolePermissionCreateDto, CdpRolePermissionRetrieveDto, CdpRolePermissionUpdateDto> {
    List<CdpRolePermissionEntity> findByRoleId(Long roleId);

    long countByPermissionId(Long permissionId);
}
