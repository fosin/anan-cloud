package top.fosin.anan.platform.modules.role.service;


import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import top.fosin.anan.cloudresource.constant.PlatformRedisConstant;
import top.fosin.anan.cloudresource.dto.res.UserRespDto;
import top.fosin.anan.cloudresource.dto.res.UserRoleRespDto;
import top.fosin.anan.cloudresource.service.AnanUserDetailService;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.platform.modules.role.dto.RoleUserReqDto;
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
    private final AnanUserDetailService ananUserDetailService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<UserRoleRespDto> createInBatch(Collection<RoleUserReqDto> dtos) {
        List<UserRole> saveEntities = new ArrayList<>();
        for (RoleUserReqDto dto : dtos) {
            UserRole userRole = new UserRole();
            userRole.setUserId(dto.getUserId());
            Role role = new Role();
            role.setId(dto.getRoleId());
            userRole.setRole(role);
            if (dto.getOrganizId() == null) {
                userRole.setOrganizId(ananUserDetailService.getAnanOrganizId());
            } else {
                userRole.setOrganizId(dto.getOrganizId());
            }
            saveEntities.add(userRole);
        }
        return BeanUtil.copyProperties(getDao().saveAll(saveEntities), UserRoleRespDto.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteInBatch(Long roleId) {
        Assert.notNull(roleId, "roleId：" + roleId + "属性值无效!");
        RoleUserReqDto reqDto = new RoleUserReqDto();
        reqDto.setFkValue(roleId);
        List<UserRole> userRoles = posByEntity(reqDto);
        //如果是用户角色，则只需要删除一个用户的缓存
        for (UserRole dto : userRoles) {
            clearUserCache(dto.getUserId());
        }
        getDao().deleteAllInBatch(userRoles);
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

    @Override
    public UserRoleDao getDao() {
        return userRoleDao;
    }
}
