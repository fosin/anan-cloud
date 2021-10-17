package top.fosin.anan.auth.repository;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import top.fosin.anan.auth.entity.AnanOrganizationEntity;
import top.fosin.anan.jpa.repository.IJpaRepository;

import java.util.List;

/**
 * @author fosin
 * @date 2017/12/27
 */
@Repository
@Lazy
public interface OrganizationRepository extends IJpaRepository<AnanOrganizationEntity, Long> {
    List<AnanOrganizationEntity> findByTopId(Long topId);
}
