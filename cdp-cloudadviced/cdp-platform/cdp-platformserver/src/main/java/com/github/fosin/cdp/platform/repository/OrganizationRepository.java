package com.github.fosin.cdp.platform.repository;

import com.github.fosin.cdp.platformapi.entity.CdpOrganizationEntity;
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
public interface OrganizationRepository extends IJpaRepository<CdpOrganizationEntity, Long> {
    List<CdpOrganizationEntity> findByPIdOrderByCodeAsc(Long pId);

    List<CdpOrganizationEntity> findByCodeStartingWithOrderByCodeAsc(String code);

    List<CdpOrganizationEntity> findAllByTopId(Long topId);
}
