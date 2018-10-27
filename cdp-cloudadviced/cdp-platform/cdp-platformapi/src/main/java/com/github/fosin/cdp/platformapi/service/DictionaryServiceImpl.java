package com.github.fosin.cdp.platformapi.service;


import com.github.fosin.cdp.platformapi.repository.DictionaryDetailRepository;
import com.github.fosin.cdp.core.exception.CdpServiceException;
import com.github.fosin.cdp.mvc.module.PageModule;
import com.github.fosin.cdp.mvc.result.Result;
import com.github.fosin.cdp.mvc.result.ResultUtils;
import com.github.fosin.cdp.platformapi.constant.SystemConstant;
import com.github.fosin.cdp.platformapi.entity.CdpSysDictionaryEntity;
import com.github.fosin.cdp.platformapi.entity.CdpSysUserEntity;
import com.github.fosin.cdp.platformapi.repository.DictionaryRepository;
import com.github.fosin.cdp.platformapi.service.inter.IDictionaryService;
import com.github.fosin.cdp.platformapi.util.LoginUserUtil;
import com.github.fosin.cdp.util.NumberUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.criteria.*;
import java.util.Collection;
import java.util.List;

/**
 * 字典表服务
 *
 * @author fosin
 * @date 2018-7-29
 */
@Service
public class DictionaryServiceImpl implements IDictionaryService {
    @Autowired
    private DictionaryRepository dictionaryRepository;
    @Autowired
    private DictionaryDetailRepository dictionaryDetailRepository;

    @Override
    public CdpSysDictionaryEntity create(CdpSysDictionaryEntity entity) throws CdpServiceException {
        //系统字典
        if (entity.getType().equals(SystemConstant.SYSTEM_DICTIONARY_TYPE)) {
            CdpSysUserEntity loginUser = LoginUserUtil.getUser();
            //非超级管理员不能创建系统字典
            Assert.isTrue(SystemConstant.SUPER_USER_CODE.equals(loginUser.getUsercode()), "没有权限创建系统字典!");

        }
        return dictionaryRepository.save(entity);
    }

    @Override
    public CdpSysDictionaryEntity update(CdpSysDictionaryEntity entity) throws CdpServiceException {
        Assert.notNull(entity, "无效的更新数据");
        Long code = entity.getCode();
        Assert.notNull(code, "无效的字典代码code");
        //系统字典
        if (entity.getType().equals(SystemConstant.SYSTEM_DICTIONARY_TYPE)) {
            CdpSysUserEntity loginUser = LoginUserUtil.getUser();
            //非超级管理员不能创建系统字典
            Assert.isTrue(SystemConstant.SUPER_USER_CODE.equals(loginUser.getUsercode()), "没有权限修改系统字典!");
        }
        return dictionaryRepository.save(entity);
    }

    @Override
    public List<CdpSysDictionaryEntity> findAll() {
        return dictionaryRepository.findAll();
    }

    @Override
    public CdpSysDictionaryEntity findOne(Long code) {
        return dictionaryRepository.findOne(code);
    }

    @Override
    @Transactional(rollbackFor = CdpServiceException.class)
    public CdpSysDictionaryEntity delete(Long code) throws CdpServiceException {
        if (code == null) {
            throw new CdpServiceException("传入的code无效!");
        }
        CdpSysDictionaryEntity entity = dictionaryRepository.findOne(code);
        if (entity == null) {
            throw new CdpServiceException("通过code没有找到对应的字典!");
        }
        //系统字典
        if (entity.getType().equals(SystemConstant.SYSTEM_DICTIONARY_TYPE)) {
            CdpSysUserEntity loginUser = LoginUserUtil.getUser();
            //非超级管理员不能删除系统字典
            if (!SystemConstant.SUPER_USER_CODE.equals(loginUser.getUsercode())) {
                throw new CdpServiceException("没有权限删除系统字典!");
            }
        }
        dictionaryDetailRepository.deleteAllByCode(entity.getCode());
        dictionaryRepository.delete(code);
        return null;
    }

    @Override
    @Transactional(rollbackFor = CdpServiceException.class)
    public Collection<CdpSysDictionaryEntity> delete(CdpSysDictionaryEntity entity) throws CdpServiceException {
        Assert.notNull(entity, "传入了空的对象!");
        //系统字典
        if (entity.getType().equals(SystemConstant.SYSTEM_DICTIONARY_TYPE)) {
            CdpSysUserEntity loginUser = LoginUserUtil.getUser();
            //非超级管理员不能删除系统字典
            Assert.isTrue(SystemConstant.SUPER_USER_CODE.equals(loginUser.getUsercode()), "没有权限删除系统字典!");
        }
        dictionaryDetailRepository.deleteAllByCode(entity.getCode());
        dictionaryRepository.delete(entity);
        return null;
    }

    @Override
    public Result findAllPage(PageModule pageModule) {
        PageRequest pageable = new PageRequest(pageModule.getPageNumber() - 1, pageModule.getPageSize(), Sort.Direction.fromString(pageModule.getSortOrder()), pageModule.getSortName());
        String searchCondition = pageModule.getSearchText();

        Specification<CdpSysDictionaryEntity> condition = new Specification<CdpSysDictionaryEntity>() {
            @Override
            public Predicate toPredicate(Root<CdpSysDictionaryEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<String> type = root.get("type");
                Path<String> scope = root.get("scope");
                Path<String> code = root.get("code");
                Path<String> name = root.get("name");

                Predicate predicate = cb.or(cb.like(scope, "%" + searchCondition + "%"), cb.like(name, "%" + searchCondition + "%"));
                if (StringUtils.isBlank(searchCondition)) {
                    return query.getRestriction();
                }
                if (NumberUtil.isInteger(searchCondition)) {
                    predicate = cb.or(cb.like(type, "%" + searchCondition + "%"), cb.like(code, "%" + searchCondition + "%"));
                }
                return predicate;
            }
        };
        //分页查找
        Page<CdpSysDictionaryEntity> page = dictionaryRepository.findAll(condition, pageable);

        return ResultUtils.success(page.getTotalElements(), page.getContent());
    }
}
