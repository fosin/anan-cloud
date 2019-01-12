package com.github.fosin.cdp.platformapi.repository;

import com.github.fosin.cdp.platformapi.entity.CdpSysOrganizationEntity;
import com.github.fosin.cdp.jpa.repository.IJpaRepository;
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
public interface OrganizationRepository extends IJpaRepository<CdpSysOrganizationEntity, Long> {
    List<CdpSysOrganizationEntity> findByPIdOrderByCodeAsc(Long pId);

    List<CdpSysOrganizationEntity> findByCodeStartingWithOrderByCodeAsc(String code);

    List<CdpSysOrganizationEntity> findAllByTopId(Long topId);
}
