package top.fosin.anan.platform.modules.pub.service.inter;

import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platform.modules.pub.dto.InternationalCharsetReqDto;
import top.fosin.anan.platform.modules.pub.dto.InternationalCharsetRespDto;
import top.fosin.anan.platform.modules.pub.entity.InternationalCharset;

import java.util.List;

/**
 * 国际化明细服务接口
 *
 * @author fosin
 * @date 2020-12-04 11:05:28
 */
public interface InternationalCharsetService extends ISimpleJpaService<InternationalCharsetReqDto, InternationalCharsetRespDto, Long,InternationalCharset> {

    List<InternationalCharsetRespDto> findAllByInternationalId(Long internationalId);

}
