package com.github.fosin.cdp.platform.service;

import com.github.fosin.cdp.platform.repository.CdpPayInvoiceRepository;
import com.github.fosin.cdp.platform.service.inter.CdpPayInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * 系统支付发票表(cdp_pay_invoice)表服务实现类
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Service
@Lazy
public class CdpPayInvoiceServiceImpl implements CdpPayInvoiceService {
    @Autowired
    private CdpPayInvoiceRepository cdpSysPayInvoiceRepository;

    /**
     * 获取DAO
     */
    @Override
    public CdpPayInvoiceRepository getRepository() {
        return cdpSysPayInvoiceRepository;
    }
}
