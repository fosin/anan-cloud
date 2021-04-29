package top.fosin.anan.platform.controller;

import top.fosin.anan.model.controller.ISimpleController;
import top.fosin.anan.model.service.ISimpleService;
import top.fosin.anan.platform.dto.request.AnanPayCreateDto;
import top.fosin.anan.platform.dto.request.AnanPayRetrieveDto;
import top.fosin.anan.platform.dto.request.AnanPayUpdateDto;
import top.fosin.anan.platform.entity.AnanPayEntity;
import top.fosin.anan.platform.service.inter.AnanPayService;
import top.fosin.anan.cloudresource.constant.UrlPrefixConstant;
import io.swagger.annotations.Api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统支付表(table:anan_pay)表控制层
 *
 * @author fosin
 * @date 2018-11-18 17:50:28
 */
@RestController
@RequestMapping(UrlPrefixConstant.PAY)
@Api(value = UrlPrefixConstant.PAY, tags = "系统支付表(anan_pay)接入层API")
public class AnanPayController implements ISimpleController<AnanPayEntity, Long, AnanPayCreateDto, AnanPayRetrieveDto, AnanPayUpdateDto> {
    /**
     * 服务对象
     */
    private final AnanPayService ananSysPayService;

    public AnanPayController(AnanPayService ananSysPayService) {
        this.ananSysPayService = ananSysPayService;
    }

    @Override
    public ISimpleService<AnanPayEntity, Long, AnanPayCreateDto, AnanPayRetrieveDto, AnanPayUpdateDto> getService() {
        return ananSysPayService;
    }
}
