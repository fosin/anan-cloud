package com.github.fosin.cdp.platformapi.repository;

import com.github.fosin.cdp.platformapi.entity.CdpSysUserPermissionEntity;
import com.github.fosin.cdp.jpa.repository.IJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Lazy;

import java.util.List;

/**
 *  2017/12/27.
 * Time:16:09
 * @author fosin
 */
@Repository
@Lazy
public interface UserPermissionRepository extends IJpaRepository<CdpSysUserPermissionEntity,Long> {
    List<CdpSysUserPermissionEntity> findByUserIdAndOrganizId(Long userId, Long organizId);

    List<CdpSysUserPermissionEntity> findByUserId(Long userId);

    void deleteByUserId(Long userId);

    long countByPermissionId(Long permissionId);
}
