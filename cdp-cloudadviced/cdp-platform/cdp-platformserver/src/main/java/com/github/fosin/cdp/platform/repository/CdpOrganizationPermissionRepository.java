package com.github.fosin.cdp.platform.repository;

import com.github.fosin.cdp.platform.entity.CdpOrganizationPermissionEntity;
import com.github.fosin.cdp.jpa.repository.IJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Lazy;

import java.util.List;

/**
 * 系统机构权限表(cdp_organization_permission)表数据库访问层
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Repository
@Lazy
public interface CdpOrganizationPermissionRepository extends IJpaRepository<CdpOrganizationPermissionEntity, Long>{
    List<CdpOrganizationPermissionEntity> findByOrganizId(Long organizId);

    long countByPermissionId(Long permissionId);

    void deleteByOrganizId(Long organizId);
}
