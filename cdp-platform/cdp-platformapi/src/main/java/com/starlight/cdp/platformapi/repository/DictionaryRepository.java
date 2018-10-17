package com.starlight.cdp.platformapi.repository;

import com.starlight.cdp.platformapi.entity.CdpSysDictionaryEntity;
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
public interface DictionaryRepository extends JpaRepository<CdpSysDictionaryEntity,Integer>,
                                    JpaSpecificationExecutor<CdpSysDictionaryEntity> {
}
