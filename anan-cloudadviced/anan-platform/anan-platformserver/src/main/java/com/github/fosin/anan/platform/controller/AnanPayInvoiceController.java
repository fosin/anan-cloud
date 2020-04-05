package com.github.fosin.anan.platform.controller;

import com.github.fosin.anan.model.controller.ISimpleController;
import com.github.fosin.anan.model.service.ISimpleService;
import com.github.fosin.anan.platform.dto.request.AnanPayInvoiceCreateDto;
import com.github.fosin.anan.platform.dto.request.AnanPayInvoiceRetrieveDto;
import com.github.fosin.anan.platform.dto.request.AnanPayInvoiceUpdateDto;
import com.github.fosin.anan.platform.entity.AnanPayInvoiceEntity;
import com.github.fosin.anan.platform.service.inter.AnanPayInvoiceService;
import io.swagger.annotations.Api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统支付发票表(table:anan_pay_invoice)表控制层
 *
 * @author fosin
 * @date 2018-11-18 17:50:28
 */
@RestController
@RequestMapping("v1/invoice")
@Api(value = "v1/invoice", tags = "系统支付发票表(anan_pay_invoice)接入层API")
public class AnanPayInvoiceController implements ISimpleController<AnanPayInvoiceEntity, Long, AnanPayInvoiceCreateDto, AnanPayInvoiceRetrieveDto, AnanPayInvoiceUpdateDto> {
    /**
     * 服务对象
     */
    private final AnanPayInvoiceService ananSysPayInvoiceService;
    public AnanPayInvoiceController(AnanPayInvoiceService ananSysPayInvoiceService) {
        this.ananSysPayInvoiceService = ananSysPayInvoiceService;
    }

    @Override
    public ISimpleService<AnanPayInvoiceEntity, Long, AnanPayInvoiceCreateDto, AnanPayInvoiceRetrieveDto, AnanPayInvoiceUpdateDto> getService() {
        return ananSysPayInvoiceService;
    }
}
