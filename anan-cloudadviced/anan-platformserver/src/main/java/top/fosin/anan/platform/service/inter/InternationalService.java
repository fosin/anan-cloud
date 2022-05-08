package top.fosin.anan.platform.service.inter;

import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platform.dto.req.AnanInternationalReqDto;
import top.fosin.anan.platform.dto.res.AnanInternationalRespDto;
import top.fosin.anan.platform.entity.AnanInternationalEntity;

import java.util.List;

/**
 * 国际化语言服务接口
 *
 * @author fosin
 * @date 2020-12-04 11:05:28
 */
public interface InternationalService extends ISimpleJpaService<AnanInternationalEntity,
        AnanInternationalRespDto,
        Long,
        AnanInternationalReqDto, AnanInternationalReqDto, AnanInternationalReqDto> {
    List<AnanInternationalRespDto> listByStatus(Integer status);

    AnanInternationalRespDto findByCode(String code);

    AnanInternationalRespDto findByDefaultFlag();
}
