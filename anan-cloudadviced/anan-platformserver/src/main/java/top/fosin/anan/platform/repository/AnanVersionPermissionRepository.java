package top.fosin.anan.platform.repository;

import top.fosin.anan.platform.entity.AnanVersionPermissionEntity;
import org.springframework.context.annotation.Lazy;
import top.fosin.anan.jpa.repository.IJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统版本权限表(anan_version_permission)表数据库访问层
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Repository
@Lazy
public interface AnanVersionPermissionRepository extends IJpaRepository<AnanVersionPermissionEntity, Long> {
    List<AnanVersionPermissionEntity> findByVersionId(Long versionId);

    long countByPermissionId(Long permissionId);

    void deleteByVersionId(Long versionId);
}
