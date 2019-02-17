package com.github.fosin.cdp.platform.service;

import com.github.fosin.cdp.platform.repository.CdpPayOrderRepository;
import com.github.fosin.cdp.platform.service.inter.ICdpPayOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * 系统支付订单表(cdp_pay_order)表服务实现类
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Service
@Lazy
public class CdpPayOrderServiceImpl implements ICdpPayOrderService {
    @Autowired
    private CdpPayOrderRepository cdpSysPayOrderRepository;

    /**
     * 获取DAO
     */
    @Override
    public CdpPayOrderRepository getRepository() {
        return cdpSysPayOrderRepository;
    }
}
