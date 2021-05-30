package top.fosin.anan.platform.repository;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import top.fosin.anan.jpa.repository.IJpaRepository;
import top.fosin.anan.platform.entity.AnanUserRoleEntity;

import java.util.List;

/**
 * @author fosin
 * @date 2017/12/27
 */
@Repository
@Lazy
public interface UserRoleRepository extends IJpaRepository<AnanUserRoleEntity, Long> {
    List<AnanUserRoleEntity> findByUserId(Long userId);

    List<AnanUserRoleEntity> findByRoleId(Long roleId);

    void deleteByUserId(Long userId);

    void deleteByRoleId(Long roleId);
}
