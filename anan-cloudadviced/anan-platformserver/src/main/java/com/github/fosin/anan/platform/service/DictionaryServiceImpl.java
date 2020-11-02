package com.github.fosin.anan.platform.service;


import com.github.fosin.anan.cloudresource.constant.SystemConstant;
import com.github.fosin.anan.cloudresource.dto.request.AnanDictionaryCreateDto;
import com.github.fosin.anan.cloudresource.dto.request.AnanDictionaryUpdateDto;
import com.github.fosin.anan.core.exception.AnanServiceException;
import com.github.fosin.anan.jpa.repository.IJpaRepository;
import com.github.fosin.anan.model.module.PageModule;
import com.github.fosin.anan.model.result.Result;
import com.github.fosin.anan.model.result.ResultUtils;
import com.github.fosin.anan.platform.repository.DictionaryDetailRepository;
import com.github.fosin.anan.platform.repository.DictionaryRepository;
import com.github.fosin.anan.platform.service.inter.DictionaryService;
import com.github.fosin.anan.platformapi.entity.AnanDictionaryEntity;
import com.github.fosin.anan.platformapi.service.AnanUserDetailService;
import com.github.fosin.anan.util.NumberUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
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
    private final DictionaryRepository dictionaryRepository;
    private final DictionaryDetailRepository dictionaryDetailRepository;
    private final AnanUserDetailService ananUserDetailService;

    public DictionaryServiceImpl(DictionaryRepository dictionaryRepository, DictionaryDetailRepository dictionaryDetailRepository, AnanUserDetailService ananUserDetailService) {
        this.dictionaryRepository = dictionaryRepository;
        this.dictionaryDetailRepository = dictionaryDetailRepository;
        this.ananUserDetailService = ananUserDetailService;
    }

    @Override
    public AnanDictionaryEntity create(AnanDictionaryCreateDto entity) {
        Assert.notNull(entity, "传入的创建数据实体对象不能为空!");
        //系统字典
        if (Objects.equals(entity.getType(), SystemConstant.SYSTEM_DICTIONARY_TYPE)) {

            //非超级管理员不能创建系统字典
            Assert.isTrue(ananUserDetailService.hasSysAdminRole(), "没有权限修改系统创建的字典明细项");

        }
        AnanDictionaryEntity createEntity = new AnanDictionaryEntity();
        BeanUtils.copyProperties(entity, createEntity);
        return dictionaryRepository.save(createEntity);
    }

    @Override
    public AnanDictionaryEntity update(AnanDictionaryUpdateDto entity) {
        Assert.notNull(entity, "无效的更新数据");
        Long id = entity.getId();
        Assert.notNull(id, "无效的字典代码id");
        AnanDictionaryEntity updateEntity = dictionaryRepository.findById(id).orElse(null);
        Assert.notNull(updateEntity, "根据传入的字典code" + id + "在数据库中未能找到对于数据!");
        //系统字典
        if (entity.getType().equals(SystemConstant.SYSTEM_DICTIONARY_TYPE)) {

            //非超级管理员不能创建系统字典
            Assert.isTrue(ananUserDetailService.hasSysAdminRole(), "没有权限修改系统创建的字典明细项");
        }

        BeanUtils.copyProperties(entity, updateEntity);
        return dictionaryRepository.save(updateEntity);
    }

    @Override
    @Transactional(rollbackFor = AnanServiceException.class)
    public AnanDictionaryEntity deleteById(Long code) {
        if (code == null) {
            throw new AnanServiceException("传入的code无效!");
        }
        AnanDictionaryEntity entity = dictionaryRepository.findById(code).orElse(null);
//        if (entity == null) {
//            throw new AnanServiceException("通过code没有找到对应的字典!");
//        }
        //系统字典

        if (Objects.equals(SystemConstant.SYSTEM_DICTIONARY_TYPE, Objects.requireNonNull(entity, "通过code没有找到对应的字典").getType())) {
            //非超级管理员不能删除系统字典
            Assert.isTrue(!ananUserDetailService.hasSysAdminRole(), "没有权限删除系统字典");
        }
        dictionaryDetailRepository.deleteAllByDictionaryId(entity.getId());
        dictionaryRepository.deleteById(code);
        return null;
    }

    @Override
    @Transactional(rollbackFor = AnanServiceException.class)
    public AnanDictionaryEntity deleteByEntity(AnanDictionaryEntity entity) {
        Assert.notNull(entity, "传入了空的对象!");
        //系统字典
        if (entity.getType().equals(SystemConstant.SYSTEM_DICTIONARY_TYPE)) {

            //非超级管理员不能删除系统字典
            Assert.isTrue(!ananUserDetailService.hasSysAdminRole(), "没有权限删除系统字典");
        }
        dictionaryDetailRepository.deleteAllByDictionaryId(entity.getId());
        dictionaryRepository.delete(entity);
        return entity;
    }

    @Override
    public Result findAllByPageSort(PageModule pageModule) {
        PageRequest pageable = PageRequest.of(pageModule.getPageNumber() - 1, pageModule.getPageSize(), Sort.Direction.fromString(pageModule.getSortOrder()), pageModule.getSortName());
        String searchCondition = pageModule.getSearchText();

        Specification<AnanDictionaryEntity> condition = (Specification<AnanDictionaryEntity>) (root, query, cb) -> {
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
        Page<AnanDictionaryEntity> page = dictionaryRepository.findAll(condition, pageable);

        return ResultUtils.success(page.getTotalElements(), page.getContent());
    }

    @Override
    public IJpaRepository<AnanDictionaryEntity, Long> getRepository() {
        return dictionaryRepository;
    }
}
