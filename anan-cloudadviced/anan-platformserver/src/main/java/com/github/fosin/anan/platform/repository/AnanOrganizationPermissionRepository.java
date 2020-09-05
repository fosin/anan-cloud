package com.github.fosin.anan.platform.repository;

import com.github.fosin.anan.platform.entity.AnanOrganizationPermissionEntity;
import com.github.fosin.anan.jpa.repository.IJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Lazy;

import java.util.List;

/**
 * 系统机构权限表(anan_organization_permission)表数据库访问层
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Repository
@Lazy
public interface AnanOrganizationPermissionRepository extends IJpaRepository<AnanOrganizationPermissionEntity, Long>{
    List<AnanOrganizationPermissionEntity> findByOrganizId(Long organizId);

    long countByPermissionId(Long permissionId);

    void deleteByOrganizId(Long organizId);
}
