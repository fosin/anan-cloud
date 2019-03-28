package com.github.fosin.cdp.platform.service;


import com.github.fosin.cdp.jpa.repository.IJpaRepository;
import com.github.fosin.cdp.platform.service.inter.IRoleService;
import com.github.fosin.cdp.platformapi.dto.request.CdpRoleCreateDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpRoleUpdateDto;
import com.github.fosin.cdp.platformapi.entity.*;
import com.github.fosin.cdp.platform.repository.OrganizationRepository;
import com.github.fosin.cdp.platform.repository.RoleRepository;
import com.github.fosin.cdp.platform.repository.UserRoleRepository;
import com.github.fosin.cdp.mvc.module.PageModule;
import com.github.fosin.cdp.mvc.result.Result;
import com.github.fosin.cdp.mvc.result.ResultUtils;
import com.github.fosin.cdp.platformapi.constant.SystemConstant;
import com.github.fosin.cdp.platformapi.util.LoginUserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import org.springframework.util.Assert;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * 2017/12/29.
 * Time:12:31
 *
 * @author fosin
 */
@Service
@Lazy
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Override
    public CdpRoleEntity create(CdpRoleCreateDto entity) {
        Assert.notNull(entity, "传入了空对象!");

        if (SystemConstant.ADMIN_ROLE_NAME.equals(entity.getValue().toUpperCase()) &&
                !SystemConstant.ADMIN_ROLE_NAME.equals(entity.getValue().toUpperCase())) {
            throw new IllegalArgumentException("不能创建角色标识" + SystemConstant.ADMIN_ROLE_NAME + "!");
        }
        Assert.isTrue(!SystemConstant.SUPER_ROLE_NAME.equals(entity.getValue().toUpperCase()),
                "不能创建超级管理员角色帐号信息!");
        CdpRoleEntity saveEntity = new CdpRoleEntity();
        BeanUtils.copyProperties(entity, saveEntity);
        return roleRepository.save(saveEntity);
    }

    @Override
    public CdpRoleEntity update(CdpRoleUpdateDto entity) {
        Assert.notNull(entity, "传入了空对象!");
        Long id = entity.getId();
        Assert.notNull(id, "传入了空ID!");

        CdpRoleEntity oldEntity = roleRepository.findOne(id);
        if (SystemConstant.ADMIN_ROLE_NAME.equals(oldEntity.getValue().toUpperCase()) &&
                !SystemConstant.ADMIN_ROLE_NAME.equals(entity.getValue().toUpperCase())) {
            throw new IllegalArgumentException("不能修改角色标识" + SystemConstant.ADMIN_ROLE_NAME + "!");
        }
        Assert.isTrue(!SystemConstant.SUPER_ROLE_NAME.equals(oldEntity.getValue().toUpperCase()),
                "不能修改超级管理员角色帐号信息!");
        CdpRoleEntity saveEntity = new CdpRoleEntity();
        BeanUtils.copyProperties(entity, saveEntity);
        return roleRepository.save(saveEntity);
    }

    @Override
    public CdpRoleEntity delete(Long id) {
        Assert.isTrue(id != null && id > 0, "传入的角色ID无效！");
        CdpRoleEntity entity = roleRepository.findOne(id);
        Assert.notNull(entity, "根据角色ID未能找到角色数据!");
        Assert.isTrue(!SystemConstant.SUPER_ROLE_NAME.equals(entity.getValue())
                        && !SystemConstant.ADMIN_ROLE_NAME.equals(entity.getValue()),
                "不能删除(超级)管理员角色帐号信息!");

        List<CdpUserRoleEntity> roleUsers = userRoleRepository.findByRoleId(id);
        Assert.isTrue(roleUsers.size() == 0,
                "该角色下还存在用户,不能直接删除角色!");
        roleRepository.delete(id);
        return null;
    }

    @Override
    public CdpRoleEntity delete(CdpRoleEntity entity) {
        Assert.notNull(entity, "传入了空对象!");
        Assert.isTrue(!SystemConstant.SUPER_ROLE_NAME.equals(entity.getValue())
                        && !SystemConstant.ADMIN_ROLE_NAME.equals(entity.getValue()),
                "不能删除(超级)管理员角色信息!");
        List<CdpUserRoleEntity> roleUsers = userRoleRepository.findByRoleId(entity.getId());
        Assert.isTrue(roleUsers.size() == 0,
                "该角色下还存在用户,不能直接删除角色!");
        roleRepository.delete(entity);
        return entity;
    }

    @Override
    public Result findAllByPageSort(PageModule pageModule) {
        PageRequest pageable = new PageRequest(pageModule.getPageNumber() - 1, pageModule.getPageSize(), Sort.Direction.fromString(pageModule.getSortOrder()), pageModule.getSortName());
        String searchCondition = pageModule.getSearchText();

        CdpUserEntity loginUser = LoginUserUtil.getUser();
        Specification<CdpRoleEntity> condition = new Specification<CdpRoleEntity>() {
            @Override
            public Predicate toPredicate(Root<CdpRoleEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<String> roleName = root.get("name");
                Path<String> roleValue = root.get("value");

                if (StringUtils.isBlank(searchCondition)) {
                    if (loginUser.getUsercode().equals(SystemConstant.SUPER_USER_CODE)) {
                        return query.getRestriction();
                    } else {
                        return cb.and(cb.notEqual(roleValue, SystemConstant.SUPER_ROLE_NAME));
                    }
                }
                Predicate predicate = cb.or(cb.like(roleName, "%" + searchCondition + "%"), cb.like(roleValue, "%" + searchCondition + "%"));
                if (loginUser.getUsercode().equals(SystemConstant.SUPER_USER_CODE)) {
                    return predicate;
                } else {
                    return cb.and(cb.notEqual(roleValue, SystemConstant.SUPER_ROLE_NAME), predicate);
                }
            }
        };
        //分页查找
        Page<CdpRoleEntity> page = roleRepository.findAll(condition, pageable);

        return ResultUtils.success(page.getTotalElements(), page.getContent());
    }

    @Override
    public List<CdpRoleEntity> findOtherUsersByRoleId(Long userId) {
        return roleRepository.findOtherRolesByUserId(userId);
    }

    @Override
    public List<CdpRoleEntity> findRoleUsersByRoleId(Long userId) {
        return roleRepository.findUserRolesByUserId(userId);
    }

    @Override
    public Result findAllByOrganizId(Long organizId, PageModule pageModule) {
        Assert.notNull(pageModule, "传入的分页信息不能为空!");
        Assert.notNull(organizId, "机构ID不能为空!");
        String searchCondition = pageModule.getSearchText();
        CdpUserEntity loginUser = LoginUserUtil.getUser();
        PageRequest pageable = new PageRequest(pageModule.getPageNumber() - 1, pageModule.getPageSize(), Sort.Direction.fromString(pageModule.getSortOrder()), pageModule.getSortName());

        Page<CdpRoleEntity> page;
        if (loginUser.getUsercode().equals(SystemConstant.SUPER_USER_CODE)) {
            Specification<CdpRoleEntity> condition = (root, query, cb) -> {
                Path<String> roleName = root.get("name");
                Path<String> roleValue = root.get("value");
                if (StringUtils.isBlank(searchCondition)) {
                    return query.getRestriction();
                }
                return cb.or(cb.like(roleName, "%" + searchCondition + "%"), cb.like(roleValue, "%" + searchCondition + "%"));
            };
            page = roleRepository.findAll(condition, pageable);
        } else {
            CdpOrganizationEntity organiz = organizationRepository.findOne(organizId);
            Assert.notNull(organiz, "根据传入的机构编码没有找到任何数据!");
            List<CdpOrganizationEntity> organizs = organizationRepository.findByCodeStartingWithOrderByCodeAsc(organiz.getCode());

            Specification<CdpRoleEntity> condition = (root, query, cb) -> {
                Path<Long> organizIdPath = root.get("organizId");
                Path<String> roleName = root.get("name");
                Path<String> roleValue = root.get("value");

                CriteriaBuilder.In<Long> in = cb.in(organizIdPath);
                for (CdpOrganizationEntity entity : organizs) {
                    in.value(entity.getId());
                }

                Predicate predicate = cb.and(in, cb.notEqual(roleValue, SystemConstant.SUPER_ROLE_NAME));
                if (StringUtils.isBlank(searchCondition)) {
                    return predicate;
                }
                predicate = cb.or(cb.like(roleName, "%" + searchCondition + "%"), cb.like(roleValue, "%" + searchCondition + "%"));
                return predicate;
            };
            //分页查找
            page = roleRepository.findAll(condition, pageable);
        }


        return ResultUtils.success(page.getTotalElements(), page.getContent());
    }

    @Override
    public List<CdpRoleEntity> findAllByOrganizId(Long organizId) {
        Assert.notNull(organizId, "机构ID不能为空!");
        CdpUserEntity loginUser = LoginUserUtil.getUser();
        if (loginUser.getUsercode().equals(SystemConstant.SUPER_USER_CODE)) {
            return roleRepository.findAll();
        } else {
            CdpOrganizationEntity organiz = organizationRepository.findOne(organizId);
            Assert.notNull(organiz, "根据传入的机构编码没有找到任何数据!");
            CdpOrganizationEntity topOrganiz = organizationRepository.findOne(organiz.getTopId());
            List<CdpOrganizationEntity> organizs = organizationRepository.findByCodeStartingWithOrderByCodeAsc(topOrganiz.getCode());

            Specification<CdpRoleEntity> condition = (root, query, cb) -> {
                Path<Long> organizIdPath = root.get("organizId");
                Path<String> roleValue = root.get("value");

                CriteriaBuilder.In<Long> in = cb.in(organizIdPath);
                for (CdpOrganizationEntity entity : organizs) {
                    in.value(entity.getId());
                }
                return cb.and(in, cb.notEqual(roleValue, SystemConstant.SUPER_ROLE_NAME));
            };
            return roleRepository.findAll(condition);
        }
    }

    @Override
    public IJpaRepository<CdpRoleEntity, Long> getRepository() {
        return roleRepository;
    }
}
