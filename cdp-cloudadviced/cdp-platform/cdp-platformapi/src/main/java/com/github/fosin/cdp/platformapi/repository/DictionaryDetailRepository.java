package com.github.fosin.cdp.platformapi.repository;

import com.github.fosin.cdp.platformapi.entity.CdpSysDictionaryDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  2017/12/27.
 * Time:16:09
 * @author fosin
 */
@Repository
public interface DictionaryDetailRepository extends JpaRepository<CdpSysDictionaryDetailEntity,Integer>,
                                    JpaSpecificationExecutor<CdpSysDictionaryDetailEntity> {
    List<CdpSysDictionaryDetailEntity> findByCode(Integer code);
    void deleteAllByCode(Integer code);
}
