package top.fosin.anan.platform.modules.role.service;


import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import top.fosin.anan.cloudresource.constant.PlatformRedisConstant;
import top.fosin.anan.cloudresource.entity.res.UserRespDTO;
import top.fosin.anan.cloudresource.service.CurrentUserService;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.platform.modules.role.dto.RoleUserUpdateDTO;
import top.fosin.anan.platform.modules.role.dto.UserRoleRespDTO;
import top.fosin.anan.platform.modules.role.po.Role;
import top.fosin.anan.platform.modules.role.service.inter.RoleUserService;
import top.fosin.anan.platform.modules.user.dao.UserRoleDao;
import top.fosin.anan.platform.modules.user.po.UserRole;
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
@AllArgsConstructor
public class RoleUserServiceImpl implements RoleUserService {
    private final UserRoleDao userRoleDao;
    private final AnanCacheManger ananCacheManger;
    private final CurrentUserService currentUserService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<UserRoleRespDTO> createInBatch(Collection<RoleUserUpdateDTO> dtos) {
        List<UserRole> saveEntities = new ArrayList<>();
        for (RoleUserUpdateDTO dto : dtos) {
            UserRole userRole = new UserRole();
            userRole.setUserId(dto.getUserId());
            Role role = new Role();
            role.setId(dto.getRoleId());
            userRole.setRole(role);
            if (dto.getOrganizId() == null) {
                userRole.setOrganizId(currentUserService.getOrganizId().orElseThrow(() -> new IllegalArgumentException("未找到当前用户的机构序号！")));
            } else {
                userRole.setOrganizId(dto.getOrganizId());
            }
            saveEntities.add(userRole);
        }
        return BeanUtil.copyProperties(getDao().saveAll(saveEntities), UserRoleRespDTO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteInBatch(Long roleId) {
        Assert.notNull(roleId, "roleId：" + roleId + "属性值无效!");
        RoleUserUpdateDTO reqDto = new RoleUserUpdateDTO();
        reqDto.setFkValue(roleId);
        List<UserRole> userRoles = posByEntity(reqDto);
        //如果是用户角色，则只需要删除一个用户的缓存
        for (UserRole dto : userRoles) {
            clearUserCache(dto.getUserId());
        }
        getDao().deleteAllInBatch(userRoles);
    }

    private void clearUserCache(Long userId) {
        UserRespDTO respDto = ananCacheManger.get(PlatformRedisConstant.ANAN_USER, userId + "-id",
                UserRespDTO.class);
        if (respDto != null) {
            ananCacheManger.evict(PlatformRedisConstant.ANAN_USER, respDto.getUsercode());
        }
        ananCacheManger.evict(PlatformRedisConstant.ANAN_USER, userId + "-id");
        ananCacheManger.evict(PlatformRedisConstant.ANAN_USER_ALL_PERMISSIONS, userId + "");
        ananCacheManger.evict(PlatformRedisConstant.ANAN_USER_PERMISSION_TREE, userId + "");
    }

    @Override
    public UserRoleDao getDao() {
        return userRoleDao;
    }
}
