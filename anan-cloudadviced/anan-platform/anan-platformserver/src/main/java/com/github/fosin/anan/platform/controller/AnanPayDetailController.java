package com.github.fosin.anan.platform.controller;

import com.github.fosin.anan.model.controller.ISimpleController;
import com.github.fosin.anan.model.service.ISimpleService;
import com.github.fosin.anan.platform.dto.request.AnanPayDetailCreateDto;
import com.github.fosin.anan.platform.dto.request.AnanPayDetailRetrieveDto;
import com.github.fosin.anan.platform.dto.request.AnanPayDetailUpdateDto;
import com.github.fosin.anan.platform.entity.AnanPayDetailEntity;
import com.github.fosin.anan.platform.service.inter.AnanPayDetailService;
import com.github.fosin.anan.platformapi.constant.UrlPrefixConstant;
import io.swagger.annotations.Api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统支付明细表(table:anan_pay_detail)表控制层
 *
 * @author fosin
 * @date 2018-11-18 17:50:28
 */
@RestController
@RequestMapping(UrlPrefixConstant.PAY_DETAIL)
@Api(value = UrlPrefixConstant.PAY_DETAIL, tags = "系统支付明细表接入层API")
public class AnanPayDetailController implements ISimpleController<AnanPayDetailEntity, Long, AnanPayDetailCreateDto, AnanPayDetailRetrieveDto, AnanPayDetailUpdateDto> {
    /**
     * 服务对象
     */
    private final AnanPayDetailService ananSysPayDetailService;
    public AnanPayDetailController(AnanPayDetailService ananSysPayDetailService) {
        this.ananSysPayDetailService = ananSysPayDetailService;
    }

    @Override
    public ISimpleService<AnanPayDetailEntity, Long, AnanPayDetailCreateDto, AnanPayDetailRetrieveDto, AnanPayDetailUpdateDto> getService() {
        return ananSysPayDetailService;
    }
}
