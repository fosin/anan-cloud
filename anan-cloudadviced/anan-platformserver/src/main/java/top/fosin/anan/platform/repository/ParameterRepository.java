package top.fosin.anan.platform.repository;

import top.fosin.anan.platformapi.entity.AnanParameterEntity;
import org.springframework.context.annotation.Lazy;
import top.fosin.anan.jpa.repository.IJpaRepository;
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
public interface ParameterRepository extends IJpaRepository<AnanParameterEntity, Long> {
    AnanParameterEntity findByTypeAndScopeAndName(Integer type, String scope, String name);

    List<AnanParameterEntity> findByStatusNot(Integer status);
}
