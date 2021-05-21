package top.fosin.anan.platform.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fosin.anan.cloudresource.constant.UrlPrefixConstant;
import top.fosin.anan.platform.dto.res.AnanPayRespDto;
import top.fosin.anan.model.controller.ISimpleController;
import top.fosin.anan.platform.dto.req.AnanPayCreateDto;
import top.fosin.anan.platform.dto.req.AnanPayRetrieveDto;
import top.fosin.anan.platform.dto.req.AnanPayUpdateDto;
import top.fosin.anan.platform.service.inter.AnanPayService;

/**
 * 系统支付表控制层
 *
 * @author fosin
 * @date 2018-11-18 17:50:28
 */
@RestController
@RequestMapping(UrlPrefixConstant.PAY)
@Api(value = UrlPrefixConstant.PAY, tags = "系统支付管理")
public class AnanPayController implements ISimpleController<AnanPayRespDto, Long, AnanPayCreateDto, AnanPayRetrieveDto, AnanPayUpdateDto> {
    /**
     * 服务对象
     */
    private final AnanPayService ananSysPayService;

    public AnanPayController(AnanPayService ananSysPayService) {
        this.ananSysPayService = ananSysPayService;
    }

    @Override
    public AnanPayService getService() {
        return ananSysPayService;
    }
}
