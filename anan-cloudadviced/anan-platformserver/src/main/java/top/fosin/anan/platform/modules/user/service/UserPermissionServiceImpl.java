package top.fosin.anan.platform.modules.user.service;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import top.fosin.anan.cloudresource.constant.PlatformRedisConstant;
import top.fosin.anan.platform.modules.user.dao.UserPermissionDao;
import top.fosin.anan.platform.modules.user.dto.UserPermissionDTO;
import top.fosin.anan.platform.modules.user.dto.UserPermissionUpdateDTO;
import top.fosin.anan.platform.modules.user.service.inter.UserPermissionService;

import java.util.Collection;

/**
 * @author fosin
 * @date 2017/12/29
 */
@Service
@Lazy
@AllArgsConstructor
public class UserPermissionServiceImpl implements UserPermissionService {
    private final UserPermissionDao userPermissionRepo;

    @Override
    @Caching(evict = {
            @CacheEvict(value = PlatformRedisConstant.ANAN_USER_ALL_PERMISSIONS, key = "#userId"),
            @CacheEvict(value = PlatformRedisConstant.ANAN_USER_PERMISSION_TREE, key = "#userId")
    })
    public void postProcessInBatch(Long userId, Collection<UserPermissionUpdateDTO> userPermissionUpdateDTOS, Collection<UserPermissionDTO> userPermissionDTOS, boolean... processAction) {
        UserPermissionService.super.postProcessInBatch(userId, userPermissionUpdateDTOS, userPermissionDTOS, processAction);
    }

    @Override
    public UserPermissionDao getDao() {
        return userPermissionRepo;
    }
}
