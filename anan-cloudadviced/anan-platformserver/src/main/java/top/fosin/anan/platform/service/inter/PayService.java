package top.fosin.anan.platform.service.inter;

import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platform.dto.req.AnanPayCreateDto;
import top.fosin.anan.platform.dto.req.AnanPayRetrieveDto;
import top.fosin.anan.platform.dto.req.AnanPayUpdateDto;
import top.fosin.anan.platform.dto.res.AnanPayRespDto;
import top.fosin.anan.platform.entity.AnanPayEntity;

/**
 * 支付表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface PayService extends ISimpleJpaService<AnanPayEntity, AnanPayRespDto,
        Long, AnanPayCreateDto, AnanPayRetrieveDto, AnanPayUpdateDto> {
}
