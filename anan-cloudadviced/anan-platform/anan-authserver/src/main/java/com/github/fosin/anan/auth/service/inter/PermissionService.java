package com.github.fosin.anan.auth.service.inter;


import com.github.fosin.anan.jpa.service.IRetrieveJpaService;
import com.github.fosin.anan.platformapi.entity.AnanPermissionEntity;
import com.github.fosin.anan.cloudresource.dto.AnanUserAllPermissionDto;
import com.github.fosin.anan.cloudresource.dto.request.AnanPermissionRetrieveDto;

import java.util.List;

/**
 * 2017/12/29.
 * Time:12:37
 *
 * @author fosin
 */
public interface PermissionService extends IRetrieveJpaService<AnanPermissionEntity, Long, AnanPermissionEntity> {
    List<AnanPermissionRetrieveDto> findByAppName(String appName);

    AnanUserAllPermissionDto copyPermissionData(AnanPermissionEntity entity);
}
