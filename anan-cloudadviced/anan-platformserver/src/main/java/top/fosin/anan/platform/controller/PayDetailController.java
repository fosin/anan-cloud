package top.fosin.anan.platform.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fosin.anan.cloudresource.constant.UrlPrefixConstant;
import top.fosin.anan.model.controller.ISimpleController;
import top.fosin.anan.platform.dto.req.AnanPayDetailCreateDto;
import top.fosin.anan.platform.dto.req.AnanPayDetailRetrieveDto;
import top.fosin.anan.platform.dto.req.AnanPayDetailUpdateDto;
import top.fosin.anan.platform.dto.res.AnanPayDetailRespDto;
import top.fosin.anan.platform.service.inter.PayDetailService;

/**
 * 支付明细表(table:anan_pay_detail)表控制层
 *
 * @author fosin
 * @date 2018-11-18 17:50:28
 */
@RestController
@RequestMapping(UrlPrefixConstant.PAY_DETAIL)
@Api(value = UrlPrefixConstant.PAY_DETAIL, tags = "支付明细表管理")
public class PayDetailController implements ISimpleController<AnanPayDetailRespDto, Long, AnanPayDetailCreateDto, AnanPayDetailRetrieveDto, AnanPayDetailUpdateDto> {
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
