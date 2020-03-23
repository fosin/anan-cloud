package com.github.fosin.anan.platform.service.inter;

import com.github.fosin.anan.platform.dto.request.AnanPayOrderCreateDto;
import com.github.fosin.anan.platform.dto.request.AnanPayOrderRetrieveDto;
import com.github.fosin.anan.platform.dto.request.AnanPayOrderUpdateDto;
import com.github.fosin.anan.platform.entity.AnanPayOrderEntity;
import com.github.fosin.anan.jpa.service.ISimpleJpaService;

/**
 * 系统支付订单表(anan_pay_order)表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface AnanPayOrderService extends ISimpleJpaService<AnanPayOrderEntity, Long, AnanPayOrderCreateDto, AnanPayOrderRetrieveDto, AnanPayOrderUpdateDto> {
}
