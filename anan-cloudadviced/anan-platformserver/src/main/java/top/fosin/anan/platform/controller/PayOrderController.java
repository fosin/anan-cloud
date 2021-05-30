package top.fosin.anan.platform.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fosin.anan.model.controller.ISimpleController;
import top.fosin.anan.platform.dto.req.AnanPayOrderCreateDto;
import top.fosin.anan.platform.dto.req.AnanPayOrderRetrieveDto;
import top.fosin.anan.platform.dto.req.AnanPayOrderUpdateDto;
import top.fosin.anan.platform.dto.res.AnanPayOrderRespDto;
import top.fosin.anan.platform.service.inter.PayOrderService;

/**
 * 系统支付订单表(table:anan_pay_order)表控制层
 *
 * @author fosin
 * @date 2018-11-18 17:50:28
 */
@RestController
@RequestMapping("v1/order")
@Api(value = "v1/order", tags = "系统支付订单管理")
public class PayOrderController implements ISimpleController<AnanPayOrderRespDto, Long, AnanPayOrderCreateDto, AnanPayOrderRetrieveDto, AnanPayOrderUpdateDto> {
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
