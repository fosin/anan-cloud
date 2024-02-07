package top.fosin.anan.platform.modules.pay;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fosin.anan.cloudresource.constant.PathPrefixConstant;
import top.fosin.anan.data.controller.ICreateController;
import top.fosin.anan.data.controller.IDeleteController;
import top.fosin.anan.data.controller.IRetrieveController;
import top.fosin.anan.data.controller.IUpdateController;
import top.fosin.anan.platform.modules.pay.dto.PayInvoiceCreateDTO;
import top.fosin.anan.platform.modules.pay.dto.PayInvoiceUpdateDTO;
import top.fosin.anan.platform.modules.pay.vo.PayInvoiceListVO;
import top.fosin.anan.platform.modules.pay.vo.PayInvoicePageVO;
import top.fosin.anan.platform.modules.pay.vo.PayInvoiceVO;
import top.fosin.anan.platform.modules.pay.query.PayInvoiceQuery;
import top.fosin.anan.platform.modules.pay.service.inter.PayInvoiceService;

/**
 * 支付发票表(table:anan_pay_invoice)表控制层
 *
 * @author fosin
 * @date 2018-11-18 17:50:28
 */
@RestController
@RequestMapping(value = PathPrefixConstant.PAY_INVOICE, params = PathPrefixConstant.DEFAULT_VERSION_PARAM)
@Tag(name = "支付发票管理", description = PathPrefixConstant.PAY_INVOICE)
public class PayInvoiceController implements ICreateController<PayInvoiceCreateDTO, Long>,
        IUpdateController<PayInvoiceUpdateDTO, Long>,
        IDeleteController<Long>,
        IRetrieveController<PayInvoiceQuery, PayInvoiceVO, PayInvoiceListVO, PayInvoicePageVO, Long> {

    private final PayInvoiceService payInvoiceService;

    public PayInvoiceController(PayInvoiceService payInvoiceService) {
        this.payInvoiceService = payInvoiceService;
    }

    @Override
    public PayInvoiceService getService() {
        return payInvoiceService;
    }
}
