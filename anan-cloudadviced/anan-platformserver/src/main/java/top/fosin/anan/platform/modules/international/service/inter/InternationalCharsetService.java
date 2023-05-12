package top.fosin.anan.platform.modules.international.service.inter;

import top.fosin.anan.jpa.service.*;
import top.fosin.anan.platform.modules.international.dto.InternationalCharsetCreateDTO;
import top.fosin.anan.platform.modules.international.dto.InternationalCharsetDTO;
import top.fosin.anan.platform.modules.international.dto.InternationalCharsetUpdateDTO;
import top.fosin.anan.platform.modules.international.po.InternationalCharset;

/**
 * 国际化明细服务接口
 *
 * @author fosin
 * @date 2020-12-04 11:05:28
 */
public interface InternationalCharsetService extends ICreateJpaService<InternationalCharsetCreateDTO, InternationalCharsetDTO, Long, InternationalCharset>,
        IRetrieveJpaService<InternationalCharsetDTO, Long, InternationalCharset>,
        IUpdateJpaService<InternationalCharsetUpdateDTO, Long, InternationalCharset>,
        ICrudBatchJpaService<InternationalCharsetDTO, InternationalCharsetDTO, Long, InternationalCharset>,
        IDeleteJpaService<Long, InternationalCharset> {

}
