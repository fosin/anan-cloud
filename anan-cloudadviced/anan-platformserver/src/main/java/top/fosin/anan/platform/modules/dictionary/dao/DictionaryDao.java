package top.fosin.anan.platform.modules.dictionary.dao;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import top.fosin.anan.jpa.repository.IJpaRepository;
import top.fosin.anan.platform.modules.dictionary.po.Dictionary;

/**
 * @author fosin
 * @date 2017/12/27
 */
@Repository
@Lazy
public interface DictionaryDao extends IJpaRepository<Long, Dictionary> {
}
