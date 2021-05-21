package top.fosin.anan.platform.repository;

import org.springframework.stereotype.Repository;
import top.fosin.anan.jpa.repository.IJpaRepository;
import top.fosin.anan.platform.entity.AnanInternationalCharsetEntity;

import java.util.List;

/**
 * 国际化明细数据库访问层
 *
 * @author fosin
 * @date 2020-12-04 11:05:40
 */
@Repository
public interface AnanInternationalCharsetRepository extends IJpaRepository<AnanInternationalCharsetEntity, Long> {
    List<AnanInternationalCharsetEntity> findAllByInternationalId(Long internationalId);

    List<AnanInternationalCharsetEntity> findAllByInternationalIdAndServiceId(Long internationalId, Long serviceId);
}
