package com.github.fosin.cdp.platformapi.service.inter;


import com.github.fosin.cdp.jpa.service.ISimpleJpaService;
import com.github.fosin.cdp.platformapi.dto.request.CdpSysPermissionCreateDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpSysPermissionRetrieveDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpSysPermissionUpdateDto;
import com.github.fosin.cdp.platformapi.entity.CdpSysPermissionEntity;

import java.util.Collection;
import java.util.List;

/**
 * 2017/12/29.
 * Time:12:37
 *
 * @author fosin
 */
public interface IPermissionService extends ISimpleJpaService<CdpSysPermissionEntity, Long, CdpSysPermissionCreateDto, CdpSysPermissionRetrieveDto, CdpSysPermissionUpdateDto> {
    List<CdpSysPermissionEntity> findByPId(Long pId);

    List<CdpSysPermissionEntity> findByType(Integer type);

    List<CdpSysPermissionEntity> findByAppName(String appName);

    List<CdpSysPermissionEntity> findByPId(Long pId, Long versionId);
}
