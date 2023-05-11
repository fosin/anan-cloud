package top.fosin.anan.platform.modules.pay.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import top.fosin.anan.platform.modules.pay.dao.PayOrderDao;
import top.fosin.anan.platform.modules.pay.service.inter.PayOrderService;

/**
 * 支付订单表服务实现类
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Service
@Lazy
public class PayOrderServiceImpl implements PayOrderService {
    private final PayOrderDao payOrderDao;

    public PayOrderServiceImpl(PayOrderDao payOrderDao) {
        this.payOrderDao = payOrderDao;
    }

    /**
     * 获取DAO
     */
    @Override
    public PayOrderDao getDao() {
        return payOrderDao;
    }
}
