package com.github.fosin.cdp.platform.service.inter;

import com.github.fosin.cdp.jpa.service.batch.ICrudBatchJpaService;
import com.github.fosin.cdp.platform.dto.request.CdpSysVersionRolePermissionCreateDto;
import com.github.fosin.cdp.platform.dto.request.CdpSysVersionRolePermissionRetrieveDto;
import com.github.fosin.cdp.platform.dto.request.CdpSysVersionRolePermissionUpdateDto;
import com.github.fosin.cdp.platform.entity.CdpSysVersionRolePermissionEntity;

import java.util.List;

/**
 * 系统版本角色权限表(cdp_sys_version_role_permission)表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface ICdpSysVersionRolePermissionService extends ICrudBatchJpaService<CdpSysVersionRolePermissionEntity,
        Long, Long, CdpSysVersionRolePermissionCreateDto, CdpSysVersionRolePermissionRetrieveDto, CdpSysVersionRolePermissionUpdateDto> {
    List<CdpSysVersionRolePermissionEntity> findByRoleId(Long roleId);
}