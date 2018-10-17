package com.starlight.cdp.platformapi.repository;

import com.starlight.cdp.platformapi.entity.CdpSysRolePermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  2017/12/27.
 * Time:16:09
 * @author fosin
 */
@Repository
public interface RolePermissionRepository extends JpaRepository<CdpSysRolePermissionEntity,Integer> {
    List<CdpSysRolePermissionEntity> findByRoleId(Integer roleId);
    long countByPermissionId(Integer permissionId);
    void deleteByRoleId(Integer roleId);
}
