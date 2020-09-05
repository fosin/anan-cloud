package com.github.fosin.anan.platform.controller;

import com.github.fosin.anan.model.controller.ISimpleController;
import com.github.fosin.anan.model.service.ISimpleService;
import com.github.fosin.anan.platform.dto.request.AnanPayOrderCreateDto;
import com.github.fosin.anan.platform.dto.request.AnanPayOrderRetrieveDto;
import com.github.fosin.anan.platform.dto.request.AnanPayOrderUpdateDto;
import com.github.fosin.anan.platform.entity.AnanPayOrderEntity;
import com.github.fosin.anan.platform.service.inter.AnanPayOrderService;
import io.swagger.annotations.Api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统支付订单表(table:anan_pay_order)表控制层
 *
 * @author fosin
 * @date 2018-11-18 17:50:28
 */
@RestController
@RequestMapping("v1/order")
@Api(value = "v1/order", tags = "系统支付订单表(anan_pay_order)接入层API")
public class AnanPayOrderController implements ISimpleController<AnanPayOrderEntity, Long, AnanPayOrderCreateDto, AnanPayOrderRetrieveDto, AnanPayOrderUpdateDto> {
    /**
     * 服务对象
     */
    private final AnanPayOrderService ananSysPayOrderService;
    public AnanPayOrderController(AnanPayOrderService ananSysPayOrderService) {
        this.ananSysPayOrderService = ananSysPayOrderService;
    }

    @Override
    public ISimpleService<AnanPayOrderEntity, Long, AnanPayOrderCreateDto, AnanPayOrderRetrieveDto, AnanPayOrderUpdateDto> getService() {
        return ananSysPayOrderService;
    }
}
