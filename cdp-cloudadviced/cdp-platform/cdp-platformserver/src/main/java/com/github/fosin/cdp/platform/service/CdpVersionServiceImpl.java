package com.github.fosin.cdp.platform.service;

import com.github.fosin.cdp.platform.repository.CdpVersionRepository;
import com.github.fosin.cdp.platform.service.inter.CdpVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * 系统版本表(cdp_version)表服务实现类
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Service
@Lazy
public class CdpVersionServiceImpl implements CdpVersionService {
    @Autowired
    private CdpVersionRepository cdpSysVersionRepository;

    /**
     * 获取DAO
     */
    @Override
    public CdpVersionRepository getRepository() {
        return cdpSysVersionRepository;
    }
}
