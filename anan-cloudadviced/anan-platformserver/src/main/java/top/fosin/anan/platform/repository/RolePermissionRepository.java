package top.fosin.anan.platform.repository;

import top.fosin.anan.platform.entity.AnanRolePermissionEntity;
import top.fosin.anan.jpa.repository.IJpaRepository;
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
public interface RolePermissionRepository extends IJpaRepository<AnanRolePermissionEntity, Long> {
    List<AnanRolePermissionEntity> findByRoleId(Long roleId);

    long countByPermissionId(Long permissionId);

    void deleteByRoleId(Long roleId);
}
