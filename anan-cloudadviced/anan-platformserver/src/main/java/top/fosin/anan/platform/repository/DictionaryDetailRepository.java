package top.fosin.anan.platform.repository;

import top.fosin.anan.platformapi.entity.AnanDictionaryDetailEntity;
import top.fosin.anan.jpa.repository.IJpaRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Lazy;

import java.util.List;

/**
 * 2017/12/27.
 * Time:16:09
 *
 * @author fosin
 */
@Repository
@Lazy
public interface DictionaryDetailRepository extends IJpaRepository<AnanDictionaryDetailEntity, Long> {
    List<AnanDictionaryDetailEntity> findAllByDictionaryId(Long dictionaryId, Sort sort);

    void deleteAllByDictionaryId(Long dictionaryId);
}
