package top.fosin.anan.platform.modules.pay.service.inter;

import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platform.modules.pay.dto.PayOrderReqDto;
import top.fosin.anan.platform.modules.pay.dto.PayOrderRespDto;
import top.fosin.anan.platform.modules.pay.entity.PayOrder;

/**
 * 支付订单表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface PayOrderService extends ISimpleJpaService<PayOrder, PayOrderRespDto,
        Long, PayOrderReqDto, PayOrderReqDto, PayOrderReqDto> {
}
