package com.github.fosin.cdp.platform.service.inter;

import com.github.fosin.cdp.jpa.service.batch.ICrudBatchJpaService;
import com.github.fosin.cdp.platform.dto.request.CdpVersionRolePermissionCreateDto;
import com.github.fosin.cdp.platform.dto.request.CdpVersionRolePermissionRetrieveDto;
import com.github.fosin.cdp.platform.dto.request.CdpVersionRolePermissionUpdateDto;
import com.github.fosin.cdp.platform.entity.CdpVersionRolePermissionEntity;

import java.util.List;

/**
 * 系统版本角色权限表(cdp_version_role_permission)表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface ICdpVersionRolePermissionService extends ICrudBatchJpaService<CdpVersionRolePermissionEntity,
        Long, Long, CdpVersionRolePermissionCreateDto, CdpVersionRolePermissionRetrieveDto, CdpVersionRolePermissionUpdateDto> {
    List<CdpVersionRolePermissionEntity> findByRoleId(Long roleId);
}
