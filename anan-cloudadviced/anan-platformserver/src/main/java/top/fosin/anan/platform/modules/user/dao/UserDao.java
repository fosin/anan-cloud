package top.fosin.anan.platform.modules.user.dao;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import top.fosin.anan.jpa.repository.IJpaRepository;
import top.fosin.anan.platform.modules.user.entity.User;

import java.util.List;

/**
 * @author fosin
 * @date 2017/12/27
 */
@Repository
@Lazy
public interface UserDao extends IJpaRepository<User, Long> {
    User findByUsercode(String usercode);

    @Query(value = "select * from anan_user where id not in (select user_id from anan_user_role where role_id =?1) and deleted=0 and status=0", nativeQuery = true)
    List<User> findOtherUsersByRoleId(Long roleId);

    @Query(value = "select a.* from anan_user a,anan_user_role b where a.id=b.user_id and b.role_id =?1 and a.deleted=0 and a.status=0", nativeQuery = true)
    List<User> findRoleUsersByRoleId(Long roleId);

    @Query(value = "select * from anan_user where organiz_id in (select id from anan_organization where top_id =?1) and (status=?2 or ?2=-1) and deleted=0 and status=0", nativeQuery = true)
    List<User> findByTopIdAndStatus(Long topId, Integer status);
}

