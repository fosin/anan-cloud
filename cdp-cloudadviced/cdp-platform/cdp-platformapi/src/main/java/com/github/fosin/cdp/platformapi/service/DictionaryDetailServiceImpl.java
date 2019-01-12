package com.github.fosin.cdp.platformapi.service;


import com.github.fosin.cdp.cache.util.CacheUtil;
import com.github.fosin.cdp.core.exception.CdpServiceException;
import com.github.fosin.cdp.jpa.repository.IJpaRepository;
import com.github.fosin.cdp.mvc.module.PageModule;
import com.github.fosin.cdp.mvc.result.Result;
import com.github.fosin.cdp.mvc.result.ResultUtils;
import com.github.fosin.cdp.platformapi.constant.SystemConstant;
import com.github.fosin.cdp.platformapi.constant.TableNameConstant;
import com.github.fosin.cdp.platformapi.entity.CdpSysDictionaryDetailEntity;
import com.github.fosin.cdp.platformapi.entity.CdpSysUserEntity;
import com.github.fosin.cdp.platformapi.repository.DictionaryDetailRepository;
import com.github.fosin.cdp.platformapi.service.inter.IDictionaryDetailService;
import com.github.fosin.cdp.platformapi.service.inter.IUserService;
import com.github.fosin.cdp.platformapi.util.LoginUserUtil;
import com.github.fosin.cdp.util.NumberUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
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
public class DictionaryDetailServiceImpl implements IDictionaryDetailService {

    @Autowired
    private DictionaryDetailRepository dictionaryDetailRepository;

    @Autowired
    private IUserService userService;

    @Override
    @CacheEvict(value = TableNameConstant.CDP_SYS_DICTIONARY_DETAIL, key = "#entity.code")
    public CdpSysDictionaryDetailEntity create(CdpSysDictionaryDetailEntity entity) {
        CdpSysUserEntity loginUser = LoginUserUtil.getUser();
        Date now = new Date();
        entity.setCreateBy(loginUser.getId());
        entity.setCreateTime(now);
        entity.setUpdateTime(now);
        entity.setUpdateBy(loginUser.getId());
        return dictionaryDetailRepository.save(entity);
    }

    @Override
    @CacheEvict(value = TableNameConstant.CDP_SYS_DICTIONARY_DETAIL, key = "#entity.code")
    public CdpSysDictionaryDetailEntity update(CdpSysDictionaryDetailEntity entity) {
        Assert.notNull(entity, "传入了空的对象!");
        Long key = entity.getCode();
        Assert.notNull(key, "无效的更新数据!");
        CdpSysUserEntity loginUser = LoginUserUtil.getUser();
        //不是超级管理员
        if (!SystemConstant.SUPER_USER_CODE.equals(loginUser.getUsercode())) {
            CdpSysUserEntity superUser = userService.findByUsercode(SystemConstant.SUPER_USER_CODE);
            //是超级管理员创建的数据则不需要非超级管理员修改
            if (superUser.getId().equals(entity.getCreateBy())) {
                throw new CdpServiceException("没有权限修改系统创建的字典明细项!");
            }
        }
        entity.setUpdateBy(loginUser.getId());
        entity.setUpdateTime(new Date());
        return dictionaryDetailRepository.save(entity);
    }

