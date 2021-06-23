package top.fosin.anan.platform.repository;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import top.fosin.anan.jpa.repository.IJpaRepository;
import top.fosin.anan.platform.entity.AnanOrganizationEntity;

import java.util.List;

/**
 * @author fosin
 * @date 2017/12/27
 */
@Repository
@Lazy
public interface OrganizationRepository extends IJpaRepository<AnanOrganizationEntity, Long> {
    List<AnanOrganizationEntity> findByPidOrderByCodeAsc(Long pid);

    List<AnanOrganizationEntity> findByTopIdAndCodeStartingWithOrderByCodeAsc(Long topId, String code);

    List<AnanOrganizationEntity> findByTopId(Long topId);
}
