package top.fosin.anan.platform.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import top.fosin.anan.cloudresource.constant.PlatformRedisConstant;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.platform.dto.req.AnanUserPermissionReqDto;
import top.fosin.anan.platform.dto.res.AnanUserPermissionRespDto;
import top.fosin.anan.platform.entity.AnanUserPermissionEntity;
import top.fosin.anan.platform.repository.UserPermissionRepository;
import top.fosin.anan.platform.service.inter.UserPermissionService;
import top.fosin.anan.platform.util.PermissionUtil;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author fosin
 * @date 2017/12/29
 */
@Service
@Lazy
public class UserPermissionServiceImpl implements UserPermissionService {
    private final UserPermissionRepository userPermissionRepo;
    public UserPermissionServiceImpl(UserPermissionRepository userPermissionRepo) {
        this.userPermissionRepo = userPermissionRepo;
    }

    @Override
    public List<AnanUserPermissionRespDto> findByUserIdAndOrganizId(Long userId, Long organizId) {
        return BeanUtil.copyCollectionProperties(
                userPermissionRepo.findByUserIdAndOrganizId(userId, organizId), AnanUserPermissionRespDto.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<AnanUserPermissionRespDto> findByUserId(Long userId) {
        return BeanUtil.copyCollectionProperties(
                userPermissionRepo.findByUserId(userId), AnanUserPermissionRespDto.class);
    }

    @Override
    public long countByPermissionId(Long permissionId) {
        return userPermissionRepo.countByPermissionId(permissionId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Caching(
            evict = {
                    @CacheEvict(value = PlatformRedisConstant.ANAN_USER_ALL_PERMISSIONS, key = "#userId"),
                    @CacheEvict(value = PlatformRedisConstant.ANAN_USER_PERMISSION_TREE, key = "#userId")

            })
    public Collection<AnanUserPermissionRespDto> updateInBatch(String deleteCol, Long userId, Collection<AnanUserPermissionReqDto> dtos) {
        Assert.notNull(userId, "传入的用户ID不能为空!");
        Assert.isTrue(dtos.stream().allMatch(entity -> entity.getUserId().equals(userId)), "需要更新的数据集中有与用户ID不匹配的数据!");
        long organizId = dtos.stream().distinct().map(AnanUserPermissionReqDto::getOrganizId)
                .findFirst().orElseThrow(() -> new IllegalArgumentException("没有找打破有效的机构序号"));
        List<AnanUserPermissionEntity> after0Permissions =
                dtos.stream().filter(dto -> dto.getAddMode() == 0).map(permission -> {
                    AnanUserPermissionEntity entity = new AnanUserPermissionEntity();
                    entity.setUserId(userId);
                    entity.setPermissionId(permission.getPermissionId());
                    entity.setOrganizId(organizId);
                    entity.setAddMode(permission.getAddMode());
                    return entity;
                }).collect(Collectors.toList());

        List<AnanUserPermissionEntity> after1Permissions =
                dtos.stream().filter(dto -> dto.getAddMode() == 1).map(permission -> {
                    AnanUserPermissionEntity entity = new AnanUserPermissionEntity();
                    entity.setUserId(userId);
                    entity.setPermissionId(permission.getPermissionId());
                    entity.setOrganizId(organizId);
                    entity.setAddMode(permission.getAddMode());
                    return entity;
                }).collect(Collectors.toList());

        List<AnanUserPermissionEntity> beforePermissions =
                userPermissionRepo.findByUserIdAndOrganizIdAndAddMode(userId, organizId, 0);
        PermissionUtil.deletePermission(beforePermissions, after0Permissions, userPermissionRepo);
        PermissionUtil.saveNewPermission(beforePermissions, after0Permissions, userPermissionRepo);
        beforePermissions =
                userPermissionRepo.findByUserIdAndOrganizIdAndAddMode(userId, organizId, 1);
        PermissionUtil.deletePermission(beforePermissions, after1Permissions, userPermissionRepo);
        PermissionUtil.saveNewPermission(beforePermissions, after1Permissions, userPermissionRepo);

        return BeanUtil.copyCollectionProperties(dtos, AnanUserPermissionRespDto.class);
    }

    @Override
    public UserPermissionRepository getRepository() {
        return userPermissionRepo;
    }
}
