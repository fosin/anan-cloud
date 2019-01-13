package com.github.fosin.cdp.platform.service;

import com.github.fosin.cdp.platform.repository.CdpSysPayOrderRepository;
import com.github.fosin.cdp.platform.service.inter.ICdpSysPayOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * 系统支付订单表(cdp_sys_pay_order)表服务实现类
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Service
@Lazy
public class CdpSysPayOrderServiceImpl implements ICdpSysPayOrderService {
    @Autowired
    private CdpSysPayOrderRepository cdpSysPayOrderRepository;

    /**
     * 获取DAO
     */
    @Override
    public CdpSysPayOrderRepository getRepository() {
        return cdpSysPayOrderRepository;
    }
}