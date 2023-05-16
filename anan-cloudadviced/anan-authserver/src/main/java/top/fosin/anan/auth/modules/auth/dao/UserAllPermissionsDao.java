package top.fosin.anan.auth.modules.auth.dao;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import top.fosin.anan.auth.modules.auth.po.UserAllPermissions;
import top.fosin.anan.jpa.repository.IJpaRepository;

import java.util.List;

/**
 * @author fosin
 */
@Repository
@Lazy
public interface UserAllPermissionsDao extends IJpaRepository<Long, UserAllPermissions> {

    List<UserAllPermissions> findByUserId(Long userId);

    List<UserAllPermissions> findByUserIdAndOrganizId(Long userId, Long organizId);
}
