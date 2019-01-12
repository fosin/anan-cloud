package com.github.fosin.cdp.platformapi.service;

import com.github.fosin.cdp.platformapi.repository.CdpSysPayInvoiceRepository;
import com.github.fosin.cdp.platformapi.service.inter.ICdpSysPayInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * 系统支付发票表(cdp_sys_pay_invoice)表服务实现类
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Service
@Lazy
public class CdpSysPayInvoiceServiceImpl implements ICdpSysPayInvoiceService {
    @Autowired
    private CdpSysPayInvoiceRepository cdpSysPayInvoiceRepository;

    /**
     * 获取DAO
     */
    @Override
    public CdpSysPayInvoiceRepository getRepository() {
        return cdpSysPayInvoiceRepository;
    }
}