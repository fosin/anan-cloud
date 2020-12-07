package com.github.fosin.anan.platform.repository;

import com.github.fosin.anan.jpa.repository.IJpaRepository;
import com.github.fosin.anan.platform.entity.AnanInternationalCharsetEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 国际化明显(anan_international_charset)表数据库访问层
 *
 * @author fosin
 * @date 2020-12-04 11:05:40
 */
@Repository
public interface AnanInternationalCharsetRepository extends IJpaRepository<AnanInternationalCharsetEntity, Long>, JpaSpecificationExecutor<AnanInternationalCharsetEntity> {
    List<AnanInternationalCharsetEntity> findAllByInternationalId(Integer internationalId);
}
