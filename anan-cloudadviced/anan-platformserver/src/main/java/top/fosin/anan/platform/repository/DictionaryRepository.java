package top.fosin.anan.platform.repository;

import top.fosin.anan.platform.entity.AnanDictionaryEntity;
import top.fosin.anan.jpa.repository.IJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Lazy;

/**
 *  2017/12/27.
 * Time:16:09
 * @author fosin
 */
@Repository
@Lazy
public interface DictionaryRepository extends IJpaRepository<AnanDictionaryEntity,Long> {
}
