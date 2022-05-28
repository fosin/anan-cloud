package top.fosin.anan.platform.modules.pay.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import top.fosin.anan.platform.modules.pay.dao.PayInvoiceDao;
import top.fosin.anan.platform.modules.pay.service.inter.PayInvoiceService;

/**
 * 支付发票表服务实现类
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Service
@Lazy
public class PayInvoiceServiceImpl implements PayInvoiceService {
    private final PayInvoiceDao ananSysPayInvoiceDao;

    public PayInvoiceServiceImpl(PayInvoiceDao ananSysPayInvoiceDao) {
        this.ananSysPayInvoiceDao = ananSysPayInvoiceDao;
    }

    /**
     * 获取DAO
     */
    @Override
    public PayInvoiceDao getDao() {
        return ananSysPayInvoiceDao;
    }
}
