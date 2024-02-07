package top.fosin.anan.platform.modules.user.service;

import com.google.protobuf.Timestamp;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import top.fosin.anan.cloudresource.constant.PlatformRedisConstant;
import top.fosin.anan.cloudresource.constant.SystemConstant;
import top.fosin.anan.cloudresource.entity.req.UserCreateDTO;
import top.fosin.anan.cloudresource.entity.req.UserUpdateDTO;
import top.fosin.anan.cloudresource.entity.res.UserDTO;
import top.fosin.anan.cloudresource.grpc.service.UserGrpcServiceImpl;
import top.fosin.anan.cloudresource.grpc.user.*;
import top.fosin.anan.cloudresource.grpc.util.StringUtil;
import top.fosin.anan.cloudresource.service.CurrentUserService;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.core.util.RegexUtil;
import top.fosin.anan.data.converter.translate.StringTranslateCacheUtil;
import top.fosin.anan.data.entity.req.PageQuery;
import top.fosin.anan.data.entity.res.TreeVO;
import top.fosin.anan.data.result.PageResult;
import top.fosin.anan.data.result.ResultUtils;
import top.fosin.anan.platform.modules.organization.dto.OrganizationDTO;
import top.fosin.anan.platform.modules.organization.po.Organization;
import top.fosin.anan.platform.modules.organization.service.inter.OrgService;
import top.fosin.anan.platform.modules.parameter.service.LocalOrganParameter;
import top.fosin.anan.platform.modules.user.dao.UserDao;
import top.fosin.anan.platform.modules.user.dao.UserRoleDao;
import top.fosin.anan.platform.modules.user.po.User;
import top.fosin.anan.platform.modules.user.po.UserRole;
import top.fosin.anan.platform.modules.user.query.UserQuery;
import top.fosin.anan.platform.modules.user.service.inter.UserService;
import top.fosin.anan.redis.cache.AnanCacheManger;

import jakarta.persistence.criteria.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author fosin
 * @date 2017/12/27
 */
