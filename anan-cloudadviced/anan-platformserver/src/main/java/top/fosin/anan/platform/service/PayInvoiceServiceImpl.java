package top.fosin.anan.platform.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import top.fosin.anan.platform.repository.PayInvoiceRepository;
import top.fosin.anan.platform.service.inter.PayInvoiceService;

/**
 * 支付发票表服务实现类
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Service
@Lazy
public class PayInvoiceServiceImpl implements PayInvoiceService {
    private final PayInvoiceRepository ananSysPayInvoiceRepository;

    public PayInvoiceServiceImpl(PayInvoiceRepository ananSysPayInvoiceRepository) {
        this.ananSysPayInvoiceRepository = ananSysPayInvoiceRepository;
    }

    /**
     * 获取DAO
     */
    @Override
    public PayInvoiceRepository getRepository() {
        return ananSysPayInvoiceRepository;
    }
}
