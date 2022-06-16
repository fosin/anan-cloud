package top.fosin.anan.platform.modules.pay;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fosin.anan.model.controller.ISimpleController;
import top.fosin.anan.platform.modules.pay.dto.PayOrderReqDto;
import top.fosin.anan.platform.modules.pay.dto.PayOrderRespDto;
import top.fosin.anan.platform.modules.pay.service.inter.PayOrderService;

/**
 * 支付订单表(table:anan_pay_order)表控制层
 *
 * @author fosin
 * @date 2018-11-18 17:50:28
 */
@RestController
@RequestMapping("v1/order")
@Api(value = "v1/order", tags = "支付订单管理")
public class PayOrderController implements ISimpleController<PayOrderReqDto, PayOrderRespDto, Long> {
    /**
     * 服务对象
     */
    private final PayOrderService ananSysPayOrderService;

    public PayOrderController(PayOrderService ananSysPayOrderService) {
        this.ananSysPayOrderService = ananSysPayOrderService;
    }

    @Override
    public PayOrderService getService() {
        return ananSysPayOrderService;
    }
}
