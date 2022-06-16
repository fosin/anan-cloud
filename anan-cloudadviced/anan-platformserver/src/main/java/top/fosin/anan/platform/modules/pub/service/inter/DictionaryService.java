package top.fosin.anan.platform.modules.pub.service.inter;

import top.fosin.anan.cloudresource.dto.req.DictionaryReqDto;
import top.fosin.anan.cloudresource.dto.res.DictionaryRespDto;
import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platform.modules.pub.entity.Dictionary;

/**
 * @author fosin
 * @date 2017/12/29
 *
 */
public interface DictionaryService extends ISimpleJpaService<DictionaryReqDto, DictionaryRespDto, Long,Dictionary> {
}
