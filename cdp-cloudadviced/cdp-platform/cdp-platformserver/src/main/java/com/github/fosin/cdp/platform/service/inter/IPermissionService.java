package com.github.fosin.cdp.platform.service.inter;


import com.github.fosin.cdp.jpa.service.ISimpleJpaService;
import com.github.fosin.cdp.platformapi.dto.request.CdpPermissionCreateDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpPermissionRetrieveDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpPermissionUpdateDto;
import com.github.fosin.cdp.platformapi.entity.CdpPermissionEntity;

import java.util.Collection;
import java.util.List;

/**
 * 2017/12/29.
 * Time:12:37
 *
 * @author fosin
 */
public interface IPermissionService extends ISimpleJpaService<CdpPermissionEntity, Long, CdpPermissionCreateDto, CdpPermissionRetrieveDto, CdpPermissionUpdateDto> {
    List<CdpPermissionEntity> findByPId(Long pId);

    List<CdpPermissionEntity> findByType(Integer type);

    List<CdpPermissionEntity> findByAppName(String appName);

    List<CdpPermissionEntity> findByPId(Long pId, Long versionId);
}
