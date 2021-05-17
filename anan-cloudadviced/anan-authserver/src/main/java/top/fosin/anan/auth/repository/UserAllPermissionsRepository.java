package top.fosin.anan.auth.repository;

import top.fosin.anan.jpa.repository.IJpaRepository;
import top.fosin.anan.auth.entity.AnanUserAllPermissionsEntity;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author fosin
 */
@Repository
@Lazy
public interface UserAllPermissionsRepository extends IJpaRepository<AnanUserAllPermissionsEntity, Long> {

    List<AnanUserAllPermissionsEntity> findByUserId(Long userId);
}
