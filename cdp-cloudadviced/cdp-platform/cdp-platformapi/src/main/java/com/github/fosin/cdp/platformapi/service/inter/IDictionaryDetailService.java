package com.github.fosin.cdp.platformapi.service.inter;

import com.github.fosin.cdp.platformapi.dto.request.CdpSysDictionaryDetailCreateDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpSysDictionaryDetailRetrieveDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpSysDictionaryDetailUpdateDto;
import com.github.fosin.cdp.platformapi.entity.CdpSysDictionaryDetailEntity;
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
public interface IDictionaryDetailService extends ISimpleJpaService<CdpSysDictionaryDetailEntity, Long, CdpSysDictionaryDetailCreateDto, CdpSysDictionaryDetailRetrieveDto, CdpSysDictionaryDetailUpdateDto> {
    Page<CdpSysDictionaryDetailEntity> findAll(String searchCondition, Pageable pageable, Long code);

    List<CdpSysDictionaryDetailEntity> findByCode(Long code);
}
