package top.fosin.anan.platform.modules.pay;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fosin.anan.cloudresource.constant.PathPrefixConstant;
import top.fosin.anan.data.controller.ISimpleController;
import top.fosin.anan.platform.modules.pay.dto.PayDetailReqDto;
import top.fosin.anan.platform.modules.pay.dto.PayDetailRespDto;
import top.fosin.anan.platform.modules.pay.service.inter.PayDetailService;

/**
 * 支付明细表(table:anan_pay_detail)表控制层
 *
 * @author fosin
 * @date 2018-11-18 17:50:28
 */
@RestController
@RequestMapping(value = PathPrefixConstant.PAY_DETAIL, params = PathPrefixConstant.DEFAULT_VERSION_PARAM)
@Api(value = PathPrefixConstant.PAY_DETAIL, tags = "支付明细表管理")
public class PayDetailController implements ISimpleController<PayDetailReqDto, PayDetailRespDto, Long> {
    /**
     * 服务对象
     */
    private final PayDetailService ananSysPayDetailService;

    public PayDetailController(PayDetailService ananSysPayDetailService) {
        this.ananSysPayDetailService = ananSysPayDetailService;
    }

    @Override
    public PayDetailService getService() {
        return ananSysPayDetailService;
    }
}
