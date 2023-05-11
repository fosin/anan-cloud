package top.fosin.anan.platform.modules.pay;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fosin.anan.cloudresource.constant.PathPrefixConstant;
import top.fosin.anan.data.controller.ICreateController;
import top.fosin.anan.data.controller.IDeleteController;
import top.fosin.anan.data.controller.IRetrieveController;
import top.fosin.anan.data.controller.IUpdateController;
import top.fosin.anan.platform.modules.pay.dto.PayOrderCreateDTO;
import top.fosin.anan.platform.modules.pay.dto.PayOrderUpdateDTO;
import top.fosin.anan.platform.modules.pay.vo.PayOrderListVO;
import top.fosin.anan.platform.modules.pay.vo.PayOrderPageVO;
import top.fosin.anan.platform.modules.pay.vo.PayOrderVO;
import top.fosin.anan.platform.modules.pay.query.PayOrderQuery;
import top.fosin.anan.platform.modules.pay.service.inter.PayOrderService;

/**
 * 支付订单表(table:anan_pay_order)表控制层
 *
 * @author fosin
 * @date 2018-11-18 17:50:28
 */
@RestController
@RequestMapping(value = PathPrefixConstant.PAY_ORDER, params = PathPrefixConstant.DEFAULT_VERSION_PARAM)
@Api(value = PathPrefixConstant.PAY_ORDER, tags = "支付订单管理")
public class PayOrderController implements ICreateController<PayOrderCreateDTO, Long>,
        IUpdateController<PayOrderUpdateDTO, Long>,
        IDeleteController<Long>,
        IRetrieveController<PayOrderQuery, PayOrderVO, PayOrderListVO, PayOrderPageVO, Long> {

    private final PayOrderService payOrderService;

    public PayOrderController(PayOrderService payOrderService) {
        this.payOrderService = payOrderService;
    }

    @Override
    public PayOrderService getService() {
        return payOrderService;
    }
}
