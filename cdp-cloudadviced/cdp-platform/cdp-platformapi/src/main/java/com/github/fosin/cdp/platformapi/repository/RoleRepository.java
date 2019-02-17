package com.github.fosin.cdp.platformapi.repository;

import com.github.fosin.cdp.jpa.repository.IJpaRepository;
import com.github.fosin.cdp.platformapi.entity.CdpRoleEntity;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Query;
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
public interface RoleRepository extends IJpaRepository<CdpRoleEntity, Long>{

    @Query(value = "select * from cdp_role where id not in (select role_id from cdp_user_role where user_id =?1)", nativeQuery = true)
    List<CdpRoleEntity> findOtherRolesByUserId(Long userId);

    @Query(value = "select * from cdp_role where id in (select role_id from cdp_user_role where user_id =?1)", nativeQuery = true)
    List<CdpRoleEntity> findUserRolesByUserId(Long userId);

//    @Query(value = "select a.* from CdpRoleEntity a where a.organizId in (select b.id from cdp_organization b where b.code like :code + '%') ")
//    Page<CdpRoleEntity> findAllByOrganizCode(String code, PageRequest pageable);
}
