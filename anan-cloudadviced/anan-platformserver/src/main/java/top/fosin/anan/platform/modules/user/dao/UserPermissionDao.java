package top.fosin.anan.platform.modules.user.dao;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import top.fosin.anan.jpa.repository.IJpaRepository;
import top.fosin.anan.platform.modules.user.po.UserPermission;

import java.util.List;

/**
 * @author fosin
 * @date 2017/12/27
 */
@Repository
@Lazy
public interface UserPermissionDao extends IJpaRepository<Long, UserPermission> {

    List<UserPermission> findByOrganizId(Long organizId);

    long countByPermissionId(Long permissionId);
}
