package com.github.fosin.cdp.platform.controller;

import com.github.fosin.cdp.mvc.controller.ISimpleController;
import com.github.fosin.cdp.mvc.service.ISimpleService;
import com.github.fosin.cdp.platformapi.entity.CdpSysPayDetailEntity;
import com.github.fosin.cdp.platformapi.service.inter.ICdpSysPayDetailService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统支付明细表(table:cdp_sys_pay_detail)表控制层
 *
 * @author fosin
 * @date 2018-11-18 17:50:28
 */
@RestController
@RequestMapping("v1/paydetail")
@Api(value = "v1/paydetail",tags = "系统支付明细表接入层API",description = "系统支付明细表(cdp_sys_pay_detail)接入层API")
public class CdpSysPayDetailController implements ISimpleController<CdpSysPayDetailEntity, Long> {
    /**
     * 服务对象
     */
    @Autowired
    private ICdpSysPayDetailService cdpSysPayDetailService;

    @Override
    public ISimpleService<CdpSysPayDetailEntity, Long> getService() {
        return cdpSysPayDetailService;
    }
}