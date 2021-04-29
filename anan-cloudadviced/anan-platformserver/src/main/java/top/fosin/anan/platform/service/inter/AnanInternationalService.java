package top.fosin.anan.platform.service.inter;

import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platform.dto.request.AnanInternationalCreateDto;
import top.fosin.anan.platform.dto.request.AnanInternationalRetrieveDto;
import top.fosin.anan.platform.dto.request.AnanInternationalUpdateDto;
import top.fosin.anan.platform.entity.AnanInternationalEntity;

import java.util.List;

/**
 * 国际化(anan_international)表服务接口
 *
 * @author fosin
 * @date 2020-12-04 11:05:28
 */
public interface AnanInternationalService extends ISimpleJpaService<AnanInternationalEntity, Integer,
        AnanInternationalCreateDto, AnanInternationalRetrieveDto, AnanInternationalUpdateDto> {
    List<AnanInternationalEntity> findAllByStatus(Integer status);

    AnanInternationalEntity findByCode(String code);

    AnanInternationalEntity findByDefaultFlag();
}
