package top.fosin.anan.platform.modules.user.service;

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
import top.fosin.anan.cloudresource.constant.PlatformRedisConstant;
import top.fosin.anan.cloudresource.constant.SystemConstant;
import top.fosin.anan.cloudresource.dto.req.UserReqDto;
import top.fosin.anan.cloudresource.dto.res.UserRespDto;
import top.fosin.anan.cloudresource.service.AnanUserDetailService;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.core.util.RegexUtil;
import top.fosin.anan.jpa.util.JpaUtil;
import top.fosin.anan.model.dto.res.TreeDto;
import top.fosin.anan.model.dto.req.PageDto;
import top.fosin.anan.model.result.PageResult;
import top.fosin.anan.model.result.ResultUtils;
import top.fosin.anan.platform.modules.organization.dao.OrgDao;
import top.fosin.anan.platform.modules.organization.entity.Organization;
import top.fosin.anan.platform.modules.pub.service.LocalOrganParameter;
import top.fosin.anan.platform.modules.user.dao.UserAllDao;
import top.fosin.anan.platform.modules.user.dao.UserDao;
import top.fosin.anan.platform.modules.user.dao.UserRoleDao;
import top.fosin.anan.platform.modules.user.dto.UserPassRespDto;
import top.fosin.anan.platform.modules.user.entity.User;
import top.fosin.anan.platform.modules.user.entity.UserAll;
import top.fosin.anan.platform.modules.user.entity.UserRole;
import top.fosin.anan.platform.modules.user.service.inter.UserService;
import top.fosin.anan.redis.cache.AnanCacheManger;

import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * @author fosin
 * @date 2017/12/27
 */
