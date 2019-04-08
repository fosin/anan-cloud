package com.github.fosin.cdp.platform.service;

import com.github.fosin.cdp.platform.repository.CdpPayRepository;
import com.github.fosin.cdp.platform.service.inter.CdpPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * 系统支付表(cdp_pay)表服务实现类
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Service
@Lazy
public class CdpPayServiceImpl implements CdpPayService {
    @Autowired
    private CdpPayRepository cdpSysPayRepository;

    /**
     * 获取DAO
     */
    @Override
    public CdpPayRepository getRepository() {
        return cdpSysPayRepository;
    }
}
