package com.github.fosin.cdp.platform.service.inter;

import com.github.fosin.cdp.platformapi.dto.request.CdpDictionaryDetailCreateDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpDictionaryDetailRetrieveDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpDictionaryDetailUpdateDto;
import com.github.fosin.cdp.platformapi.entity.CdpDictionaryDetailEntity;
import com.github.fosin.cdp.core.exception.CdpServiceException;
import com.github.fosin.cdp.jpa.service.ISimpleJpaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 2017/12/29.
 * Time:12:30
 *
 * @author fosin
 */
public interface IDictionaryDetailService extends ISimpleJpaService<CdpDictionaryDetailEntity, Long, CdpDictionaryDetailCreateDto, CdpDictionaryDetailRetrieveDto, CdpDictionaryDetailUpdateDto> {
    Page<CdpDictionaryDetailEntity> findAll(String searchCondition, Pageable pageable, Long code);

    List<CdpDictionaryDetailEntity> findByDictionaryId(Long dictionaryId);
}
