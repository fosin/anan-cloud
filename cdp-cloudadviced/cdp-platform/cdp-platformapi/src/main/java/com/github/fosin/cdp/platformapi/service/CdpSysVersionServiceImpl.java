package com.github.fosin.cdp.platformapi.service;

import com.github.fosin.cdp.platformapi.entity.CdpSysVersionEntity;
import com.github.fosin.cdp.platformapi.repository.CdpSysVersionRepository;
import com.github.fosin.cdp.platformapi.service.inter.ICdpSysVersionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import org.springframework.util.Assert;
import com.github.fosin.cdp.mvc.module.PageModule;
import com.github.fosin.cdp.mvc.result.Result;
import com.github.fosin.cdp.mvc.result.ResultUtils;

import javax.persistence.criteria.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.github.fosin.cdp.core.exception.CdpServiceException;

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