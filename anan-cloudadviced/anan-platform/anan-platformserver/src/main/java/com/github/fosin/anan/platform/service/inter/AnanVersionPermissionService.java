package com.github.fosin.anan.platform.service.inter;


import com.github.fosin.anan.jpa.service.batch.ICrudBatchJpaService;
import com.github.fosin.anan.platform.dto.request.AnanVersionPermissionCreateDto;
import com.github.fosin.anan.platform.dto.request.AnanVersionPermissionRetrieveDto;
import com.github.fosin.anan.platform.dto.request.AnanVersionPermissionUpdateDto;
import com.github.fosin.anan.platform.entity.AnanVersionPermissionEntity;

import java.util.List;

/**
 * 系统版本权限表(anan_version_permission)表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface AnanVersionPermissionService extends ICrudBatchJpaService<AnanVersionPermissionEntity,
        Long, Long, AnanVersionPermissionCreateDto, AnanVersionPermissionRetrieveDto, AnanVersionPermissionUpdateDto> {
    List<AnanVersionPermissionEntity> findByVersionId(Long versionId);

    long countByPermissionId(Long permissionId);

}
