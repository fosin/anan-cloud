package com.github.fosin.anan.platformapi.repository;

import com.github.fosin.anan.platformapi.entity.AnanUserPermissionEntity;
import com.github.fosin.anan.jpa.repository.IJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Lazy;

import java.util.List;

/**
 *  2017/12/27.
 * Time:16:09
 * @author fosin
 */
@Repository
@Lazy
public interface UserPermissionRepository extends IJpaRepository<AnanUserPermissionEntity,Long> {
    List<AnanUserPermissionEntity> findByUserIdAndOrganizId(Long userId, Long organizId);

    List<AnanUserPermissionEntity> findByUserId(Long userId);

    void deleteByUserId(Long userId);

    long countByPermissionId(Long permissionId);
}
