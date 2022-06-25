package top.fosin.anan.platform.modules.user.service;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.fosin.anan.cloudresource.constant.PlatformRedisConstant;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.platform.modules.user.dao.UserPermissionDao;
import top.fosin.anan.platform.modules.user.dto.UserPermissionReqDto;
import top.fosin.anan.platform.modules.user.dto.UserPermissionRespDto;
import top.fosin.anan.platform.modules.user.service.inter.UserPermissionService;

import java.util.Collection;
import java.util.List;

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
    public List<UserPermissionRespDto> findByUserIdAndOrganizId(Long userId, Long organizId) {
        return BeanUtil.copyProperties(
                userPermissionRepo.findByUserIdAndOrganizId(userId, organizId), UserPermissionRespDto.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Caching(evict = {
            @CacheEvict(value = PlatformRedisConstant.ANAN_USER_ALL_PERMISSIONS, key = "#userId"),
            @CacheEvict(value = PlatformRedisConstant.ANAN_USER_PERMISSION_TREE, key = "#userId")
    })
    public List<UserPermissionRespDto> processInBatch(Long userId, Collection<UserPermissionReqDto> dtos, boolean... processAction) {
        return UserPermissionService.super.processInBatch(userId, dtos, processAction);
    }

    @Override
    public UserPermissionDao getDao() {
        return userPermissionRepo;
    }
}
