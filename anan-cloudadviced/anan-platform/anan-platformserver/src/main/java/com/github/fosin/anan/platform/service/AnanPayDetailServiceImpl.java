package com.github.fosin.anan.platform.service;

import com.github.fosin.anan.platform.repository.AnanPayDetailRepository;
import com.github.fosin.anan.platform.service.inter.AnanPayDetailService;

import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;

/**
 * 系统支付明细表(anan_pay_detail)表服务实现类
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Service
@Lazy
public class AnanPayDetailServiceImpl implements AnanPayDetailService {
    private final AnanPayDetailRepository ananSysPayDetailRepository;

    public AnanPayDetailServiceImpl(AnanPayDetailRepository ananSysPayDetailRepository) {
        this.ananSysPayDetailRepository = ananSysPayDetailRepository;
    }

    /**
     * 获取DAO
     */
    @Override
    public AnanPayDetailRepository getRepository() {
        return ananSysPayDetailRepository;
    }

}
