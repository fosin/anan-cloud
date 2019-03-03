package com.github.fosin.cdp.platformapi.service;


import com.github.fosin.cdp.cache.util.CacheUtil;
import com.github.fosin.cdp.core.exception.CdpServiceException;
import com.github.fosin.cdp.jpa.repository.IJpaRepository;
import com.github.fosin.cdp.mvc.module.PageModule;
import com.github.fosin.cdp.mvc.result.Result;
import com.github.fosin.cdp.mvc.result.ResultUtils;
import com.github.fosin.cdp.platformapi.constant.SystemConstant;
import com.github.fosin.cdp.platformapi.constant.TableNameConstant;
import com.github.fosin.cdp.platformapi.dto.request.CdpDictionaryDetailCreateDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpDictionaryDetailUpdateDto;
import com.github.fosin.cdp.platformapi.entity.CdpDictionaryDetailEntity;
import com.github.fosin.cdp.platformapi.entity.CdpUserEntity;
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
import org.springframework.data.auditing.CurrentDateTimeProvider;
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
import java.util.Objects;

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
    @CacheEvict(value = TableNameConstant.CDP_DICTIONARY_DETAIL, key = "#result.dictionaryId")
    public CdpDictionaryDetailEntity create(CdpDictionaryDetailCreateDto entity) {
        Assert.notNull(entity, "传入的创建数据实体对象不能为空!");
        CdpDictionaryDetailEntity createEntiy = new CdpDictionaryDetailEntity();
        BeanUtils.copyProperties(entity, createEntiy);
        return getRepository().save(createEntiy);
    }

    @Override
    @CacheEvict(value = TableNameConstant.CDP_DICTIONARY_DETAIL, key = "#entity.dictionaryId")
    public CdpDictionaryDetailEntity update(CdpDictionaryDetailUpdateDto entity) {
        Assert.notNull(entity, "传入的更新数据实体对象不能为空!");
        Long id = entity.getId();
        Assert.notNull(id, "传入的更新数据实体对象主键不能为空!");
        CdpDictionaryDetailEntity findEntity = dictionaryDetailRepository.findById(id).orElse(null);
        Assert.notNull(findEntity, "根据传入的主键[" + id + "]在数据库中未能找到数据!");
        isSuperUser(findEntity);
        BeanUtils.copyProperties(entity, findEntity);
        return dictionaryDetailRepository.save(findEntity);
    }

    private void isSuperUser(CdpDictionaryDetailEntity findEntity) {
        CdpUserEntity loginUser = LoginUserUtil.getUser();
        //不是超级管理员
        if (!SystemConstant.SUPER_USER_CODE.equals(loginUser.getUsercode())) {
            CdpUserEntity superUser = userService.findByUsercode(SystemConstant.SUPER_USER_CODE);
            //是超级管理员创建的数据则不需要非超级管理员修改
            if (Objects.equals(superUser.getId(), findEntity.getCreateBy())) {
                throw new CdpServiceException("没有权限修改系统创建的字典明细项!");
            }
        }
    }

    @Override
    public CdpDictionaryDetailEntity deleteById(Long id) {
        Assert.notNull(id, "传入了空的ID!");
        CdpDictionaryDetailEntity entity = dictionaryDetailRepository.findById(id).orElse(null);
        Assert.notNull(entity, "传入的ID找不到数据!");
        isSuperUser(entity);

        CacheUtil.evict(TableNameConstant.CDP_DICTIONARY_DETAIL, entity.getDictionaryId() + "");
        dictionaryDetailRepository.deleteById(id);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new CdpServiceException(e);
        }
        CacheUtil.evict(TableNameConstant.CDP_DICTIONARY_DETAIL, entity.getDictionaryId() + "");
        return null;
    }

    @Override
    @CacheEvict(value = TableNameConstant.CDP_DICTIONARY_DETAIL, key = "#entity.dictionaryId")
    public CdpDictionaryDetailEntity deleteByEntity(CdpDictionaryDetailEntity entity) {
        Assert.notNull(entity, "传入了空的对象!");
        isSuperUser(entity);
        dictionaryDetailRepository.delete(entity);
        return entity;
    }

    @Override
    public Result findAllByPageSort(PageModule pageModule) {
        PageRequest pageable = PageRequest.of(pageModule.getPageNumber() - 1, pageModule.getPageSize(), Sort.Direction.fromString(pageModule.getSortOrder()), pageModule.getSortName());
        String searchCondition = pageModule.getSearchText();

        Specification<CdpDictionaryDetailEntity> condition = (root, query, cb) -> {
            Path<String> name = root.get("name");
            Path<String> value = root.get("value");
            Path<String> code = root.get("dictionaryId");
            if (StringUtils.isBlank(searchCondition)) {
                return query.getRestriction();
            }
            return cb.or(cb.like(code, "%" + searchCondition + "%"), cb.like(name, "%" + searchCondition + "%"), cb.like(value, "%" + searchCondition + "%"));

        };
        //分页查找
        Page<CdpDictionaryDetailEntity> page = dictionaryDetailRepository.findAll(condition, pageable);

        return ResultUtils.success(page.getTotalElements(), page.getContent());
    }

    @Override
    public Page<CdpDictionaryDetailEntity> findAll(String searchCondition, Pageable pageable, Long dictionaryId) {
        Specification<CdpDictionaryDetailEntity> condition = (root, query, cb) -> {
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
    @Cacheable(value = TableNameConstant.CDP_DICTIONARY_DETAIL, key = "#dictionaryId")
    public List<CdpDictionaryDetailEntity> findByDictionaryId(Long dictionaryId) {
        return dictionaryDetailRepository.findByDictionaryId(dictionaryId);
    }


    @Override
    public IJpaRepository<CdpDictionaryDetailEntity, Long> getRepository() {
        return dictionaryDetailRepository;
    }
}
