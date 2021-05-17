package top.fosin.anan.platform.service.inter;

import top.fosin.anan.platform.dto.res.AnanInternationalCharsetRespDto;
import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platform.dto.req.AnanInternationalCharsetCreateDto;
import top.fosin.anan.platform.dto.req.AnanInternationalCharsetRetrieveDto;
import top.fosin.anan.platform.dto.req.AnanInternationalCharsetUpdateDto;
import top.fosin.anan.platform.entity.AnanInternationalCharsetEntity;

import java.util.List;

/**
 * 国际化明显(anan_international_charset)表服务接口
 *
 * @author fosin
 * @date 2020-12-04 11:05:28
 */
public interface AnanInternationalCharsetService extends ISimpleJpaService<AnanInternationalCharsetEntity,
        AnanInternationalCharsetRespDto,
        Long,
        AnanInternationalCharsetCreateDto,
        AnanInternationalCharsetRetrieveDto,
        AnanInternationalCharsetUpdateDto> {
    List<AnanInternationalCharsetRespDto> findAllByInternationalId(Long internationalId);

    List<AnanInternationalCharsetRespDto> findAllByInternationalIdAndServiceId(Long internationalId, Long serviceId);

}
