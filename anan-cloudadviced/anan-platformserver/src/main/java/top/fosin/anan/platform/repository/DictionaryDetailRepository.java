package top.fosin.anan.platform.repository;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import top.fosin.anan.jpa.repository.IJpaRepository;
import top.fosin.anan.platform.entity.DictionaryDetailEntity;

import java.util.List;

/**
 * @author fosin
 * @date 2017/12/27
 */
@Repository
@Lazy
public interface DictionaryDetailRepository extends IJpaRepository<DictionaryDetailEntity, Long> {
    List<DictionaryDetailEntity> findAllByDictionaryId(Long dictionaryId, Sort sort);

    void deleteAllByDictionaryId(Long dictionaryId);
}
