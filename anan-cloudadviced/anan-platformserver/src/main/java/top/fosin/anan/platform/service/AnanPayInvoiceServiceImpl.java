package top.fosin.anan.platform.service;

import top.fosin.anan.platform.repository.AnanPayInvoiceRepository;
import top.fosin.anan.platform.service.inter.AnanPayInvoiceService;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * 系统支付发票表(anan_pay_invoice)表服务实现类
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Service
@Lazy
public class AnanPayInvoiceServiceImpl implements AnanPayInvoiceService {
    private final AnanPayInvoiceRepository ananSysPayInvoiceRepository;

    public AnanPayInvoiceServiceImpl(AnanPayInvoiceRepository ananSysPayInvoiceRepository) {
        this.ananSysPayInvoiceRepository = ananSysPayInvoiceRepository;
    }

    /**
     * 获取DAO
     */
    @Override
    public AnanPayInvoiceRepository getRepository() {
        return ananSysPayInvoiceRepository;
    }
}
