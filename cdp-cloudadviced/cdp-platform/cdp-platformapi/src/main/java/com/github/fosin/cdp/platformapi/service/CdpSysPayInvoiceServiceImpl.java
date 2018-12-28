package com.github.fosin.cdp.platformapi.service;

import com.github.fosin.cdp.platformapi.entity.CdpSysPayInvoiceEntity;
import com.github.fosin.cdp.platformapi.repository.CdpSysPayInvoiceRepository;
import com.github.fosin.cdp.platformapi.service.inter.ICdpSysPayInvoiceService;
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
 * 系统支付发票表(cdp_sys_pay_invoice)表服务实现类
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Service
@Lazy
public class CdpSysPayInvoiceServiceImpl implements ICdpSysPayInvoiceService {
    @Autowired
    private CdpSysPayInvoiceRepository cdpSysPayInvoiceRepository;

    /**
     * 获取DAO
     */
    protected CdpSysPayInvoiceRepository getRepository() {
        return cdpSysPayInvoiceRepository;
    }
    /**
     * 通过实体类创建新数据
     *
     * @param entity 系统支付发票表 实体对象
     * @return entity 实例对象
     */
    @Override
    public CdpSysPayInvoiceEntity create(CdpSysPayInvoiceEntity entity) {
        Assert.notNull(entity,"创建数据的实体对象不能为空!");
        return getRepository().save(entity);
    }

    /**
     * 修改数据
     *
     * @param entity 系统支付发票表 实体对象
     * @return entity 实例对象
     */
    @Override
    public CdpSysPayInvoiceEntity update(CdpSysPayInvoiceEntity entity) {
        Assert.notNull(entity,"更新数据的实体对象不能为空!");
        return getRepository().save(entity);
    }

    /**
     * 通过主键查找一条数据
     *
     * @param invoceId 主键
     * @return 是否成功
     */
    @Override
    public CdpSysPayInvoiceEntity findOne(Long invoceId) {
        return getRepository().findOne(invoceId);
    }

    @Override
    public Collection<CdpSysPayInvoiceEntity> findAllByEntity(CdpSysPayInvoiceEntity cdpSysPayInvoiceEntity) {
        return null;
    }

    /**
     * 通过主键删除数据
     *
     * @param invoceId 主键
     * @return 是否成功
     */
    @Override
    public CdpSysPayInvoiceEntity delete(Long invoceId) {
        Assert.notNull(invoceId,"需要删除的数据主键不能为空!");
        return delete(getRepository().findOne(invoceId));
    }
    
    /**
     * 通过实体对象删除数据
     *
     * @param entity 系统支付发票表 实体对象
     * @return entity 实例对象
     */
    @Override
    public CdpSysPayInvoiceEntity delete(CdpSysPayInvoiceEntity entity) {
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
        Specification<CdpSysPayInvoiceEntity> condition = (root, query, cb) -> {
            Path<Long> invoceId = root.get("invoceId");
  
            if (StringUtils.isBlank(searchCondition)) {
                return query.getRestriction();
            }
            Predicate predicate = cb.equal(invoceId, searchCondition);
            return predicate;
        };
   
        //分页查找
        Page<CdpSysPayInvoiceEntity> page = getRepository().findAll(condition, pageable);

        return ResultUtils.success(page.getTotalElements(), page.getContent());
    }
}