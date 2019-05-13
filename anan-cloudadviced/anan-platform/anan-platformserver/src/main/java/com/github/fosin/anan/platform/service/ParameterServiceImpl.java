package com.github.fosin.anan.platform.service;


import com.github.fosin.anan.cache.util.CacheUtil;
import com.github.fosin.anan.core.exception.AnanServiceException;
import com.github.fosin.anan.jpa.repository.IJpaRepository;
import com.github.fosin.anan.mvc.module.PageModule;
import com.github.fosin.anan.mvc.result.Result;
import com.github.fosin.anan.mvc.result.ResultUtils;
import com.github.fosin.anan.platform.repository.OrganizationRepository;
import com.github.fosin.anan.platform.repository.ParameterRepository;
import com.github.fosin.anan.platform.service.inter.ParameterService;
import com.github.fosin.anan.platformapi.constant.TableNameConstant;
import com.github.fosin.anan.platformapi.dto.request.AnanParameterCreateDto;
import com.github.fosin.anan.platformapi.dto.request.AnanParameterUpdateDto;
import com.github.fosin.anan.platformapi.entity.AnanOrganizationEntity;
import com.github.fosin.anan.platformapi.entity.AnanParameterEntity;
import com.github.fosin.anan.platformapi.entity.AnanUserEntity;
import com.github.fosin.anan.platformapi.parameter.OrganStrategy;
import com.github.fosin.anan.platformapi.util.LoginUserUtil;
import com.github.fosin.anan.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.criteria.Path;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 字典表服务
 *
 * @author fosin
 * @date 2018-7-29
 */
@Service
@Lazy
@Slf4j
public class ParameterServiceImpl implements ParameterService {
    @Autowired
    private ParameterRepository parameterRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Override
    @CachePut(value = TableNameConstant.ANAN_PARAMETER, key = "#root.target.getCacheKey(#result)")
    public AnanParameterEntity create(AnanParameterCreateDto entity) {
        Assert.notNull(entity, "传入的创建数据实体对象不能为空!");
        AnanParameterEntity createEntiy = new AnanParameterEntity();
        BeanUtils.copyProperties(entity, createEntiy);
        return getRepository().save(createEntiy);
    }

    @Override
    public AnanParameterEntity update(AnanParameterUpdateDto entity) {
        Assert.notNull(entity, "传入了空对象!");
        Long id = entity.getId();
        Assert.notNull(id, "ID不能为空!");
        AnanParameterEntity cEntity = parameterRepository.findById(id).orElse(null);
        BeanUtils.copyProperties(entity, Objects.requireNonNull(cEntity, "通过ID：" + id + "未能找到对应的数据!"));
        AnanParameterEntity save = parameterRepository.save(cEntity);
        String cCacheKey = getCacheKey(cEntity);
        String cacheKey = getCacheKey(entity.getType(), entity.getScope(), entity.getName());
        //如果修改了type、scope、name则需要删除以前的缓存并设置新缓存
        if (!cCacheKey.equals(cacheKey)) {
            //新key设置旧值，需要发布以后才刷新缓存换成本次更新的新值
            CacheUtil.put(TableNameConstant.ANAN_PARAMETER, cacheKey, cEntity);
            CacheUtil.evict(TableNameConstant.ANAN_PARAMETER, cCacheKey);
        }
        return save;
    }

