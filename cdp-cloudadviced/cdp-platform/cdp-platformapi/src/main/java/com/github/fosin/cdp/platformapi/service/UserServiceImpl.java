package com.github.fosin.cdp.platformapi.service;

import com.github.fosin.cdp.platformapi.repository.UserRoleRepository;
import com.github.fosin.cdp.core.exception.CdpServiceException;
import com.github.fosin.cdp.mvc.module.PageModule;
import com.github.fosin.cdp.mvc.result.Result;
import com.github.fosin.cdp.mvc.result.ResultUtils;
import com.github.fosin.cdp.platformapi.constant.SystemConstant;
import com.github.fosin.cdp.platformapi.constant.TableNameConstant;
import com.github.fosin.cdp.platformapi.entity.CdpSysOrganizationEntity;
import com.github.fosin.cdp.platformapi.entity.CdpSysUserEntity;
import com.github.fosin.cdp.platformapi.entity.CdpSysUserRoleEntity;
import com.github.fosin.cdp.platformapi.parameter.OrganizParameterUtil;
import com.github.fosin.cdp.platformapi.repository.OrganizationRepository;
import com.github.fosin.cdp.platformapi.repository.UserRepository;
import com.github.fosin.cdp.platformapi.service.inter.IUserService;
import com.github.fosin.cdp.platformapi.util.LoginUserUtil;
import com.github.fosin.cdp.cache.util.CacheUtil;
import com.github.fosin.cdp.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.criteria.*;
import java.util.*;

/**
 * 2017/12/27.
 * Time:15:13
 *
 * @author fosin
 */
