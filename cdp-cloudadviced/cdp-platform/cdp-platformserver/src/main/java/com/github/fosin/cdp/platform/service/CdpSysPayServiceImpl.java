package com.github.fosin.cdp.platform.service;

import com.github.fosin.cdp.platform.repository.CdpSysPayRepository;
import com.github.fosin.cdp.platform.service.inter.ICdpSysPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * 系统支付表(cdp_sys_pay)表服务实现类
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Service
@Lazy
public class CdpSysPayServiceImpl implements ICdpSysPayService {
    @Autowired
    private CdpSysPayRepository cdpSysPayRepository;

    /**
     * 获取DAO
     */
    @Override
    public CdpSysPayRepository getRepository() {
        return cdpSysPayRepository;
    }
}