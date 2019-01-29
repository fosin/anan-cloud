package com.github.fosin.cdp.platform.service.inter;

import com.github.fosin.cdp.jpa.service.batch.ICrudBatchJpaService;
import com.github.fosin.cdp.platform.dto.request.CdpSysOrganizationPermissionCreateDto;
import com.github.fosin.cdp.platform.dto.request.CdpSysOrganizationPermissionRetrieveDto;
import com.github.fosin.cdp.platform.dto.request.CdpSysOrganizationPermissionUpdateDto;
import com.github.fosin.cdp.platform.entity.CdpSysOrganizationPermissionEntity;

import java.util.List;

/**
 * 系统机构权限表(cdp_sys_organization_permission)表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface ICdpSysOrganizationPermissionService extends
        ICrudBatchJpaService<CdpSysOrganizationPermissionEntity, Long, Long,
                CdpSysOrganizationPermissionCreateDto, CdpSysOrganizationPermissionRetrieveDto,
                CdpSysOrganizationPermissionUpdateDto> {
    List<CdpSysOrganizationPermissionEntity> findByOrganizId(Long organizId);

    long countByPermissionId(Long permissionId);

}