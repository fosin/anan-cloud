package top.fosin.anan.platform.service.inter;

import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.model.module.PageModule;
import top.fosin.anan.model.result.ListResult;
import top.fosin.anan.platform.dto.request.AnanInternationalCharsetCreateDto;
import top.fosin.anan.platform.dto.request.AnanInternationalCharsetRetrieveDto;
import top.fosin.anan.platform.dto.request.AnanInternationalCharsetUpdateDto;
import top.fosin.anan.platform.entity.AnanInternationalCharsetEntity;

import java.util.List;

/**
 * 国际化明显(anan_international_charset)表服务接口
 *
 * @author fosin
 * @date 2020-12-04 11:05:28
 */
public interface AnanInternationalCharsetService extends ISimpleJpaService<AnanInternationalCharsetEntity, Long,
        AnanInternationalCharsetCreateDto, AnanInternationalCharsetRetrieveDto, AnanInternationalCharsetUpdateDto> {
    List<AnanInternationalCharsetEntity> findAllByInternationalId(Integer internationalId);

    List<AnanInternationalCharsetEntity> findAllByInternationalIdAndServiceId(Integer internationalId, Integer serviceId);

    ListResult<AnanInternationalCharsetEntity> findAllCharsetPageByinternationalId(PageModule pageModule, Integer internationalId);
}
