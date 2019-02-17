package com.github.fosin.cdp.platformapi.repository;

import com.github.fosin.cdp.platformapi.entity.CdpRolePermissionEntity;
import com.github.fosin.cdp.jpa.repository.IJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Lazy;

import java.util.List;

/**
 * 2017/12/27.
 * Time:16:09
 *
 * @author fosin
 */
@Repository
@Lazy
public interface RolePermissionRepository extends IJpaRepository<CdpRolePermissionEntity, Long> {
    List<CdpRolePermissionEntity> findByRoleId(Long roleId);

    long countByPermissionId(Long permissionId);

    void deleteByRoleId(Long roleId);
}
