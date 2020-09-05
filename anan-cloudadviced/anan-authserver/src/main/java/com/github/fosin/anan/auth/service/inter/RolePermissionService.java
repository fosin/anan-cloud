package com.github.fosin.anan.auth.service.inter;


import com.github.fosin.anan.platformapi.entity.AnanRolePermissionEntity;

import java.util.List;

/**
 * 2017/12/29.
 * Time:12:37
 *
 * @author fosin
 */
public interface RolePermissionService {
    List<AnanRolePermissionEntity> findByRoleId(Long roleId);
}
