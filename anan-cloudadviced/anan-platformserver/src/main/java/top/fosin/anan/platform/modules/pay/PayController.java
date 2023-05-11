package top.fosin.anan.platform.modules.pay;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fosin.anan.cloudresource.constant.PathPrefixConstant;
import top.fosin.anan.data.controller.ISimpleController;
import top.fosin.anan.platform.modules.pay.dto.PayReqDto;
import top.fosin.anan.platform.modules.pay.dto.PayRespDto;
import top.fosin.anan.platform.modules.pay.service.inter.PayService;

/**
 * 支付表控制层
 *
 * @author fosin
 * @date 2018-11-18 17:50:28
 */
@RestController
@RequestMapping(value = PathPrefixConstant.PAY, params = PathPrefixConstant.DEFAULT_VERSION_PARAM)
@Api(value = PathPrefixConstant.PAY, tags = "支付管理")
public class PayController implements ISimpleController<PayReqDto, PayRespDto, Long> {
    /**
     * 服务对象
     */
    private final PayService payService;

    public PayController(PayService payService) {
        this.payService = payService;
    }

    @Override
    public PayService getService() {
        return payService;
    }
}
