package com.github.fosin.cdp.platform.repository;

import com.github.fosin.cdp.platformapi.entity.CdpDictionaryEntity;
import com.github.fosin.cdp.jpa.repository.IJpaRepository;
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
public interface DictionaryRepository extends IJpaRepository<CdpDictionaryEntity,Long> {
}
