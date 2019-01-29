package com.github.fosin.cdp.platform.service.inter;

import com.github.fosin.cdp.platform.dto.request.CdpSysPayInvoiceCreateDto;
import com.github.fosin.cdp.platform.dto.request.CdpSysPayInvoiceRetrieveDto;
import com.github.fosin.cdp.platform.dto.request.CdpSysPayInvoiceUpdateDto;
import com.github.fosin.cdp.platform.entity.CdpSysPayInvoiceEntity;
import com.github.fosin.cdp.jpa.service.ISimpleJpaService;

/**
 * 系统支付发票表(cdp_sys_pay_invoice)表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface ICdpSysPayInvoiceService extends ISimpleJpaService<CdpSysPayInvoiceEntity, Long, CdpSysPayInvoiceCreateDto, CdpSysPayInvoiceRetrieveDto, CdpSysPayInvoiceUpdateDto> {
}