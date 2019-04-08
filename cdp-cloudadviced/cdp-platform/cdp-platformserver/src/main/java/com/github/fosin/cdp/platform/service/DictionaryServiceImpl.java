package com.github.fosin.cdp.platform.service;


import com.github.fosin.cdp.jpa.repository.IJpaRepository;
import com.github.fosin.cdp.platform.service.inter.DictionaryService;
import com.github.fosin.cdp.platformapi.dto.request.CdpDictionaryCreateDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpDictionaryUpdateDto;
import com.github.fosin.cdp.platform.repository.DictionaryDetailRepository;
import com.github.fosin.cdp.core.exception.CdpServiceException;
import com.github.fosin.cdp.mvc.module.PageModule;
import com.github.fosin.cdp.mvc.result.Result;
import com.github.fosin.cdp.mvc.result.ResultUtils;
import com.github.fosin.cdp.platformapi.constant.SystemConstant;
import com.github.fosin.cdp.platformapi.entity.CdpDictionaryEntity;
import com.github.fosin.cdp.platformapi.entity.CdpUserEntity;
import com.github.fosin.cdp.platform.repository.DictionaryRepository;
import com.github.fosin.cdp.platformapi.util.LoginUserUtil;
import com.github.fosin.cdp.util.NumberUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.criteria.*;
import java.util.Objects;

/**
 * 字典表服务
 *
 * @author fosin
 * @date 2018-7-29
 */
@Service
@Lazy
public class DictionaryServiceImpl implements DictionaryService {
    @Autowired
    private DictionaryRepository dictionaryRepository;
    @Autowired
    private DictionaryDetailRepository dictionaryDetailRepository;

    @Override
    public CdpDictionaryEntity create(CdpDictionaryCreateDto entity) {
        Assert.notNull(entity, "传入的创建数据实体对象不能为空!");
        //系统字典
        if (Objects.equals(entity.getType(), SystemConstant.SYSTEM_DICTIONARY_TYPE)) {
            CdpUserEntity loginUser = LoginUserUtil.getUser();
            //非超级管理员不能创建系统字典
            Assert.isTrue(SystemConstant.SUPER_USER_CODE.equals(loginUser.getUsercode()), "没有权限创建系统字典!");

        }
        CdpDictionaryEntity createEntity = new CdpDictionaryEntity();
        BeanUtils.copyProperties(entity, createEntity);
        return dictionaryRepository.save(createEntity);
    }

    @Override
    public CdpDictionaryEntity update(CdpDictionaryUpdateDto entity) {
        Assert.notNull(entity, "无效的更新数据");
        Long id = entity.getId();
        Assert.notNull(id, "无效的字典代码id");
        CdpDictionaryEntity updateEntity = dictionaryRepository.findById(id).orElse(null);
        Assert.notNull(updateEntity, "根据传入的字典code" + id + "在数据库中未能找到对于数据!");
        //系统字典
        if (entity.getType().equals(SystemConstant.SYSTEM_DICTIONARY_TYPE)) {
            CdpUserEntity loginUser = LoginUserUtil.getUser();
            //非超级管理员不能创建系统字典
            Assert.isTrue(SystemConstant.SUPER_USER_CODE.equals(loginUser.getUsercode()), "没有权限修改系统字典!");
        }

        BeanUtils.copyProperties(entity, updateEntity);
        return dictionaryRepository.save(updateEntity);
    }

    @Override
    @Transactional(rollbackFor = CdpServiceException.class)
    public CdpDictionaryEntity deleteById(Long code) {
        if (code == null) {
            throw new CdpServiceException("传入的code无效!");
        }
        CdpDictionaryEntity entity = dictionaryRepository.findById(code).orElse(null);
//        if (entity == null) {
//            throw new CdpServiceException("通过code没有找到对应的字典!");
//        }
        //系统字典

        if (Objects.equals(SystemConstant.SYSTEM_DICTIONARY_TYPE, Objects.requireNonNull(entity,"通过code没有找到对应的字典").getType())) {
            CdpUserEntity loginUser = LoginUserUtil.getUser();
            //非超级管理员不能删除系统字典
            if (!SystemConstant.SUPER_USER_CODE.equals(loginUser.getUsercode())) {
                throw new CdpServiceException("没有权限删除系统字典!");
            }
        }
        dictionaryDetailRepository.deleteAllByDictionaryId(entity.getId());
        dictionaryRepository.deleteById(code);
        return null;
    }

    @Override
    @Transactional(rollbackFor = CdpServiceException.class)
    public CdpDictionaryEntity deleteByEntity(CdpDictionaryEntity entity) {
        Assert.notNull(entity, "传入了空的对象!");
        //系统字典
        if (entity.getType().equals(SystemConstant.SYSTEM_DICTIONARY_TYPE)) {
            CdpUserEntity loginUser = LoginUserUtil.getUser();
            //非超级管理员不能删除系统字典
            Assert.isTrue(SystemConstant.SUPER_USER_CODE.equals(loginUser.getUsercode()), "没有权限删除系统字典!");
        }
        dictionaryDetailRepository.deleteAllByDictionaryId(entity.getId());
        dictionaryRepository.delete(entity);
        return entity;
    }

    @Override
    public Result findAllByPageSort(PageModule pageModule) {
        PageRequest pageable = PageRequest.of(pageModule.getPageNumber() - 1, pageModule.getPageSize(), Sort.Direction.fromString(pageModule.getSortOrder()), pageModule.getSortName());
        String searchCondition = pageModule.getSearchText();

        Specification<CdpDictionaryEntity> condition = (Specification<CdpDictionaryEntity>) (root, query, cb) -> {
            Path<String> type = root.get("type");
            Path<String> scope = root.get("scope");
            Path<String> name = root.get("name");

            Predicate predicate = cb.or(cb.like(scope, "%" + searchCondition + "%"), cb.like(name, "%" + searchCondition + "%"));
            if (StringUtils.isBlank(searchCondition)) {
                return query.getRestriction();
            }
            if (NumberUtil.isInteger(searchCondition)) {
                predicate = cb.or(cb.like(type, "%" + searchCondition + "%"));
            }
            return predicate;
        };
        //分页查找
        Page<CdpDictionaryEntity> page = dictionaryRepository.findAll(condition, pageable);

        return ResultUtils.success(page.getTotalElements(), page.getContent());
    }

    @Override
    public IJpaRepository<CdpDictionaryEntity, Long> getRepository() {
        return dictionaryRepository;
    }
}
