package top.fosin.anan.platform.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import top.fosin.anan.platform.repository.PayOrderRepository;
import top.fosin.anan.platform.service.inter.PayOrderService;

/**
 * 支付订单表服务实现类
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Service
@Lazy
public class PayOrderServiceImpl implements PayOrderService {
    private final PayOrderRepository ananSysPayOrderRepository;

    public PayOrderServiceImpl(PayOrderRepository ananSysPayOrderRepository) {
        this.ananSysPayOrderRepository = ananSysPayOrderRepository;
    }

    /**
     * 获取DAO
     */
    @Override
    public PayOrderRepository getRepository() {
        return ananSysPayOrderRepository;
    }
}
