package com.github.fosin.cdp.platformapi.service.inter;

import com.github.fosin.cdp.platformapi.entity.CdpSysPayInvoiceEntity;
import com.github.fosin.cdp.platformapi.entity.CdpSysPayOrderEntity;
import com.github.fosin.cdp.mvc.service.ISimpleService;

/**
 * 系统支付订单表(cdp_sys_pay_order)表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface ICdpSysPayOrderService extends ISimpleService<CdpSysPayOrderEntity, Long, CdpSysPayOrderEntity, CdpSysPayOrderEntity, CdpSysPayOrderEntity> {
}