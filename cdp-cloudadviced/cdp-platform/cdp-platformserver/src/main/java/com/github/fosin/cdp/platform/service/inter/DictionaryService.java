package com.github.fosin.cdp.platform.service.inter;

import com.github.fosin.cdp.jpa.service.ISimpleJpaService;
import com.github.fosin.cdp.platformapi.dto.request.CdpDictionaryCreateDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpDictionaryRetrieveDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpDictionaryUpdateDto;
import com.github.fosin.cdp.platformapi.entity.CdpDictionaryEntity;

/**
 * 2017/12/29.
 * Time:12:30
 *
 * @author fosin
 */
public interface DictionaryService extends ISimpleJpaService<CdpDictionaryEntity, Long, CdpDictionaryCreateDto, CdpDictionaryRetrieveDto, CdpDictionaryUpdateDto> {
}
