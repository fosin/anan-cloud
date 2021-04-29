package top.fosin.anan.platform.repository;

import top.fosin.anan.jpa.repository.IJpaRepository;
import top.fosin.anan.platformapi.entity.AnanRoleEntity;
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
public interface RoleRepository extends IJpaRepository<AnanRoleEntity, Long> {

    @Query(value = "select * from anan_role where id not in (select role_id from anan_user_role where user_id =?1)", nativeQuery = true)
    List<AnanRoleEntity> findOtherRolesByUserId(Long userId);

    @Query(value = "select * from anan_role where id in (select role_id from anan_user_role where user_id =?1)", nativeQuery = true)
    List<AnanRoleEntity> findUserRolesByUserId(Long userId);

//    @Query(value = "select a.* from AnanRoleEntity a where a.organizId in (select b.id from anan_organization b where b.code like :code + '%') ")
//    Page<AnanRoleEntity> findAllByOrganizCode(String code, PageRequest pageable);

    List<AnanRoleEntity> findAllByOrganizId(Long organizId);

    AnanRoleEntity findByOrganizIdAndValue(Long organizId, String value);
}
