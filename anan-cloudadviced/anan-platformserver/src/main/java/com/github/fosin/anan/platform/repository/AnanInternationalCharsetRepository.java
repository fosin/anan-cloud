package com.github.fosin.anan.platform.repository;

import java.util.List;

import com.github.fosin.anan.jpa.repository.IJpaRepository;
import com.github.fosin.anan.platform.entity.AnanInternationalCharsetEntity;

import org.springframework.stereotype.Repository;

/**
 * 国际化明显(anan_international_charset)表数据库访问层
 *
 * @author fosin
 * @date 2020-12-04 11:05:40
 */
@Repository
public interface AnanInternationalCharsetRepository extends IJpaRepository<AnanInternationalCharsetEntity, Long> {
    List<AnanInternationalCharsetEntity> findAllByInternationalId(Integer internationalId);

    List<AnanInternationalCharsetEntity> findAllByInternationalIdAndServiceId(Integer internationalId, Integer serviceId);
}
