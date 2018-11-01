package com.github.fosin.cdp.platformapi.service;

import com.github.fosin.cdp.platformapi.entity.CdpSysPermissionEntity;
import com.github.fosin.cdp.core.exception.CdpServiceException;
import com.github.fosin.cdp.mvc.module.PageModule;
import com.github.fosin.cdp.mvc.result.Result;
import com.github.fosin.cdp.mvc.result.ResultUtils;
import com.github.fosin.cdp.platformapi.constant.TableNameConstant;
import com.github.fosin.cdp.platformapi.entity.CdpSysUserEntity;
import com.github.fosin.cdp.platformapi.repository.PermissionRepository;
import com.github.fosin.cdp.platformapi.service.inter.IPermissionService;
import com.github.fosin.cdp.platformapi.service.inter.IRolePermissionService;
import com.github.fosin.cdp.platformapi.service.inter.IUserPermissionService;
import com.github.fosin.cdp.platformapi.util.LoginUserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.persistence.criteria.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 2017/12/29.
 * Time:12:38
 *
 * @author fosin
 */
@Service
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private IUserPermissionService userPermissionService;

    @Autowired
    private IRolePermissionService rolePermissionService;

    @Override
    @CachePut(value = TableNameConstant.CDP_SYS_PERMISSION, key = "#entity.id")
    public CdpSysPermissionEntity create(CdpSysPermissionEntity entity) throws CdpServiceException {
        Assert.notNull(entity, "传入了空对象!");
        CdpSysUserEntity loginUser = LoginUserUtil.getUser();
        Date now = new Date();
        entity.setCreateTime(now);
        entity.setUpdateTime(now);
        Long id = loginUser.getId();
        entity.setCreateBy(id);
        entity.setUpdateBy(id);
        return permissionRepository.save(entity);
    }

    @Override
    @CacheEvict(value = TableNameConstant.CDP_SYS_PERMISSION, key = "#id")
    public CdpSysPermissionEntity delete(Long id) throws CdpServiceException {
        Assert.notNull(id, "传入了空ID!");
        long countByPermissionId = rolePermissionService.countByPermissionId(id);
        Assert.isTrue(countByPermissionId == 0, "还有角色在使用该权限，不能直接删除!");
        countByPermissionId = userPermissionService.countByPermissionId(id);
        Assert.isTrue(countByPermissionId == 0, "还有用户在使用该权限，不能直接删除!");
        List<CdpSysPermissionEntity> entities = findByPId(id);
        Assert.isTrue(entities == null || entities.size() == 0, "该节点还存在子节点不能直接删除!");
        permissionRepository.delete(id);
        return null;
    }

    @Override
    @CacheEvict(value = TableNameConstant.CDP_SYS_PERMISSION, key = "#entity.id")
    public CdpSysPermissionEntity delete(CdpSysPermissionEntity entity) throws CdpServiceException {
        Assert.notNull(entity, "传入了空对象!");
        Long id = entity.getId();
        Assert.notNull(id, "传入了空ID!");
        long countByPermissionId = rolePermissionService.countByPermissionId(id);
        Assert.isTrue(countByPermissionId == 0, "还有角色在使用该权限，不能直接删除!");
        countByPermissionId = userPermissionService.countByPermissionId(id);
        Assert.isTrue(countByPermissionId == 0, "还有用户在使用该权限，不能直接删除!");
        List<CdpSysPermissionEntity> entities = findByPId(id);
        Assert.isTrue(entities == null || entities.size() == 0, "该节点还存在子节点不能直接删除!");
        permissionRepository.delete(entity);
        return entity;
    }

    @Override
    public Result findAllPage(PageModule pageModule) {
        PageRequest pageable = new PageRequest(pageModule.getPageNumber() - 1, pageModule.getPageSize(), Sort.Direction.fromString(pageModule.getSortOrder()), pageModule.getSortName());
        String searchCondition = pageModule.getSearchText();

        Specification<CdpSysPermissionEntity> condition = new Specification<CdpSysPermissionEntity>() {
            @Override
            public Predicate toPredicate(Root<CdpSysPermissionEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                if (StringUtils.isBlank(searchCondition)) {
                    return query.getRestriction();
                }
                Path<String> name = root.get("name");
                Path<String> code = root.get("code");
                Path<String> url = root.get("url");
                Path<String> type = root.get("type");
                return cb.or(cb.like(name, "%" + searchCondition + "%"), cb.like(code, "%" + searchCondition + "%"), cb.like(url, "%" + searchCondition + "%"), cb.like(type, "%" + searchCondition + "%"));
            }
        };
        //分页查找
        Page<CdpSysPermissionEntity> page = permissionRepository.findAll(condition, pageable);

        return ResultUtils.success(page.getTotalElements(), page.getContent());
    }

    @Override
    @Cacheable(value = TableNameConstant.CDP_SYS_PERMISSION, key = "#id")
    public CdpSysPermissionEntity findOne(Long id) {
        return permissionRepository.findOne(id);
    }

    @Override
    @CachePut(value = TableNameConstant.CDP_SYS_PERMISSION, key = "#entity.id")
    public CdpSysPermissionEntity update(CdpSysPermissionEntity entity) throws CdpServiceException {
        Assert.notNull(entity, "传入了空对象!");
        Assert.notNull(entity.getId(), "传入了空ID!");
        CdpSysUserEntity loginUser = LoginUserUtil.getUser();
        entity.setUpdateTime(new Date());
        entity.setUpdateBy(loginUser.getId());
        return permissionRepository.save(entity);
    }

    @Override
    public Collection<CdpSysPermissionEntity> findAll() {
        return permissionRepository.findAll();
    }

    @Override
    public List<CdpSysPermissionEntity> findByPId(Long pId) {
        Sort sort = new Sort(Sort.Direction.fromString("ASC"), "sort");
        return permissionRepository.findByPId(pId, sort);
    }

    @Override
    public List<CdpSysPermissionEntity> findByType(Integer type) {
        return permissionRepository.findByType(type);
    }

    @Override
    public List<CdpSysPermissionEntity> findByAppName(String appName) {
        return permissionRepository.findByAppName(appName);
    }
}
