package com.github.fosin.anan.platform.service;

import com.github.fosin.anan.cloudresource.constant.RedisConstant;
import com.github.fosin.anan.cloudresource.constant.SystemConstant;
import com.github.fosin.anan.cloudresource.dto.request.AnanUserCreateDto;
import com.github.fosin.anan.cloudresource.dto.request.AnanUserUpdateDto;
import com.github.fosin.anan.jpa.repository.IJpaRepository;
import com.github.fosin.anan.model.module.PageModule;
import com.github.fosin.anan.model.result.Result;
import com.github.fosin.anan.model.result.ResultUtils;
import com.github.fosin.anan.platform.repository.OrganizationRepository;
import com.github.fosin.anan.platform.repository.UserRoleRepository;
import com.github.fosin.anan.platform.service.inter.UserService;
import com.github.fosin.anan.platformapi.entity.AnanOrganizationEntity;
import com.github.fosin.anan.platformapi.entity.AnanUserEntity;
import com.github.fosin.anan.platformapi.entity.AnanUserRoleEntity;
import com.github.fosin.anan.platformapi.repository.UserRepository;
import com.github.fosin.anan.platformapi.service.AnanUserDetailService;
import com.github.fosin.anan.redis.cache.AnanCacheManger;
import com.github.fosin.anan.util.NumberUtil;
import com.github.fosin.anan.util.RegexUtil;
import com.github.fosin.anan.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * 2017/12/27.
 * Time:15:13
 *
 * @author fosin
 */
@Service
@Lazy
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final OrganizationRepository organizationRepository;
    private final PasswordEncoder passwordEncoder;
    private final LocalOrganParameter localOrganParameter;
    private final AnanCacheManger ananCacheManger;
    private final AnanUserDetailService ananUserDetailService;

    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, OrganizationRepository organizationRepository, PasswordEncoder passwordEncoder, LocalOrganParameter localOrganParameter, AnanCacheManger ananCacheManger, AnanUserDetailService ananUserDetailService) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.organizationRepository = organizationRepository;
        this.passwordEncoder = passwordEncoder;
        this.localOrganParameter = localOrganParameter;
        this.ananCacheManger = ananCacheManger;
        this.ananUserDetailService = ananUserDetailService;
    }

    @Override
    @Cacheable(value = RedisConstant.ANAN_USER, key = "#usercode")
    @Transactional(readOnly = true)
    public AnanUserEntity findByUsercode(String usercode) {
        Assert.notNull(usercode, "用户工号不能为空!");

        AnanUserEntity userEntity = userRepository.findByUsercode(usercode);

        if (userEntity != null) {
            List<AnanUserRoleEntity> userRoles = userEntity.getUserRoles();
            //该代码看似无用，其实是为了解决懒加载和缓存先后问题
            log.debug(userRoles.toString());
        }
        return userEntity;
    }

    private String encryptBeforeSave(AnanUserEntity entity) {
        String password = entity.getPassword();
        //如果密码为空则随机生成4位以下密码
        if (StringUtil.isEmpty(password)) {
            password = getPassword();
        }
        entity.setPassword(passwordEncoder.encode(password));
        return password;
    }

    @Override
