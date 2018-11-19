package com.github.fosin.cdp.platformapi.repository;

import com.github.fosin.cdp.platformapi.entity.CdpSysDictionaryDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Lazy;

import java.util.List;

/**
 *  2017/12/27.
 * Time:16:09
 * @author fosin
 */
@Repository
@Lazy
public interface DictionaryDetailRepository extends JpaRepository<CdpSysDictionaryDetailEntity,Long>,
                                    JpaSpecificationExecutor<CdpSysDictionaryDetailEntity> {
    List<CdpSysDictionaryDetailEntity> findByCode(Long code);
    void deleteAllByCode(Long code);
}
