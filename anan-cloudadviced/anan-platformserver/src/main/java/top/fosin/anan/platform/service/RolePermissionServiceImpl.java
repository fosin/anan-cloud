package top.fosin.anan.platform.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import top.fosin.anan.cloudresource.constant.RedisConstant;
import top.fosin.anan.platform.dto.req.AnanRolePermissionCreateDto;
import top.fosin.anan.cloudresource.dto.res.AnanRolePermissionRespDto;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.platform.entity.AnanRolePermissionEntity;
import top.fosin.anan.platform.repository.RolePermissionRepository;
import top.fosin.anan.platform.service.inter.RolePermissionService;
import top.fosin.anan.redis.cache.AnanCacheManger;

import java.util.Collection;
import java.util.List;

/**
 * 2017/12/29.
 * Time:12:38
 *
 * @author fosin
 */
@Service
@Lazy
public class RolePermissionServiceImpl implements RolePermissionService {
    private final RolePermissionRepository rolePermissionRepository;

    public RolePermissionServiceImpl(RolePermissionRepository rolePermissionRepository, AnanCacheManger ananCacheManger) {
        this.rolePermissionRepository = rolePermissionRepository;
    }

    @Override
    @Cacheable(value = RedisConstant.ANAN_ROLE_PERMISSION, key = "#roleId")
    public List<AnanRolePermissionEntity> findByRoleId(Long roleId) {
        return rolePermissionRepository.findByRoleId(roleId);
    }

    @Override
    public long countByPermissionId(Long permissionId) {
        return rolePermissionRepository.countByPermissionId(permissionId);
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
                    @CacheEvict(value = RedisConstant.ANAN_ROLE_PERMISSION, key = "#roleId"),
                    @CacheEvict(value = RedisConstant.ANAN_USER_ALL_PERMISSIONS, allEntries = true)
            }
    )
    @Transactional(rollbackFor = Exception.class)
    public List<AnanRolePermissionRespDto> updateInBatch(String deleteCol, Long roleId, Collection<AnanRolePermissionCreateDto> dtos) {
        Assert.notNull(roleId, "传入的角色ID不能为空!");
        Assert.isTrue(dtos.stream().allMatch(entity -> entity.getRoleId().equals(roleId)), "需要更新的数据集中有与版本ID不匹配的数据!");
        Collection<AnanRolePermissionEntity> saveEntities = BeanUtil.copyCollectionProperties(dtos, AnanRolePermissionEntity.class);

        rolePermissionRepository.deleteByRoleId(roleId);
        return BeanUtil.copyCollectionProperties(rolePermissionRepository.saveAll(saveEntities), AnanRolePermissionRespDto.class);
    }

    @Override
    public RolePermissionRepository getRepository() {
        return rolePermissionRepository;
    }
}
