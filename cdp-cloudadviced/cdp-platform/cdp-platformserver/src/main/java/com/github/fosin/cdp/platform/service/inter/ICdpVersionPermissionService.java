package com.github.fosin.cdp.platform.service.inter;


import com.github.fosin.cdp.jpa.service.batch.ICrudBatchJpaService;
import com.github.fosin.cdp.platform.dto.request.CdpVersionPermissionCreateDto;
import com.github.fosin.cdp.platform.dto.request.CdpVersionPermissionRetrieveDto;
import com.github.fosin.cdp.platform.dto.request.CdpVersionPermissionUpdateDto;
import com.github.fosin.cdp.platform.entity.CdpVersionPermissionEntity;

import java.util.List;

/**
 * 系统版本权限表(cdp_version_permission)表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface ICdpVersionPermissionService extends ICrudBatchJpaService<CdpVersionPermissionEntity,
        Long, Long, CdpVersionPermissionCreateDto, CdpVersionPermissionRetrieveDto, CdpVersionPermissionUpdateDto> {
    List<CdpVersionPermissionEntity> findByVersionId(Long versionId);

    long countByPermissionId(Long permissionId);

}
