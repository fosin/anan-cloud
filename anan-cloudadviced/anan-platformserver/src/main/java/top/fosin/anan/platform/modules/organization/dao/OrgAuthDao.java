package top.fosin.anan.platform.modules.organization.dao;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import top.fosin.anan.jpa.repository.IJpaRepository;
import top.fosin.anan.platform.modules.organization.entity.OrganizationAuth;

import java.util.List;

/**
 * 系统机构授权系统机构授权表数据库访问层
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Repository
@Lazy
public interface OrgAuthDao extends IJpaRepository<OrganizationAuth, Long> {
    List<OrganizationAuth> findAllByVersionId(Long versionId);

    List<OrganizationAuth> findAllByOrganizId(Long organizId);
}