//    @Caching(
//            put = {
//                    @CachePut(value = RedisConstant.ANAN_USER, key = "#result.id", condition = "#result.id != null"),
//                    @CachePut(value = RedisConstant.ANAN_USER, key = "#result.usercode", condition = "#result.usercode != null")
//            }
//    )
    public AnanUserEntity create(AnanUserCreateDto entity) {
        AnanUserEntity createUser = new AnanUserEntity();
        BeanUtils.copyProperties(entity, createUser);

//        String passwordStrength = localOrganParameter.getOrCreateParameter("DefaultPasswordStrength", RegexUtil.PASSWORD_STRONG, "用户密码强度正则表达式,密码最少6位，包括至少1个大写字母，1个小写字母，1个数字，1个特殊字符");
//        Assert.isTrue(RegexUtil.matcher(createUser.getPassword(), passwordStrength), "用户密码强度正则表达式,密码最少6位，包括至少1个大写字母，1个小写字母，1个数字，1个特殊字符!");
        String password = encryptBeforeSave(createUser);
        AnanUserEntity savedEntity = userRepository.save(createUser);
        AnanUserEntity rcEntity = new AnanUserEntity();
        BeanUtils.copyProperties(savedEntity, rcEntity);
        rcEntity.setPassword(password);
        return rcEntity;
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = RedisConstant.ANAN_USER, beforeInvocation = true, key = "#root.caches[0].get(#entity.id).get().usercode", condition = "#root.caches[0].get(#entity.id) != null && !#root.caches[0].get(#entity.id).get().usercode.equals(#entity.usercode)"),
                    @CacheEvict(value = RedisConstant.ANAN_USER, key = "#entity.id"),
                    @CacheEvict(value = RedisConstant.ANAN_USER_PERMISSION, key = "#entity.id")
            }
    )
    public AnanUserEntity update(AnanUserUpdateDto entity) {
        Long id = entity.getId();
        Assert.notNull(id, "更新的数据id不能为空或者小于1!");
        AnanUserEntity createUser = userRepository.findById(id).orElse(null);
        String usercode = Objects.requireNonNull(createUser, "通过ID：" + id + "未能找到对应的数据!").getUsercode().toLowerCase();
        if (ananUserDetailService.isAdminUser(usercode) &&
                !ananUserDetailService.isAdminUser(entity.getUsercode().toLowerCase())) {
            throw new IllegalArgumentException("不能修改管理员" + SystemConstant.ADMIN_USER_CODE + "的帐号名称!");
        }
        Assert.isTrue(!ananUserDetailService.isSysAdminUser(usercode),
                "不能修改超级管理员帐号信息!");
        BeanUtils.copyProperties(entity, createUser);
        return userRepository.save(createUser);
    }

    public List<AnanUserEntity> findAll(Iterable<Long> ids) {
        List<AnanUserEntity> rcList = new ArrayList<>();
        List<Long> needQueryIds = new ArrayList<>();
        for (Long id : ids) {
            AnanUserEntity userEntity = ananCacheManger.get(RedisConstant.ANAN_USER, id + "", AnanUserEntity.class);
            if (userEntity != null) {
                rcList.add(userEntity);
            } else {
                needQueryIds.add(id);
            }
        }
        if (needQueryIds.size() > 0) {
            List<AnanUserEntity> queryList = userRepository.findAllById(needQueryIds);
            rcList.addAll(queryList);
        }

        return rcList;
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = RedisConstant.ANAN_USER, key = "#root.caches[0].get(#id).get().usercode", condition = "#root.caches[0].get(#id) != null"),
                    @CacheEvict(value = RedisConstant.ANAN_USER, key = "#id"),
                    @CacheEvict(value = RedisConstant.ANAN_USER_PERMISSION, key = "#id"),
                    @CacheEvict(value = RedisConstant.ANAN_USER_ALL_PERMISSIONS, key = "#id")
            }
    )
    public AnanUserEntity deleteById(Long id) {
        Assert.isTrue(id != null && id > 0, "传入的用户ID无效！");
        AnanUserEntity entity = ananCacheManger.get(RedisConstant.ANAN_USER, id + "", AnanUserEntity.class);
        if (entity == null) {
            entity = userRepository.findById(id).orElse(null);
        }
        Assert.notNull(entity, "通过该ID没有找到相应的用户数据!");
        Assert.isTrue(!ananUserDetailService.isSysAdminUser(entity.getUsercode())
                && !ananUserDetailService.isAdminUser(entity.getUsercode()), "不能删除管理员帐号!");
//        List<AnanUserRoleEntity> userRoles = userRoleRepository.findByUserId(id);
//        Assert.isTrue(userRoles.size() == 0, "该用户下还存在角色信息,不能直接删除用户!");
        userRepository.deleteById(id);
        return entity;
    }

    @Override
    @Cacheable(value = RedisConstant.ANAN_USER, key = "#id")
    @Transactional(readOnly = true)
    public AnanUserEntity findById(Long id) {
        Assert.isTrue(id != null && id > 0, "传入的用户ID无效！");
        //该代码看似无用，其实是为了解决懒加载和缓存先后问题
        AnanUserEntity userEntity = userRepository.findById(id).orElse(null);
        List<AnanUserRoleEntity> userRoles = Objects.requireNonNull(userEntity, "通过ID：" + id + "未能找到对应的数据!").getUserRoles();
        log.debug(userRoles.toString());
        return userEntity;
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = RedisConstant.ANAN_USER, key = "#entity.usercode"),
                    @CacheEvict(value = RedisConstant.ANAN_USER, key = "#entity.id"),
                    @CacheEvict(value = RedisConstant.ANAN_USER_PERMISSION, key = "#entity.id"),
                    @CacheEvict(value = RedisConstant.ANAN_USER_ALL_PERMISSIONS, key = "#entity.id")
            }
    )
    public AnanUserEntity deleteByEntity(AnanUserEntity entity) {
        Assert.notNull(entity, "不能删除空的用户对象!");
        Assert.isTrue(!ananUserDetailService.isSysAdminUser(entity.getUsercode())
                && !ananUserDetailService.isAdminUser(entity.getUsercode()), "不能删除管理员帐号!");
        List<AnanUserRoleEntity> userRoles = userRoleRepository.findByUserId(entity.getId());
        Assert.isTrue(userRoles.size() == 0, "该用户下还存在角色信息,不能直接删除用户!");
        userRepository.delete(entity);
        return entity;
    }

    @Override
    public Result findAllByPageSort(PageModule pageModule) {
        PageRequest pageable = PageRequest.of(pageModule.getPageNumber() - 1, pageModule.getPageSize(), Sort.Direction.fromString(pageModule.getSortOrder()), pageModule.getSortName());
        String searchCondition = pageModule.getSearchText();

        Specification<AnanUserEntity> condition = (Specification<AnanUserEntity>) (root, query, cb) -> {
            Path<String> usercode = root.get("usercode");
            Path<String> username = root.get("username");
            Path<String> phone = root.get("phone");
            Path<String> email = root.get("email");

            CriteriaBuilder.In<Object> organizId1 = null;
            boolean sysAdminUser = ananUserDetailService.isSysAdminUser();
            if (!sysAdminUser) {
                Long organizId = ananUserDetailService.getAnanOrganizId();
                AnanOrganizationEntity organizationEntity = organizationRepository.findById(organizId).orElse(null);

                Subquery<Integer> subQuery = query.subquery(Integer.class);
                //从哪张表查询
                Root<AnanOrganizationEntity> celluseRoot = subQuery.from(AnanOrganizationEntity.class);
                //查询出什么
                subQuery.select(celluseRoot.get("id"));
                //条件是什么
                Predicate p = cb.like(celluseRoot.get("code"), Objects.requireNonNull(organizationEntity, "通过organizId：" + organizId + "未能找到对应的数据!").getCode() + "%");
                subQuery.where(p);

                organizId1 = cb.in(root.get("organizId")).value(subQuery);
            }

            if (StringUtils.isBlank(searchCondition)) {
                if (sysAdminUser) {
                    return query.getRestriction();
                } else {
                    return cb.and(cb.notEqual(usercode, SystemConstant.ANAN_USER_CODE), organizId1);
                }
            }
            Predicate predicate = cb.or(cb.like(username, "%" + searchCondition + "%"),
                    cb.like(usercode, "%" + searchCondition + "%"),
                    cb.like(phone, "%" + searchCondition + "%"),
                    cb.like(email, "%" + searchCondition + "%"));
            if (sysAdminUser) {
                return predicate;
            } else {
                return cb.and(cb.notEqual(usercode, SystemConstant.ANAN_USER_CODE), predicate, organizId1);
            }
        };
        //分页查找
        Page<AnanUserEntity> page = userRepository.findAll(condition, pageable);
        //主动清空用户角色属性，防止JPA懒加载加载这些数据，因为前端不需要角色信息，这样也可以提高性能
        for (AnanUserEntity user : page) {
            user.setUserRoles(null);
        }
        return ResultUtils.success(page.getTotalElements(), page.getContent());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = RedisConstant.ANAN_USER, key = "#id"),
                    @CacheEvict(value = RedisConstant.ANAN_USER, key = "#result.usercode")
            }
    )
    public AnanUserEntity changePassword(Long id, String password, String confirmPassword1, String confirmPassword2) {
        Assert.notNull(id, "用户ID不能为空!");
        Assert.isTrue(!StringUtil.isEmpty(confirmPassword1) &&
                !StringUtil.isEmpty(confirmPassword2) && confirmPassword1.equals(confirmPassword2), "新密码和确认新密码不能为空且必须一致!");
        Assert.isTrue(!confirmPassword1.equals(password), "新密码和原密码不能相同!");
        String passwordStrength = localOrganParameter.getOrCreateParameter("DefaultPasswordStrength", RegexUtil.PASSWORD_STRONG, "用户密码强度正则表达式,密码最少6位，包括至少1个大写字母，1个小写字母，1个数字，1个特殊字符");
        Assert.isTrue(RegexUtil.matcher(confirmPassword1, passwordStrength), "新密码强度不符合强度要求!");

        AnanUserEntity user = userRepository.findById(id).orElse(null);
        Assert.isTrue(passwordEncoder.matches(password, Objects.requireNonNull(user, "通过ID：" + id + "未找到对应的用户信息!").getPassword()), "原密码不正确!");
        user.setPassword(confirmPassword1);
        encryptBeforeSave(user);
        return userRepository.save(user);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = RedisConstant.ANAN_USER, key = "#id"),
                    @CacheEvict(value = RedisConstant.ANAN_USER, key = "#result.usercode")
            }
    )
    public AnanUserEntity resetPassword(Long id) {
        Assert.notNull(id, "用户ID不能为空!");

        Long loginId = ananUserDetailService.getAnanUserId();
        Assert.isTrue(!Objects.equals(loginId, id), "不能重置本人密码,请使用修改密码功能!");
        AnanUserEntity user = userRepository.findById(id).orElse(null);
        String password = getPassword();
        Objects.requireNonNull(user, "通过ID：" + id + "未找到对应的用户信息!").setPassword(password);
        encryptBeforeSave(user);
        userRepository.save(user);
        AnanUserEntity rcEntity = new AnanUserEntity();
        BeanUtils.copyProperties(user, rcEntity);
        rcEntity.setPassword(password);
        return rcEntity;
    }

    public String getPassword() {
        String password;
        String resetUserPasswordType = localOrganParameter.getOrCreateParameter("UserResetPasswordType", "2", "重置用户密码时密码的生成规则(1、重置成系统参数中的固定密码 2、设置成随机4位密码)");
        if ("1".equals(resetUserPasswordType)) {
            password = localOrganParameter.getOrCreateParameter("UserDefaultPassword", "123456", "用户的默认密码");
        } else {
            String length = localOrganParameter.getOrCreateParameter("UserResetPasswordLength", "4", "重置用户密码时密码长度,只支持1-9位,否则将默认取4位");
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
    public List<AnanUserEntity> findOtherUsersByRoleId(Long roleId) {
        return userRepository.findOtherUsersByRoleId(roleId);
    }

    @Override
    public List<AnanUserEntity> findRoleUsersByRoleId(Long roleId) {
        return userRepository.findRoleUsersByRoleId(roleId);
    }

    @Override
    public List<AnanUserEntity> findAllByOrganizId(Long organizId) {
        Assert.notNull(organizId, "机构ID不能为空!");

        if (ananUserDetailService.hasSysAdminRole()) {
            return userRepository.findAll();
        } else {
            AnanOrganizationEntity organiz = organizationRepository.findById(organizId).orElse(null);
            Assert.notNull(organiz, "根据传入的机构编码没有找到任何数据!");
            Long topId = organiz.getTopId();
            AnanOrganizationEntity topOrganiz = organizationRepository.findById(topId).orElse(null);
            List<AnanOrganizationEntity> organizs = organizationRepository.findByCodeStartingWithOrderByCodeAsc(Objects.requireNonNull(topOrganiz, "通过topId：" + topId + "未找到对应的用户信息!").getCode());

            Specification<AnanUserEntity> condition = (root, query, cb) -> {
                Path<Long> organizIdPath = root.get("organizId");
                Path<String> usercodePath = root.get("usercode");

                CriteriaBuilder.In<Long> in = cb.in(organizIdPath);
                for (AnanOrganizationEntity entity : organizs) {
                    in.value(entity.getId());
                }
                return cb.and(in, cb.notEqual(usercodePath, SystemConstant.ANAN_USER_CODE));
            };
            return userRepository.findAll(condition);
        }
    }

    @Override
    public IJpaRepository<AnanUserEntity, Long> getRepository() {
        return userRepository;
    }
}
