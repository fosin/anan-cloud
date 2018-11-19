package com.github.fosin.cdp.platformapi.service;

import com.github.fosin.cdp.platformapi.entity.CdpSysPayEntity;
import com.github.fosin.cdp.platformapi.repository.CdpSysPayRepository;
import com.github.fosin.cdp.platformapi.service.inter.ICdpSysPayService;
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
import java.util.List;
import com.github.fosin.cdp.core.exception.CdpServiceException;

/**
 * 系统支付表(cdp_sys_pay)表服务实现类
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Service
@Lazy
public class CdpSysPayServiceImpl implements ICdpSysPayService {
    @Autowired
    private CdpSysPayRepository cdpSysPayRepository;

    /**
     * 获取DAO
     */
    protected CdpSysPayRepository getRepository() {
        return cdpSysPayRepository;
    }
    /**
     * 通过实体类创建新数据
     *
     * @param entity 系统支付表 实体对象
     * @return entity 实例对象
     */
    @Override
    public CdpSysPayEntity create(CdpSysPayEntity entity) {
        Assert.notNull(entity,"创建数据的实体对象不能为空!");
        return getRepository().save(entity);
    }

    /**
     * 修改数据
     *
     * @param entity 系统支付表 实体对象
     * @return entity 实例对象
     */
    @Override
    public CdpSysPayEntity update(CdpSysPayEntity entity) {
        Assert.notNull(entity,"更新数据的实体对象不能为空!");
        return getRepository().save(entity);
    }
    
    @Override
    public List<CdpSysPayEntity> findAll() {
        return getRepository().findAll();
    }

    /**
     * 通过主键查找一条数据
     *
     * @param payId 主键
     * @return 是否成功
     */
    @Override
    public CdpSysPayEntity findOne(Long payId) {
        return getRepository().findOne(payId);
    }

    /**
     * 通过主键删除数据
     *
     * @param payId 主键
     * @return 是否成功
     */
    @Override
    public CdpSysPayEntity delete(Long payId) {
        Assert.notNull(payId,"需要删除的数据主键不能为空!");
        return delete(getRepository().findOne(payId));
    }
    
    /**
     * 通过实体对象删除数据
     *
     * @param entity 系统支付表 实体对象
     * @return entity 实例对象
     */
    @Override
    public CdpSysPayEntity delete(CdpSysPayEntity entity) {
        Assert.notNull(entity,"删除数据的实体对象不能为空!");
        getRepository().delete(entity);
        return entity;
    }
    /**
     * 根据查询条件查询分页排序数据集
     *
     * @param pageModule 分页排序条件
     * @return Result结果集
     */
    @Override
    public Result findAllPage(PageModule pageModule) {
        PageRequest pageable = new PageRequest(pageModule.getPageNumber() - 1, pageModule.getPageSize(), Sort.Direction.fromString(pageModule.getSortOrder()), pageModule.getSortName());
        String searchCondition = pageModule.getSearchText();
        Specification<CdpSysPayEntity> condition = (root, query, cb) -> {
            Path<Long> payId = root.get("payId");
  
            if (StringUtils.isBlank(searchCondition)) {
                return query.getRestriction();
            }
            Predicate predicate = cb.equal(payId, searchCondition);
            return predicate;
        };
   
        //分页查找
        Page<CdpSysPayEntity> page = getRepository().findAll(condition, pageable);

        return ResultUtils.success(page.getTotalElements(), page.getContent());
    }
}