package com.github.fosin.anan.platform.service.inter;

import com.github.fosin.anan.jpa.service.ISimpleJpaService;
import com.github.fosin.anan.platformapi.dto.request.AnanDictionaryCreateDto;
import com.github.fosin.anan.platformapi.dto.request.AnanDictionaryRetrieveDto;
import com.github.fosin.anan.platformapi.dto.request.AnanDictionaryUpdateDto;
import com.github.fosin.anan.platformapi.entity.AnanDictionaryEntity;

/**
 * 2017/12/29.
 * Time:12:30
 *
 * @author fosin
 */
public interface DictionaryService extends ISimpleJpaService<AnanDictionaryEntity, Long, AnanDictionaryCreateDto, AnanDictionaryRetrieveDto, AnanDictionaryUpdateDto> {
}
