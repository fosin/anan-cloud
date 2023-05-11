package top.fosin.anan.platform.modules.dictionary.service.inter;

import top.fosin.anan.cloudresource.entity.req.DictionaryDetailReqDTO;
import top.fosin.anan.cloudresource.entity.res.DictionaryDetailRespDTO;
import top.fosin.anan.jpa.service.ICrudBatchJpaService;
import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platform.modules.dictionary.po.DictionaryDetail;

/**
 * @author fosin
 * @date 2017/12/29
 */
public interface DictionaryDetailService extends ISimpleJpaService<DictionaryDetailReqDTO, DictionaryDetailRespDTO, Long,DictionaryDetail>,
        ICrudBatchJpaService<DictionaryDetailReqDTO, DictionaryDetailRespDTO, Long, DictionaryDetail> {
}
