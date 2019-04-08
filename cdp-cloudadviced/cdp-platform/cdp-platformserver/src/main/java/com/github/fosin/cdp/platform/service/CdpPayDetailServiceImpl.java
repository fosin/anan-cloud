package com.github.fosin.cdp.platform.service;

import com.github.fosin.cdp.platform.repository.CdpPayDetailRepository;
import com.github.fosin.cdp.platform.service.inter.CdpPayDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;

/**
 * 系统支付明细表(cdp_pay_detail)表服务实现类
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Service
@Lazy
public class CdpPayDetailServiceImpl implements CdpPayDetailService {
    @Autowired
    private CdpPayDetailRepository cdpSysPayDetailRepository;

    /**
     * 获取DAO
     */
    @Override
    public CdpPayDetailRepository getRepository() {
        return cdpSysPayDetailRepository;
    }

}
