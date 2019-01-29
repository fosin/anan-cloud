package com.github.fosin.cdp.platformapi.service;


import com.github.fosin.cdp.cache.util.CacheUtil;
import com.github.fosin.cdp.core.exception.CdpServiceException;
import com.github.fosin.cdp.jpa.repository.IJpaRepository;
import com.github.fosin.cdp.mvc.module.PageModule;
import com.github.fosin.cdp.mvc.result.Result;
import com.github.fosin.cdp.mvc.result.ResultUtils;
import com.github.fosin.cdp.platformapi.constant.SystemConstant;
import com.github.fosin.cdp.platformapi.constant.TableNameConstant;
import com.github.fosin.cdp.platformapi.dto.request.CdpSysDictionaryDetailCreateDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpSysDictionaryDetailUpdateDto;
import com.github.fosin.cdp.platformapi.entity.CdpSysDictionaryDetailEntity;
import com.github.fosin.cdp.platformapi.entity.CdpSysUserEntity;
import com.github.fosin.cdp.platformapi.repository.DictionaryDetailRepository;
import com.github.fosin.cdp.platformapi.service.inter.IDictionaryDetailService;
import com.github.fosin.cdp.platformapi.service.inter.IUserService;
import com.github.fosin.cdp.platformapi.util.LoginUserUtil;
import com.github.fosin.cdp.util.NumberUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
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
    @CacheEvict(value = TableNameConstant.CDP_SYS_DICTIONARY_DETAIL, key = "#result.dictionaryId")
    public CdpSysDictionaryDetailEntity create(CdpSysDictionaryDetailCreateDto entity) {
        Assert.notNull(entity, "传入的创建数据实体对象不能为空!");
        CdpSysDictionaryDetailEntity createEntiy = new CdpSysDictionaryDetailEntity();
        BeanUtils.copyProperties(entity, createEntiy);
        return getRepository().save(createEntiy);
    }

    @Override
    @CacheEvict(value = TableNameConstant.CDP_SYS_DICTIONARY_DETAIL, key = "#entity.dictionaryId")
    public CdpSysDictionaryDetailEntity update(CdpSysDictionaryDetailUpdateDto entity) {
        Assert.notNull(entity, "传入的更新数据实体对象不能为空!");
        Long id = entity.getId();
        Assert.notNull(id, "传入的更新数据实体对象主键不能为空!");
        CdpSysDictionaryDetailEntity findEntity = dictionaryDetailRepository.findOne(id);
        Assert.notNull(findEntity, "根据传入的主键[" + id + "]在数据库中未能找到数据!");
        CdpSysUserEntity loginUser = LoginUserUtil.getUser();
        //不是超级管理员
        if (!SystemConstant.SUPER_USER_CODE.equals(loginUser.getUsercode())) {
            CdpSysUserEntity superUser = userService.findByUsercode(SystemConstant.SUPER_USER_CODE);
            //是超级管理员创建的数据则不需要非超级管理员修改
            if (superUser.getId().equals(findEntity.getCreateBy())) {
                throw new CdpServiceException("没有权限修改系统创建的字典明细项!");
            }
        }
        BeanUtils.copyProperties(entity, findEntity);
        return dictionaryDetailRepository.save(findEntity);
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

        CacheUtil.evict(TableNameConstant.CDP_SYS_DICTIONARY_DETAIL, entity.getDictionaryId() + "");
        dictionaryDetailRepository.delete(id);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new CdpServiceException(e);
        }
        CacheUtil.evict(TableNameConstant.CDP_SYS_DICTIONARY_DETAIL, entity.getDictionaryId() + "");
        return null;
    }

    @Override
    @CacheEvict(value = TableNameConstant.CDP_SYS_DICTIONARY_DETAIL, key = "#entity.dictionaryId")
    public CdpSysDictionaryDetailEntity delete(CdpSysDictionaryDetailEntity entity) {
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
        return entity;
    }

    @Override
    public Result findAllByPageSort(PageModule pageModule) {
        PageRequest pageable = new PageRequest(pageModule.getPageNumber() - 1, pageModule.getPageSize(), Sort.Direction.fromString(pageModule.getSortOrder()), pageModule.getSortName());
        String searchCondition = pageModule.getSearchText();

        Specification<CdpSysDictionaryDetailEntity> condition = (root, query, cb) -> {
            Path<String> name = root.get("name");
            Path<String> value = root.get("value");
            Path<String> code = root.get("dictionaryId");
            if (StringUtils.isBlank(searchCondition)) {
                return query.getRestriction();
            }
            return cb.or(cb.like(code, "%" + searchCondition + "%"), cb.like(name, "%" + searchCondition + "%"), cb.like(value, "%" + searchCondition + "%"));

        };
        //分页查找
        Page<CdpSysDictionaryDetailEntity> page = dictionaryDetailRepository.findAll(condition, pageable);

        return ResultUtils.success(page.getTotalElements(), page.getContent());
    }

    @Override
    public Page<CdpSysDictionaryDetailEntity> findAll(String searchCondition, Pageable pageable, Long dictionaryId) {
        Specification<CdpSysDictionaryDetailEntity> condition = (root, query, cb) -> {
            Path<String> name = root.get("name");
            Path<String> value = root.get("value");
            Path<String> dictionaryIdPath = root.get("dictionaryId");
            Predicate predicate1 = cb.equal(dictionaryIdPath, dictionaryId);
            Predicate predicate2 = cb.or(cb.like(value, "%" + searchCondition + "%"));
            if (StringUtils.isBlank(searchCondition)) {
                return predicate1;
            }
            if (NumberUtil.isInteger(searchCondition)) {
                predicate2 = cb.or(cb.like(name, "%" + searchCondition + "%"), cb.like(dictionaryIdPath, "%" + searchCondition + "%"), predicate2);
            }

            return cb.and(predicate1, predicate2);
        };
        return dictionaryDetailRepository.findAll(condition, pageable);
    }

    @Override
    @Cacheable(value = TableNameConstant.CDP_SYS_DICTIONARY_DETAIL, key = "#dictionaryId")
    public List<CdpSysDictionaryDetailEntity> findByDictionaryId(Long dictionaryId) {
        return dictionaryDetailRepository.findByDictionaryId(dictionaryId);
    }


    @Override
    public IJpaRepository<CdpSysDictionaryDetailEntity, Long> getRepository() {
        return dictionaryDetailRepository;
    }
}
