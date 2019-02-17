package com.github.fosin.cdp.platformapi.repository;

import com.github.fosin.cdp.jpa.repository.IJpaRepository;
import com.github.fosin.cdp.platformapi.entity.CdpUserEntity;
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
public interface UserRepository extends IJpaRepository<CdpUserEntity,Long> {
    CdpUserEntity findByUsercode(String usercode);

    @Query(value = "select * from cdp_user where id not in (select user_id from cdp_user_role where role_id =?1)",nativeQuery = true)
    List<CdpUserEntity> findOtherUsersByRoleId(Long roleId);

    @Query(value = "select * from cdp_user where id in (select user_id from cdp_user_role where role_id =?1)",nativeQuery = true)
    List<CdpUserEntity> findRoleUsersByRoleId(Long roleId);
}

