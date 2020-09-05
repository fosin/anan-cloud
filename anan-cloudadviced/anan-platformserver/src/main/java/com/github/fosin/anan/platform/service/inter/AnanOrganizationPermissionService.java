package com.github.fosin.anan.platform.service.inter;

import com.github.fosin.anan.jpa.service.batch.ICrudBatchJpaService;
import com.github.fosin.anan.platform.dto.request.AnanOrganizationPermissionCreateDto;
import com.github.fosin.anan.platform.dto.request.AnanOrganizationPermissionRetrieveDto;
import com.github.fosin.anan.platform.dto.request.AnanOrganizationPermissionUpdateDto;
import com.github.fosin.anan.platform.entity.AnanOrganizationPermissionEntity;

import java.util.List;

/**
 * 系统机构权限表(anan_organization_permission)表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface AnanOrganizationPermissionService extends
        ICrudBatchJpaService<AnanOrganizationPermissionEntity, Long, Long,
                AnanOrganizationPermissionCreateDto, AnanOrganizationPermissionRetrieveDto,
                AnanOrganizationPermissionUpdateDto> {
    List<AnanOrganizationPermissionEntity> findByOrganizId(Long organizId);

    long countByPermissionId(Long permissionId);

}
