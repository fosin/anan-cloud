package com.github.fosin.cdp.platformapi.service;

import com.github.fosin.cdp.platformapi.entity.CdpSysPayDetailEntity;
import com.github.fosin.cdp.platformapi.repository.CdpSysPayDetailRepository;
import com.github.fosin.cdp.platformapi.service.inter.ICdpSysPayDetailService;
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
import java.util.List;

import com.github.fosin.cdp.core.exception.CdpServiceException;

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