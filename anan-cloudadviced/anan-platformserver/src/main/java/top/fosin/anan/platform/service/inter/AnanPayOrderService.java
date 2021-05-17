package top.fosin.anan.platform.service.inter;

import top.fosin.anan.platform.dto.res.AnanPayOrderRespDto;
import top.fosin.anan.platform.dto.request.AnanPayOrderCreateDto;
import top.fosin.anan.platform.dto.request.AnanPayOrderRetrieveDto;
import top.fosin.anan.platform.dto.request.AnanPayOrderUpdateDto;
import top.fosin.anan.platform.entity.AnanPayOrderEntity;
import top.fosin.anan.jpa.service.ISimpleJpaService;

/**
 * 系统支付订单表(anan_pay_order)表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface AnanPayOrderService extends ISimpleJpaService<AnanPayOrderEntity, AnanPayOrderRespDto,
        Long, AnanPayOrderCreateDto, AnanPayOrderRetrieveDto, AnanPayOrderUpdateDto> {
}
