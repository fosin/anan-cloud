package com.github.fosin.anan.platform.repository;

import com.github.fosin.anan.platformapi.entity.AnanOrganizationEntity;
import com.github.fosin.anan.jpa.repository.IJpaRepository;
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
public interface OrganizationRepository extends IJpaRepository<AnanOrganizationEntity, Long> {
    List<AnanOrganizationEntity> findByPidOrderByCodeAsc(Long pid);

    List<AnanOrganizationEntity> findByCodeStartingWithOrderByCodeAsc(String code);

    List<AnanOrganizationEntity> findAllByTopId(Long topId);
}
