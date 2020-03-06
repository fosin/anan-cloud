package com.github.fosin.anan.auth.service.inter;


import com.github.fosin.anan.jpa.service.IRetrieveOneJpaService;
import com.github.fosin.anan.platformapi.entity.AnanPermissionEntity;

import java.util.List;

/**
 * 2017/12/29.
 * Time:12:37
 *
 * @author fosin
 */
public interface PermissionService extends IRetrieveOneJpaService<AnanPermissionEntity, Long> {
    List<AnanPermissionEntity> findByAppName(String appName);
}
