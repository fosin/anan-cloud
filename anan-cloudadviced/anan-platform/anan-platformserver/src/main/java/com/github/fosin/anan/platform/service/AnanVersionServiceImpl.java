package com.github.fosin.anan.platform.service;

import com.github.fosin.anan.platform.repository.AnanVersionRepository;
import com.github.fosin.anan.platform.service.inter.AnanVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * 系统版本表(anan_version)表服务实现类
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Service
@Lazy
public class AnanVersionServiceImpl implements AnanVersionService {
    @Autowired
    private AnanVersionRepository ananSysVersionRepository;

    /**
     * 获取DAO
     */
    @Override
    public AnanVersionRepository getRepository() {
        return ananSysVersionRepository;
    }
}
