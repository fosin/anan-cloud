package com.github.fosin.anan.platform.repository;

import com.github.fosin.anan.platformapi.entity.AnanDictionaryEntity;
import com.github.fosin.anan.jpa.repository.IJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
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