@Service
@Lazy
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final UserAllDao userAllDao;
    private final UserRoleDao userRoleDao;
    private final PasswordEncoder passwordEncoder;
    private final LocalOrganParameter localOrganParameter;
    private final AnanCacheManger ananCacheManger;
    private final AnanUserDetailService ananUserDetailService;
    private final OrgDao orgDao;

    public UserServiceImpl(UserDao userDao, UserAllDao userAllDao, UserRoleDao userRoleDao, PasswordEncoder passwordEncoder, LocalOrganParameter localOrganParameter, AnanCacheManger ananCacheManger, AnanUserDetailService ananUserDetailService, OrgDao orgDao) {
        this.userDao = userDao;
        this.userAllDao = userAllDao;
        this.userRoleDao = userRoleDao;
        this.passwordEncoder = passwordEncoder;
        this.localOrganParameter = localOrganParameter;
        this.ananCacheManger = ananCacheManger;
        this.ananUserDetailService = ananUserDetailService;
        this.orgDao = orgDao;
    }

    /**
     * 根据主键编号查找所有数据服务(包括软删除)
     *
     * @param ids 主键编号
     * @return 查找结果集
     */
    @Override
    public List<UserRespDto> listByIds(Collection<Long> ids) {
        return BeanUtil.copyProperties(userAllDao.findAllById(ids), UserRespDto.class);
    }

    /**
     * 根据用户工号查找数据服务(包括软删除)
     *
     * @param usercode 用户工号
     * @return 用户
     */
    @Override
    @Cacheable(value = PlatformRedisConstant.ANAN_USER, key = "#usercode", unless = "#result == null")
    @Transactional(readOnly = true)
    public UserRespDto findByUsercode(String usercode) {
        Assert.notNull(usercode, "用户工号不能为空!");
        User userEntity = userAllDao.findByUsercode(usercode);
        if (userEntity == null) {
            return null;
        }

        UserRespDto respDto = BeanUtil.copyProperties(userEntity, UserRespDto.class);
        Long organizId = userEntity.getOrganizId();
        if (organizId > 0) {
            Organization organization = orgDao.getById(organizId);
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
    @Cacheable(value = PlatformRedisConstant.ANAN_USER, key = "#id+'-id'", unless = "#result == null")
    @Transactional(readOnly = true)
    public UserRespDto findOneById(Long id) {
        UserAll userEntity = userAllDao.findById(id).orElseThrow(() -> new IllegalArgumentException("未找到对应数据!"));
        UserRespDto respDto = BeanUtil.copyProperties(userEntity, UserRespDto.class);
        Long organizId = userEntity.getOrganizId();
        if (organizId > 0) {
            Organization organization = orgDao.getById(organizId);
            respDto.setTopId(organization.getTopId());
        }
        return respDto;
    }


    private String encryptBeforeSave(User entity) {
        String password = entity.getPassword();
        //如果密码为空则随机生成4位以下密码
        if (!StringUtils.hasText(password)) {
            password = getPassword();
        } else {
            int length = localOrganParameter.getOrCreateParameter("UserInitPasswordLength", 6,
                            "用户初始密码长度");
            Assert.isTrue(password.length() >= length, "用户初始密码最少"+length+"位!");
        }
        entity.setPassword(passwordEncoder.encode(password));
        return password;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserPassRespDto create(UserReqDto dto) {
        UserRespDto entityDynamic = this.findByUsercode(dto.getUsercode());
        Assert.isNull(entityDynamic, "用户工号已存在，请核对!");
        User createUser = new User();
        BeanUtils.copyProperties(dto, createUser);
        String password = encryptBeforeSave(createUser);
        createUser = userDao.save(createUser);
        UserPassRespDto respPassDto = BeanUtil.copyProperties(createUser, UserPassRespDto.class);
        respPassDto.setPassword(password);
        return respPassDto;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(UserReqDto dto) {
        Long id = dto.getId();
        Assert.isTrue(id > 0, "更新的数据id不能为空或者小于1!");
        User createUser = userDao.findById(id).orElse(null);
        String usercode = Objects.requireNonNull(createUser, "通过ID：" + id + "未能找到对应的数据!").getUsercode().toLowerCase();
        if (ananUserDetailService.isAdminUser(usercode) &&
                !ananUserDetailService.isAdminUser(dto.getUsercode().toLowerCase())) {
            throw new IllegalArgumentException("不能修改管理员" + SystemConstant.ADMIN_USER_CODE + "的帐号名称!");
        }
        Assert.isTrue(!ananUserDetailService.isSysAdminUser(usercode),
                "不能修改超级管理员帐号信息!");
        BeanUtils.copyProperties(dto, createUser);
        ananCacheManger.evict(PlatformRedisConstant.ANAN_USER, usercode);
        ananCacheManger.evict(PlatformRedisConstant.ANAN_USER, id + "-id");
        ananCacheManger.evict(PlatformRedisConstant.ANAN_USER_ALL_PERMISSIONS, id + "");
        ananCacheManger.evict(PlatformRedisConstant.ANAN_USER_PERMISSION_TREE, id + "");
        userDao.save(createUser);
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = PlatformRedisConstant.ANAN_USER, key = "#root.caches[0].get(#id).get().usercode", condition = "#root.caches[0].get(#id) != null"),
                    @CacheEvict(value = PlatformRedisConstant.ANAN_USER, key = "#id+'-id'"),
                    @CacheEvict(value = PlatformRedisConstant.ANAN_USER_PERMISSION_TREE, key = "#id"),
                    @CacheEvict(value = PlatformRedisConstant.ANAN_USER_ALL_PERMISSIONS, key = "#id")
            }
    )
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        Assert.isTrue(id != null && id > 0, "传入的用户ID无效！");
        User entity = ananCacheManger.get(PlatformRedisConstant.ANAN_USER, id + "-id", User.class);
        if (entity == null) {
            entity = userDao.findById(id).orElse(null);
        }
        Assert.notNull(entity, "通过该ID没有找到相应的用户数据!");
        deleteByEntity(entity);
    }

    private void deleteByEntity(User entity) {
        Assert.isTrue(!ananUserDetailService.isSysAdminUser(entity.getUsercode())
                && !ananUserDetailService.isAdminUser(entity.getUsercode()), "不能删除管理员帐号!");
        List<UserRole> userRoles = userRoleDao.findByUserId(entity.getId());
        Assert.isTrue(userRoles.size() == 0, "该用户下还存在角色信息,不能直接删除用户!");
        userDao.delete(entity);
    }

    /**
     * 根据主键删除多条数据
     *
     * @param ids 主键编号集合
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByIds(Collection<Long> ids) {
        List<User> entities = userDao.findAllById(ids);
        for (User entity : entities) {
            deleteByEntity(entity);
        }
        for (User entity : entities) {
            String id = entity.getId() + "";
            ananCacheManger.evict(PlatformRedisConstant.ANAN_USER, id + "-id");
            ananCacheManger.evict(PlatformRedisConstant.ANAN_USER, entity.getUsercode());
            ananCacheManger.evict(PlatformRedisConstant.ANAN_USER_ALL_PERMISSIONS, id);
            ananCacheManger.evict(PlatformRedisConstant.ANAN_USER_PERMISSION_TREE, id);
        }
    }

    @Override
    public PageResult<UserRespDto> findPage(PageDto<UserReqDto> PageDto) {
        PageRequest pageable = PageRequest.of(PageDto.getPageNumber() - 1, PageDto.getPageSize(),
                JpaUtil.buildSortRules(PageDto.getParams().getSortRules()));

        Specification<User> condition = (root, query, cb) -> {
            Path<String> usercodePath = root.get("usercode");
            Path<String> usernamePath = root.get("username");
            Path<String> phonePath = root.get("phone");
            Path<String> emailPath = root.get("email");

            CriteriaBuilder.In<Object> organizId1 = null;
            boolean sysAdminUser = ananUserDetailService.isSysAdminUser();
            if (!sysAdminUser) {
                Long organizId = ananUserDetailService.getAnanOrganizId();
                Organization organization = orgDao.findById(organizId).orElse(null);

                Subquery<Integer> subQuery = query.subquery(Integer.class);
                //从哪张表查询
                Root<Organization> celluseRoot = subQuery.from(Organization.class);
                //查询出什么
                subQuery.select(celluseRoot.get(TreeDto.ID_NAME));
                //条件是什么
                Predicate p = cb.like(celluseRoot.get("code"), Objects.requireNonNull(organization, "通过organizId：" + organizId + "未能找到对应的数据!").getCode() + "%");
                subQuery.where(p);

                organizId1 = cb.in(root.get("organizId")).value(subQuery);
            }
            UserReqDto params = PageDto.getParams();

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
                if (!StringUtils.hasText(username)
                        && !StringUtils.hasText(usercode)
                        && !StringUtils.hasText(phone)
                        && !StringUtils.hasText(email)
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
        Page<User> page = userDao.findAll(condition, pageable);
        return ResultUtils.success(page.getTotalElements(), page.getTotalPages(),
                BeanUtil.copyProperties(page.getContent(), UserRespDto.class));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = PlatformRedisConstant.ANAN_USER, key = "#id+'-id'"),
                    @CacheEvict(value = PlatformRedisConstant.ANAN_USER, key = "#result.usercode")
            }
    )
    public UserRespDto changePassword(Long id, String password, String confirmPassword1, String confirmPassword2) {
        Assert.isTrue(StringUtils.hasText(confirmPassword1) &&
                StringUtils.hasText(confirmPassword2) && confirmPassword1.equals(confirmPassword2), "新密码和确认新密码不能为空且必须一致!");
        Assert.isTrue(!confirmPassword1.equals(password), "新密码和原密码不能相同!");
        String pwdDesc = "密码最少6位，包括至少1个大写字母，1个小写字母，1个数字，1个特殊字符";
        String description = "用户密码强度正则表达式," + pwdDesc;
        String passwordStrength = localOrganParameter.getOrCreateParameter("DefaultPasswordStrength", RegexUtil.PASSWORD_STRONG, description);
        Assert.isTrue(RegexUtil.matcher(confirmPassword1, passwordStrength), "新密码强度不符合强度要求(" + pwdDesc + ")!");

        User user = userDao.findById(id).orElse(null);
        Assert.isTrue(passwordEncoder.matches(password, Objects.requireNonNull(user, "通过ID：" + id + "未找到对应的用户信息!").getPassword()), "原密码不正确!");
        user.setPassword(confirmPassword1);
        encryptBeforeSave(user);
        return BeanUtil.copyProperties(userDao.save(user), UserRespDto.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = PlatformRedisConstant.ANAN_USER, key = "#id+'-id'"),
                    @CacheEvict(value = PlatformRedisConstant.ANAN_USER, key = "#result.usercode")
            }
    )
    public UserPassRespDto resetPassword(Long id) {
        Assert.notNull(id, "用户ID不能为空!");

        Long loginId = ananUserDetailService.getAnanUserId();
        Assert.isTrue(!Objects.equals(loginId, id), "不能重置本人密码,请使用修改密码功能!");
        User user = userDao.findById(id).orElse(null);
        String password = getPassword();
        Objects.requireNonNull(user, "通过ID：" + id + "未找到对应的用户信息!").setPassword(password);
        encryptBeforeSave(user);
        userDao.save(user);
        UserPassRespDto respPassDto = BeanUtil.copyProperties(user, UserPassRespDto.class);
        respPassDto.setPassword(password);
        return respPassDto;
    }

    public String getPassword() {
        String password;
        String resetUserPasswordType = localOrganParameter.getOrCreateParameter("UserDefaultPasswordStrategy", "2",
                "用户默认密码策略(1、固定密码（UserDefaultPassword） 2、随机密码（UserInitPasswordLength）)");
        if ("1".equals(resetUserPasswordType)) {
            password = localOrganParameter.getOrCreateParameter("UserDefaultPassword", "123456", "用户的默认密码");
        } else {
            int i = localOrganParameter.getOrCreateParameter("UserInitPasswordLength", 6, "用户初始密码长度");
            int random = 9999;
            if (i > 0) {
                random = (int) (Math.pow(10, i)) - 1;
            }
            password = new Random().nextInt(random) + "";
        }
        return password;
    }

    @Override
    public List<UserRespDto> findOtherUsersByRoleId(Long roleId) {
        return BeanUtil.copyProperties(
                userDao.findOtherUsersByRoleId(roleId), UserRespDto.class);
    }

    @Override
    public List<UserRespDto> findRoleUsersByRoleId(Long roleId) {
        return BeanUtil.copyProperties(
                userDao.findRoleUsersByRoleId(roleId), UserRespDto.class);
    }

    @Override
    public List<UserRespDto> listAllChildByTopId(Long topId, Integer status) {
        List<User> entities;
        if (ananUserDetailService.isUserRequest() && ananUserDetailService.hasSysAdminRole()) {
            entities = userDao.findAll();
        } else {
            if (topId < 1) {
                Organization organiz =
                        orgDao.findById(ananUserDetailService.getAnanOrganizId()).orElseThrow(() -> new IllegalArgumentException("根据传入的机构编码没有找到任何数据!"));
                topId = organiz.getTopId();
            }
            entities = userDao.findByTopIdAndStatus(topId, status);
            entities.add(userDao.getById(SystemConstant.ANAN_USER_ID));
        }
        return BeanUtil.copyProperties(entities, UserRespDto.class);
    }

    @Override
    public List<UserRespDto> listByOrganizId(Long organizId, Integer status) {
        List<User> entities;
        if (ananUserDetailService.isUserRequest() && ananUserDetailService.hasSysAdminRole()) {
            entities = userDao.findAll();
        } else {
            Organization organiz = orgDao.findById(organizId).orElse(null);
            Assert.notNull(organiz, "根据传入的机构编码没有找到任何数据!");
            Long topId = organiz.getTopId();
            Organization topOrganiz = orgDao.findById(topId).orElse(null);
            List<Organization> organizs = orgDao.findByTopIdAndCodeStartingWithOrderByCodeAsc(topId, Objects.requireNonNull(topOrganiz, "通过topId：" + topId + "未找到对应的用户信息!").getCode());

            Specification<User> condition = (root, query, cb) -> {
                Path<Long> organizIdPath = root.get("organizId");
                Path<Integer> statusPath = root.get("status");
                Path<String> usercodePath = root.get("usercode");
                Predicate predicate = cb.notEqual(usercodePath, SystemConstant.ANAN_USER_CODE);
                CriteriaBuilder.In<Long> in = cb.in(organizIdPath);
                for (Organization entity : organizs) {
                    in.value(entity.getId());
                }
                if (status != -1) {
                    predicate = cb.and(predicate, cb.equal(statusPath, status));
                }
                return cb.and(in, predicate);
            };
            entities = userDao.findAll(condition);
        }
        return BeanUtil.copyProperties(entities, UserRespDto.class);
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
            UserRespDto respDto = ananCacheManger.get(PlatformRedisConstant.ANAN_USER, id + "-id",
                    UserRespDto.class);
            if (respDto != null) {
                ananCacheManger.evict(PlatformRedisConstant.ANAN_USER, respDto.getUsercode());
            }
            ananCacheManger.evict(PlatformRedisConstant.ANAN_USER, id + "-id");
            ananCacheManger.evict(PlatformRedisConstant.ANAN_USER_ALL_PERMISSIONS, id + "");
            ananCacheManger.evict(PlatformRedisConstant.ANAN_USER_PERMISSION_TREE, id + "");
        });
        return count;
    }

    @Override
    public UserDao getDao() {
        return userDao;
    }
}
