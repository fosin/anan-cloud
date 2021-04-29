package top.fosin.anan.platform.service.inter;

import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.cloudresource.dto.request.AnanDictionaryCreateDto;
import top.fosin.anan.cloudresource.dto.request.AnanDictionaryRetrieveDto;
import top.fosin.anan.cloudresource.dto.request.AnanDictionaryUpdateDto;
import top.fosin.anan.platformapi.entity.AnanDictionaryEntity;

/**
 * 2017/12/29.
 * Time:12:30
 *
 * @author fosin
 */
public interface DictionaryService extends ISimpleJpaService<AnanDictionaryEntity, Long, AnanDictionaryCreateDto, AnanDictionaryRetrieveDto, AnanDictionaryUpdateDto> {
}
