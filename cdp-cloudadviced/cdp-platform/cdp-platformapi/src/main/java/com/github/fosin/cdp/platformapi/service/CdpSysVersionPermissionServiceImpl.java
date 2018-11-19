package com.github.fosin.cdp.platformapi.service;

import com.github.fosin.cdp.platformapi.entity.CdpSysVersionPermissionEntity;
import com.github.fosin.cdp.platformapi.repository.CdpSysVersionPermissionRepository;
import com.github.fosin.cdp.platformapi.service.inter.ICdpSysVersionPermissionService;
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
 * 系统版本权限表(cdp_sys_version_permission)表服务实现类
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Service
@Lazy
public class CdpSysVersionPermissionServiceImpl implements ICdpSysVersionPermissionService {
    @Autowired
    private CdpSysVersionPermissionRepository cdpSysVersionPermissionRepository;

    /**
     * 获取DAO
     */
    protected CdpSysVersionPermissionRepository getRepository() {
        return cdpSysVersionPermissionRepository;
    }
    /**
     * 通过实体类创建新数据
     *
     * @param entity 系统版本权限表 实体对象
     * @return entity 实例对象
     */
    @Override
    public CdpSysVersionPermissionEntity create(CdpSysVersionPermissionEntity entity) {
        Assert.notNull(entity,"创建数据的实体对象不能为空!");
        return getRepository().save(entity);
    }

    /**
     * 修改数据
     *
     * @param entity 系统版本权限表 实体对象
     * @return entity 实例对象
     */
    @Override
    public CdpSysVersionPermissionEntity update(CdpSysVersionPermissionEntity entity) {
        Assert.notNull(entity,"更新数据的实体对象不能为空!");
        return getRepository().save(entity);
    }
    
    @Override
    public List<CdpSysVersionPermissionEntity> findAll() {
        return getRepository().findAll();
    }

    /**
     * 通过主键查找一条数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public CdpSysVersionPermissionEntity findOne(Long id) {
        return getRepository().findOne(id);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public CdpSysVersionPermissionEntity delete(Long id) {
        Assert.notNull(id,"需要删除的数据主键不能为空!");
        return delete(getRepository().findOne(id));
    }
    
    /**
     * 通过实体对象删除数据
     *
     * @param entity 系统版本权限表 实体对象
     * @return entity 实例对象
     */
    @Override
    public CdpSysVersionPermissionEntity delete(CdpSysVersionPermissionEntity entity) {
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
        Specification<CdpSysVersionPermissionEntity> condition = (root, query, cb) -> {
            Path<Long> id = root.get("id");
  
            if (StringUtils.isBlank(searchCondition)) {
                return query.getRestriction();
            }
            Predicate predicate = cb.equal(id, searchCondition);
            return predicate;
        };
   
        //分页查找
        Page<CdpSysVersionPermissionEntity> page = getRepository().findAll(condition, pageable);

        return ResultUtils.success(page.getTotalElements(), page.getContent());
    }
}