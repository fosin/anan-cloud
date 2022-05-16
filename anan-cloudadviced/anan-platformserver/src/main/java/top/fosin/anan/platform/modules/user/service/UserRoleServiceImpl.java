package top.fosin.anan.platform.modules.user.service;


import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import top.fosin.anan.cloudresource.constant.PlatformRedisConstant;
import top.fosin.anan.cloudresource.dto.res.UserRespDto;
import top.fosin.anan.cloudresource.dto.res.UserRoleRespDto;
import top.fosin.anan.cloudresource.service.AnanUserDetailService;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.platform.modules.role.entity.Role;
import top.fosin.anan.platform.modules.user.entity.UserRole;
import top.fosin.anan.platform.modules.user.dto.UserRoleReqDto;
import top.fosin.anan.platform.modules.user.entity.User;
import top.fosin.anan.platform.modules.user.dao.UserDao;
import top.fosin.anan.platform.modules.user.dao.UserRoleDao;
import top.fosin.anan.platform.modules.user.service.inter.UserRoleService;
import top.fosin.anan.redis.cache.AnanCacheManger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author fosin
 * @date 2017/12/29
 */
@Service
@Lazy
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleDao userRoleDao;
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final AnanCacheManger ananCacheManger;
    private final AnanUserDetailService ananUserDetailService;

    public UserRoleServiceImpl(UserRoleDao userRoleDao,
                               UserDao userDao,
                               PasswordEncoder passwordEncoder, AnanCacheManger ananCacheManger,
                               AnanUserDetailService ananUserDetailService) {
        this.userRoleDao = userRoleDao;
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.ananCacheManger = ananCacheManger;
        this.ananUserDetailService = ananUserDetailService;
    }

    @Override
    public List<UserRole> findByUserId(Long userId) {
        return userRoleDao.findByUserId(userId);
    }

    @Override
    public List<UserRole> findByRoleId(Long roleId) {
        return userRoleDao.findByRoleId(roleId);
    }

    @Override
    public List<UserRole> findByUsercodeAndPassword(String usercode, String password) {
        Assert.isTrue(null != usercode && !"".equals(usercode), "请输入用户名!");
        Assert.isTrue(null != password && !"".equals(password), "传入的用户ID不能为空!");
        User entity = userDao.findByUsercode(usercode);
        Assert.isTrue(null != entity && entity.getId() > 0 && passwordEncoder.matches(password, entity.getPassword()), "用户或密码不正确!");
        return findByUserId(entity.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<UserRoleRespDto> updateInBatch(String deleteCol, Long deleteValue, Collection<UserRoleReqDto> dtos) {
        if ("userId".equals(deleteCol)) {
            Assert.isTrue(dtos.stream().allMatch(entity -> entity.getUserId().equals(deleteValue)), "需要更新的数据集中有与用户ID不匹配的数据!");
            //如果是用户角色，则只需要删除一个用户的缓存
            clearUserCache(deleteValue);
            userRoleDao.deleteByUserId(deleteValue);
        } else {
            Assert.isTrue(dtos.stream().allMatch(entity -> entity.getRoleId().equals(deleteValue)), "需要更新的数据集中有与角色ID不匹配的数据!");
            //如果是角色用户，则需要删除所有角色相关用户的缓存
            for (UserRoleReqDto dto : dtos) {
                clearUserCache(dto.getUserId());
            }
            userRoleDao.deleteByRoleId(deleteValue);
        }

        return getAnanUserRoleEntities(dtos);
    }

    private void clearUserCache(Long userId) {
        UserRespDto respDto = ananCacheManger.get(PlatformRedisConstant.ANAN_USER, userId + "-id",
                UserRespDto.class);
        if (respDto != null) {
            ananCacheManger.evict(PlatformRedisConstant.ANAN_USER, respDto.getUsercode());
        }
        ananCacheManger.evict(PlatformRedisConstant.ANAN_USER, userId + "-id");
        ananCacheManger.evict(PlatformRedisConstant.ANAN_USER_ALL_PERMISSIONS, userId + "");
        ananCacheManger.evict(PlatformRedisConstant.ANAN_USER_PERMISSION_TREE, userId + "");
    }

    private List<UserRoleRespDto> getAnanUserRoleEntities(Collection<UserRoleReqDto> dtos) {
        List<UserRole> saveEntities = new ArrayList<>();
        if (dtos == null || dtos.size() == 0) {
            return null;
        }
        Long organizId = ananUserDetailService.getAnanOrganizId();
        for (UserRoleReqDto entity : dtos) {
            UserRole userRole = new UserRole();
            userRole.setUserId(entity.getUserId());
            Role role = new Role();
            role.setId(entity.getRoleId());
            userRole.setRole(role);
            if (entity.getOrganizId() == null) {
                userRole.setOrganizId(organizId);
            } else {
                userRole.setOrganizId(entity.getOrganizId());
            }
            saveEntities.add(userRole);
        }

        return BeanUtil.copyProperties(getRepository().saveAll(saveEntities), UserRoleRespDto.class);
    }

    public String getCacheKey(Integer type, Iterable<UserRole> entitis) {
        if (type == 1) {
            return getCacheKey(type, entitis.iterator().next().getRole().getId());
        } else {
            return getCacheKey(type, entitis.iterator().next().getUserId());
        }

    }

    public String getCacheKey(Integer type, Long id) {
        if (type == 1) {
            return "RoleId" + id;
        } else {
            return "UserId" + id;
        }
    }

    @Override
    public UserRoleDao getRepository() {
        return userRoleDao;
    }
}
