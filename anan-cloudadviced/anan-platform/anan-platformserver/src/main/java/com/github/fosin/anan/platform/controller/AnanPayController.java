package com.github.fosin.anan.platform.controller;

import com.github.fosin.anan.mvc.controller.ISimpleController;
import com.github.fosin.anan.mvc.service.ISimpleService;
import com.github.fosin.anan.platform.dto.request.AnanPayCreateDto;
import com.github.fosin.anan.platform.dto.request.AnanPayRetrieveDto;
import com.github.fosin.anan.platform.dto.request.AnanPayUpdateDto;
import com.github.fosin.anan.platform.entity.AnanPayEntity;
import com.github.fosin.anan.platform.service.inter.AnanPayService;
import com.github.fosin.anan.platformapi.constant.UrlPrefixConstant;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
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
@Api(value = UrlPrefixConstant.PAY, tags = "系统支付表接入层API", description = "系统支付表(anan_pay)接入层API")
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
