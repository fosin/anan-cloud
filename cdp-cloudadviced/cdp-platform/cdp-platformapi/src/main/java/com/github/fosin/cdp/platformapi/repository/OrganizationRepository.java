package com.github.fosin.cdp.platformapi.repository;

import com.github.fosin.cdp.platformapi.entity.CdpSysOrganizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Lazy;
import java.util.List;

/**
 * 2017/12/27.
 * Time:16:09
 *
 * @author fosin
 */
@Repository
@Lazy
public interface OrganizationRepository extends JpaRepository<CdpSysOrganizationEntity, Long>,
        JpaSpecificationExecutor<CdpSysOrganizationEntity> {
    List<CdpSysOrganizationEntity> findByPIdOrderByCodeAsc(Long pId);

    List<CdpSysOrganizationEntity> findByCodeStartingWithOrderByCodeAsc(String code);

    List<CdpSysOrganizationEntity> findAllByTopId(Long topId);
}
