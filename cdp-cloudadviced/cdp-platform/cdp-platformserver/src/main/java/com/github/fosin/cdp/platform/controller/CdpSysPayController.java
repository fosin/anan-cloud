package com.github.fosin.cdp.platform.controller;

import com.github.fosin.cdp.mvc.controller.ISimpleController;
import com.github.fosin.cdp.mvc.service.ISimpleService;
import com.github.fosin.cdp.platformapi.entity.CdpSysPayEntity;
import com.github.fosin.cdp.platformapi.service.inter.ICdpSysPayService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统支付表(table:cdp_sys_pay)表控制层
 *
 * @author fosin
 * @date 2018-11-18 17:50:28
 */
@RestController
@RequestMapping("v1/pay")
@Api(value = "v1/pay",tags = "系统支付表接入层API",description = "系统支付表(cdp_sys_pay)接入层API")
public class CdpSysPayController implements ISimpleController<CdpSysPayEntity, Long> {
    /**
     * 服务对象
     */
    @Autowired
    private ICdpSysPayService cdpSysPayService;

    @Override
    public ISimpleService<CdpSysPayEntity, Long> getService() {
        return cdpSysPayService;
    }
}