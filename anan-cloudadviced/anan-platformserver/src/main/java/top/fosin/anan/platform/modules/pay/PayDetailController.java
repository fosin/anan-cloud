package top.fosin.anan.platform.modules.pay;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fosin.anan.cloudresource.constant.PathPrefixConstant;
import top.fosin.anan.data.controller.ICreateController;
import top.fosin.anan.data.controller.IDeleteController;
import top.fosin.anan.data.controller.IRetrieveController;
import top.fosin.anan.data.controller.IUpdateController;
import top.fosin.anan.platform.modules.pay.dto.PayDetailCreateDTO;
import top.fosin.anan.platform.modules.pay.dto.PayDetailUpdateDTO;
import top.fosin.anan.platform.modules.pay.query.PayDetailQuery;
import top.fosin.anan.platform.modules.pay.service.inter.PayDetailService;
import top.fosin.anan.platform.modules.pay.vo.PayDetailListVO;
import top.fosin.anan.platform.modules.pay.vo.PayDetailPageVO;
import top.fosin.anan.platform.modules.pay.vo.PayDetailVO;

/**
 * 支付明细表(table:anan_pay_detail)表控制层
 *
 * @author fosin
 * @date 2018-11-18 17:50:28
 */
@RestController
@RequestMapping(value = PathPrefixConstant.PAY_DETAIL, params = PathPrefixConstant.DEFAULT_VERSION_PARAM)
@Tag(name = "支付明细管理", description = PathPrefixConstant.PAY_DETAIL)
public class PayDetailController implements ICreateController<PayDetailCreateDTO, Long>,
        IUpdateController<PayDetailUpdateDTO, Long>,
        IDeleteController<Long>,
        IRetrieveController<PayDetailQuery, PayDetailVO, PayDetailListVO, PayDetailPageVO, Long> {

    private final PayDetailService payDetailService;

    public PayDetailController(PayDetailService payDetailService) {
        this.payDetailService = payDetailService;
    }

    @Override
    public PayDetailService getService() {
        return payDetailService;
    }
}
