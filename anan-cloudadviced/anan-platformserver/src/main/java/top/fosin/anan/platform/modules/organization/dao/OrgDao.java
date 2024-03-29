package top.fosin.anan.platform.modules.organization.dao;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import top.fosin.anan.jpa.repository.IJpaRepository;
import top.fosin.anan.platform.modules.organization.po.Organization;

import java.util.List;

/**
 * @author fosin
 * @date 2017/12/27
 */
@Repository
@Lazy
public interface OrgDao extends IJpaRepository<Long, Organization> {
    List<Organization> findByTopIdAndCodeStartingWithOrderByCodeAsc(Long topId, String code);

    List<Organization> findByPidOrderByCodeAsc(Long pid);
}
