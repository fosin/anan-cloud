package com.github.fosin.anan.platform.service;

import com.github.fosin.anan.platform.repository.AnanPayOrderRepository;
import com.github.fosin.anan.platform.service.inter.AnanPayOrderService;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * 系统支付订单表(anan_pay_order)表服务实现类
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Service
@Lazy
public class AnanPayOrderServiceImpl implements AnanPayOrderService {
    private final AnanPayOrderRepository ananSysPayOrderRepository;

    public AnanPayOrderServiceImpl(AnanPayOrderRepository ananSysPayOrderRepository) {
        this.ananSysPayOrderRepository = ananSysPayOrderRepository;
    }

    /**
     * 获取DAO
     */
    @Override
    public AnanPayOrderRepository getRepository() {
        return ananSysPayOrderRepository;
    }
}
