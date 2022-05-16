package top.fosin.anan.platform.modules.pub.dao;

import org.springframework.stereotype.Repository;
import top.fosin.anan.jpa.repository.IJpaRepository;
import top.fosin.anan.platform.modules.pub.entity.InternationalCharset;

import java.util.List;

/**
 * 国际化明细数据库访问层
 *
 * @author fosin
 * @date 2020-12-04 11:05:40
 */
@Repository
public interface InternationalCharsetDao extends IJpaRepository<InternationalCharset, Long> {
    List<InternationalCharset> findAllByInternationalId(Long internationalId);

    List<InternationalCharset> findAllByInternationalIdAndServiceId(Long internationalId, Long serviceId);
}
