package top.fosin.anan.platform.modules.dictionary.service.inter;

import top.fosin.anan.cloudresource.entity.req.DictionaryReqDTO;
import top.fosin.anan.cloudresource.entity.res.DictionaryRespDTO;
import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platform.modules.dictionary.po.Dictionary;

/**
 * @author fosin
 * @date 2017/12/29
 */
public interface DictionaryService extends ISimpleJpaService<DictionaryReqDTO, DictionaryRespDTO, Long, Dictionary> {
}
