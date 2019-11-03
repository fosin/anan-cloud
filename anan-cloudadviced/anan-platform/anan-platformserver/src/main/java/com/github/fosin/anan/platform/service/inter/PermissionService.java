package com.github.fosin.anan.platform.service.inter;


import com.github.fosin.anan.jpa.service.ISimpleJpaService;
import com.github.fosin.anan.platformapi.dto.request.AnanPermissionCreateDto;
import com.github.fosin.anan.platformapi.dto.request.AnanPermissionRetrieveDto;
import com.github.fosin.anan.platformapi.dto.request.AnanPermissionUpdateDto;
import com.github.fosin.anan.platformapi.entity.AnanPermissionEntity;

import java.util.List;

/**
 * 2017/12/29.
 * Time:12:37
 *
 * @author fosin
 */
public interface PermissionService extends ISimpleJpaService<AnanPermissionEntity, Long, AnanPermissionCreateDto, AnanPermissionRetrieveDto, AnanPermissionUpdateDto> {
    List<AnanPermissionEntity> findByPid(Long pid);

    List<AnanPermissionEntity> findByType(Integer type);

    List<AnanPermissionEntity> findByAppName(String appName);

    List<AnanPermissionEntity> findByPid(Long pid, Long versionId);
}
