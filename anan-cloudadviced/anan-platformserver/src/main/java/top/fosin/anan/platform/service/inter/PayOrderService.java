package top.fosin.anan.platform.service.inter;

import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platform.dto.req.AnanPayOrderReqDto;
import top.fosin.anan.platform.dto.res.AnanPayOrderRespDto;
import top.fosin.anan.platform.entity.AnanPayOrderEntity;

/**
 * 支付订单表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface PayOrderService extends ISimpleJpaService<AnanPayOrderEntity, AnanPayOrderRespDto,
        Long, AnanPayOrderReqDto, AnanPayOrderReqDto, AnanPayOrderReqDto> {
}
