package com.github.fosin.cdp.platform.controller;

import com.github.fosin.cdp.mvc.controller.ISimpleController;
import com.github.fosin.cdp.mvc.service.ISimpleService;
import com.github.fosin.cdp.platform.dto.request.CdpPayOrderCreateDto;
import com.github.fosin.cdp.platform.dto.request.CdpPayOrderRetrieveDto;
import com.github.fosin.cdp.platform.dto.request.CdpPayOrderUpdateDto;
import com.github.fosin.cdp.platform.entity.CdpPayOrderEntity;
import com.github.fosin.cdp.platform.service.inter.ICdpPayOrderService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统支付订单表(table:cdp_pay_order)表控制层
 *
 * @author fosin
 * @date 2018-11-18 17:50:28
 */
@RestController
@RequestMapping("v1/order")
@Api(value = "v1/order", tags = "系统支付订单表接入层API", description = "系统支付订单表(cdp_pay_order)接入层API")
public class CdpPayOrderController implements ISimpleController<CdpPayOrderEntity, Long, CdpPayOrderCreateDto, CdpPayOrderRetrieveDto, CdpPayOrderUpdateDto> {
    /**
     * 服务对象
     */
    @Autowired
    private ICdpPayOrderService cdpSysPayOrderService;

    @Override
    public ISimpleService<CdpPayOrderEntity, Long, CdpPayOrderCreateDto, CdpPayOrderRetrieveDto, CdpPayOrderUpdateDto> getService() {
        return cdpSysPayOrderService;
    }
}
