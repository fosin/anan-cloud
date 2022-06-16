package top.fosin.anan.platform.modules.pay.service.inter;

import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platform.modules.pay.dto.PayInvoiceReqDto;
import top.fosin.anan.platform.modules.pay.dto.PayInvoiceRespDto;
import top.fosin.anan.platform.modules.pay.entity.PayInvoice;

/**
 * 支付发票表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface PayInvoiceService extends ISimpleJpaService<PayInvoiceReqDto, PayInvoiceRespDto, Long,PayInvoice> {
}
