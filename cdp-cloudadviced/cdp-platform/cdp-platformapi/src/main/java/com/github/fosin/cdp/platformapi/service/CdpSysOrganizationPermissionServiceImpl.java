package com.github.fosin.cdp.platformapi.service;

import com.github.fosin.cdp.platformapi.entity.CdpSysOrganizationPermissionEntity;
import com.github.fosin.cdp.platformapi.repository.CdpSysOrganizationPermissionRepository;
import com.github.fosin.cdp.platformapi.service.inter.ICdpSysOrganizationPermissionService;
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
 * 系统机构权限表(cdp_sys_organization_permission)表服务实现类
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Service
@Lazy
public class CdpSysOrganizationPermissionServiceImpl implements ICdpSysOrganizationPermissionService {
    @Autowired
    private CdpSysOrganizationPermissionRepository cdpSysOrganizationPermissionRepository;

    /**
     * 获取DAO
     */
    protected CdpSysOrganizationPermissionRepository getRepository() {
        return cdpSysOrganizationPermissionRepository;
    }
    /**
     * 通过实体类创建新数据
     *
     * @param entity 系统机构权限表 实体对象
     * @return entity 实例对象
     */
    @Override
    public CdpSysOrganizationPermissionEntity create(CdpSysOrganizationPermissionEntity entity) {
        Assert.notNull(entity,"创建数据的实体对象不能为空!");
        return getRepository().save(entity);
    }

    /**
     * 修改数据
     *
     * @param entity 系统机构权限表 实体对象
     * @return entity 实例对象
     */
    @Override
    public CdpSysOrganizationPermissionEntity update(CdpSysOrganizationPermissionEntity entity) {
        Assert.notNull(entity,"更新数据的实体对象不能为空!");
        return getRepository().save(entity);
    }
    
    @Override
    public List<CdpSysOrganizationPermissionEntity> findAll() {
        return getRepository().findAll();
    }

    /**
     * 通过主键查找一条数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public CdpSysOrganizationPermissionEntity findOne(Long id) {
        return getRepository().findOne(id);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public CdpSysOrganizationPermissionEntity delete(Long id) {
        Assert.notNull(id,"需要删除的数据主键不能为空!");
        return delete(getRepository().findOne(id));
    }
    
    /**
     * 通过实体对象删除数据
     *
     * @param entity 系统机构权限表 实体对象
     * @return entity 实例对象
     */
    @Override
    public CdpSysOrganizationPermissionEntity delete(CdpSysOrganizationPermissionEntity entity) {
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
        Specification<CdpSysOrganizationPermissionEntity> condition = (root, query, cb) -> {
            Path<Long> id = root.get("id");
  
            if (StringUtils.isBlank(searchCondition)) {
                return query.getRestriction();
            }
            Predicate predicate = cb.equal(id, searchCondition);
            return predicate;
        };
   
        //分页查找
        Page<CdpSysOrganizationPermissionEntity> page = getRepository().findAll(condition, pageable);

        return ResultUtils.success(page.getTotalElements(), page.getContent());
    }
}