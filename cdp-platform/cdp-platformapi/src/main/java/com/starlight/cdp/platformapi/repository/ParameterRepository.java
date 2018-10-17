package com.starlight.cdp.platformapi.repository;

import com.starlight.cdp.platformapi.entity.CdpSysParameterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 2017/12/27.
 * Time:16:09
 *
 * @author fosin
 */
@Repository
public interface ParameterRepository extends JpaRepository<CdpSysParameterEntity, Integer>,
        JpaSpecificationExecutor<CdpSysParameterEntity> {
    CdpSysParameterEntity findByTypeAndScopeAndName(Integer type, String scope, String name);

    List<CdpSysParameterEntity> findByStatusNot(Integer status);
}
