package top.fosin.anan.platform.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import cn.hutool.core.util.NumberUtil;
import top.fosin.anan.cloudresource.constant.RedisConstant;
import top.fosin.anan.cloudresource.constant.SystemConstant;
import top.fosin.anan.cloudresource.dto.req.AnanUserRetrieveDto;
import top.fosin.anan.cloudresource.dto.res.AnanUserRespDto;
import top.fosin.anan.cloudresource.service.AnanUserDetailService;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.core.util.RegexUtil;
import top.fosin.anan.model.dto.TreeDto;
import top.fosin.anan.model.module.PageModule;
import top.fosin.anan.model.result.ListResult;
import top.fosin.anan.model.result.ResultUtils;
import top.fosin.anan.platform.dto.req.AnanUserCreateDto;
import top.fosin.anan.platform.dto.req.AnanUserUpdateDto;
import top.fosin.anan.platform.dto.res.AnanUserRespPassDto;
import top.fosin.anan.platform.entity.AnanOrganizationEntity;
import top.fosin.anan.platform.entity.AnanUserAllEntity;
import top.fosin.anan.platform.entity.AnanUserEntity;
import top.fosin.anan.platform.entity.AnanUserRoleEntity;
import top.fosin.anan.platform.repository.OrganizationRepository;
import top.fosin.anan.platform.repository.UserAllRepository;
import top.fosin.anan.platform.repository.UserRepository;
import top.fosin.anan.platform.repository.UserRoleRepository;
import top.fosin.anan.platform.service.inter.UserService;
import top.fosin.anan.redis.cache.AnanCacheManger;

/**
 * @author fosin
 * @date 2017/12/27
 */
