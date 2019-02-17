package com.github.fosin.cdp.platform.repository;

import com.github.fosin.cdp.platform.entity.CdpVersionPermissionEntity;
import org.springframework.context.annotation.Lazy;
import com.github.fosin.cdp.jpa.repository.IJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统版本权限表(cdp_version_permission)表数据库访问层
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Repository
@Lazy
public interface CdpVersionPermissionRepository extends IJpaRepository<CdpVersionPermissionEntity, Long> {
    List<CdpVersionPermissionEntity> findByVersionId(Long versionId);

    long countByPermissionId(Long permissionId);

    void deleteByVersionId(Long versionId);
}
