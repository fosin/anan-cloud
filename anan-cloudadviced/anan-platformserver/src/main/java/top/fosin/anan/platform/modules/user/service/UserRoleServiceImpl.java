package top.fosin.anan.platform.modules.user.service;


import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import top.fosin.anan.cloudresource.constant.PlatformRedisConstant;
import top.fosin.anan.cloudresource.dto.res.UserRespDto;
import top.fosin.anan.cloudresource.dto.res.UserRoleRespDto;
import top.fosin.anan.cloudresource.service.AnanUserDetailService;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.platform.modules.role.po.Role;
import top.fosin.anan.platform.modules.user.dao.UserRoleDao;
import top.fosin.anan.platform.modules.user.dto.UserRoleReqDto;
import top.fosin.anan.platform.modules.user.po.UserRole;
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
@AllArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleDao userRoleDao;
    private final AnanCacheManger ananCacheManger;
    private final AnanUserDetailService ananUserDetailService;

    @Override
    public List<UserRoleRespDto> createInBatch(Collection<UserRoleReqDto> dtos) {
        List<UserRole> saveEntities = new ArrayList<>();
        for (UserRoleReqDto dto : dtos) {
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
    public void deleteInBatch(Long userId) {
        //如果是用户角色，则只需要删除一个用户的缓存
        clearUserCache(userId);
        UserRoleService.super.deleteInBatch(userId);
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
