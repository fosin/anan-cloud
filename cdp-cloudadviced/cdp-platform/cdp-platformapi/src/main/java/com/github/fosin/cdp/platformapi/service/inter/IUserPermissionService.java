package com.github.fosin.cdp.platformapi.service.inter;


import com.github.fosin.cdp.platformapi.dto.request.CdpUserPermissionCreateDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpUserPermissionRetrieveDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpUserPermissionUpdateDto;
import com.github.fosin.cdp.platformapi.entity.CdpUserPermissionEntity;
import com.github.fosin.cdp.jpa.service.batch.ICrudBatchJpaService;

import java.util.List;

/**
 * 2017/12/29.
 * Time:12:37
 *
 * @author fosin
 */
public interface IUserPermissionService extends ICrudBatchJpaService<CdpUserPermissionEntity, Long, Long, CdpUserPermissionCreateDto, CdpUserPermissionRetrieveDto, CdpUserPermissionUpdateDto> {
    List<CdpUserPermissionEntity> findByUserIdAndOrganizId(Long userId, Long organizId);

    List<CdpUserPermissionEntity> findByUserId(Long userId);

    long countByPermissionId(Long permissionId);
}
