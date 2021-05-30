package top.fosin.anan.platform.repository;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import top.fosin.anan.jpa.repository.IJpaRepository;
import top.fosin.anan.platform.entity.AnanDictionaryEntity;

/**
 * @author fosin
 * @date 2017/12/27
 */
@Repository
@Lazy
public interface DictionaryRepository extends IJpaRepository<AnanDictionaryEntity, Long> {
}
