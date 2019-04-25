package com.github.fosin.anan.platform.service.inter;

import com.github.fosin.anan.platformapi.dto.request.AnanDictionaryDetailCreateDto;
import com.github.fosin.anan.platformapi.dto.request.AnanDictionaryDetailRetrieveDto;
import com.github.fosin.anan.platformapi.dto.request.AnanDictionaryDetailUpdateDto;
import com.github.fosin.anan.platformapi.entity.AnanDictionaryDetailEntity;
import com.github.fosin.anan.core.exception.AnanServiceException;
import com.github.fosin.anan.jpa.service.ISimpleJpaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 2017/12/29.
 * Time:12:30
 *
 * @author fosin
 */
public interface DictionaryDetailService extends ISimpleJpaService<AnanDictionaryDetailEntity, Long, AnanDictionaryDetailCreateDto, AnanDictionaryDetailRetrieveDto, AnanDictionaryDetailUpdateDto> {
    Page<AnanDictionaryDetailEntity> findAll(String searchCondition, Pageable pageable, Long code);

    List<AnanDictionaryDetailEntity> findByDictionaryId(Long dictionaryId);
}
