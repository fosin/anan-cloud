package com.github.fosin.cdp.platform.service.inter;

import com.github.fosin.cdp.jpa.service.batch.ICrudBatchJpaService;
import com.github.fosin.cdp.platform.dto.request.CdpOrganizationPermissionCreateDto;
import com.github.fosin.cdp.platform.dto.request.CdpOrganizationPermissionRetrieveDto;
import com.github.fosin.cdp.platform.dto.request.CdpOrganizationPermissionUpdateDto;
import com.github.fosin.cdp.platform.entity.CdpOrganizationPermissionEntity;

import java.util.List;

/**
 * 系统机构权限表(cdp_organization_permission)表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface ICdpOrganizationPermissionService extends
        ICrudBatchJpaService<CdpOrganizationPermissionEntity, Long, Long,
                CdpOrganizationPermissionCreateDto, CdpOrganizationPermissionRetrieveDto,
                CdpOrganizationPermissionUpdateDto> {
    List<CdpOrganizationPermissionEntity> findByOrganizId(Long organizId);

    long countByPermissionId(Long permissionId);

}
