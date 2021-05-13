package top.fosin.anan.platform.service.inter;

import top.fosin.anan.cloudresource.dto.request.AnanDictionaryDetailCreateDto;
import top.fosin.anan.cloudresource.dto.request.AnanDictionaryDetailRetrieveDto;
import top.fosin.anan.cloudresource.dto.request.AnanDictionaryDetailUpdateDto;
import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platformapi.entity.AnanDictionaryDetailEntity;

import java.util.List;

/**
 * 2017/12/29.
 * Time:12:30
 *
 * @author fosin
 */
public interface DictionaryDetailService extends ISimpleJpaService<AnanDictionaryDetailEntity,
        Long, AnanDictionaryDetailCreateDto, AnanDictionaryDetailRetrieveDto, AnanDictionaryDetailUpdateDto> {
    List<AnanDictionaryDetailEntity> findByDictionaryId(Long dictionaryId);
}
