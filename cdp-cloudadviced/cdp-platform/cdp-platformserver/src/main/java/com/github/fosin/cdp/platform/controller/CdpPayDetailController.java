package com.github.fosin.cdp.platform.controller;

import com.github.fosin.cdp.mvc.controller.ISimpleController;
import com.github.fosin.cdp.mvc.service.ISimpleService;
import com.github.fosin.cdp.platform.dto.request.CdpPayDetailCreateDto;
import com.github.fosin.cdp.platform.dto.request.CdpPayDetailRetrieveDto;
import com.github.fosin.cdp.platform.dto.request.CdpPayDetailUpdateDto;
import com.github.fosin.cdp.platform.entity.CdpPayDetailEntity;
import com.github.fosin.cdp.platform.service.inter.ICdpPayDetailService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统支付明细表(table:cdp_pay_detail)表控制层
 *
 * @author fosin
 * @date 2018-11-18 17:50:28
 */
@RestController
@RequestMapping("v1/paydetail")
@Api(value = "v1/paydetail", tags = "系统支付明细表接入层API", description = "系统支付明细表(cdp_pay_detail)接入层API")
public class CdpPayDetailController implements ISimpleController<CdpPayDetailEntity, Long, CdpPayDetailCreateDto, CdpPayDetailRetrieveDto, CdpPayDetailUpdateDto> {
    /**
     * 服务对象
     */
    @Autowired
    private ICdpPayDetailService cdpSysPayDetailService;

    @Override
    public ISimpleService<CdpPayDetailEntity, Long, CdpPayDetailCreateDto, CdpPayDetailRetrieveDto, CdpPayDetailUpdateDto> getService() {
        return cdpSysPayDetailService;
    }
}
