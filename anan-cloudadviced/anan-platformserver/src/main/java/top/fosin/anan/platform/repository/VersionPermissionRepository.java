package top.fosin.anan.platform.repository;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import top.fosin.anan.jpa.repository.IJpaRepository;
import top.fosin.anan.platform.entity.AnanVersionPermissionEntity;

import java.util.List;

/**
 * 系统版本权限系统版本权限表数据库访问层
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Repository
@Lazy
public interface VersionPermissionRepository extends IJpaRepository<AnanVersionPermissionEntity, Long> {
    List<AnanVersionPermissionEntity> findByVersionId(Long versionId);

    long countByPermissionId(Long permissionId);

    void deleteByVersionId(Long versionId);
}
