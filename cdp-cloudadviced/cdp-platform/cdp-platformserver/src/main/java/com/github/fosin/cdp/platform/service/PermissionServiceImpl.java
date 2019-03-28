package com.github.fosin.cdp.platform.service;

import com.github.fosin.cdp.jpa.repository.IJpaRepository;
import com.github.fosin.cdp.mvc.module.PageModule;
import com.github.fosin.cdp.mvc.result.Result;
import com.github.fosin.cdp.mvc.result.ResultUtils;
import com.github.fosin.cdp.platform.service.inter.IPermissionService;
import com.github.fosin.cdp.platform.service.inter.IRolePermissionService;
import com.github.fosin.cdp.platform.service.inter.IUserPermissionService;
import com.github.fosin.cdp.platformapi.constant.TableNameConstant;
import com.github.fosin.cdp.platformapi.dto.request.CdpPermissionCreateDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpPermissionUpdateDto;
import com.github.fosin.cdp.platformapi.entity.CdpPermissionEntity;
import com.github.fosin.cdp.platformapi.repository.PermissionRepository;
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
import org.springframework.util.Assert;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * 2017/12/29.
 * Time:12:38
 *
 * @author fosin
 */
@Service
@Lazy
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private IUserPermissionService userPermissionService;

    @Autowired
    private IRolePermissionService rolePermissionService;

    @Override
    @CachePut(value = TableNameConstant.CDP_PERMISSION, key = "#result.id")
    public CdpPermissionEntity create(CdpPermissionCreateDto entity) {
        Assert.notNull(entity, "传入的创建数据实体对象不能为空!");

        CdpPermissionEntity createEntity = new CdpPermissionEntity();
        BeanUtils.copyProperties(entity, createEntity);
        Long pId = entity.getPId();

        int level = 1;
        if (pId != 0) {
            CdpPermissionEntity parentEntity = permissionRepository.findOne(pId);
            Assert.notNull(parentEntity, "传入的创建数据实体找不到对于的父节点数据!");
            level = parentEntity.getLevel() + 1;
        }
        createEntity.setLevel(level);
        return permissionRepository.save(createEntity);
    }

    @Override
    @CachePut(value = TableNameConstant.CDP_PERMISSION, key = "#entity.id")
    public CdpPermissionEntity update(CdpPermissionUpdateDto entity) {
        Assert.notNull(entity, "传入了空对象!");
        Long id = entity.getId();
        Assert.notNull(id, "传入了空ID!");

        CdpPermissionEntity updateEntity = permissionRepository.findOne(id);
        BeanUtils.copyProperties(entity, updateEntity);
        Long pId = entity.getPId();
        if (!updateEntity.getPId().equals(pId)) {
            CdpPermissionEntity parentEntity = permissionRepository.findOne(pId);
            Assert.notNull(parentEntity, "传入的创建数据实体找不到对于的父节点数据!");
            updateEntity.setLevel(parentEntity.getLevel() + 1);
        }

        return permissionRepository.save(updateEntity);
    }


    @Override
    @CacheEvict(value = TableNameConstant.CDP_PERMISSION, key = "#id")
    public CdpPermissionEntity delete(Long id) {
        Assert.notNull(id, "传入了空ID!");
        CdpPermissionEntity entity = permissionRepository.findOne(id);
        Assert.notNull(entity, "传入了空对象!");
        delete(id, entity);
        return null;
    }

    private void delete(Long id, CdpPermissionEntity entity) {
        long countByPermissionId = rolePermissionService.countByPermissionId(id);
        Assert.isTrue(countByPermissionId == 0, "还有角色在使用该权限，不能直接删除!");
        countByPermissionId = userPermissionService.countByPermissionId(id);
        Assert.isTrue(countByPermissionId == 0, "还有用户在使用该权限，不能直接删除!");
        List<CdpPermissionEntity> entities = findByPId(id);
        Assert.isTrue(entities == null || entities.size() == 0, "该节点还存在子节点不能直接删除!");
        permissionRepository.delete(entity);
    }

    @Override
    @CacheEvict(value = TableNameConstant.CDP_PERMISSION, key = "#entity.id")
    public CdpPermissionEntity delete(CdpPermissionEntity entity) {
        Assert.notNull(entity, "传入了空对象!");
        Long id = entity.getId();
        Assert.notNull(id, "传入了空ID!");
        delete(id, entity);
        return entity;
    }

    @Override
    public Result findAllByPageSort(PageModule pageModule) {
        PageRequest pageable = new PageRequest(pageModule.getPageNumber() - 1, pageModule.getPageSize(), Sort.Direction.fromString(pageModule.getSortOrder()), pageModule.getSortName());
        String searchCondition = pageModule.getSearchText();

        Specification<CdpPermissionEntity> condition = new Specification<CdpPermissionEntity>() {
            @Override
            public Predicate toPredicate(Root<CdpPermissionEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                if (StringUtils.isBlank(searchCondition)) {
                    return query.getRestriction();
                }
                Path<String> name = root.get("name");
                Path<String> code = root.get("code");
                Path<String> url = root.get("url");
                Path<String> type = root.get("type");
                return cb.or(cb.like(name, "%" + searchCondition + "%"), cb.like(code, "%" + searchCondition + "%"), cb.like(url, "%" + searchCondition + "%"), cb.like(type, "%" + searchCondition + "%"));
            }
        };
        //分页查找
        Page<CdpPermissionEntity> page = permissionRepository.findAll(condition, pageable);

        return ResultUtils.success(page.getTotalElements(), page.getContent());
    }

    @Override
    @Cacheable(value = TableNameConstant.CDP_PERMISSION, key = "#id")
    public CdpPermissionEntity findOne(Long id) {
        return permissionRepository.findOne(id);
    }

    @Override
    public List<CdpPermissionEntity> findByPId(Long pId) {
        Sort sort = new Sort(Sort.Direction.fromString("ASC"), "sort");
        return permissionRepository.findByPId(pId, sort);
    }

    @Override
    public List<CdpPermissionEntity> findByPId(Long pId, Long versionId) {
        return permissionRepository.findByPId(pId, versionId);
    }

    @Override
    public List<CdpPermissionEntity> findByType(Integer type) {
        return permissionRepository.findByType(type);
    }

    @Override
    public List<CdpPermissionEntity> findByAppName(String appName) {
        return permissionRepository.findByAppName(appName);
    }

    @Override
    public IJpaRepository<CdpPermissionEntity, Long> getRepository() {
        return permissionRepository;
    }
}
