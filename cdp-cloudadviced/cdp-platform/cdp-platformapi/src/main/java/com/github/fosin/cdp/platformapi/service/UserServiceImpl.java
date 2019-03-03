package com.github.fosin.cdp.platformapi.service;

import com.github.fosin.cdp.cache.util.CacheUtil;
import com.github.fosin.cdp.jpa.repository.IJpaRepository;
import com.github.fosin.cdp.mvc.module.PageModule;
import com.github.fosin.cdp.mvc.result.Result;
import com.github.fosin.cdp.mvc.result.ResultUtils;
import com.github.fosin.cdp.platformapi.constant.SystemConstant;
import com.github.fosin.cdp.platformapi.constant.TableNameConstant;
import com.github.fosin.cdp.platformapi.dto.request.CdpUserCreateDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpUserUpdateDto;
import com.github.fosin.cdp.platformapi.entity.CdpOrganizationEntity;
import com.github.fosin.cdp.platformapi.entity.CdpUserEntity;
import com.github.fosin.cdp.platformapi.entity.CdpUserRoleEntity;
import com.github.fosin.cdp.platformapi.parameter.OrganizParameterUtil;
import com.github.fosin.cdp.platformapi.repository.OrganizationRepository;
import com.github.fosin.cdp.platformapi.repository.UserRepository;
import com.github.fosin.cdp.platformapi.repository.UserRoleRepository;
import com.github.fosin.cdp.platformapi.service.inter.IUserService;
import com.github.fosin.cdp.platformapi.util.LoginUserUtil;
import com.github.fosin.cdp.util.NumberUtil;
import com.github.fosin.cdp.util.RegexUtil;
import com.github.fosin.cdp.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
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
@Lazy
@Slf4j
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Cacheable(value = TableNameConstant.CDP_USER, key = "#usercode")
    @Transactional(readOnly = true)
    public CdpUserEntity findByUsercode(String usercode) {
        Assert.notNull(usercode, "用户工号不能为空!");
        //该代码看似无用，其实是为了解决懒加载和缓存先后问题
        CdpUserEntity userEntity = userRepository.findByUsercode(usercode);
        if (userEntity != null) {
            List<CdpUserRoleEntity> userRoles = userEntity.getUserRoles();
            log.debug(userRoles.toString());
        }
        return userEntity;
    }

    private String encryptBeforeSave(CdpUserEntity entity) {
        String password = entity.getPassword();
        //如果密码为空则随机生成4位以下密码
        if (StringUtil.isEmpty(password)) {
            password = getPassword();
        }
        String encode = passwordEncoder.encode(password);
        entity.setPassword(encode);
        return password;
    }

    @Override
    @Caching(
            put = {
                    @CachePut(value = TableNameConstant.CDP_USER, key = "#result.id", condition = "#result.id != null"),
                    @CachePut(value = TableNameConstant.CDP_USER, key = "#result.usercode", condition = "#result.usercode != null"),
            }
    )
    public CdpUserEntity create(CdpUserCreateDto entity) {
        CdpUserEntity createUser = new CdpUserEntity();
        BeanUtils.copyProperties(entity, createUser);

        String passwordStrength = OrganizParameterUtil.getOrCreateParameter("DefaultPasswordStrength", RegexUtil.PASSWORD_STRONG, "用户密码强度正则表达式,密码最少6位，包括至少1个大写字母，1个小写字母，1个数字，1个特殊字符");
        Assert.isTrue(RegexUtil.matcher(createUser.getPassword(), passwordStrength), "密码强度不符合强度要求!");
        String password = encryptBeforeSave(createUser);
        CdpUserEntity savedEntity = userRepository.save(createUser);
        CdpUserEntity rcEntity = new CdpUserEntity();
        BeanUtils.copyProperties(savedEntity, rcEntity);
        rcEntity.setPassword(password);
        return rcEntity;
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = TableNameConstant.CDP_USER, beforeInvocation = true, key = "#root.caches[0].get(#entity.id).get().usercode", condition = "#root.caches[0].get(#entity.id) != null && !#root.caches[0].get(#entity.id).get().usercode.equals(#entity.usercode)"),
                    @CacheEvict(value = TableNameConstant.CDP_USER_PERMISSION, key = "#entity.id")
            },
            put = {
                    @CachePut(value = TableNameConstant.CDP_USER, key = "#result.id", condition = "#result.id != null"),
                    @CachePut(value = TableNameConstant.CDP_USER, key = "#result.usercode", condition = "#result.usercode != null"),
            }
    )
    public CdpUserEntity update(CdpUserUpdateDto entity) {
        Long id = entity.getId();
        Assert.notNull(id, "更新的数据id不能为空或者小于1!");
        CdpUserEntity createUser = userRepository.findById(id).orElse(null);
        String usercode = Objects.requireNonNull(createUser, "通过ID：" + id + "未能找到对应的数据!").getUsercode().toLowerCase();
        if (SystemConstant.ADMIN_USER_CODE.equals(usercode) &&
                !SystemConstant.ADMIN_USER_CODE.equals(entity.getUsercode().toLowerCase())) {
            throw new IllegalArgumentException("不能修改管理员" + SystemConstant.ADMIN_USER_CODE + "的帐号名称!");
        }
        Assert.isTrue(!SystemConstant.SUPER_USER_CODE.equals(usercode),
                "不能修改超级管理员帐号信息!");
        BeanUtils.copyProperties(entity, createUser);
        return userRepository.save(createUser);
    }

    public List<CdpUserEntity> findAll(Iterable<Long> ids) {
        List<CdpUserEntity> rcList = new ArrayList<>();
        List<Long> needQueryIds = new ArrayList<>();
        for (Long id : ids) {
            CdpUserEntity userEntity = CacheUtil.get(TableNameConstant.CDP_USER, id + "", CdpUserEntity.class);
            if (userEntity != null) {
                rcList.add(userEntity);
            } else {
                needQueryIds.add(id);
            }
        }
        if (needQueryIds.size() > 0) {
            List<CdpUserEntity> queryList = userRepository.findAllById(needQueryIds);
            rcList.addAll(queryList);
        }

        return rcList;
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = TableNameConstant.CDP_USER, key = "#root.caches[0].get(#id).get().usercode", condition = "#root.caches[0].get(#id) != null"),
                    @CacheEvict(value = TableNameConstant.CDP_USER, key = "#id"),
                    @CacheEvict(value = TableNameConstant.CDP_USER_PERMISSION, key = "#id")
            }
    )
    public CdpUserEntity deleteById(Long id) {
        Assert.isTrue(id != null && id > 0, "传入的用户ID无效！");
        CdpUserEntity entity = CacheUtil.get(TableNameConstant.CDP_USER, id + "", CdpUserEntity.class);
        if (entity == null) {
            entity = userRepository.findById(id).orElse(null);
        }
        Assert.notNull(entity, "通过该ID没有找到相应的用户数据!");
        Assert.isTrue(!SystemConstant.SUPER_USER_CODE.equals(entity.getUsercode())
                && !SystemConstant.ADMIN_USER_CODE.equals(entity.getUsercode()), "不能删除管理员帐号!");
//        List<CdpUserRoleEntity> userRoles = userRoleRepository.findByUserId(id);
//        Assert.isTrue(userRoles.size() == 0, "该用户下还存在角色信息,不能直接删除用户!");
        userRepository.deleteById(id);
        return entity;
    }

    @Override
    @Cacheable(value = TableNameConstant.CDP_USER, key = "#id")
    @Transactional(readOnly = true)
    public CdpUserEntity findById(Long id) {
        Assert.isTrue(id != null && id > 0, "传入的用户ID无效！");
        //该代码看似无用，其实是为了解决懒加载和缓存先后问题
        CdpUserEntity userEntity = userRepository.findById(id).orElse(null);
        List<CdpUserRoleEntity> userRoles = Objects.requireNonNull(userEntity, "通过ID：" + id + "未能找到对应的数据!").getUserRoles();
        log.debug(userRoles.toString());
        return userEntity;
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = TableNameConstant.CDP_USER, key = "#entity.id"),
                    @CacheEvict(value = TableNameConstant.CDP_USER, key = "#entity.usercode"),
                    @CacheEvict(value = TableNameConstant.CDP_USER_PERMISSION, key = "#entity.id")
            }
    )
    public CdpUserEntity deleteByEntity(CdpUserEntity entity) {
        Assert.notNull(entity, "不能删除空的用户对象!");
        Assert.isTrue(!SystemConstant.SUPER_USER_CODE.equals(entity.getUsercode())
                && !SystemConstant.ADMIN_USER_CODE.equals(entity.getUsercode()), "不能删除管理员帐号!");
        List<CdpUserRoleEntity> userRoles = userRoleRepository.findByUserId(entity.getId());
        Assert.isTrue(userRoles.size() == 0, "该用户下还存在角色信息,不能直接删除用户!");
        userRepository.delete(entity);
        return entity;
    }

    @Override
    public Result findAllByPageSort(PageModule pageModule) {
        PageRequest pageable = PageRequest.of(pageModule.getPageNumber() - 1, pageModule.getPageSize(), Sort.Direction.fromString(pageModule.getSortOrder()), pageModule.getSortName());
        String searchCondition = pageModule.getSearchText();

        CdpUserEntity loginUser = LoginUserUtil.getUser();
        Specification<CdpUserEntity> condition = (Specification<CdpUserEntity>) (root, query, cb) -> {
            Path<String> usercode = root.get("usercode");
            Path<String> username = root.get("username");
            Path<String> phone = root.get("phone");
            Path<String> email = root.get("email");

            CriteriaBuilder.In<Object> organizId1 = null;
            if (loginUser != null && !loginUser.getUsercode().equals(SystemConstant.SUPER_USER_CODE)) {
                Long organizId = loginUser.getOrganizId();
                CdpOrganizationEntity organizationEntity = organizationRepository.findById(organizId).orElse(null);

                Subquery<Integer> subQuery = query.subquery(Integer.class);
                //从哪张表查询
                Root<CdpOrganizationEntity> celluseRoot = subQuery.from(CdpOrganizationEntity.class);
                //查询出什么
                subQuery.select(celluseRoot.get("id"));
                //条件是什么
                Predicate p = cb.like(celluseRoot.get("code"), Objects.requireNonNull(organizationEntity, "通过organizId：" + organizId + "未能找到对应的数据!").getCode() + "%");
                subQuery.where(p);

                organizId1 = cb.in(root.get("organizId")).value(subQuery);
            }

            if (StringUtils.isBlank(searchCondition)) {
                if (loginUser != null && loginUser.getUsercode().equals(SystemConstant.SUPER_USER_CODE)) {
                    return query.getRestriction();
                } else {
                    return cb.and(cb.notEqual(usercode, SystemConstant.SUPER_USER_CODE), organizId1);
                }
            }
            Predicate predicate = cb.or(cb.like(username, "%" + searchCondition + "%"),
                    cb.like(usercode, "%" + searchCondition + "%"),
                    cb.like(phone, "%" + searchCondition + "%"),
                    cb.like(email, "%" + searchCondition + "%"));
            if (loginUser != null && loginUser.getUsercode().equals(SystemConstant.SUPER_USER_CODE)) {
                return predicate;
            } else {
                return cb.and(cb.notEqual(usercode, SystemConstant.SUPER_USER_CODE), predicate, organizId1);
            }
        };
        //分页查找
        Page<CdpUserEntity> page = userRepository.findAll(condition, pageable);
        //主动清空用户角色属性，防止JPA懒加载加载这些数据，因为前端不需要角色信息，这样也可以提高性能
        for (CdpUserEntity user : page) {
            user.setUserRoles(null);
        }
        return ResultUtils.success(page.getTotalElements(), page.getContent());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = TableNameConstant.CDP_USER, key = "#result.id"),
                    @CacheEvict(value = TableNameConstant.CDP_USER, key = "#result.usercode")
            }
    )
    public CdpUserEntity changePassword(Long id, String password, String confirmPassword1, String confirmPassword2) {
        Assert.notNull(id, "用户ID不能为空!");
        Assert.isTrue(!StringUtil.isEmpty(confirmPassword1) &&
                !StringUtil.isEmpty(confirmPassword1) && confirmPassword1.equals(confirmPassword2), "新密码和确认新密码不能为空且必须一致!");
        Assert.isTrue(!confirmPassword1.equals(password), "新密码和原密码不能相同!");
        String passwordStrength = OrganizParameterUtil.getOrCreateParameter("DefaultPasswordStrength", RegexUtil.PASSWORD_STRONG, "用户密码强度正则表达式,密码最少6位，包括至少1个大写字母，1个小写字母，1个数字，1个特殊字符");
        Assert.isTrue(RegexUtil.matcher(confirmPassword1, passwordStrength), "新密码强度不符合强度要求!");

        CdpUserEntity user = userRepository.findById(id).orElse(null);
        Assert.isTrue(passwordEncoder.matches(password, Objects.requireNonNull(user, "通过ID：" + id + "未找到对应的用户信息!").getPassword()), "原密码不正确!");
        user.setPassword(confirmPassword1);
        encryptBeforeSave(user);
        return userRepository.save(user);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = TableNameConstant.CDP_USER, key = "#result.usercode"),
                    @CacheEvict(value = TableNameConstant.CDP_USER, key = "#id")
            }
    )
    public CdpUserEntity resetPassword(Long id) {
        Assert.notNull(id, "用户ID不能为空!");
        CdpUserEntity loginUser = LoginUserUtil.getUser();
        Long loginId = loginUser.getId();
        Assert.isTrue(!Objects.equals(loginId, id), "不能重置本人密码,请使用修改密码功能!");
        CdpUserEntity user = userRepository.findById(id).orElse(null);
        String password = getPassword();
        Objects.requireNonNull(user, "通过ID：" + id + "未找到对应的用户信息!").setPassword(password);
        encryptBeforeSave(user);
        userRepository.save(user);
        CdpUserEntity rcEntity = new CdpUserEntity();
        BeanUtils.copyProperties(user, rcEntity);
        rcEntity.setPassword(password);
        return rcEntity;
    }

    public String getPassword() {
        String password;
        String resetUserPasswordType = OrganizParameterUtil.getOrCreateParameter("UserResetPasswordType", "2", "重置用户密码时密码的生成规则(1、重置成系统参数中的固定密码 2、设置成随机4位密码)");
        if ("1".equals(resetUserPasswordType)) {
            password = OrganizParameterUtil.getOrCreateParameter("UserDefaultPassword", "123456", "用户的默认密码");
        } else {
            String length = OrganizParameterUtil.getOrCreateParameter("UserResetPasswordLength", "4", "重置用户密码时密码长度,只支持1-9位,否则将默认取4位");
            int random = 9999;
            if (NumberUtil.isInteger(length)) {
                int i = Integer.parseInt(length);
                if (i > 0 && i < 10) {
                    random = (int) (Math.pow(10, i)) - 1;
                }
            }

            password = new Random().nextInt(random) + "";
        }
        return password;
    }

    @Override
    public List<CdpUserEntity> findOtherUsersByRoleId(Long roleId) {
        return userRepository.findOtherUsersByRoleId(roleId);
    }

    @Override
    public List<CdpUserEntity> findRoleUsersByRoleId(Long roleId) {
        return userRepository.findRoleUsersByRoleId(roleId);
    }

    @Override
    public List<CdpUserEntity> findAllByOrganizId(Long organizId) {
        Assert.notNull(organizId, "机构ID不能为空!");
        CdpUserEntity loginUser = LoginUserUtil.getUser();
        if (loginUser.getUsercode().equals(SystemConstant.SUPER_USER_CODE)) {
            return userRepository.findAll();
        } else {
            CdpOrganizationEntity organiz = organizationRepository.findById(organizId).orElse(null);
            Assert.notNull(organiz, "根据传入的机构编码没有找到任何数据!");
            Long topId = organiz.getTopId();
            CdpOrganizationEntity topOrganiz = organizationRepository.findById(topId).orElse(null);
            List<CdpOrganizationEntity> organizs = organizationRepository.findByCodeStartingWithOrderByCodeAsc(Objects.requireNonNull(topOrganiz, "通过topId：" + topId + "未找到对应的用户信息!").getCode());

            Specification<CdpUserEntity> condition = (root, query, cb) -> {
                Path<Long> organizIdPath = root.get("organizId");
                Path<String> usercodePath = root.get("usercode");

                CriteriaBuilder.In<Long> in = cb.in(organizIdPath);
                for (CdpOrganizationEntity entity : organizs) {
                    in.value(entity.getId());
                }
                return cb.and(in, cb.notEqual(usercodePath, SystemConstant.SUPER_USER_CODE));
            };
            return userRepository.findAll(condition);
        }
    }

    @Override
    public IJpaRepository<CdpUserEntity, Long> getRepository() {
        return userRepository;
    }
}
