package top.fosin.anan.platform.modules.pay;

import top.fosin.anan.cloudresource.constant.PathPrefixConstant;
import top.fosin.anan.platform.modules.pay.service.inter.PayService;
import top.fosin.anan.platform.modules.pay.dto.PayCreateDTO;
import top.fosin.anan.platform.modules.pay.dto.PayUpdateDTO;
import top.fosin.anan.platform.modules.pay.query.PayQuery;
import top.fosin.anan.platform.modules.pay.vo.PayVO;
import top.fosin.anan.platform.modules.pay.vo.PayListVO;
import top.fosin.anan.platform.modules.pay.vo.PayPageVO;
import top.fosin.anan.data.controller.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 系统支付表(anan_pay)控制类
 *
 * @author fosin
 * @date 2023-05-11
 */
@RestController
@Tag(name = "支付管理", description = PathPrefixConstant.PAY)
@RequestMapping(value = PathPrefixConstant.PAY, params = PathPrefixConstant.DEFAULT_VERSION_PARAM)
public class PayController implements ICreateController<PayCreateDTO, Long>,
        IUpdateController<PayUpdateDTO, Long>,
        IDeleteController<Long>,
        IRetrieveController<PayQuery, PayVO, PayListVO, PayPageVO, Long> {

    private final PayService payService;

    public PayController(PayService payService) {
        this.payService = payService;
    }

    @Override
    public PayService getService() {
        return payService;
    }
}

