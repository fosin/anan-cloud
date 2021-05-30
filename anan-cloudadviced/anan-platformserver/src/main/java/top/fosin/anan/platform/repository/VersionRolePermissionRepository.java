package top.fosin.anan.platform.repository;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import top.fosin.anan.jpa.repository.IJpaRepository;
import top.fosin.anan.platform.entity.AnanVersionRolePermissionEntity;

import java.util.List;

/**
 * 系统版本角色权限系统版本角色权限表数据库访问层
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Repository
@Lazy
public interface VersionRolePermissionRepository extends IJpaRepository<AnanVersionRolePermissionEntity, Long> {
    List<AnanVersionRolePermissionEntity> findByRoleId(Long roleId);

    void deleteByRoleId(Long roleId);

    long countByPermissionId(Long permissionId);
}
