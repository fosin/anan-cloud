package com.github.fosin.cdp.platformapi.repository;

import com.github.fosin.cdp.platformapi.entity.CdpSysRolePermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  2017/12/27.
 * Time:16:09
 * @author fosin
 */
@Repository
public interface RolePermissionRepository extends JpaRepository<CdpSysRolePermissionEntity,Long> {
    List<CdpSysRolePermissionEntity> findByRoleId(Long roleId);
    long countByPermissionId(Long permissionId);
    void deleteByRoleId(Long roleId);
}
