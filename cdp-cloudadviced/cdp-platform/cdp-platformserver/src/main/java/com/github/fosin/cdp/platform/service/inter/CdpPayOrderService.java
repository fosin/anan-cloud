package com.github.fosin.cdp.platform.service.inter;

import com.github.fosin.cdp.platform.dto.request.CdpPayOrderCreateDto;
import com.github.fosin.cdp.platform.dto.request.CdpPayOrderRetrieveDto;
import com.github.fosin.cdp.platform.dto.request.CdpPayOrderUpdateDto;
import com.github.fosin.cdp.platform.entity.CdpPayInvoiceEntity;
import com.github.fosin.cdp.platform.entity.CdpPayOrderEntity;
import com.github.fosin.cdp.jpa.service.ISimpleJpaService;

/**
 * 系统支付订单表(cdp_pay_order)表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface CdpPayOrderService extends ISimpleJpaService<CdpPayOrderEntity, Long, CdpPayOrderCreateDto, CdpPayOrderRetrieveDto, CdpPayOrderUpdateDto> {
}
