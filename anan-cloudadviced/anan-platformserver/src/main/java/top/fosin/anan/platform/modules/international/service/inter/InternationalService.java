package top.fosin.anan.platform.modules.international.service.inter;

import top.fosin.anan.jpa.service.ICreateJpaService;
import top.fosin.anan.jpa.service.IDeleteJpaService;
import top.fosin.anan.jpa.service.IRetrieveJpaService;
import top.fosin.anan.jpa.service.IUpdateJpaService;
import top.fosin.anan.platform.modules.international.dto.InternationalCreateDTO;
import top.fosin.anan.platform.modules.international.dto.InternationalDTO;
import top.fosin.anan.platform.modules.international.dto.InternationalUpdateDTO;
import top.fosin.anan.platform.modules.international.po.International;

import java.util.List;

/**
 * 国际化语言服务接口
 *
 * @author fosin
 * @date 2020-12-04 11:05:28
 */
public interface InternationalService extends ICreateJpaService<InternationalCreateDTO, InternationalDTO, Long, International>,
        IRetrieveJpaService<InternationalDTO, Long, International>,
        IUpdateJpaService<InternationalUpdateDTO, Long, International>,
        IDeleteJpaService<Long, International> {
    List<InternationalDTO> listByStatus(Integer status);

    InternationalDTO findByCode(String code);

    InternationalDTO findByDefaultFlag();
}
