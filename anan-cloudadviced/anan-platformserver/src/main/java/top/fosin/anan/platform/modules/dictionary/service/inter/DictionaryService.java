package top.fosin.anan.platform.modules.dictionary.service.inter;

import top.fosin.anan.cloudresource.entity.res.DictionaryRespDTO;
import top.fosin.anan.jpa.service.ICreateJpaService;
import top.fosin.anan.jpa.service.IDeleteJpaService;
import top.fosin.anan.jpa.service.IRetrieveJpaService;
import top.fosin.anan.jpa.service.IUpdateJpaService;
import top.fosin.anan.platform.modules.dictionary.dto.DictionaryCreateDTO;
import top.fosin.anan.platform.modules.dictionary.dto.DictionaryUpdateDTO;
import top.fosin.anan.platform.modules.dictionary.po.Dictionary;

/**
 * @author fosin
 * @date 2017/12/29
 */
public interface DictionaryService extends
        ICreateJpaService<DictionaryCreateDTO, DictionaryRespDTO, Long, Dictionary>,
        IRetrieveJpaService<DictionaryRespDTO, Long, Dictionary>,
        IUpdateJpaService<DictionaryUpdateDTO, Long, Dictionary>,
        IDeleteJpaService<Long, Dictionary> {
}