    @Override
    public AnanParameterEntity deleteById(Long id) {
        AnanParameterEntity entity = parameterRepository.findById(id).orElse(null);
        Assert.notNull(entity, "通过ID没有能找到参数数据,删除被取消!");
        String cacheKey = getCacheKey(entity);
        CacheUtil.evict(TableNameConstant.ANAN_PARAMETER, cacheKey);
        parameterRepository.deleteById(id);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new AnanServiceException(e);
        }
        CacheUtil.evict(TableNameConstant.ANAN_PARAMETER, cacheKey);
        return null;
    }

    @Override
    @CacheEvict(value = TableNameConstant.ANAN_PARAMETER, key = "#root.target.getCacheKey(#entity)")
    public AnanParameterEntity deleteByEntity(AnanParameterEntity entity) {
        Assert.notNull(entity, "传入了空对象!");
        parameterRepository.delete(entity);
        return entity;
    }

    @Override
    public Result findAllByPageSort(PageModule pageModule) {
        PageRequest pageable = PageRequest.of(pageModule.getPageNumber() - 1, pageModule.getPageSize(), Sort.Direction.fromString(pageModule.getSortOrder()), pageModule.getSortName());
        String searchCondition = pageModule.getSearchText();

        Specification<AnanParameterEntity> condition = (Specification<AnanParameterEntity>) (root, query, cb) -> {
            Path<String> name = root.get("name");
            Path<String> scope = root.get("scope");
            Path<String> value = root.get("value");
            if (StringUtils.isBlank(searchCondition)) {
                return query.getRestriction();
            }
            return cb.or(cb.like(scope, "%" + searchCondition + "%"), cb.like(name, "%" + searchCondition + "%"), cb.like(value, "%" + searchCondition + "%"));

        };
        //分页查找
        Page<AnanParameterEntity> page = parameterRepository.findAll(condition, pageable);

        return ResultUtils.success(page.getTotalElements(), page.getContent());
    }

    public String getCacheKey(AnanParameterEntity entity) {
        return getCacheKey(entity.getType(), entity.getScope(), entity.getName());
    }

    private String getCacheKey(Integer type, String scope, String name) {
        if (StringUtil.isEmpty(scope)) {
            scope = "";
        }
        return type + "-" + scope + "-" + name;
    }

    @Override
    @Cacheable(value = TableNameConstant.ANAN_PARAMETER, key = "#root.target.getCacheKey(#type,#scope,#name)")
    public AnanParameterEntity getParameter(Integer type, String scope, String name) {
        AnanParameterEntity entity = parameterRepository.findByTypeAndScopeAndName(type, scope, name);
        //因为参数会逐级上上级机构查找，为减少没有必要的查询，该代码为解决Spring Cache默认不缓存null值问题
        if (entity == null) {
            entity = new AnanParameterEntity();
        }
        return entity;
    }

    /**
     * 得到机构链中最接近的参数
     *
     * @param scope 机构ID AnanParameterEntity.scope
     * @param name  参数名称 AnanParameterEntity.name
     * @return 参数
     */
    @Override
    @Cacheable(value = TableNameConstant.ANAN_PARAMETER, key = "#root.target.getCacheKey(#type,#scope,#name)")
    public AnanParameterEntity getNearestParameter(int type, String scope, String name) {
        AnanParameterEntity parameter = parameterRepository.findByTypeAndScopeAndName(type, scope, name);
        boolean finded = parameter != null && parameter.getId() != null;
        if (StringUtil.isEmpty(scope)) {
            String info = "没有从参数[" + "type:" + type + " scope:" + scope + " name:" + name + "]中查询到参数";
            Assert.isTrue(finded, info);
            return parameter;
        }

        //parameter为空表示没有参数记录，则依次向上找父机构的参数
        if (!finded) {
            parameter = getNearestParameter(type, getNearestScope(type, scope), name);
        }

        return parameter;
    }

    private String getNearestScope(int type, String scope) {
        String rc = null;
        OrganStrategy organStrategy = new OrganStrategy();
        if (type == organStrategy.getType()) {
            Long id = Long.parseLong(scope);
            AnanOrganizationEntity entity = organizationRepository.findById(id).orElse(null);
            if (entity != null && entity.getLevel() != 0) {
                rc = entity.getPId() + "";
            }
        }
        return rc;
    }

    @Override
    @Cacheable(value = TableNameConstant.ANAN_PARAMETER, key = "#root.target.getCacheKey(#type,#scope,#name)")
    public AnanParameterEntity getOrCreateParameter(int type, String scope, String name, String defaultValue, String description) {
        AnanParameterEntity entity;
        try {
            entity = getNearestParameter(type, scope, name);
        } catch (Exception e) {// 报异常说明没有找到任何相关参数，则需要创建一个无域参数，这样默认所有机构共享这一个参数，如果需要设置机构个性化参数则需要在前端手动创建
            log.debug("报异常说明没有找到任何相关参数，则需要创建一个无域参数，这样默认所有机构共享这一个参数，如果需要设置机构个性化参数则需要在前端手动创建");
            AnanParameterCreateDto createDto = new AnanParameterCreateDto();
            createDto.setValue(defaultValue);
            createDto.setType(type);
            createDto.setScope(scope);
            createDto.setName(name);
            createDto.setDefaultValue(defaultValue);
            createDto.setDescription(description);
            createDto.setStatus(0);
            entity = create(createDto);
        }
        return entity;
    }

    @Override
    @Transactional(rollbackFor = AnanServiceException.class)
    public Boolean applyChange(Long id) {
        AnanParameterEntity entity = parameterRepository.findById(id).orElse(null);
        Assert.notNull(entity, "该参数已经不存在!");
        String cacheKey = getCacheKey(entity);
        AnanUserEntity loginUser;
        boolean success;
        switch (entity.getStatus()) {
            case 1:
                loginUser = LoginUserUtil.getUser();
                entity.setApplyBy(loginUser.getId());
                entity.setApplyTime(new Date());
                entity.setStatus(0);
                success = CacheUtil.put(TableNameConstant.ANAN_PARAMETER, cacheKey, entity);
                if (success) {
                    parameterRepository.save(entity);
                }
                break;
            case 2:
                success = CacheUtil.evict(TableNameConstant.ANAN_PARAMETER, cacheKey);
                if (success) {
                    parameterRepository.deleteById(id);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new AnanServiceException(e);
                    }
                    success = CacheUtil.evict(TableNameConstant.ANAN_PARAMETER, cacheKey);
                }
                break;
            default:
                success = CacheUtil.put(TableNameConstant.ANAN_PARAMETER, cacheKey, entity);
        }
        return success;
    }

    @Override
    @Transactional(rollbackFor = AnanServiceException.class)
    public Boolean applyChanges() {
        List<AnanParameterEntity> entities = parameterRepository.findByStatusNot(0);
        Assert.isTrue(entities != null && entities.size() != 0, "没有更改过任何参数，不需要发布!");
        for (AnanParameterEntity entity : entities) {
            applyChange(entity.getId());
        }
        return true;
    }

    @Override
    public IJpaRepository<AnanParameterEntity, Long> getRepository() {
        return parameterRepository;
    }
}
