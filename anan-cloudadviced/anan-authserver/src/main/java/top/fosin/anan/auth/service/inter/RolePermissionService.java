package top.fosin.anan.auth.service.inter;


import top.fosin.anan.platformapi.entity.AnanRolePermissionEntity;

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