    @Override
    public CdpSysDictionaryDetailEntity delete(Long id) {
        Assert.notNull(id, "传入了空的ID!");
        CdpSysDictionaryDetailEntity entity = dictionaryDetailRepository.findOne(id);
        Assert.notNull(entity, "传入的ID找不到数据!");
        CdpSysUserEntity loginUser = LoginUserUtil.getUser();
        //不是超级管理员
        if (!SystemConstant.SUPER_USER_CODE.equals(loginUser.getUsercode())) {
            CdpSysUserEntity superUser = userService.findByUsercode(SystemConstant.SUPER_USER_CODE);
            //是超级管理员创建的数据则不需要非超级管理员修改
            if (superUser.getId().equals(entity.getCreateBy())) {
                throw new CdpServiceException("没有权限修改系统创建的字典明细项!");
            }
        }

        CacheUtil.evict(TableNameConstant.CDP_SYS_DICTIONARY_DETAIL, entity.getCode() + "");
        dictionaryDetailRepository.delete(id);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new CdpServiceException(e);
        }
        CacheUtil.evict(TableNameConstant.CDP_SYS_DICTIONARY_DETAIL, entity.getCode() + "");
        return null;
    }

    @Override
    @CacheEvict(value = TableNameConstant.CDP_SYS_DICTIONARY_DETAIL, key = "#entity.code")
    public CdpSysDictionaryDetailEntity delete(CdpSysDictionaryDetailEntity entity) throws CdpServiceException {
        Assert.notNull(entity, "传入了空的对象!");
        CdpSysUserEntity loginUser = LoginUserUtil.getUser();
        //不是超级管理员
        if (!SystemConstant.SUPER_USER_CODE.equals(loginUser.getUsercode())) {
            CdpSysUserEntity superUser = userService.findByUsercode(SystemConstant.SUPER_USER_CODE);
            //是超级管理员创建的数据则不需要非超级管理员修改
            if (superUser.getId().equals(entity.getCreateBy())) {
                throw new CdpServiceException("没有权限修改系统创建的字典明细项!");
            }
        }
        dictionaryDetailRepository.delete(entity);
        return null;
    }

    @Override
    public Result findAllByPageSort(PageModule pageModule) {
        PageRequest pageable = new PageRequest(pageModule.getPageNumber() - 1, pageModule.getPageSize(), Sort.Direction.fromString(pageModule.getSortOrder()), pageModule.getSortName());
        String searchCondition = pageModule.getSearchText();

        Specification<CdpSysDictionaryDetailEntity> condition = new Specification<CdpSysDictionaryDetailEntity>() {
            @Override
            public Predicate toPredicate(Root<CdpSysDictionaryDetailEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<String> name = root.get("name");
                Path<String> value = root.get("value");
                Path<String> code = root.get("code");
                if (StringUtils.isBlank(searchCondition)) {
                    return query.getRestriction();
                }
                return cb.or(cb.like(code, "%" + searchCondition + "%"), cb.like(name, "%" + searchCondition + "%"), cb.like(value, "%" + searchCondition + "%"));

            }
        };
        //分页查找
        Page<CdpSysDictionaryDetailEntity> page = dictionaryDetailRepository.findAll(condition, pageable);

        return ResultUtils.success(page.getTotalElements(), page.getContent());
    }

    @Override
    public Page<CdpSysDictionaryDetailEntity> findAll(String searchCondition, Pageable pageable, Long code) throws CdpServiceException {
        Specification<CdpSysDictionaryDetailEntity> condition = new Specification<CdpSysDictionaryDetailEntity>() {
            @Override
            public Predicate toPredicate(Root<CdpSysDictionaryDetailEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<String> name = root.get("name");
                Path<String> value = root.get("value");
                Path<String> codePath = root.get("code");
                Predicate predicate1 = cb.equal(codePath, code);
                Predicate predicate2 = cb.or(cb.like(value, "%" + searchCondition + "%"));
                if (StringUtils.isBlank(searchCondition)) {
                    return predicate1;
                }
                if (NumberUtil.isInteger(searchCondition)) {
                    predicate2 = cb.or(cb.like(name, "%" + searchCondition + "%"), cb.like(codePath, "%" + searchCondition + "%"), predicate2);
                }

                return cb.and(predicate1, predicate2);
            }
        };
        return dictionaryDetailRepository.findAll(condition, pageable);
    }

    @Override
    @Cacheable(value = TableNameConstant.CDP_SYS_DICTIONARY_DETAIL, key = "#code")
    public List<CdpSysDictionaryDetailEntity> findByCode(Long code) {
        return dictionaryDetailRepository.findByCode(code);
    }


    @Override
    public IJpaRepository<CdpSysDictionaryDetailEntity, Long> getRepository() {
        return dictionaryDetailRepository;
    }
}
