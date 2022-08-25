package top.fosin.anan.platform.modules.pub.service.inter;

import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platform.modules.pub.dto.InternationalReqDto;
import top.fosin.anan.platform.modules.pub.dto.InternationalRespDto;
import top.fosin.anan.platform.modules.pub.po.International;

import java.util.List;

/**
 * 国际化语言服务接口
 *
 * @author fosin
 * @date 2020-12-04 11:05:28
 */
public interface InternationalService extends ISimpleJpaService<InternationalReqDto, InternationalRespDto, Long,International> {
    List<InternationalRespDto> listByStatus(Integer status);

    InternationalRespDto findByCode(String code);

    InternationalRespDto findByDefaultFlag();
}
