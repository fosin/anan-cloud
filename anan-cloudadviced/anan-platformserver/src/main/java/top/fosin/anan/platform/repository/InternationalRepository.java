package top.fosin.anan.platform.repository;

import org.springframework.stereotype.Repository;
import top.fosin.anan.jpa.repository.IJpaRepository;
import top.fosin.anan.platform.entity.AnanInternationalEntity;

import java.util.List;

/**
 * 国际化语言数据库访问层
 *
 * @author fosin
 * @date 2020-12-04 11:05:40
 */
@Repository
public interface InternationalRepository extends IJpaRepository<AnanInternationalEntity, Long> {
    List<AnanInternationalEntity> findAllByStatus(Integer status);

    AnanInternationalEntity findByCode(String code);

    AnanInternationalEntity findByDefaultFlag(Integer defaultFlag);
}
