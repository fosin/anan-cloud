package top.fosin.anan.platform.repository;

import java.util.List;

import top.fosin.anan.jpa.repository.IJpaRepository;
import top.fosin.anan.platform.entity.AnanInternationalEntity;

import org.springframework.stereotype.Repository;

/**
 * 国际化(anan_international)表数据库访问层
 *
 * @author fosin
 * @date 2020-12-04 11:05:40
 */
@Repository
public interface AnanInternationalRepository extends IJpaRepository<AnanInternationalEntity, Integer> {
    List<AnanInternationalEntity> findAllByStatus(Integer status);

    AnanInternationalEntity findByCode(String code);

    AnanInternationalEntity findByDefaultFlag(Integer defaultFlag);
}
