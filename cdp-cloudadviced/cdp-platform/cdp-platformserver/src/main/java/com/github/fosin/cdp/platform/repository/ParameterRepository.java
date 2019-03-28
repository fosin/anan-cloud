package com.github.fosin.cdp.platform.repository;

import com.github.fosin.cdp.platformapi.entity.CdpParameterEntity;
import org.springframework.context.annotation.Lazy;
import com.github.fosin.cdp.jpa.repository.IJpaRepository;
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
public interface ParameterRepository extends IJpaRepository<CdpParameterEntity, Long> {
    CdpParameterEntity findByTypeAndScopeAndName(Integer type, String scope, String name);

    List<CdpParameterEntity> findByStatusNot(Integer status);
}