@Service
@Slf4j
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private OrganizationRepository organizationRepository;

    @Override
    @Cacheable(value = TableNameConstant.CDP_SYS_USER, key = "#usercode")
    @Transactional(readOnly = true)
    public CdpSysUserEntity findByUsercode(String usercode) {
        Assert.notNull(usercode, "用户工号不能为空!");
        //该代码看似无用，其实是为了解决懒加载和缓存先后问题
        CdpSysUserEntity userEntity = userRepository.findByUsercode(usercode);
        if (userEntity != null) {
            List<CdpSysUserRoleEntity> userRoles = userEntity.getUserRoles();
            log.debug(userRoles.toString());
        }
        return userEntity;
    }

    private String encryptBeforeSave(CdpSysUserEntity entity) {
        String password = entity.getPassword();
        //如果密码为空则随机生成4位以下密码
        if (StringUtil.isEmpty(password)) {
            password = getPassword();
        }
        entity.setPassword(new BCryptPasswordEncoder().encode(password));
        return password;
    }

    @Override
    @Caching(
            put = {
                    @CachePut(value = TableNameConstant.CDP_SYS_USER, key = "#result.id", condition = "#result.id != null"),
                    @CachePut(value = TableNameConstant.CDP_SYS_USER, key = "#result.usercode", condition = "#result.usercode != null"),
            }
    )
    public CdpSysUserEntity create(CdpSysUserEntity entity) {
        CdpSysUserEntity loginUser = LoginUserUtil.getUser();
        Date now = new Date();
        entity.setCreateTime(now);
        entity.setCreateBy(loginUser.getId());
        entity.setUpdateTime(now);
        entity.setUpdateBy(loginUser.getId());
        List<CdpSysUserRoleEntity> userRoles = entity.getUserRoles();
        for (CdpSysUserRoleEntity userRole : userRoles) {
            userRole.setCreateBy(loginUser.getId());
            userRole.setCreateTime(now);
        }
        String password = encryptBeforeSave(entity);
        CdpSysUserEntity savedEntity = userRepository.save(entity);
        savedEntity.setPassword(password);
        return savedEntity;
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = TableNameConstant.CDP_SYS_USER, beforeInvocation = true, key = "#root.caches[0].get(#entity.id).get().usercode", condition = "#root.caches[0].get(#entity.id) != null && !#root.caches[0].get(#entity.id).get().usercode.equals(#entity.usercode)"),
                    @CacheEvict(value = TableNameConstant.CDP_SYS_USER_PERMISSION, key = "#entity.id")
            },
            put = {
                    @CachePut(value = TableNameConstant.CDP_SYS_USER, key = "#result.id", condition = "#result.id != null"),
                    @CachePut(value = TableNameConstant.CDP_SYS_USER, key = "#result.usercode", condition = "#result.usercode != null"),
            }
    )
    public CdpSysUserEntity update(CdpSysUserEntity entity) throws CdpServiceException {
        Integer id = entity.getId();
        Assert.notNull(id, "更新的数据id不能为空或者小于1!");
        CdpSysUserEntity oldEntity = userRepository.findOne(id);
        if (SystemConstant.ADMIN_USER_CODE.equals(oldEntity.getUsercode().toLowerCase()) &&
                !SystemConstant.ADMIN_USER_CODE.equals(entity.getUsercode().toLowerCase())) {
            throw new IllegalArgumentException("不能修改管理员" + SystemConstant.ADMIN_USER_CODE + "的帐号名称!");
        }
        Assert.isTrue(!SystemConstant.SUPER_USER_CODE.equals(oldEntity.getUsercode().toLowerCase()),
                "不能修改超级管理员帐号信息!");

        CdpSysUserEntity loginUser = LoginUserUtil.getUser();
        Date now = new Date();
        entity.setUpdateBy(loginUser.getId());
        entity.setUpdateTime(now);
        List<CdpSysUserRoleEntity> userRoles = entity.getUserRoles();
        for (CdpSysUserRoleEntity userRole : userRoles) {
            userRole.setCreateBy(loginUser.getId());
            userRole.setCreateTime(now);
        }
        return userRepository.save(entity);
    }

    @Override
    public List<CdpSysUserEntity> findAll() {
        CdpSysUserEntity loginUser = LoginUserUtil.getUser();
        Specification<CdpSysUserEntity> condition = new Specification<CdpSysUserEntity>() {
            @Override
            public Predicate toPredicate(Root<CdpSysUserEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<String> usercode = root.get("usercode");
                if (loginUser.getUsercode().equals(SystemConstant.SUPER_USER_CODE)) {
                    return query.getRestriction();
                } else {
                    return cb.and(cb.notEqual(usercode, SystemConstant.SUPER_USER_CODE));
                }
            }
        };
        return userRepository.findAll(condition);
    }

    public List<CdpSysUserEntity> findAll(Iterable<Integer> ids) throws CdpServiceException {
        List<CdpSysUserEntity> rcList = new ArrayList<>();
        List<Integer> needQueryIds = new ArrayList<>();
        for (Integer id : ids) {
            CdpSysUserEntity userEntity = CacheUtil.get(TableNameConstant.CDP_SYS_USER, id + "", CdpSysUserEntity.class);
            if (userEntity != null) {
                rcList.add(userEntity);
            } else {
                needQueryIds.add(id);
            }
        }
        if (needQueryIds.size() > 0) {
            List<CdpSysUserEntity> queryList = userRepository.findAll(needQueryIds);
            rcList.addAll(queryList);
        }

        return rcList;
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = TableNameConstant.CDP_SYS_USER, key = "#root.caches[0].get(#id).get().usercode", condition = "#root.caches[0].get(#id) != null"),
                    @CacheEvict(value = TableNameConstant.CDP_SYS_USER, key = "#id"),
                    @CacheEvict(value = TableNameConstant.CDP_SYS_USER_PERMISSION, key = "#id")
            }
    )
    public CdpSysUserEntity delete(Integer id) throws CdpServiceException {
        Assert.notNull(id, "传入了空ID!");
        CdpSysUserEntity entity = CacheUtil.get(TableNameConstant.CDP_SYS_USER, id + "", CdpSysUserEntity.class);
        if (entity == null) {
            entity = userRepository.findOne(id);
        }
        Assert.notNull(entity, "通过该ID没有找到相应的数据!");
        Assert.isTrue(!SystemConstant.SUPER_USER_CODE.equals(entity.getUsercode())
                && !SystemConstant.ADMIN_USER_CODE.equals(entity.getUsercode()), "不能删除管理员帐号!");
//        List<CdpSysUserRoleEntity> userRoles = userRoleRepository.findByUserId(id);
//        Assert.isTrue(userRoles.size() == 0, "该用户下还存在角色信息,不能直接删除用户!");
        userRepository.delete(id);
        return null;
    }

    @Override
    @Cacheable(value = TableNameConstant.CDP_SYS_USER, key = "#id")
    @Transactional(readOnly = true)
    public CdpSysUserEntity findOne(Integer id) {
        //该代码看似无用，其实是为了解决懒加载和缓存先后问题
        CdpSysUserEntity userEntity = userRepository.findOne(id);
        List<CdpSysUserRoleEntity> userRoles = userEntity.getUserRoles();
        log.debug(userRoles.toString());
        return userEntity;
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = TableNameConstant.CDP_SYS_USER, key = "#entity.id"),
                    @CacheEvict(value = TableNameConstant.CDP_SYS_USER, key = "#entity.usercode"),
                    @CacheEvict(value = TableNameConstant.CDP_SYS_USER_PERMISSION, key = "#entity.id")
            }
    )
    public Collection<CdpSysUserEntity> delete(CdpSysUserEntity entity) throws CdpServiceException {
        Assert.notNull(entity, "不能删除空的用户对象!");
        Assert.isTrue(!SystemConstant.SUPER_USER_CODE.equals(entity.getUsercode())
                && !SystemConstant.ADMIN_USER_CODE.equals(entity.getUsercode()), "不能删除管理员帐号!");
        List<CdpSysUserRoleEntity> userRoles = userRoleRepository.findByUserId(entity.getId());
        Assert.isTrue(userRoles.size() == 0, "该用户下还存在角色信息,不能直接删除用户!");
        userRepository.delete(entity);
        return null;
    }

    @Override
    public Result findAllPage(PageModule pageModule) {
        PageRequest pageable = new PageRequest(pageModule.getPageNumber() - 1, pageModule.getPageSize(), Sort.Direction.fromString(pageModule.getSortOrder()), pageModule.getSortName());
        String searchCondition = pageModule.getSearchText();

        CdpSysUserEntity loginUser = LoginUserUtil.getUser();
        Specification<CdpSysUserEntity> condition = new Specification<CdpSysUserEntity>() {
            @Override
            public Predicate toPredicate(Root<CdpSysUserEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<String> usercode = root.get("usercode");
                Path<String> username = root.get("username");
                Path<String> phone = root.get("phone");
                Path<String> email = root.get("email");

                CriteriaBuilder.In<Object> organizId1 = null;
                if (!loginUser.getUsercode().equals(SystemConstant.SUPER_USER_CODE)) {
                    Integer organizId = loginUser.getOrganizId();
                    CdpSysOrganizationEntity organizationEntity = organizationRepository.findOne(organizId);

                    Subquery<Integer> subQuery = query.subquery(Integer.class);
                    //从哪张表查询
                    Root<CdpSysOrganizationEntity> celluseRoot = subQuery.from(CdpSysOrganizationEntity.class);
                    //查询出什么
                    subQuery.select(celluseRoot.get("id"));
                    //条件是什么
                    Predicate p = cb.like(celluseRoot.get("code"), organizationEntity.getCode() + "%");
                    subQuery.where(p);

                    organizId1 = cb.in(root.get("organizId")).value(subQuery);
                }

                if (StringUtils.isBlank(searchCondition)) {
                    if (loginUser.getUsercode().equals(SystemConstant.SUPER_USER_CODE)) {
                        return query.getRestriction();
                    } else {
                        return cb.and(cb.notEqual(usercode, SystemConstant.SUPER_USER_CODE), organizId1);
                    }
                }
                Predicate predicate = cb.or(cb.like(username, "%" + searchCondition + "%"),
                        cb.like(usercode, "%" + searchCondition + "%"),
                        cb.like(phone, "%" + searchCondition + "%"),
                        cb.like(email, "%" + searchCondition + "%"));
                if (loginUser.getUsercode().equals(SystemConstant.SUPER_USER_CODE)) {
                    return predicate;
                } else {
                    return cb.and(cb.notEqual(usercode, SystemConstant.SUPER_USER_CODE), predicate, organizId1);
                }
            }
        };
        //分页查找
        Page<CdpSysUserEntity> page = userRepository.findAll(condition, pageable);
        //主动清空用户角色属性，防止JPA懒加载加载这些数据，因为前端不需要角色信息，这样也可以提高性能
        for (CdpSysUserEntity user : page) {
            user.setUserRoles(null);
        }
        return ResultUtils.success(page.getTotalElements(), page.getContent());
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = TableNameConstant.CDP_SYS_USER, key = "#result.id"),
                    @CacheEvict(value = TableNameConstant.CDP_SYS_USER, key = "#result.usercode")
            }
    )
    public CdpSysUserEntity changePassword(Integer id, String password, String confirmPassword1, String confirmPassword2) {
        Assert.notNull(id, "用户ID不能为空!");
        Assert.isTrue(!StringUtil.isEmpty(confirmPassword1) &&
                !StringUtil.isEmpty(confirmPassword1) && confirmPassword1.equals(confirmPassword2), "新密码和确认新密码不能为空且一致!");
        CdpSysUserEntity user = userRepository.findOne(id);
        Assert.isTrue(user != null && user.getId() != null, "通过ID未找到对应的用户信息!");
        Assert.isTrue(new BCryptPasswordEncoder().matches(password, user.getPassword()), "原密码不正确!");
        user.setPassword(confirmPassword1);
        encryptBeforeSave(user);
        CdpSysUserEntity loginUser = LoginUserUtil.getUser();
        user.setUpdateTime(new Date());
        user.setUpdateBy(loginUser.getId());
        return userRepository.save(user);
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = TableNameConstant.CDP_SYS_USER, key = "#root.caches[0].get(#id).get().usercode", condition = "#root.caches[0].get(#id) != null"),
                    @CacheEvict(value = TableNameConstant.CDP_SYS_USER, key = "#id")
            }
    )
    public String resetPassword(Integer id) {
        Assert.notNull(id, "用户ID不能为空!");
        CdpSysUserEntity loginUser = LoginUserUtil.getUser();
        Integer loginId = loginUser.getId();
        Assert.isTrue(!loginId.equals(id), "不能重置本人密码,请使用修改密码功能!");
        CdpSysUserEntity user = userRepository.findOne(id);
        Assert.isTrue(user != null && user.getId() != null, "通过ID未找到对应的用户信息!");
        String password = getPassword();
        user.setPassword(password);
        encryptBeforeSave(user);
        user.setUpdateTime(new Date());
        user.setUpdateBy(loginUser.getId());
        userRepository.save(user);
        return password;
    }

    public String getPassword() {
        String password;
        String resetUserPasswordType = OrganizParameterUtil.getOrCreateParameter("UserResetPasswordType", "2", "重置用户密码时密码的生成规则(1、重置成系统参数中的固定密码 2、设置成随机4位密码)");
        if ("1".equals(resetUserPasswordType)) {
            password = OrganizParameterUtil.getOrCreateParameter("UserDefaultPassword", "123456", "用户的默认密码");
        } else {
            password = new Random().nextInt(9999) + "";
        }
        return password;
    }

    @Override
    public List<CdpSysUserEntity> findOtherUsersByRoleId(Integer roleId) {
        return userRepository.findOtherUsersByRoleId(roleId);
    }

    @Override
    public List<CdpSysUserEntity> findRoleUsersByRoleId(Integer roleId) {
        return userRepository.findRoleUsersByRoleId(roleId);
    }

}
