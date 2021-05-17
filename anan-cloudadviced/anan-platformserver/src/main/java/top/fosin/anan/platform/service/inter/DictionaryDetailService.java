package top.fosin.anan.platform.service.inter;

import top.fosin.anan.platform.dto.req.AnanDictionaryDetailCreateDto;
import top.fosin.anan.cloudresource.dto.req.AnanDictionaryDetailRetrieveDto;
import top.fosin.anan.platform.dto.req.AnanDictionaryDetailUpdateDto;
import top.fosin.anan.cloudresource.dto.res.AnanDictionaryDetailRespDto;
import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platform.entity.AnanDictionaryDetailEntity;

import java.util.List;

/**
 * 2017/12/29.
 * Time:12:30
 *
 * @author fosin
 */
public interface DictionaryDetailService extends ISimpleJpaService<AnanDictionaryDetailEntity, AnanDictionaryDetailRespDto,
        Long, AnanDictionaryDetailCreateDto, AnanDictionaryDetailRetrieveDto, AnanDictionaryDetailUpdateDto> {
    List<AnanDictionaryDetailRespDto> findByDictionaryId(Long dictionaryId);
}
