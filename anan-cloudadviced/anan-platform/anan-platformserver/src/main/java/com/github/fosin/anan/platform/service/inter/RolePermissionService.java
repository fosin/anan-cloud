package com.github.fosin.anan.platform.service.inter;


import com.github.fosin.anan.jpa.service.batch.ICrudBatchJpaService;
import com.github.fosin.anan.pojo.dto.request.AnanRolePermissionCreateDto;
import com.github.fosin.anan.pojo.dto.request.AnanRolePermissionRetrieveDto;
import com.github.fosin.anan.pojo.dto.request.AnanRolePermissionUpdateDto;
import com.github.fosin.anan.platformapi.entity.AnanRolePermissionEntity;

import java.util.List;

/**
 * 2017/12/29.
 * Time:12:37
 *
 * @author fosin
 */
public interface RolePermissionService extends ICrudBatchJpaService<AnanRolePermissionEntity, Long, Long, AnanRolePermissionCreateDto, AnanRolePermissionRetrieveDto, AnanRolePermissionUpdateDto> {
    List<AnanRolePermissionEntity> findByRoleId(Long roleId);

    long countByPermissionId(Long permissionId);
}
