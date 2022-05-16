package top.fosin.anan.platform.modules.pub.service.inter;

import top.fosin.anan.cloudresource.dto.req.DictionaryDetailReqDto;
import top.fosin.anan.cloudresource.dto.res.DictionaryDetailRespDto;
import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platform.modules.pub.entity.DictionaryDetail;

import java.util.List;

/**
 * @author fosin
 * @date 2017/12/29
 *
 */
public interface DictionaryDetailService extends ISimpleJpaService<DictionaryDetail, DictionaryDetailRespDto,
        Long, DictionaryDetailReqDto, DictionaryDetailReqDto, DictionaryDetailReqDto> {
    List<DictionaryDetailRespDto> findByDictionaryId(Long dictionaryId);
}
