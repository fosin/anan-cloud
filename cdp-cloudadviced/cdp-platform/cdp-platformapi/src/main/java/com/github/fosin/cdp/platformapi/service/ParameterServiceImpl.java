package com.github.fosin.cdp.platformapi.service;


import com.github.fosin.cdp.jpa.repository.IJpaRepository;
import com.github.fosin.cdp.platformapi.repository.ParameterRepository;
import com.github.fosin.cdp.core.exception.CdpServiceException;
import com.github.fosin.cdp.mvc.module.PageModule;
import com.github.fosin.cdp.mvc.result.Result;
import com.github.fosin.cdp.mvc.result.ResultUtils;
import com.github.fosin.cdp.platformapi.constant.TableNameConstant;
import com.github.fosin.cdp.platformapi.entity.CdpSysParameterEntity;
import com.github.fosin.cdp.platformapi.entity.CdpSysUserEntity;
import com.github.fosin.cdp.platformapi.service.inter.IParameterService;
import com.github.fosin.cdp.platformapi.util.LoginUserUtil;
import com.github.fosin.cdp.cache.util.CacheUtil;
import com.github.fosin.cdp.util.StringUtil;
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
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.criteria.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 字典表服务
 *
 * @author fosin
 * @date 2018-7-29
 */
@Service
@Lazy
public class ParameterServiceImpl implements IParameterService {
    @Autowired
    private ParameterRepository parameterRepository;

    @Override
    @CachePut(value = TableNameConstant.CDP_SYS_PARAMETER, key = "#root.target.getCacheKey(#result)")
    public CdpSysParameterEntity create(CdpSysParameterEntity entity) throws CdpServiceException {
        Assert.notNull(entity, "传入了空对象!");
        CdpSysUserEntity loginUser = LoginUserUtil.getUser();
        Date now = new Date();
        entity.setCreateBy(loginUser.getId());
        entity.setCreateTime(now);
        entity.setUpdateBy(loginUser.getId());
        entity.setUpdateTime(now);
        entity.setApplyBy(loginUser.getId());
        entity.setApplyTime(now);
        entity.setStatus(0);
        return parameterRepository.save(entity);
    }

    @Override
    public CdpSysParameterEntity update(CdpSysParameterEntity entity) throws CdpServiceException {
        Assert.notNull(entity, "传入了空对象!");
        Long id = entity.getId();
        Assert.notNull(id, "ID不能为空!");
        CdpSysParameterEntity cEntity = parameterRepository.findOne(id);
        CdpSysUserEntity loginUser = LoginUserUtil.getUser();
        entity.setUpdateBy(loginUser.getId());
        entity.setUpdateTime(new Date());
        CdpSysParameterEntity save = parameterRepository.save(entity);
        if (save != null) {
            String cCacheKey = getCacheKey(cEntity);
            String cacheKey = getCacheKey(entity);
            //如果修改了type、scope、name则需要删除以前的缓存并设置新缓存
            if (!cCacheKey.equals(cacheKey)) {
                //新key设置旧值，需要发布以后才刷新缓存换成本次更新的新值
                CacheUtil.put(TableNameConstant.CDP_SYS_PARAMETER, cacheKey, cEntity);
                CacheUtil.evict(TableNameConstant.CDP_SYS_PARAMETER, cCacheKey);
            }
        }
        return save;
    }

