package com.github.fosin.cdp.platformapi.repository;

import com.github.fosin.cdp.platformapi.entity.CdpSysParameterEntity;
import org.springframework.context.annotation.Lazy;
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
public interface ParameterRepository extends JpaRepository<CdpSysParameterEntity, Long>,
        JpaSpecificationExecutor<CdpSysParameterEntity> {
    CdpSysParameterEntity findByTypeAndScopeAndName(Integer type, String scope, String name);

    List<CdpSysParameterEntity> findByStatusNot(Integer status);
}
