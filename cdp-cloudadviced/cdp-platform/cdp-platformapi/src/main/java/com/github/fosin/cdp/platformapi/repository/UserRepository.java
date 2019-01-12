package com.github.fosin.cdp.platformapi.repository;

import com.github.fosin.cdp.jpa.repository.IJpaRepository;
import com.github.fosin.cdp.platformapi.entity.CdpSysUserEntity;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  2017/12/27.
 * Time:14:52
 * @author fosin
 */
@Repository
@Lazy
public interface UserRepository extends IJpaRepository<CdpSysUserEntity,Long> {
    CdpSysUserEntity findByUsercode(String usercode);

    @Query(value = "select * from cdp_sys_user where id not in (select user_id from cdp_sys_user_role where role_id =?1)",nativeQuery = true)
    List<CdpSysUserEntity> findOtherUsersByRoleId(Long roleId);

    @Query(value = "select * from cdp_sys_user where id in (select user_id from cdp_sys_user_role where role_id =?1)",nativeQuery = true)
    List<CdpSysUserEntity> findRoleUsersByRoleId(Long roleId);
}

