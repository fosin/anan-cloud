package top.fosin.anan.platform.modules.role.dao;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import top.fosin.anan.jpa.repository.IJpaRepository;
import top.fosin.anan.platform.modules.role.po.Role;

import java.util.List;

/**
 * @author fosin
 * @date 2017/12/27
 */
@Repository
@Lazy
public interface RoleDao extends IJpaRepository<Long, Role> {

    @Query(value = "select * from anan_role where id not in (select role_id from anan_user_role where user_id =?1) and status=0", nativeQuery = true)
    List<Role> findOtherRolesByUserId(Long userId);

    @Query(value = "select * from anan_role where id in (select role_id from anan_user_role where user_id =?1) and status=0", nativeQuery = true)
    List<Role> findUserRolesByUserId(Long userId);

    List<Role> findAllByOrganizId(Long organizId);

    Role findByOrganizIdAndValue(Long organizId, String value);
}