    @Override
    public CdpSysParameterEntity delete(Long id) throws CdpServiceException {
        CdpSysParameterEntity entity = parameterRepository.findOne(id);
        Assert.notNull(entity, "通过ID没有能找到参数数据,删除被取消!");
        String cacheKey = getCacheKey(entity);
        CacheUtil.evict(TableNameConstant.CDP_SYS_PARAMETER, cacheKey);
        parameterRepository.delete(id);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new CdpServiceException(e);
        }
        CacheUtil.evict(TableNameConstant.CDP_SYS_PARAMETER, cacheKey);
        return null;
    }

    @Override
    @CacheEvict(value = TableNameConstant.CDP_SYS_PARAMETER, key = "#root.target.getCacheKey(#entity)")
    public CdpSysParameterEntity delete(CdpSysParameterEntity entity) throws CdpServiceException {
        Assert.notNull(entity, "传入了空对象!");
        parameterRepository.delete(entity);
        return entity;
    }

    @Override
    public Result findAllByPageSort(PageModule pageModule) {
        PageRequest pageable = new PageRequest(pageModule.getPageNumber() - 1, pageModule.getPageSize(), Sort.Direction.fromString(pageModule.getSortOrder()), pageModule.getSortName());
        String searchCondition = pageModule.getSearchText();

        Specification<CdpSysParameterEntity> condition = new Specification<CdpSysParameterEntity>() {
            @Override
            public Predicate toPredicate(Root<CdpSysParameterEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<String> name = root.get("name");
                Path<String> scope = root.get("scope");
                Path<String> value = root.get("value");
                if (StringUtils.isBlank(searchCondition)) {
                    return query.getRestriction();
                }
                return cb.or(cb.like(scope, "%" + searchCondition + "%"), cb.like(name, "%" + searchCondition + "%"), cb.like(value, "%" + searchCondition + "%"));

            }
        };
        //分页查找
        Page<CdpSysParameterEntity> page = parameterRepository.findAll(condition, pageable);

        return ResultUtils.success(page.getTotalElements(), page.getContent());
    }

    public String getCacheKey(CdpSysParameterEntity entity) {
        return getCacheKey(entity.getType(), entity.getScope(), entity.getName());
    }

    public String getCacheKey(Integer type, String scope, String name) {
        if (StringUtil.isEmpty(scope)) {
            scope = "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(type).append("-").append(scope).append("-").append(name);
        return sb.toString();
    }

    @Override
    @Cacheable(value = TableNameConstant.CDP_SYS_PARAMETER, key = "#root.target.getCacheKey(#type,#scope,#name)")
    public CdpSysParameterEntity findByTypeAndScopeAndName(Integer type, String scope, String name) {
        CdpSysParameterEntity entity = parameterRepository.findByTypeAndScopeAndName(type, scope, name);
        //因为参数会逐级上上级机构查找，为减少没有必要的查询，该代码为解决Spring Cache默认不缓存null值问题
        if (entity == null) {
            entity = new CdpSysParameterEntity();
        }
        return entity;
    }

    @Override
    @Transactional(rollbackFor = CdpServiceException.class)
    public boolean applyChange(Long id) throws CdpServiceException {
        CdpSysParameterEntity entity = parameterRepository.findOne(id);
        Assert.notNull(entity, "该参数已经不存在!");
        String cacheKey = getCacheKey(entity);
        CdpSysUserEntity loginUser;
        boolean success;
        switch (entity.getStatus()) {
            case 1:
                loginUser = LoginUserUtil.getUser();
                entity.setApplyBy(loginUser.getId());
                entity.setApplyTime(new Date());
                entity.setStatus(0);
                success = CacheUtil.put(TableNameConstant.CDP_SYS_PARAMETER, cacheKey, entity);
                if (success) {
                    parameterRepository.save(entity);
                }
                break;
            case 2:
                success = CacheUtil.evict(TableNameConstant.CDP_SYS_PARAMETER, cacheKey);
                if (success) {
                    parameterRepository.delete(id);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new CdpServiceException(e);
                    }
                    success = CacheUtil.evict(TableNameConstant.CDP_SYS_PARAMETER, cacheKey);
                }
                break;
            default:
                success = CacheUtil.put(TableNameConstant.CDP_SYS_PARAMETER, cacheKey, entity);
        }
        return success;
    }

    @Override
    public boolean applyChanges() throws CdpServiceException {
        List<CdpSysParameterEntity> entities = parameterRepository.findByStatusNot(0);
        Assert.isTrue(entities != null && entities.size() != 0, "没有更改过任何参数，不需要发布!");
        for (CdpSysParameterEntity entity : entities) {
            applyChange(entity.getId());
        }
        return true;
    }

    @Override
    public IJpaRepository<CdpSysParameterEntity, Long> getRepository() {
        return parameterRepository;
    }

//    protected synchronized void addCacheEvict(CdpSysParameterEntity entity) {
//        Set<CdpSysParameterEntity> set = CacheUtil.get(CDP_SYS_PARAMETER_EVICTCACHE, CDP_SYS_PARAMETER_EVICTCACHE, Set.class);
//        if (set == null) {
//            set = new HashSet<>();
//        }
//        set.add(entity);
//        CacheUtil.put(CDP_SYS_PARAMETER_EVICTCACHE, CDP_SYS_PARAMETER_EVICTCACHE, set);
//    }
//
//    protected void removeCacheEvict(CdpSysParameterEntity entity) {
//        Set<CdpSysParameterEntity> set = CacheUtil.get(CDP_SYS_PARAMETER_EVICTCACHE, CDP_SYS_PARAMETER_EVICTCACHE, Set.class);
//        if (set == null || set.size() == 0) {
//            return;
//        }
//        for (CdpSysParameterEntity e : set) {
//            if (e.getId().equals(entity.getId())) {
//                set.remove(e);
//            }
//        }
//        CacheUtil.put(CDP_SYS_PARAMETER_EVICTCACHE, CDP_SYS_PARAMETER_EVICTCACHE, set);
//    }
}
