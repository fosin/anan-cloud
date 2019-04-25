package com.github.fosin.anan.platformapi.repository;

import com.github.fosin.anan.jpa.repository.IJpaRepository;
import com.github.fosin.anan.platformapi.entity.AnanUserEntity;
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
public interface UserRepository extends IJpaRepository<AnanUserEntity,Long> {
    AnanUserEntity findByUsercode(String usercode);

    @Query(value = "select * from anan_user where id not in (select user_id from anan_user_role where role_id =?1)",nativeQuery = true)
    List<AnanUserEntity> findOtherUsersByRoleId(Long roleId);

    @Query(value = "select * from anan_user where id in (select user_id from anan_user_role where role_id =?1)",nativeQuery = true)
    List<AnanUserEntity> findRoleUsersByRoleId(Long roleId);
}

