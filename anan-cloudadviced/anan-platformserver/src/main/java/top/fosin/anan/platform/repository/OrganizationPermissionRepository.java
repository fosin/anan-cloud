package top.fosin.anan.platform.repository;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import top.fosin.anan.jpa.repository.IJpaRepository;
import top.fosin.anan.platform.entity.AnanOrganizationPermissionEntity;

import java.util.List;

/**
 * 系统机构权限系统机构权限表数据库访问层
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Repository
@Lazy
public interface OrganizationPermissionRepository extends IJpaRepository<AnanOrganizationPermissionEntity, Long> {
    List<AnanOrganizationPermissionEntity> findByOrganizId(Long organizId);

    long countByPermissionId(Long permissionId);

    void deleteByOrganizId(Long organizId);
}
