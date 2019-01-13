package com.github.fosin.cdp.platform.service;

import com.github.fosin.cdp.platform.entity.CdpSysVersionEntity;
import com.github.fosin.cdp.platform.repository.CdpSysVersionRepository;
import com.github.fosin.cdp.platform.service.inter.ICdpSysVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import org.springframework.util.Assert;

import java.util.Date;

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

    /**
     * 通过实体类创建新数据
     *
     * @param entity 系统版本表 实体对象
     * @return entity 实例对象
     */
    @Override
    public CdpSysVersionEntity create(CdpSysVersionEntity entity) {
        Assert.notNull(entity, "创建数据的实体对象不能为空!");
        entity.setCreateTime(new Date());
        return getRepository().save(entity);
    }
}