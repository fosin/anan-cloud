package top.fosin.anan.platform.modules.role.service;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import top.fosin.anan.cloudresource.constant.PlatformRedisConstant;
import top.fosin.anan.platform.modules.role.dao.RolePermissionDao;
import top.fosin.anan.platform.modules.role.dto.RolePermissionDTO;
import top.fosin.anan.platform.modules.role.dto.RolePermissionUpdateDTO;
import top.fosin.anan.platform.modules.role.service.inter.RolePermissionService;

import java.util.Collection;

/**
 * @author fosin
 * @date 2017/12/29
 */
@Service
@Lazy
@AllArgsConstructor
public class RolePermissionServiceImpl implements RolePermissionService {
    private final RolePermissionDao rolePermissionDao;

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = PlatformRedisConstant.ANAN_USER_ALL_PERMISSIONS, allEntries = true),
                    @CacheEvict(value = PlatformRedisConstant.ANAN_USER_PERMISSION_TREE, allEntries = true)
            })
    public void postProcessInBatch(Long roleId, Collection<RolePermissionUpdateDTO> updateDTOS, Collection<RolePermissionDTO> permissionDTOS, boolean... processAction) {
        RolePermissionService.super.postProcessInBatch(roleId, updateDTOS, permissionDTOS, processAction);
    }

    @Override
    public RolePermissionDao getDao() {
        return rolePermissionDao;
    }

}
