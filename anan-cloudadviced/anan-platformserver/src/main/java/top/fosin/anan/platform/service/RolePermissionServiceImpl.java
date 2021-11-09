package top.fosin.anan.platform.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import top.fosin.anan.cloudresource.constant.PlatformRedisConstant;
import top.fosin.anan.cloudresource.dto.res.AnanRolePermissionRespDto;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.platform.dto.req.AnanRolePermissionCreateDto;
import top.fosin.anan.platform.entity.AnanRolePermissionEntity;
import top.fosin.anan.platform.repository.RolePermissionRepository;
import top.fosin.anan.platform.service.inter.RolePermissionService;
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
public class RolePermissionServiceImpl implements RolePermissionService {
    private final RolePermissionRepository rolePermissionRepo;

    public RolePermissionServiceImpl(RolePermissionRepository rolePermissionRepo) {
        this.rolePermissionRepo = rolePermissionRepo;
    }

    @Override
    public List<AnanRolePermissionEntity> findByRoleId(Long roleId) {
        return rolePermissionRepo.findByRoleId(roleId);
    }

    @Override
    public long countByPermissionId(Long permissionId) {
        return rolePermissionRepo.countByPermissionId(permissionId);
    }

    /**
     * 根据实体集合批量删除
     *
     * @param deleteCol 实体类中属性名称，
     * @param aLong     属性值
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteInBatch(String deleteCol, Long aLong) {
        RolePermissionService.super.deleteInBatch(deleteCol, aLong);
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = PlatformRedisConstant.ANAN_USER_ALL_PERMISSIONS, allEntries = true),
                    @CacheEvict(value = PlatformRedisConstant.ANAN_USER_PERMISSION_TREE, allEntries = true)

            })
    @Transactional(rollbackFor = Exception.class)
    public List<AnanRolePermissionRespDto> updateInBatch(String deleteCol, Long roleId, Collection<AnanRolePermissionCreateDto> dtos) {
        Assert.notNull(roleId, "传入的角色ID不能为空!");
        Assert.isTrue(dtos.stream().allMatch(entity -> entity.getRoleId().equals(roleId)), "需要更新的数据集中有与用户ID不匹配的数据!");
        List<AnanRolePermissionEntity> afterRolePermissions = dtos.stream().map(permission -> {
            AnanRolePermissionEntity entity = new AnanRolePermissionEntity();
            entity.setRoleId(roleId);
            entity.setPermissionId(permission.getPermissionId());
            return entity;
        }).collect(Collectors.toList());
        List<AnanRolePermissionEntity> rolePermissions = rolePermissionRepo.findByRoleId(roleId);
        PermissionUtil.deletePermission(rolePermissions, afterRolePermissions, rolePermissionRepo);
        PermissionUtil.saveNewPermission(rolePermissions, afterRolePermissions, rolePermissionRepo);

        return BeanUtil.copyCollectionProperties(afterRolePermissions, AnanRolePermissionRespDto.class);
    }

    @Override
    public RolePermissionRepository getRepository() {
        return rolePermissionRepo;
    }
}
