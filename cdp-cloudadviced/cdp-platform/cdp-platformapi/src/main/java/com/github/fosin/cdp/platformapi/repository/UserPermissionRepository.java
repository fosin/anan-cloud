package com.github.fosin.cdp.platformapi.repository;

import com.github.fosin.cdp.platformapi.entity.CdpSysUserPermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  2017/12/27.
 * Time:16:09
 * @author fosin
 */
@Repository
public interface UserPermissionRepository extends JpaRepository<CdpSysUserPermissionEntity,Integer> {
    List<CdpSysUserPermissionEntity> findByUserIdAndOrganizId(Integer userId, Integer organizId);

    List<CdpSysUserPermissionEntity> findByUserId(Integer userId);

    void deleteByUserId(Integer userId);

    long countByPermissionId(Integer permissionId);
}
