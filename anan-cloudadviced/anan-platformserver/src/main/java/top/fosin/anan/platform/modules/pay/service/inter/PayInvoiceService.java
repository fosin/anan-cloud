package top.fosin.anan.platform.modules.pay.service.inter;

import top.fosin.anan.jpa.service.ICreateJpaService;
import top.fosin.anan.jpa.service.IDeleteJpaService;
import top.fosin.anan.jpa.service.IRetrieveJpaService;
import top.fosin.anan.jpa.service.IUpdateJpaService;
import top.fosin.anan.platform.modules.pay.dto.PayInvoiceCreateDTO;
import top.fosin.anan.platform.modules.pay.dto.PayInvoiceDTO;
import top.fosin.anan.platform.modules.pay.dto.PayInvoiceUpdateDTO;
import top.fosin.anan.platform.modules.pay.po.PayInvoice;

/**
 * 系统支付发票表(anan_pay_invoice)服务类
 *
 * @author fosin
 * @date 2023-05-11 22:57:02
 */
public interface PayInvoiceService extends
        ICreateJpaService<PayInvoiceCreateDTO, PayInvoiceDTO, Long, PayInvoice>,
        IRetrieveJpaService<PayInvoiceDTO, Long, PayInvoice>,
        IUpdateJpaService<PayInvoiceUpdateDTO, Long, PayInvoice>,
        IDeleteJpaService<Long, PayInvoice> {
}

