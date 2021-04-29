package top.fosin.anan.platform.repository;

import top.fosin.anan.jpa.repository.IJpaRepository;
import top.fosin.anan.platform.entity.AnanOrganizationAuthEntity;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统机构授权表(anan_organization_auth)表数据库访问层
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Repository
@Lazy
public interface AnanOrganizationAuthRepository extends IJpaRepository<AnanOrganizationAuthEntity, Long>{
    List<AnanOrganizationAuthEntity> findAllByVersionId(Long versionId);

    List<AnanOrganizationAuthEntity> findAllByOrganizId(Long organizId);
}
