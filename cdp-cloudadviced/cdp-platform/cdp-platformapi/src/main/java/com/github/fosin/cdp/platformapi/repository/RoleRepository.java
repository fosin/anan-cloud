package com.github.fosin.cdp.platformapi.repository;

import com.github.fosin.cdp.platformapi.entity.CdpSysRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  2017/12/27.
 * Time:16:09
 * @author fosin
 */
@Repository
public interface RoleRepository extends JpaRepository<CdpSysRoleEntity,Integer>,JpaSpecificationExecutor<CdpSysRoleEntity> {

    @Query(value = "select * from cdp_sys_role where id not in (select role_id from cdp_sys_user_role where user_id =?1)",nativeQuery = true)
    List<CdpSysRoleEntity> findOtherRolesByUserId(Integer userId);

    @Query(value = "select * from cdp_sys_role where id in (select role_id from cdp_sys_user_role where user_id =?1)",nativeQuery = true)
    List<CdpSysRoleEntity> findUserRolesByUserId(Integer userId);
}
