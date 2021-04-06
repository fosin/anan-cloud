package com.github.fosin.anan.platform.service;

import com.github.fosin.anan.cloudresource.constant.RedisConstant;
import com.github.fosin.anan.cloudresource.constant.SystemConstant;
import com.github.fosin.anan.cloudresource.dto.request.AnanDictionaryDetailCreateDto;
import com.github.fosin.anan.cloudresource.dto.request.AnanDictionaryDetailUpdateDto;
import com.github.fosin.anan.core.exception.AnanServiceException;
import com.github.fosin.anan.jpa.repository.IJpaRepository;
import com.github.fosin.anan.model.module.PageModule;
import com.github.fosin.anan.model.result.Result;
import com.github.fosin.anan.model.result.ResultUtils;
import com.github.fosin.anan.platform.repository.DictionaryDetailRepository;
import com.github.fosin.anan.platform.repository.DictionaryRepository;
import com.github.fosin.anan.platform.service.inter.DictionaryDetailService;
import com.github.fosin.anan.platformapi.entity.AnanDictionaryDetailEntity;
import com.github.fosin.anan.platformapi.entity.AnanDictionaryEntity;
import com.github.fosin.anan.platformapi.service.AnanUserDetailService;
import cn.hutool.core.util.NumberUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.List;

/**
 * 字典表服务
 *
 * @author fosin
 * @date 2018-7-29
 */
@Service
@Lazy
public class DictionaryDetailServiceImpl implements DictionaryDetailService {

    private final DictionaryDetailRepository dictionaryDetailRepository;
    private final AnanUserDetailService ananUserDetailService;
    private final DictionaryRepository dictionaryRepository;

    public DictionaryDetailServiceImpl(DictionaryDetailRepository dictionaryDetailRepository,
                                       AnanUserDetailService ananUserDetailService,
                                       DictionaryRepository dictionaryRepository) {
        this.dictionaryDetailRepository = dictionaryDetailRepository;
        this.ananUserDetailService = ananUserDetailService;
        this.dictionaryRepository = dictionaryRepository;
    }

    @Override
    @CacheEvict(value = RedisConstant.ANAN_DICTIONARY_DETAIL, key = "#result.dictionaryId")
    public AnanDictionaryDetailEntity create(AnanDictionaryDetailCreateDto entity) {
        Assert.notNull(entity, "传入的创建数据实体对象不能为空!");
        AnanDictionaryDetailEntity createEntiy = new AnanDictionaryDetailEntity();
        BeanUtils.copyProperties(entity, createEntiy);
        hasModifiedPrivileges(createEntiy.getDictionaryId());
        return getRepository().save(createEntiy);
    }

    @Override
    @CacheEvict(value = RedisConstant.ANAN_DICTIONARY_DETAIL, key = "#entity.dictionaryId")
    public AnanDictionaryDetailEntity update(AnanDictionaryDetailUpdateDto entity) {
        Assert.notNull(entity, "传入的更新数据实体对象不能为空!");
        Long id = entity.getId();
        Assert.notNull(id, "传入的更新数据实体对象主键不能为空!");
        AnanDictionaryDetailEntity findEntity = dictionaryDetailRepository.findById(id).orElse(null);
        Assert.notNull(findEntity, "根据传入的主键[" + id + "]在数据库中未能找到数据!");
        hasModifiedPrivileges(entity.getDictionaryId());
        BeanUtils.copyProperties(entity, findEntity);
        return dictionaryDetailRepository.save(findEntity);
    }

    private void hasModifiedPrivileges(Long dictionaryId) {
        AnanDictionaryEntity dictionaryEntity = dictionaryRepository.findById(dictionaryId).orElse(new AnanDictionaryEntity());
        if (SystemConstant.SYSTEM_DICTIONARY_TYPE.equals(dictionaryEntity.getType())) {
            //非超级管理员不能修改系统字典
            Assert.isTrue(ananUserDetailService.hasSysAdminRole(), "没有权限增删改系统字典!");
        }
    }

    @Override
    @CacheEvict(value = RedisConstant.ANAN_DICTIONARY_DETAIL, key = "#result.dictionaryId")
    public AnanDictionaryDetailEntity deleteById(Long id) {
        Assert.notNull(id, "传入了空的ID!");
        AnanDictionaryDetailEntity entity = dictionaryDetailRepository.findById(id).orElse(null);
        Assert.notNull(entity, "传入的ID找不到数据!");
        hasModifiedPrivileges(entity.getDictionaryId());
        dictionaryDetailRepository.deleteById(id);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new AnanServiceException(e);
        }
        return null;
    }

    @Override
    @CacheEvict(value = RedisConstant.ANAN_DICTIONARY_DETAIL, key = "#entity.dictionaryId")
    public AnanDictionaryDetailEntity deleteByEntity(AnanDictionaryDetailEntity entity) {
        Assert.notNull(entity, "传入了空的对象!");
        hasModifiedPrivileges(entity.getDictionaryId());
        dictionaryDetailRepository.delete(entity);
        return entity;
    }

    @Override
    public Result findAllByPageSort(PageModule pageModule) {
        PageRequest pageable = PageRequest.of(pageModule.getPageNumber() - 1, pageModule.getPageSize(), Sort.Direction.fromString(pageModule.getSortOrder()), pageModule.getSortName());
        String searchCondition = pageModule.getSearchText();

        Specification<AnanDictionaryDetailEntity> condition = (root, query, cb) -> {
            Path<String> name = root.get("name");
            Path<String> value = root.get("value");
            Path<String> code = root.get("dictionaryId");
            if (StringUtils.isBlank(searchCondition)) {
                return query.getRestriction();
            }
            return cb.or(cb.like(code, "%" + searchCondition + "%"), cb.like(name, "%" + searchCondition + "%"), cb.like(value, "%" + searchCondition + "%"));

        };
        //分页查找
        Page<AnanDictionaryDetailEntity> page = dictionaryDetailRepository.findAll(condition, pageable);

        return ResultUtils.success(page.getTotalElements(), page.getContent());
    }

    @Override
    public Page<AnanDictionaryDetailEntity> findAll(String searchCondition, Pageable pageable, Long dictionaryId) {
        Specification<AnanDictionaryDetailEntity> condition = (root, query, cb) -> {
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
    @Cacheable(value = RedisConstant.ANAN_DICTIONARY_DETAIL, key = "#dictionaryId")
    public List<AnanDictionaryDetailEntity> findByDictionaryId(Long dictionaryId) {
        Sort sort = Sort.by(Sort.Direction.fromString("ASC"), "sort");
        return dictionaryDetailRepository.findAllByDictionaryId(dictionaryId, sort);
    }


    @Override
    public IJpaRepository<AnanDictionaryDetailEntity, Long> getRepository() {
        return dictionaryDetailRepository;
    }
}
