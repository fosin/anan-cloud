package com.github.fosin.cdp.platformapi.repository;

import com.github.fosin.cdp.jpa.repository.IJpaRepository;
import com.github.fosin.cdp.platformapi.entity.CdpPermissionEntity;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 2017/12/27.
 * Time:16:09
 *
 * @author fosin
 */
@Repository
@Lazy
public interface PermissionRepository extends IJpaRepository<CdpPermissionEntity, Long> {
    //    @Query(value = "select menu.* from cdp_menu menu,cdp_role_privilege p where menu.id=p.menu_id and p.role_id=?1",nativeQuery = true)
//    List<CdpPermissionEntity> findRoleMenuByRoleId(Integer roleId);
//
//    @Query(value = "select menu.* from cdp_menu menu,cdp_user_privilege p where menu.id=p.menu_id and p.user_id=?1 and p.role_id=?2 and p.add_mode=?3",nativeQuery = true)
//    List<CdpPermissionEntity> findUserMenuByUserIdAndRoleIdAndAddMode(Integer userId, Integer roleId, Integer addMode);
//
//    @Query(value = "select m.* from cdp_menu m,(select p.role_id,p.menu_id,r.add_mode from cdp_role_privilege p left join cdp_user_privilege r on p.role_id =r.role_id and p.menu_id=r.menu_id  and r.user_id=?1  where p.role_id=?2 and (r.add_mode = 0 or r.add_mode is null)) z where m.id=z.menu_id union select m.* from cdp_menu m ,cdp_user_privilege r where m.id=r.menu_id and r.role_id=?2 and r.user_id=?1 and r.add_mode=0",nativeQuery = true)
//    List<CdpPermissionEntity> getAllMenuByUserIdAndRoleId(Integer userId, Integer roleId);
    List<CdpPermissionEntity> findByPId(Long pId, Sort sort);

    @Query(value = "select * from cdp_permission where p_id = :pId and id in (select permission_id from cdp_version_permission where version_id = :versionId) order by sort", nativeQuery = true)
    List<CdpPermissionEntity> findByPId(@Param(value = "pId") Long pId, @Param(value = "versionId") Long versionId);

    List<CdpPermissionEntity> findByType(Integer type);

    List<CdpPermissionEntity> findByAppName(String appName);
}
