package com.github.fosin.cdp.platform.repository;

import com.github.fosin.cdp.platform.entity.CdpVersionRolePermissionEntity;
import com.github.fosin.cdp.jpa.repository.IJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Lazy;

import java.util.List;

/**
 * 系统版本角色权限表(cdp_version_role_permission)表数据库访问层
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Repository
@Lazy
public interface CdpVersionRolePermissionRepository extends IJpaRepository<CdpVersionRolePermissionEntity, Long>{
    List<CdpVersionRolePermissionEntity> findByRoleId(Long roleId);

    void deleteByRoleId(Long roleId);
}
