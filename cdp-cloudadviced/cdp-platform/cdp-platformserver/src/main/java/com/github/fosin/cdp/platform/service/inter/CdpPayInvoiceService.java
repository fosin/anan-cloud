package com.github.fosin.cdp.platform.service.inter;

import com.github.fosin.cdp.platform.dto.request.CdpPayInvoiceCreateDto;
import com.github.fosin.cdp.platform.dto.request.CdpPayInvoiceRetrieveDto;
import com.github.fosin.cdp.platform.dto.request.CdpPayInvoiceUpdateDto;
import com.github.fosin.cdp.platform.entity.CdpPayInvoiceEntity;
import com.github.fosin.cdp.jpa.service.ISimpleJpaService;

/**
 * 系统支付发票表(cdp_pay_invoice)表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface CdpPayInvoiceService extends ISimpleJpaService<CdpPayInvoiceEntity, Long, CdpPayInvoiceCreateDto, CdpPayInvoiceRetrieveDto, CdpPayInvoiceUpdateDto> {
}
