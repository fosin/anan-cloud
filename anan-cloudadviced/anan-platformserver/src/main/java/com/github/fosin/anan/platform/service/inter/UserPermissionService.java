package com.github.fosin.anan.platform.service.inter;


import com.github.fosin.anan.cloudresource.dto.request.AnanUserPermissionCreateDto;
import com.github.fosin.anan.cloudresource.dto.request.AnanUserPermissionRetrieveDto;
import com.github.fosin.anan.cloudresource.dto.request.AnanUserPermissionUpdateDto;
import com.github.fosin.anan.platformapi.entity.AnanUserPermissionEntity;
import com.github.fosin.anan.jpa.service.batch.ICrudBatchJpaService;

import java.util.List;

/**
 * 2017/12/29.
 * Time:12:37
 *
 * @author fosin
 */
public interface UserPermissionService extends ICrudBatchJpaService<AnanUserPermissionEntity, Long, Long, AnanUserPermissionCreateDto, AnanUserPermissionRetrieveDto, AnanUserPermissionUpdateDto> {
    List<AnanUserPermissionEntity> findByUserIdAndOrganizId(Long userId, Long organizId);

    List<AnanUserPermissionEntity> findByUserId(Long userId);

    long countByPermissionId(Long permissionId);
}
