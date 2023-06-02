package top.fosin.anan.auth.modules.auth.dao;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import top.fosin.anan.auth.modules.auth.po.UserRole;
import top.fosin.anan.jpa.repository.IJpaRepository;

import java.util.List;

/**
 * @author fosin
 * @date 2017/12/27
 */
@Repository
@Lazy
public interface UserRoleDao extends IJpaRepository<Long, UserRole> {
    List<UserRole> findByUserId(Long userId);
}
