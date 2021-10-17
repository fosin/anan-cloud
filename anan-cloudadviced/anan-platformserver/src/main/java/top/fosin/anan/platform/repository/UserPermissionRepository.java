package top.fosin.anan.platform.repository;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import top.fosin.anan.jpa.repository.IJpaRepository;
import top.fosin.anan.platform.entity.AnanUserPermissionEntity;

import java.util.List;

/**
 * @author fosin
 * @date 2017/12/27
 */
@Repository
@Lazy
public interface UserPermissionRepository extends IJpaRepository<AnanUserPermissionEntity, Long> {
    List<AnanUserPermissionEntity> findByUserIdAndOrganizId(Long userId, Long organizId);

    List<AnanUserPermissionEntity> findByUserIdAndOrganizIdAndAddMode(Long userId, Long organizId, int addMode);

    List<AnanUserPermissionEntity> findByOrganizId(Long organizId);

    List<AnanUserPermissionEntity> findByUserId(Long userId);

    void deleteByUserId(Long userId);

    void deleteByOrganizId(Long organizId);

    long countByPermissionId(Long permissionId);
}
