package top.fosin.anan.platform.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fosin.anan.cloudresource.constant.UrlPrefixConstant;
import top.fosin.anan.model.controller.ISimpleController;
import top.fosin.anan.platform.dto.req.AnanPayReqDto;
import top.fosin.anan.platform.dto.req.AnanPayReqDto;
import top.fosin.anan.platform.dto.req.AnanPayReqDto;
import top.fosin.anan.platform.dto.res.AnanPayRespDto;
import top.fosin.anan.platform.service.inter.PayService;

/**
 * 支付表控制层
 *
 * @author fosin
 * @date 2018-11-18 17:50:28
 */
@RestController
@RequestMapping(UrlPrefixConstant.PAY)
@Api(value = UrlPrefixConstant.PAY, tags = "支付管理")
public class PayController implements ISimpleController<AnanPayRespDto, Long, AnanPayReqDto, AnanPayReqDto, AnanPayReqDto> {
    /**
     * 服务对象
     */
    private final PayService ananSysPayService;

    public PayController(PayService ananSysPayService) {
        this.ananSysPayService = ananSysPayService;
    }

    @Override
    public PayService getService() {
        return ananSysPayService;
    }
}
