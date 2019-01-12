package com.github.fosin.cdp.platformapi.repository;

import com.github.fosin.cdp.platformapi.entity.CdpSysParameterEntity;
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
public interface ParameterRepository extends IJpaRepository<CdpSysParameterEntity, Long> {
    CdpSysParameterEntity findByTypeAndScopeAndName(Integer type, String scope, String name);

    List<CdpSysParameterEntity> findByStatusNot(Integer status);
}
