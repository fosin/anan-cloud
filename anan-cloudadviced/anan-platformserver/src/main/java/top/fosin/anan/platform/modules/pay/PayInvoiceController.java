package top.fosin.anan.platform.modules.pay;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fosin.anan.model.controller.ISimpleController;
import top.fosin.anan.platform.modules.pay.dto.PayInvoiceReqDto;
import top.fosin.anan.platform.modules.pay.dto.PayInvoiceRespDto;
import top.fosin.anan.platform.modules.pay.service.inter.PayInvoiceService;

/**
 * 支付发票表(table:anan_pay_invoice)表控制层
 *
 * @author fosin
 * @date 2018-11-18 17:50:28
 */
@RestController
@RequestMapping("v1/invoice")
@Api(value = "v1/invoice", tags = "支付发票管理")
public class PayInvoiceController implements ISimpleController<PayInvoiceRespDto, Long, PayInvoiceReqDto, PayInvoiceReqDto, PayInvoiceReqDto> {
    /**
     * 服务对象
     */
    private final PayInvoiceService ananSysPayInvoiceService;

    public PayInvoiceController(PayInvoiceService ananSysPayInvoiceService) {
        this.ananSysPayInvoiceService = ananSysPayInvoiceService;
    }

    @Override
    public PayInvoiceService getService() {
        return ananSysPayInvoiceService;
    }
}
