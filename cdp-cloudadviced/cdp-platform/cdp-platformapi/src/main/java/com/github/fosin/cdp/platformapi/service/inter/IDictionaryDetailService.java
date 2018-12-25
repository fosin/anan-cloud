package com.github.fosin.cdp.platformapi.service.inter;

import com.github.fosin.cdp.platformapi.entity.CdpSysDictionaryDetailEntity;
import com.github.fosin.cdp.core.exception.CdpServiceException;
import com.github.fosin.cdp.mvc.service.ISimpleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 2017/12/29.
 * Time:12:30
 *
 * @author fosin
 */
public interface IDictionaryDetailService extends ISimpleService<CdpSysDictionaryDetailEntity, Long, CdpSysDictionaryDetailEntity, CdpSysDictionaryDetailEntity> {
    Page<CdpSysDictionaryDetailEntity> findAll(String searchCondition, Pageable pageable, Long code) throws CdpServiceException;

    List<CdpSysDictionaryDetailEntity> findByCode(Long code);
}
