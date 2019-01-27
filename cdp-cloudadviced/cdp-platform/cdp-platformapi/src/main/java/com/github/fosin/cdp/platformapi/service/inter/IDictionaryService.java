package com.github.fosin.cdp.platformapi.service.inter;

import com.github.fosin.cdp.jpa.service.ISimpleJpaService;
import com.github.fosin.cdp.platformapi.dto.request.CdpSysDictionaryCreateDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpSysDictionaryRetrieveDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpSysDictionaryUpdateDto;
import com.github.fosin.cdp.platformapi.entity.CdpSysDictionaryEntity;

/**
 * 2017/12/29.
 * Time:12:30
 *
 * @author fosin
 */
public interface IDictionaryService extends ISimpleJpaService<CdpSysDictionaryEntity, Long, CdpSysDictionaryCreateDto, CdpSysDictionaryRetrieveDto, CdpSysDictionaryUpdateDto> {
}
