package top.fosin.anan.platform.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.fosin.anan.cloudresource.constant.RedisConstant;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.platform.dto.req.AnanUserPermissionCreateDto;
import top.fosin.anan.platform.dto.res.AnanUserPermissionRespDto;
import top.fosin.anan.platform.repository.UserPermissionRepository;
import top.fosin.anan.platform.service.inter.UserPermissionService;

import java.util.Collection;
import java.util.List;

/**
 * @author fosin
 * @date 2017/12/29
 */
@Service
@Lazy
public class UserPermissionServiceImpl implements UserPermissionService {
    private final UserPermissionRepository userPermissionRepository;

    public UserPermissionServiceImpl(UserPermissionRepository userPermissionRepository) {
        this.userPermissionRepository = userPermissionRepository;
    }

    @Override
    public List<AnanUserPermissionRespDto> findByUserIdAndOrganizId(Long userId, Long organizId) {
        return BeanUtil.copyCollectionProperties(
                userPermissionRepository.findByUserIdAndOrganizId(userId, organizId), AnanUserPermissionRespDto.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<AnanUserPermissionRespDto> findByUserId(Long userId) {
        return BeanUtil.copyCollectionProperties(
                userPermissionRepository.findByUserId(userId), AnanUserPermissionRespDto.class);
    }

    @Override
    public long countByPermissionId(Long permissionId) {
        return userPermissionRepository.countByPermissionId(permissionId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = RedisConstant.ANAN_USER_ALL_PERMISSIONS, key = "#userId")
    public Collection<AnanUserPermissionRespDto> updateInBatch(String deleteCol, Long userId, Collection<AnanUserPermissionCreateDto> dtos) {
        return UserPermissionService.super.updateInBatch(deleteCol, userId, dtos);
    }

    @Override
    public UserPermissionRepository getRepository() {
        return userPermissionRepository;
    }
}
