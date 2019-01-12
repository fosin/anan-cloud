package com.github.fosin.cdp.platformapi.service.inter;

import com.github.fosin.cdp.platformapi.entity.CdpSysPayInvoiceEntity;
import com.github.fosin.cdp.jpa.service.ISimpleJpaService;

/**
 * 系统支付发票表(cdp_sys_pay_invoice)表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface ICdpSysPayInvoiceService extends ISimpleJpaService<CdpSysPayInvoiceEntity, Long, CdpSysPayInvoiceEntity, CdpSysPayInvoiceEntity, CdpSysPayInvoiceEntity> {
}