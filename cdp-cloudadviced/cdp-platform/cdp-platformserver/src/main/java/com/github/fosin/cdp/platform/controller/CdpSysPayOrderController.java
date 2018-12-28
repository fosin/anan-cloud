package com.github.fosin.cdp.platform.controller;

import com.github.fosin.cdp.mvc.controller.ISimpleController;
import com.github.fosin.cdp.mvc.service.ISimpleService;
import com.github.fosin.cdp.platformapi.entity.CdpSysPayOrderEntity;
import com.github.fosin.cdp.platformapi.service.inter.ICdpSysPayOrderService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统支付订单表(table:cdp_sys_pay_order)表控制层
 *
 * @author fosin
 * @date 2018-11-18 17:50:28
 */
@RestController
@RequestMapping("v1/order")
@Api(value = "v1/order", tags = "系统支付订单表接入层API", description = "系统支付订单表(cdp_sys_pay_order)接入层API")
public class CdpSysPayOrderController implements ISimpleController<CdpSysPayOrderEntity, Long, CdpSysPayOrderEntity, CdpSysPayOrderEntity, CdpSysPayOrderEntity> {
    /**
     * 服务对象
     */
    @Autowired
    private ICdpSysPayOrderService cdpSysPayOrderService;

    @Override
    public ISimpleService<CdpSysPayOrderEntity, Long, CdpSysPayOrderEntity, CdpSysPayOrderEntity, CdpSysPayOrderEntity> getService() {
        return cdpSysPayOrderService;
    }
}