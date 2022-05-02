package top.fosin.anan.platform.service.inter;

import top.fosin.anan.jpa.service.ISimpleJpaService;


import top.fosin.anan.platform.dto.req.AnanInternationalCharsetReqDto;
import top.fosin.anan.platform.dto.req.AnanInternationalCharsetReqDto;
import top.fosin.anan.platform.dto.req.AnanInternationalCharsetReqDto;
import top.fosin.anan.platform.dto.res.AnanInternationalCharsetRespDto;
import top.fosin.anan.platform.entity.AnanInternationalCharsetEntity;

import java.util.List;

/**
 * 国际化明细服务接口
 *
 * @author fosin
 * @date 2020-12-04 11:05:28
 */
public interface InternationalCharsetService extends ISimpleJpaService<AnanInternationalCharsetEntity,
        AnanInternationalCharsetRespDto,
        Long,
        AnanInternationalCharsetReqDto,
        AnanInternationalCharsetReqDto,
        AnanInternationalCharsetReqDto> {

    List<AnanInternationalCharsetRespDto> findAllByInternationalId(Long internationalId);

}
