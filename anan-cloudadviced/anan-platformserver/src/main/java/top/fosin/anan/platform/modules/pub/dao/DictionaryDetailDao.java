package top.fosin.anan.platform.modules.pub.dao;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import top.fosin.anan.jpa.repository.IJpaRepository;
import top.fosin.anan.platform.modules.pub.po.DictionaryDetail;

import java.util.List;

/**
 * @author fosin
 * @date 2017/12/27
 */
@Repository
@Lazy
public interface DictionaryDetailDao extends IJpaRepository<Long, DictionaryDetail> {
    List<DictionaryDetail> findAllByDictionaryId(Long dictionaryId, Sort sort);

    void deleteAllByDictionaryId(Long dictionaryId);
}
