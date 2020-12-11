package com.github.fosin.anan.platform.repository;

import com.github.fosin.anan.jpa.repository.IJpaRepository;
import com.github.fosin.anan.platform.entity.AnanInternationalEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 国际化(anan_international)表数据库访问层
 *
 * @author fosin
 * @date 2020-12-04 11:05:40
 */
@Repository
public interface AnanInternationalRepository extends IJpaRepository<AnanInternationalEntity, Integer>, JpaSpecificationExecutor<AnanInternationalEntity> {
    List<AnanInternationalEntity> findAllByStatus(Integer status);

    AnanInternationalEntity findByCode(String code);

    AnanInternationalEntity findByDefaultFlag(Integer defaultFlag);
}
