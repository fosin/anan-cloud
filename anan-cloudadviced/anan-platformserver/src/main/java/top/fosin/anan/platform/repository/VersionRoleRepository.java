package top.fosin.anan.platform.repository;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import top.fosin.anan.jpa.repository.IJpaRepository;
import top.fosin.anan.platform.entity.AnanVersionRoleEntity;

import java.util.List;

/**
 * 系统版本角色表数据库访问层
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Repository
@Lazy
public interface VersionRoleRepository extends IJpaRepository<AnanVersionRoleEntity, Long> {
    List<AnanVersionRoleEntity> findByVersionId(Long versionId);
}
