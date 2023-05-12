package top.fosin.anan.platform.modules.international.dao;

import org.springframework.stereotype.Repository;
import top.fosin.anan.jpa.repository.IJpaRepository;
import top.fosin.anan.platform.modules.international.po.International;

import java.util.List;

/**
 * 国际化语言数据库访问层
 *
 * @author fosin
 * @date 2020-12-04 11:05:40
 */
@Repository
public interface InternationalDao extends IJpaRepository<Long, International> {
    List<International> findAllByStatus(Integer status);

    International findByCode(String code);

    International findByDefaultFlag(Integer defaultFlag);
}
