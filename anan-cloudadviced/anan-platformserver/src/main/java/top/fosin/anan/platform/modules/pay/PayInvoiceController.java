package top.fosin.anan.platform.modules.pay;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fosin.anan.cloudresource.constant.PathPrefixConstant;
import top.fosin.anan.data.controller.ISimpleController;
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
@RequestMapping(value = PathPrefixConstant.PAY_INVOICE, params = PathPrefixConstant.DEFAULT_VERSION_PARAM)
@Api(value = PathPrefixConstant.PAY_INVOICE, tags = "支付发票管理")
public class PayInvoiceController implements ISimpleController<PayInvoiceReqDto, PayInvoiceRespDto, Long> {
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
