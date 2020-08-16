package com.github.fosin.anan.platform.service;

import com.github.fosin.anan.core.exception.AnanServiceException;
import com.github.fosin.anan.jpa.repository.IJpaRepository;
import com.github.fosin.anan.model.module.PageModule;
import com.github.fosin.anan.model.result.Result;
import com.github.fosin.anan.model.result.ResultUtils;
import com.github.fosin.anan.platform.repository.DictionaryDetailRepository;
import com.github.fosin.anan.platform.service.inter.DictionaryDetailService;
import com.github.fosin.anan.platform.service.inter.UserService;
import com.github.fosin.anan.platformapi.constant.SystemConstant;
import com.github.fosin.anan.platformapi.constant.RedisConstant;
import com.github.fosin.anan.platformapi.entity.AnanDictionaryDetailEntity;
import com.github.fosin.anan.platformapi.entity.AnanUserEntity;
import com.github.fosin.anan.platformapi.util.LoginUserUtil;
import com.github.fosin.anan.pojo.dto.AnanUserDto;
import com.github.fosin.anan.pojo.dto.request.AnanDictionaryDetailCreateDto;
import com.github.fosin.anan.pojo.dto.request.AnanDictionaryDetailUpdateDto;
import com.github.fosin.anan.redis.cache.AnanCacheManger;
import com.github.fosin.anan.util.NumberUtil;
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
import java.util.Objects;

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
    private final UserService userService;
    private final AnanCacheManger ananCacheManger;

    public DictionaryDetailServiceImpl(DictionaryDetailRepository dictionaryDetailRepository, UserService userService, AnanCacheManger ananCacheManger) {
        this.dictionaryDetailRepository = dictionaryDetailRepository;
        this.userService = userService;
        this.ananCacheManger = ananCacheManger;
    }

    @Override
    @CacheEvict(value = RedisConstant.ANAN_DICTIONARY_DETAIL, key = "#result.dictionaryId")
    public AnanDictionaryDetailEntity create(AnanDictionaryDetailCreateDto entity) {
        Assert.notNull(entity, "传入的创建数据实体对象不能为空!");
        AnanDictionaryDetailEntity createEntiy = new AnanDictionaryDetailEntity();
        BeanUtils.copyProperties(entity, createEntiy);
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
        isSuperUser(findEntity);
        BeanUtils.copyProperties(entity, findEntity);
        return dictionaryDetailRepository.save(findEntity);
    }

    private void isSuperUser(AnanDictionaryDetailEntity findEntity) {
        AnanUserDto loginUser = LoginUserUtil.getUser();
        //不是超级管理员
        if (!SystemConstant.ANAN_USER_CODE.equals(loginUser.getUsercode())) {
            AnanUserEntity superUser = userService.findByUsercode(SystemConstant.ANAN_USER_CODE);
            //是超级管理员创建的数据则不需要非超级管理员修改
            if (Objects.equals(superUser.getId(), findEntity.getCreateBy())) {
                throw new AnanServiceException("没有权限修改系统创建的字典明细项!");
            }
        }
    }

    @Override
    public AnanDictionaryDetailEntity deleteById(Long id) {
        Assert.notNull(id, "传入了空的ID!");
        AnanDictionaryDetailEntity entity = dictionaryDetailRepository.findById(id).orElse(null);
        Assert.notNull(entity, "传入的ID找不到数据!");
        isSuperUser(entity);

        ananCacheManger.evict(RedisConstant.ANAN_DICTIONARY_DETAIL, entity.getDictionaryId() + "");
        dictionaryDetailRepository.deleteById(id);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new AnanServiceException(e);
        }
        ananCacheManger.evict(RedisConstant.ANAN_DICTIONARY_DETAIL, entity.getDictionaryId() + "");
        return null;
    }

    @Override
    @CacheEvict(value = RedisConstant.ANAN_DICTIONARY_DETAIL, key = "#entity.dictionaryId")
    public AnanDictionaryDetailEntity deleteByEntity(AnanDictionaryDetailEntity entity) {
        Assert.notNull(entity, "传入了空的对象!");
        isSuperUser(entity);
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
        return dictionaryDetailRepository.findByDictionaryId(dictionaryId);
    }


    @Override
    public IJpaRepository<AnanDictionaryDetailEntity, Long> getRepository() {
        return dictionaryDetailRepository;
    }
}
