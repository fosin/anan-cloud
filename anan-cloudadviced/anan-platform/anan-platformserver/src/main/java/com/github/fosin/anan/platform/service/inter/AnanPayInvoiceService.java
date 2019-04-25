package com.github.fosin.anan.platform.service.inter;

import com.github.fosin.anan.platform.dto.request.AnanPayInvoiceCreateDto;
import com.github.fosin.anan.platform.dto.request.AnanPayInvoiceRetrieveDto;
import com.github.fosin.anan.platform.dto.request.AnanPayInvoiceUpdateDto;
import com.github.fosin.anan.platform.entity.AnanPayInvoiceEntity;
import com.github.fosin.anan.jpa.service.ISimpleJpaService;

/**
 * 系统支付发票表(anan_pay_invoice)表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface AnanPayInvoiceService extends ISimpleJpaService<AnanPayInvoiceEntity, Long, AnanPayInvoiceCreateDto, AnanPayInvoiceRetrieveDto, AnanPayInvoiceUpdateDto> {
}
