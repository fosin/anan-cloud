package top.fosin.anan.platform.repository;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import top.fosin.anan.jpa.repository.IJpaRepository;
import top.fosin.anan.platform.entity.AnanParameterEntity;

import java.util.List;

/**
 * @author fosin
 * @date 2017/12/27
 */
@Repository
@Lazy
public interface ParameterRepository extends IJpaRepository<AnanParameterEntity, Long> {
    AnanParameterEntity findByTypeAndScopeAndName(Integer type, String scope, String name);

    List<AnanParameterEntity> findByStatusNot(Integer status);
}
