package top.fosin.anan.platform.service.inter;

import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.jpa.service.IStatusJpaService;
import top.fosin.anan.platform.dto.req.AnanInternationalCreateDto;
import top.fosin.anan.platform.dto.req.AnanInternationalRetrieveDto;
import top.fosin.anan.platform.dto.req.AnanInternationalUpdateDto;
import top.fosin.anan.platform.dto.res.AnanInternationalRespDto;
import top.fosin.anan.platform.entity.AnanInternationalEntity;

/**
 * 国际化语言服务接口
 *
 * @author fosin
 * @date 2020-12-04 11:05:28
 */
public interface InternationalService extends ISimpleJpaService<AnanInternationalEntity,
        AnanInternationalRespDto,
        Long,
        AnanInternationalCreateDto, AnanInternationalRetrieveDto, AnanInternationalUpdateDto>,
        IStatusJpaService<AnanInternationalEntity, AnanInternationalRespDto, Long, Integer> {
    AnanInternationalRespDto findByCode(String code);

    AnanInternationalRespDto findByDefaultFlag();
}
