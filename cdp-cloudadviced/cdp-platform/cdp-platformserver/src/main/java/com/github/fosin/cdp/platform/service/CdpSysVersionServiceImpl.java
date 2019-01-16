package com.github.fosin.cdp.platform.service;

import com.github.fosin.cdp.platform.repository.CdpSysVersionRepository;
import com.github.fosin.cdp.platform.service.inter.ICdpSysVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * 系统版本表(cdp_sys_version)表服务实现类
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Service
@Lazy
public class CdpSysVersionServiceImpl implements ICdpSysVersionService {
    @Autowired
    private CdpSysVersionRepository cdpSysVersionRepository;

    /**
     * 获取DAO
     */
    @Override
    public CdpSysVersionRepository getRepository() {
        return cdpSysVersionRepository;
    }
}