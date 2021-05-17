package top.fosin.anan.platform.repository;

import top.fosin.anan.platform.entity.AnanUserRoleEntity;
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
public interface UserRoleRepository extends IJpaRepository<AnanUserRoleEntity, Long> {
    List<AnanUserRoleEntity> findByUserId(Long userId);
    List<AnanUserRoleEntity> findByRoleId(Long roleId);
    void deleteByUserId(Long userId);
    void deleteByRoleId(Long roleId);
}
