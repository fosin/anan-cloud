package top.fosin.anan.platform.modules.user.dao;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import top.fosin.anan.jpa.repository.IJpaRepository;
import top.fosin.anan.platform.modules.user.entity.UserAll;
import top.fosin.anan.platform.modules.user.entity.User;

import java.util.List;

/**
 * 全部用户（包括软删除）
 *
 * @author fosin
 * @date 2021/6/1
 */
@Repository
@Lazy
public interface UserAllDao extends IJpaRepository<UserAll, Long> {
    User findByUsercode(String usercode);

    @Query(value = "select * from anan_user where id not in (select user_id from anan_user_role where role_id =?1)", nativeQuery = true)
    List<User> findOtherUsersByRoleId(Long roleId);

    @Query(value = "select * from anan_user where id in (select user_id from anan_user_role where role_id =?1)", nativeQuery = true)
    List<User> findRoleUsersByRoleId(Long roleId);
}

