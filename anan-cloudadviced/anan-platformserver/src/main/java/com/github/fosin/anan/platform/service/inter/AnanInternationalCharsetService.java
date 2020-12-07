package com.github.fosin.anan.platform.service.inter;

import com.github.fosin.anan.jpa.service.ISimpleJpaService;
import com.github.fosin.anan.platform.dto.request.AnanInternationalCharsetCreateDto;
import com.github.fosin.anan.platform.dto.request.AnanInternationalCharsetRetrieveDto;
import com.github.fosin.anan.platform.dto.request.AnanInternationalCharsetUpdateDto;
import com.github.fosin.anan.platform.entity.AnanInternationalCharsetEntity;

import java.util.List;
import java.util.Map;

/**
 * 国际化明显(anan_international_charset)表服务接口
 *
 * @author fosin
 * @date 2020-12-04 11:05:28
 */
public interface AnanInternationalCharsetService extends ISimpleJpaService<AnanInternationalCharsetEntity, Long,
        AnanInternationalCharsetCreateDto, AnanInternationalCharsetRetrieveDto, AnanInternationalCharsetUpdateDto> {
    List<AnanInternationalCharsetEntity> findAllByInternationalId(Integer internationalId);

    Map<String, Object> findCharsetByInternationalId(Integer internationalId);
}