@GrpcService
@Lazy
@AllArgsConstructor
@Slf4j
public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase implements UserService {
    private final UserDao userDao;
    private final UserRoleDao userRoleDao;
    private final PasswordEncoder passwordEncoder;
    private final LocalOrganParameter localOrganParameter;
    private final AnanCacheManger ananCacheManger;
    private final CurrentUserService currentUserService;
    private final OrgService orgService;

    /**
     * 根据用户工号查找数据服务(包括软删除)
     *
     * @param usercode 用户工号
     * @return 用户
     */
    @Override
    public UserDTO findOneByUsercode(String usercode) {
        UserDTO respDto = ananCacheManger.get(PlatformRedisConstant.ANAN_USER_USERCODE, usercode, UserDTO.class);
        if (respDto == null) {
            User userEntity = userDao.findByUsercode(usercode);
            if (userEntity == null) {
                return null;
            }

            respDto = BeanUtil.copyProperties(userEntity, UserDTO.class);
            Long organizId = userEntity.getOrganizId();
            if (organizId > 0) {
                OrganizationDTO organization = orgService.findOneById(organizId);
                respDto.setTopId(organization.getTopId());
            } else {
                respDto.setTopId(0L);
            }
            ananCacheManger.put(PlatformRedisConstant.ANAN_USER_USERCODE, usercode, respDto);
        }
        return respDto;
    }

    @Override
    public UserDTO findOneById(Long id, boolean... findRefs) {
        UserDTO respDto = ananCacheManger.get(PlatformRedisConstant.ANAN_USER, String.valueOf(id), UserDTO.class);
        if (respDto == null) {
            User userEntity = userDao.findById(id).orElseThrow(() -> new IllegalArgumentException("未找到对应用户数据：" + id));
            respDto = BeanUtil.copyProperties(userEntity, UserDTO.class);
            Long organizId = respDto.getOrganizId();
            if (organizId > 0) {
                OrganizationDTO organization = orgService.findOneById(organizId);
                respDto.setTopId(organization.getTopId());
            } else {
                respDto.setTopId(0L);
            }
            ananCacheManger.put(PlatformRedisConstant.ANAN_USER, String.valueOf(id), respDto);
        }
        return respDto;
    }

    private String encryptBeforeSave(User entity) {
        String password = entity.getPassword();
        //如果密码为空则随机生成4位以下密码
        if (StringUtils.hasText(password)) {
            int length = localOrganParameter.getOrCreateParameter("UserInitPasswordLength", 6, "用户初始密码长度");
            Assert.isTrue(password.length() >= length, "用户初始密码最少" + length + "位!");
        } else {
            password = getPassword();
        }
        entity.setPassword(passwordEncoder.encode(password));
        return password;
    }

    @Override
    public UserDTO create(UserCreateDTO dto) {
        UserDTO respDTO = this.findOneByField("usercode", dto.getUsercode());
        Assert.isNull(respDTO, "用户工号已存在，请核对!");
        User createUser = BeanUtil.copyProperties(dto, User.class);
        String password = encryptBeforeSave(createUser);
        createUser = userDao.save(createUser);
        UserDTO userDTO = BeanUtil.copyProperties(createUser, UserDTO.class);
        userDTO.setPassword(password);
        return userDTO;
    }

    @Override
    public void update(UserUpdateDTO reqDto, String[] ignoreProperties) {
        Long id = reqDto.getId();
        User createUser = userDao.findById(id).orElseThrow(() -> new IllegalArgumentException("通过ID：" + id + "未能找到对应的数据!"));
        String usercode = createUser.getUsercode().toLowerCase();
        boolean changedName = !reqDto.getUsername().equals(createUser.getUsername());
        Assert.isTrue(!currentUserService.isSysAdminUser(usercode), "不能修改超级管理员帐号信息!");
        BeanUtil.copyProperties(reqDto, createUser, ignoreProperties);
        ananCacheManger.evict(PlatformRedisConstant.ANAN_USER, usercode);
        ananCacheManger.evict(PlatformRedisConstant.ANAN_USER, String.valueOf(id));
        ananCacheManger.evict(PlatformRedisConstant.ANAN_USER_ALL_PERMISSIONS, String.valueOf(id));
        ananCacheManger.evict(PlatformRedisConstant.ANAN_USER_PERMISSION_TREE, String.valueOf(id));
        userDao.save(createUser);
        if (changedName) StringTranslateCacheUtil.put(UserGrpcServiceImpl.class, reqDto.getId(), reqDto.getUsername());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        User entity = ananCacheManger.get(PlatformRedisConstant.ANAN_USER, String.valueOf(id), User.class);
        if (entity == null) {
            entity = userDao.findById(id).orElseThrow(() -> new IllegalArgumentException("通过该ID没有找到相应的用户数据!"));
        }
        deleteByEntity(entity);
    }

    private void deleteByEntity(User entity) {
        Assert.isTrue(!currentUserService.isSysAdminUser(entity.getUsercode()), "不能删除超级管理员帐号!");
        Long id = entity.getId();
        List<UserRole> userRoles = userRoleDao.findByUserId(id);
        Assert.isTrue(userRoles.size() == 0, "该用户下还存在角色信息,不能直接删除用户!");
        ananCacheManger.evict(PlatformRedisConstant.ANAN_USER, entity.getUsercode());
        String key = String.valueOf(id);
        ananCacheManger.evict(PlatformRedisConstant.ANAN_USER, key);
        ananCacheManger.evict(PlatformRedisConstant.ANAN_USER_ALL_PERMISSIONS, key);
        ananCacheManger.evict(PlatformRedisConstant.ANAN_USER_PERMISSION_TREE, key);
        userDao.delete(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByIds(Collection<Long> ids) {
        List<User> entities = userDao.findAllById(ids);
        for (User entity : entities) {
            deleteByEntity(entity);
        }
    }

    @Override
    public PageResult<UserDTO> findPage(PageQuery<?> PageQuery) {
        UserQuery params = (UserQuery) PageQuery.getParams();
        if (currentUserService.hasSysAdminRole()) {
            return UserService.super.findPage(PageQuery);
        }

        Specification<User> condition = (root, query, cb) -> {
            Path<String> usercodePath = root.get("usercode");
            Path<String> usernamePath = root.get("username");
            Path<String> phonePath = root.get("phone");
            Path<String> emailPath = root.get("email");

            Long organizId = currentUserService.getOrganizId();
            OrganizationDTO organization = orgService.findOneById(organizId);

            Subquery<Integer> subQuery = query.subquery(Integer.class);
            //从哪张表查询
            Root<Organization> celluseRoot = subQuery.from(Organization.class);
            //查询出什么
            subQuery.select(celluseRoot.get(TreeVO.ID_NAME));
            //条件是什么
            Predicate p = cb.like(celluseRoot.get("code"), Objects.requireNonNull(organization, "通过organizId：" + organizId + "未能找到对应的数据!").getCode() + "%");
            subQuery.where(p);

            Predicate defaultPredicate = cb.and(cb.notEqual(usercodePath, SystemConstant.ANAN_USER_CODE), cb.in(root.get("organizId")).value(subQuery));

            if (cn.hutool.core.bean.BeanUtil.isEmpty(params)) {
                return defaultPredicate;
            } else {
                String username = params.getUsername();
                String usercode = params.getUsercode();
                String phone = params.getPhone();
                String email = params.getEmail();
                if (!StringUtils.hasText(username) && !StringUtils.hasText(usercode) && !StringUtils.hasText(phone) && !StringUtils.hasText(email)) {
                    return defaultPredicate;
                }
                Predicate predicate = cb.or(cb.like(usernamePath, "%" + username + "%"), cb.like(usercodePath, "%" + usercode + "%"), cb.like(phonePath, "%" + phone + "%"), cb.like(emailPath, "%" + email + "%"));
                return cb.and(defaultPredicate, predicate);
            }
        };
        //分页查找
        PageRequest pageable = toPage(PageQuery);
        Page<User> page = userDao.findAll(condition, pageable);
        return ResultUtils.success(page.getTotalElements(), page.getTotalPages(), BeanUtil.copyProperties(page.getContent(), UserDTO.class));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void changePassword(Long id, String password, String confirmPassword1, String confirmPassword2) {
        Assert.isTrue(StringUtils.hasText(confirmPassword1) && StringUtils.hasText(confirmPassword2) && confirmPassword1.equals(confirmPassword2), "新密码和确认新密码不能为空且必须一致!");
        Assert.isTrue(!confirmPassword1.equals(password), "新密码和原密码不能相同!");
        String pwdDesc = "密码最少6位，包括至少1个大写字母，1个小写字母，1个数字，1个特殊字符";
        String description = "用户密码强度正则表达式," + pwdDesc;
        String passwordStrength = localOrganParameter.getOrCreateParameter("DefaultPasswordStrength", RegexUtil.PASSWORD_STRONG, description);
        Assert.isTrue(RegexUtil.matcher(confirmPassword1, passwordStrength), "新密码强度不符合强度要求(" + pwdDesc + ")!");

        User user = userDao.findById(id).orElse(null);
        Assert.isTrue(passwordEncoder.matches(password, Objects.requireNonNull(user, "通过ID：" + id + "未找到对应的用户信息!").getPassword()), "原密码不正确!");
        user.setPassword(confirmPassword1);
        encryptBeforeSave(user);
        ananCacheManger.evict(PlatformRedisConstant.ANAN_USER, user.getUsercode());
        ananCacheManger.evict(PlatformRedisConstant.ANAN_USER, String.valueOf(id));
        userDao.save(user);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String resetPassword(Long id) {
        User user = userDao.findById(id).orElseThrow(() -> new IllegalArgumentException("通过ID：" + id + "未找到对应的用户信息!"));
        String password = getPassword();
        user.setPassword(password);
        encryptBeforeSave(user);
        ananCacheManger.evict(PlatformRedisConstant.ANAN_USER, user.getUsercode());
        ananCacheManger.evict(PlatformRedisConstant.ANAN_USER, String.valueOf(id));
        userDao.save(user);
        return password;
    }

    public String getPassword() {
        String password;
        String resetUserPasswordType = localOrganParameter.getOrCreateParameter("UserDefaultPasswordStrategy", "2", "用户默认密码策略(1、固定密码（UserDefaultPassword） 2、随机密码（UserInitPasswordLength）)");
        if ("1".equals(resetUserPasswordType)) {
            password = localOrganParameter.getOrCreateParameter("UserDefaultPassword", "123456", "用户的默认密码");
        } else {
            int i = localOrganParameter.getOrCreateParameter("UserInitPasswordLength", 6, "用户初始密码长度");
            int random = 666888;
            if (i > 0) {
                random = (int) (Math.pow(10, i)) - 1;
            }
            password = String.valueOf(new Random().nextInt(random));
        }
        return password;
    }

    @Override
    public List<UserDTO> listOtherUsersByRoleId(Long roleId) {
        return BeanUtil.copyProperties(userDao.findOtherUsersByRoleId(roleId), UserDTO.class);
    }

    @Override
    public List<UserDTO> listAllChildByTopId(Long topId, Byte status) {
        List<User> entities = listAllChildByTopIdReal(topId, status);
        return BeanUtil.copyProperties(entities, UserDTO.class);
    }

    private List<User> listAllChildByTopIdReal(Long topId, Byte status) {
        List<User> entities;
        if (currentUserService.hasAuthentication() && currentUserService.hasSysAdminRole()) {
            entities = userDao.findAll();
        } else {
            if (topId < 1) {
                topId = currentUserService.getTopId();
            }
            entities = userDao.findByTopIdAndStatus(topId, status);
            entities.add(userDao.findById(SystemConstant.ANAN_USER_ID).orElseThrow(() -> new IllegalArgumentException("未找到用户序号：" + SystemConstant.ANAN_USER_ID + "的用户信息！")));
        }
        return entities;
    }

    @Override
    public List<UserDTO> listByOrganizId(Long organizId, Byte status) {
        List<User> entities = listByOrganizIdReal(organizId, status);
        return BeanUtil.copyProperties(entities, UserDTO.class);
    }

    private List<User> listByOrganizIdReal(Long organizId, Byte status) {
        List<User> entities;
        if (currentUserService.hasAuthentication() && currentUserService.hasSysAdminRole()) {
            entities = userDao.findAll();
        } else {
            OrganizationDTO organiz = orgService.findOneById(organizId);
            List<Organization> organizs = orgService.findByTopIdAndCodeStartingWithOrderByCodeAsc(organiz.getTopId(), organiz.getCode());

            Specification<User> condition = (root, query, cb) -> {
                Path<Long> organizIdPath = root.get("organizId");
                Path<Integer> statusPath = root.get("status");
                Path<Integer> deletedPath = root.get("deleted");
                Path<String> usercodePath = root.get("usercode");
                Predicate predicate = cb.notEqual(usercodePath, SystemConstant.ANAN_USER_CODE);
                CriteriaBuilder.In<Long> in = cb.in(organizIdPath);
                for (Organization entity : organizs) {
                    in.value(entity.getId());
                }
                predicate = cb.and(predicate, cb.equal(deletedPath, 0));
                if (status != -1) {
                    predicate = cb.and(predicate, cb.equal(statusPath, status));
                }
                return cb.and(in, predicate);
            };
            entities = userDao.findAll(condition);
        }
        return entities;
    }

    @Override
    public long updateOneField(String name, Serializable value, Collection<Long> ids) {
        long count = UserService.super.updateOneField(name, value, ids);
        ids.forEach(id -> {
            UserDTO respDto = ananCacheManger.get(PlatformRedisConstant.ANAN_USER, String.valueOf(id), UserDTO.class);
            if (respDto != null) {
                ananCacheManger.evict(PlatformRedisConstant.ANAN_USER, respDto.getUsercode());
            }
            ananCacheManger.evict(PlatformRedisConstant.ANAN_USER, String.valueOf(id));
            ananCacheManger.evict(PlatformRedisConstant.ANAN_USER_ALL_PERMISSIONS, String.valueOf(id));
            ananCacheManger.evict(PlatformRedisConstant.ANAN_USER_PERMISSION_TREE, String.valueOf(id));
        });
        return count;
    }

    @Override
    public void findOneById(UserIdReq request, StreamObserver<UserResp> responseObserver) {
        long id = request.getId();
        UserDTO dto = this.findOneById(id);
        toUserResp(responseObserver, dto);
    }

    private static void toUserResp(StreamObserver<UserResp> responseObserver, UserDTO dto) {
        //@formatter:off
        UserResp userResp = UserResp.newBuilder()
                .setUsercode(dto.getUsercode())
                .setUsername(dto.getUsername())
                .setFamilyName(StringUtil.getNonNullValue(dto.getFamilyName()))
                .setMiddleName(StringUtil.getNonNullValue(dto.getMiddleName()))
                .setGivenName(StringUtil.getNonNullValue(dto.getGivenName()))
                .setNickname(StringUtil.getNonNullValue(dto.getNickname()))
                .setPreferredUsername(StringUtil.getNonNullValue(dto.getPreferredUsername()))
                .setRealNameVerified(dto.getRealNameVerified())
                .setOrganizId(dto.getOrganizId())
                .setTopId(dto.getTopId())
                .setBirthday(Timestamp.newBuilder().setSeconds(dto.getBirthday().getTime() / 1000).build())
                .setSex(dto.getSex())
                .setEmail(StringUtil.getNonNullValue(dto.getEmail()))
                .setEmailVerified(dto.getEmailVerified())
                .setPhone(StringUtil.getNonNullValue(dto.getPhone()))
                .setPhoneVerified(dto.getPhoneVerified())
                .setStatus(dto.getStatus())
                .setAvatar(StringUtil.getNonNullValue(dto.getAvatar()))
                .setWebsite(StringUtil.getNonNullValue(dto.getWebsite()))
                .setExpireTime(Timestamp.newBuilder().setSeconds(dto.getExpireTime().getTime() / 1000).build())
                .setId(dto.getId())
                .setCreateBy(dto.getCreateBy())
                .setCreateTime(Timestamp.newBuilder().setSeconds(dto.getCreateTime().getTime() / 1000).build())
                .setUpdateBy(dto.getUpdateBy())
                .setUpdateTime(Timestamp.newBuilder().setSeconds(dto.getUpdateTime().getTime() / 1000).build())
                .setPassword(dto.getPassword())
                .build();
        responseObserver.onNext(userResp);
        responseObserver.onCompleted();
        //@formatter:on
    }

    @Override
    public void findOneByUsercode(UsercodeReq request, StreamObserver<UserResp> responseObserver) {
        String usercode = request.getUsercode();
        UserDTO dto = this.findOneByUsercode(usercode);
        toUserResp(responseObserver, dto);
    }

    @Override
    public void listByIds(UserIdsReq request, StreamObserver<UsersResp> responseObserver) {
        List<Long> ids = request.getIdList();
        List<User> users = this.getDao().findAllById(ids);
        toGrpcResps(responseObserver, users);
    }

    @Override
    public void listByOrganizId(OrganizReq request, StreamObserver<UsersResp> responseObserver) {
        List<User> users = listByOrganizIdReal(request.getOrganizId(), (byte) request.getStatus());
        toGrpcResps(responseObserver, users);
    }

    @Override
    public void listAllChildByTopId(TopOrganizReq request, StreamObserver<UsersResp> responseObserver) {
        List<User> users = listAllChildByTopIdReal(request.getTopId(), (byte) request.getStatus());
        toGrpcResps(responseObserver, users);
    }

    @NotNull
    private UserResp toGrpcResp(User user, Long topId) {
        //@formatter:off
        return UserResp.newBuilder()
                .setUsercode(user.getUsercode())
                .setUsername(user.getUsername())
                .setFamilyName(StringUtil.getNonNullValue(user.getFamilyName()))
                .setMiddleName(StringUtil.getNonNullValue(user.getMiddleName()))
                .setGivenName(StringUtil.getNonNullValue(user.getGivenName()))
                .setNickname(StringUtil.getNonNullValue(user.getNickname()))
                .setPreferredUsername(StringUtil.getNonNullValue(user.getPreferredUsername()))
                .setRealNameVerified(user.getRealNameVerified())
                .setOrganizId(user.getOrganizId())
                .setTopId(topId)
                .setBirthday(Timestamp.newBuilder().setSeconds(user.getBirthday().getTime() / 1000).build())
                .setSex(user.getSex())
                .setEmail(StringUtil.getNonNullValue(user.getEmail()))
                .setEmailVerified(user.getEmailVerified())
                .setPhone(StringUtil.getNonNullValue(user.getPhone()))
                .setPhoneVerified(user.getPhoneVerified())
                .setStatus(user.getStatus())
                .setAvatar(StringUtil.getNonNullValue(user.getAvatar()))
                .setWebsite(StringUtil.getNonNullValue(user.getWebsite()))
                .setExpireTime(Timestamp.newBuilder().setSeconds(user.getExpireTime().getTime() / 1000).build())
                .setId(user.getId())
                .setCreateBy(user.getCreateBy())
                .setCreateTime(Timestamp.newBuilder().setSeconds(user.getCreateTime().getTime() / 1000).build())
                .setUpdateBy(user.getUpdateBy())
                .setUpdateTime(Timestamp.newBuilder().setSeconds(user.getUpdateTime().getTime() / 1000).build())
                .setPassword(user.getPassword())
                .build();
        //@formatter:on
    }

    private void toGrpcResps(StreamObserver<UsersResp> responseObserver, List<User> users) {
        List<Long> organizIds = users.stream().map(User::getOrganizId).distinct().collect(Collectors.toList());
        List<OrganizationDTO> organizations = orgService.listByIds(organizIds);
        List<Long> topIds = organizations.stream().map(OrganizationDTO::getTopId).distinct().collect(Collectors.toList());
        UsersResp usersResp = UsersResp.newBuilder().addAllUser(users.stream().map(entity -> {
            Long organizId = entity.getOrganizId();
            long topId = topIds.size() == 0 ? 0 : topIds.get(0);
            if (organizId > 0 && topId == 0) {
                for (int i = 0; i < organizIds.size(); i++) {
                    if (organizId.equals(organizIds.get(i))) {
                        topId = organizations.get(i).getTopId();
                    }
                }
            }
            return toGrpcResp(entity, topId);
        }).collect(Collectors.toList())).build();
        responseObserver.onNext(usersResp);
        responseObserver.onCompleted();
    }

    @Override
    public UserDao getDao() {
        return userDao;
    }
}
