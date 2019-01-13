package com.github.fosin.cdp.platform.service;

import com.github.fosin.cdp.platform.repository.CdpSysPayDetailRepository;
import com.github.fosin.cdp.platform.service.inter.ICdpSysPayDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;

/**
 * 系统支付明细表(cdp_sys_pay_detail)表服务实现类
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Service
@Lazy
public class CdpSysPayDetailServiceImpl implements ICdpSysPayDetailService {
    @Autowired
    private CdpSysPayDetailRepository cdpSysPayDetailRepository;

    /**
     * 获取DAO
     */
    @Override
    public CdpSysPayDetailRepository getRepository() {
        return cdpSysPayDetailRepository;
    }

}