package top.fosin.anan.platform.service.inter;

import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platform.dto.req.AnanPayDetailCreateDto;
import top.fosin.anan.platform.dto.req.AnanPayDetailRetrieveDto;
import top.fosin.anan.platform.dto.req.AnanPayDetailUpdateDto;
import top.fosin.anan.platform.dto.res.AnanPayDetailRespDto;
import top.fosin.anan.platform.entity.AnanPayDetailEntity;

/**
 * 系统支付明细系统支付明细表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface PayDetailService extends ISimpleJpaService<AnanPayDetailEntity, AnanPayDetailRespDto,
        Long, AnanPayDetailCreateDto, AnanPayDetailRetrieveDto, AnanPayDetailUpdateDto> {
}