@Service
@Lazy
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserAllRepository userAllRepository;
    private final UserRoleRepository userRoleRepository;
    private final OrganizationRepository organizationRepository;
    private final PasswordEncoder passwordEncoder;
    private final LocalOrganParameter localOrganParameter;
    private final AnanCacheManger ananCacheManger;
    private final AnanUserDetailService ananUserDetailService;
    private final OrganizationRepository orgRepo;

    public UserServiceImpl(UserRepository userRepository, UserAllRepository userAllRepository, UserRoleRepository userRoleRepository, OrganizationRepository organizationRepository, PasswordEncoder passwordEncoder, LocalOrganParameter localOrganParameter, AnanCacheManger ananCacheManger, AnanUserDetailService ananUserDetailService, OrganizationRepository orgRepo) {
        this.userRepository = userRepository;
        this.userAllRepository = userAllRepository;
        this.userRoleRepository = userRoleRepository;
        this.organizationRepository = organizationRepository;
        this.passwordEncoder = passwordEncoder;
        this.localOrganParameter = localOrganParameter;
        this.ananCacheManger = ananCacheManger;
        this.ananUserDetailService = ananUserDetailService;
        this.orgRepo = orgRepo;
    }

    /**
     * 根据主键编号查找所有数据服务(包括软删除)
     *
     * @param ids 主键编号
     * @return 查找结果集
     */
    @Override
    public List<AnanUserRespDto> listByIds(Collection<Long> ids) {
        return BeanUtil.copyCollectionProperties(userAllRepository.findAllById(ids), AnanUserRespDto.class);
    }

    /**
     * 根据用户工号查找数据服务(包括软删除)
     *
     * @param usercode 用户工号
     * @return 用户
     */
    @Override
    @Cacheable(value = RedisConstant.ANAN_USER, key = "#usercode", condition = "#result != null")
    @Transactional(readOnly = true)
    public AnanUserRespDto findByUsercode(String usercode) {
        Assert.notNull(usercode, "用户工号不能为空!");
        AnanUserEntity userEntity = userAllRepository.findByUsercode(usercode);
        if (userEntity == null) {
            return null;
        }

        AnanUserRespDto respDto = BeanUtil.copyProperties(userEntity, AnanUserRespDto.class);
        Long organizId = userEntity.getOrganizId();
        if (organizId > 0) {
            AnanOrganizationEntity organization = orgRepo.getOne(organizId);
            respDto.setTopId(organization.getTopId());
        }
        return respDto;
    }

    /**
     * 根据用户编号查找数据服务(包括软删除)
     *
     * @param id 用户编号
     * @return 用户
     */
    @Override
    @Cacheable(value = RedisConstant.ANAN_USER, key = "#id", condition = "#result != null")
    @Transactional(readOnly = true)
    public AnanUserRespDto findOneById(Long id) {
        AnanUserAllEntity userEntity = userAllRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("未找到对应数据!"));
        AnanUserRespDto respDto = BeanUtil.copyProperties(userEntity, AnanUserRespDto.class);
        Long organizId = userEntity.getOrganizId();
        if (organizId > 0) {
            AnanOrganizationEntity organization = orgRepo.getOne(organizId);
            respDto.setTopId(organization.getTopId());
        }
        return respDto;
    }


    private String encryptBeforeSave(AnanUserEntity entity) {
        String password = entity.getPassword();
        //如果密码为空则随机生成4位以下密码
        if (StringUtils.isEmpty(password)) {
            password = getPassword();
        }
        entity.setPassword(passwordEncoder.encode(password));
        return password;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AnanUserRespPassDto create(AnanUserCreateDto dto) {
        AnanUserRespDto entityDynamic = this.findByUsercode(dto.getUsercode());
        Assert.isNull(entityDynamic, "用户工号已存在，请核对!");
//        String passwordStrength = localOrganParameter.getOrCreateParameter("DefaultPasswordStrength", RegexUtil.PASSWORD_STRONG, "用户密码强度正则表达式,密码最少6位，包括至少1个大写字母，1个小写字母，1个数字，1个特殊字符");
//        Assert.isTrue(RegexUtil.matcher(createUser.getPassword(), passwordStrength), "用户密码强度正则表达式,密码最少6位，包括至少1个大写字母，1个小写字母，1个数字，1个特殊字符!");
        AnanUserEntity createUser = new AnanUserEntity();
        BeanUtils.copyProperties(dto, createUser);
        String password = encryptBeforeSave(createUser);
        createUser = userRepository.save(createUser);
        AnanUserRespPassDto respPassDto = BeanUtil.copyProperties(createUser, AnanUserRespPassDto.class);
        respPassDto.setPassword(password);
        return respPassDto;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(AnanUserUpdateDto dto) {
        Long id = dto.getId();
        Assert.isTrue(id > 0, "更新的数据id不能为空或者小于1!");
        AnanUserEntity createUser = userRepository.findById(id).orElse(null);
        String usercode = Objects.requireNonNull(createUser, "通过ID：" + id + "未能找到对应的数据!").getUsercode().toLowerCase();
        if (ananUserDetailService.isAdminUser(usercode) &&
                !ananUserDetailService.isAdminUser(dto.getUsercode().toLowerCase())) {
            throw new IllegalArgumentException("不能修改管理员" + SystemConstant.ADMIN_USER_CODE + "的帐号名称!");
        }
        Assert.isTrue(!ananUserDetailService.isSysAdminUser(usercode),
                "不能修改超级管理员帐号信息!");
        BeanUtils.copyProperties(dto, createUser);
        ananCacheManger.evict(RedisConstant.ANAN_USER, usercode);
        ananCacheManger.evict(RedisConstant.ANAN_USER, id + "");
        ananCacheManger.evict(RedisConstant.ANAN_USER_ALL_PERMISSIONS, id + "");
        userRepository.save(createUser);
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = RedisConstant.ANAN_USER, key = "#root.caches[0].get(#id).get().usercode", condition = "#root.caches[0].get(#id) != null"),
                    @CacheEvict(value = RedisConstant.ANAN_USER, key = "#id"),
                    @CacheEvict(value = RedisConstant.ANAN_USER_ALL_PERMISSIONS, key = "#id")
            }
    )
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        Assert.isTrue(id != null && id > 0, "传入的用户ID无效！");
        AnanUserEntity entity = ananCacheManger.get(RedisConstant.ANAN_USER, id + "", AnanUserEntity.class);
        if (entity == null) {
            entity = userRepository.findById(id).orElse(null);
        }
        Assert.notNull(entity, "通过该ID没有找到相应的用户数据!");
        deleteByEntity(entity);
    }

    private void deleteByEntity(AnanUserEntity entity) {
        Assert.isTrue(!ananUserDetailService.isSysAdminUser(entity.getUsercode())
                && !ananUserDetailService.isAdminUser(entity.getUsercode()), "不能删除管理员帐号!");
        List<AnanUserRoleEntity> userRoles = userRoleRepository.findByUserId(entity.getId());
        Assert.isTrue(userRoles.size() == 0, "该用户下还存在角色信息,不能直接删除用户!");
        userRepository.delete(entity);
    }

    /**
     * 根据主键删除多条数据
     *
     * @param ids 主键编号集合
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByIds(Collection<Long> ids) {
        List<AnanUserEntity> entities = userRepository.findAllById(ids);
        for (AnanUserEntity entity : entities) {
            deleteByEntity(entity);
        }
        for (AnanUserEntity entity : entities) {
            String id = entity.getId() + "";
            ananCacheManger.evict(RedisConstant.ANAN_USER, id);
            ananCacheManger.evict(RedisConstant.ANAN_USER, entity.getUsercode());
            ananCacheManger.evict(RedisConstant.ANAN_USER_ALL_PERMISSIONS, id);
        }
    }

    @Override
    public ListResult<AnanUserRespDto> findPage(PageModule<AnanUserRetrieveDto> pageModule) {
        PageRequest pageable = PageRequest.of(pageModule.getPageNumber() - 1, pageModule.getPageSize(), this.buildSortRules(pageModule.getParams().getSortRules()));

        Specification<AnanUserEntity> condition = (root, query, cb) -> {
            Path<String> usercodePath = root.get("usercode");
            Path<String> usernamePath = root.get("username");
            Path<String> phonePath = root.get("phone");
            Path<String> emailPath = root.get("email");

            CriteriaBuilder.In<Object> organizId1 = null;
            boolean sysAdminUser = ananUserDetailService.isSysAdminUser();
            if (!sysAdminUser) {
                Long organizId = ananUserDetailService.getAnanOrganizId();
                AnanOrganizationEntity organizationEntity = organizationRepository.findById(organizId).orElse(null);

                Subquery<Integer> subQuery = query.subquery(Integer.class);
                //从哪张表查询
                Root<AnanOrganizationEntity> celluseRoot = subQuery.from(AnanOrganizationEntity.class);
                //查询出什么
                subQuery.select(celluseRoot.get(TreeDto.ID_NAME));
                //条件是什么
                Predicate p = cb.like(celluseRoot.get("code"), Objects.requireNonNull(organizationEntity, "通过organizId：" + organizId + "未能找到对应的数据!").getCode() + "%");
                subQuery.where(p);

                organizId1 = cb.in(root.get("organizId")).value(subQuery);
            }
            AnanUserRetrieveDto params = pageModule.getParams();

            if (params == null) {
                if (sysAdminUser) {
                    return query.getRestriction();
                } else {
                    return cb.and(cb.notEqual(usercodePath, SystemConstant.ANAN_USER_CODE), organizId1);
                }
            } else {
                String username = params.getUsername();
                String usercode = params.getUsercode();
                String phone = params.getPhone();
                String email = params.getEmail();
                if (StringUtils.isEmpty(username)
                        && StringUtils.isEmpty(usercode)
                        && StringUtils.isEmpty(phone)
                        && StringUtils.isEmpty(email)
                ) {
                    if (sysAdminUser) {
                        return query.getRestriction();
                    } else {
                        return cb.and(cb.notEqual(usercodePath, SystemConstant.ANAN_USER_CODE), organizId1);
                    }
                }
                Predicate predicate = cb.or(cb.like(usernamePath, "%" + username + "%"),
                        cb.like(usercodePath, "%" + usercode + "%"),
                        cb.like(phonePath, "%" + phone + "%"),
                        cb.like(emailPath, "%" + email + "%"));
                if (sysAdminUser) {
                    return predicate;
                } else {
                    return cb.and(cb.notEqual(usercodePath, SystemConstant.ANAN_USER_CODE), predicate, organizId1);
                }
            }
        };
        //分页查找
        Page<AnanUserEntity> page = userRepository.findAll(condition, pageable);
        return ResultUtils.success(page.getTotalElements(), page.getTotalPages(),
                BeanUtil.copyCollectionProperties(page.getContent(), AnanUserRespDto.class));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = RedisConstant.ANAN_USER, key = "#id"),
                    @CacheEvict(value = RedisConstant.ANAN_USER, key = "#result.usercode")
            }
    )
    public AnanUserRespDto changePassword(Long id, String password, String confirmPassword1, String confirmPassword2) {
        Assert.isTrue(!StringUtils.isEmpty(confirmPassword1) &&
                !StringUtils.isEmpty(confirmPassword2) && confirmPassword1.equals(confirmPassword2), "新密码和确认新密码不能为空且必须一致!");
        Assert.isTrue(!confirmPassword1.equals(password), "新密码和原密码不能相同!");
        String passwordStrength = localOrganParameter.getOrCreateParameter("DefaultPasswordStrength", RegexUtil.PASSWORD_STRONG, "用户密码强度正则表达式,密码最少6位，包括至少1个大写字母，1个小写字母，1个数字，1个特殊字符");
        Assert.isTrue(RegexUtil.matcher(confirmPassword1, passwordStrength), "新密码强度不符合强度要求!");

        AnanUserEntity user = userRepository.findById(id).orElse(null);
        Assert.isTrue(passwordEncoder.matches(password, Objects.requireNonNull(user, "通过ID：" + id + "未找到对应的用户信息!").getPassword()), "原密码不正确!");
        user.setPassword(confirmPassword1);
        encryptBeforeSave(user);
        return BeanUtil.copyProperties(userRepository.save(user), AnanUserRespDto.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = RedisConstant.ANAN_USER, key = "#id"),
                    @CacheEvict(value = RedisConstant.ANAN_USER, key = "#result.usercode")
            }
    )
    public AnanUserRespPassDto resetPassword(Long id) {
        Assert.notNull(id, "用户ID不能为空!");

        Long loginId = ananUserDetailService.getAnanUserId();
        Assert.isTrue(!Objects.equals(loginId, id), "不能重置本人密码,请使用修改密码功能!");
        AnanUserEntity user = userRepository.findById(id).orElse(null);
        String password = getPassword();
        Objects.requireNonNull(user, "通过ID：" + id + "未找到对应的用户信息!").setPassword(password);
        encryptBeforeSave(user);
        userRepository.save(user);
        AnanUserRespPassDto respPassDto = BeanUtil.copyProperties(user, AnanUserRespPassDto.class);
        respPassDto.setPassword(password);
        return respPassDto;
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
                if (i > 0) {
                    random = (int) (Math.pow(10, i)) - 1;
                }
            }

            password = new Random().nextInt(random) + "";
        }
        return password;
    }

    @Override
    public List<AnanUserRespDto> findOtherUsersByRoleId(Long roleId) {
        return BeanUtil.copyCollectionProperties(
                userRepository.findOtherUsersByRoleId(roleId), AnanUserRespDto.class);
    }

    @Override
    public List<AnanUserRespDto> findRoleUsersByRoleId(Long roleId) {
        return BeanUtil.copyCollectionProperties(
                userRepository.findRoleUsersByRoleId(roleId), AnanUserRespDto.class);
    }

    @Override
    public List<AnanUserRespDto> listAllChildByTopId(Long topId, Integer status) {
        List<AnanUserEntity> entities;
        if (ananUserDetailService.isUserRequest() && ananUserDetailService.hasSysAdminRole()) {
            entities = userRepository.findAll();
        } else {
            if (topId < 1) {
                AnanOrganizationEntity organiz =
                        organizationRepository.findById(ananUserDetailService.getAnanOrganizId()).orElseThrow(() -> new IllegalArgumentException("根据传入的机构编码没有找到任何数据!"));
                topId = organiz.getTopId();
            }
            entities = userRepository.findByTopIdAndStatus(topId, status);
            entities.add(userRepository.getOne(SystemConstant.ANAN_USER_ID));
        }
        return BeanUtil.copyCollectionProperties(entities, AnanUserRespDto.class);
    }

    @Override
    public List<AnanUserRespDto> listByOrganizId(Long organizId, Integer status) {
        List<AnanUserEntity> entities;
        if (ananUserDetailService.isUserRequest() && ananUserDetailService.hasSysAdminRole()) {
            entities = userRepository.findAll();
        } else {
            AnanOrganizationEntity organiz = organizationRepository.findById(organizId).orElse(null);
            Assert.notNull(organiz, "根据传入的机构编码没有找到任何数据!");
            Long topId = organiz.getTopId();
            AnanOrganizationEntity topOrganiz = organizationRepository.findById(topId).orElse(null);
            List<AnanOrganizationEntity> organizs = organizationRepository.findByTopIdAndCodeStartingWithOrderByCodeAsc(topId, Objects.requireNonNull(topOrganiz, "通过topId：" + topId + "未找到对应的用户信息!").getCode());

            Specification<AnanUserEntity> condition = (root, query, cb) -> {
                Path<Long> organizIdPath = root.get("organizId");
                Path<Integer> statusPath = root.get("status");
                Path<String> usercodePath = root.get("usercode");
                Predicate predicate = cb.notEqual(usercodePath, SystemConstant.ANAN_USER_CODE);
                CriteriaBuilder.In<Long> in = cb.in(organizIdPath);
                for (AnanOrganizationEntity entity : organizs) {
                    in.value(entity.getId());
                }
                if (status != -1) {
                    predicate = cb.and(predicate, cb.equal(statusPath, status));
                }
                return cb.and(in, predicate);
            };
            entities = userRepository.findAll(condition);
        }
        return BeanUtil.copyCollectionProperties(entities, AnanUserRespDto.class);
    }

    /**
     * 根据传入的实体类动态查询多条符合条件的数据
     *
     * @return 动态查找的结果集
     */
    @Override
    public List<AnanUserRespDto> listAll() {
        return BeanUtil.copyCollectionProperties(
                userAllRepository.findAll(), AnanUserRespDto.class);
    }

    /**
     * 根据主键集合批量更新一个字段
     *
     * @param name  更新的字段名
     * @param value 更新的值
     * @param ids   主键集合
     * @return 更新的数量
     */
    @Override
    public long updateOneField(String name, Serializable value, Collection<Long> ids) {
        long count = UserService.super.updateOneField(name, value, ids);
        ids.forEach(id -> {
            AnanUserRespDto respDto = ananCacheManger.get(RedisConstant.ANAN_USER, id + "",
                    AnanUserRespDto.class);
            if (respDto != null) {
                ananCacheManger.evict(RedisConstant.ANAN_USER, respDto.getUsercode());
            }
            ananCacheManger.evict(RedisConstant.ANAN_USER, id + "");
            ananCacheManger.evict(RedisConstant.ANAN_USER_ALL_PERMISSIONS, id + "");
        });
        return count;
    }

    @Override
    public UserRepository getRepository() {
        return userRepository;
    }
}
