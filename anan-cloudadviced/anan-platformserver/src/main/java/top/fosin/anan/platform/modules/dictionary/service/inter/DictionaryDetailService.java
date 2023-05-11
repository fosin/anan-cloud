package top.fosin.anan.platform.modules.dictionary.service.inter;

import top.fosin.anan.cloudresource.entity.res.DictionaryDetailRespDTO;
import top.fosin.anan.jpa.service.*;
import top.fosin.anan.platform.modules.dictionary.dto.DictionaryDetailCreateDTO;
import top.fosin.anan.platform.modules.dictionary.dto.DictionaryDetailUpdateDTO;
import top.fosin.anan.platform.modules.dictionary.po.DictionaryDetail;

/**
 * @author fosin
 * @date 2017/12/29
 */
public interface DictionaryDetailService extends
        ICreateJpaService<DictionaryDetailCreateDTO, DictionaryDetailRespDTO, Long, DictionaryDetail>,
        IRetrieveJpaService<DictionaryDetailRespDTO, Long, DictionaryDetail>,
        IUpdateJpaService<DictionaryDetailUpdateDTO, Long, DictionaryDetail>,
        IDeleteJpaService<Long, DictionaryDetail>,
        ICrudBatchJpaService<DictionaryDetailUpdateDTO, DictionaryDetailRespDTO, Long, DictionaryDetail> {
}
