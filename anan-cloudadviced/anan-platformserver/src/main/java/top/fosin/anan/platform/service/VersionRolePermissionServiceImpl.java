package top.fosin.anan.platform.service;


import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import top.fosin.anan.cloudresource.constant.PlatformRedisConstant;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.platform.dto.req.AnanVersionRolePermissionReqDto;
import top.fosin.anan.platform.dto.res.AnanVersionRolePermissionRespDto;
import top.fosin.anan.platform.entity.AnanOrganizationAuthEntity;
import top.fosin.anan.platform.entity.AnanRoleEntity;
import top.fosin.anan.platform.entity.AnanRolePermissionEntity;
import top.fosin.anan.platform.entity.AnanVersionRolePermissionEntity;
import top.fosin.anan.platform.repository.*;
import top.fosin.anan.platform.service.inter.VersionRolePermissionService;
import top.fosin.anan.platform.util.PermissionUtil;
import top.fosin.anan.redis.cache.AnanCacheManger;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统版本角色权限系统版本角色权限表服务实现类
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Service
@Lazy
public class VersionRolePermissionServiceImpl implements VersionRolePermissionService {
    private final VersionRolePermissionRepository versionRolePermissionRepo;
    private final OrganizationAuthRepository organizationAuthRepo;
    private final VersionRoleRepository versionRoleRepo;
    private final RoleRepository roleRepository;
    private final RolePermissionRepository rolePermissionRepo;
    private final AnanCacheManger ananCacheManger;

    public VersionRolePermissionServiceImpl(VersionRolePermissionRepository versionRolePermissionRepo, OrganizationAuthRepository organizationAuthRepo, VersionRoleRepository versionRoleRepo, RoleRepository roleRepository, RolePermissionRepository rolePermissionRepo, AnanCacheManger ananCacheManger) {
        this.versionRolePermissionRepo = versionRolePermissionRepo;
        this.organizationAuthRepo = organizationAuthRepo;
        this.versionRoleRepo = versionRoleRepo;
        this.roleRepository = roleRepository;
        this.rolePermissionRepo = rolePermissionRepo;
        this.ananCacheManger = ananCacheManger;
    }

    /**
     * 获取DAOs
     */
    @Override
    public VersionRolePermissionRepository getRepository() {
        return versionRolePermissionRepo;
    }

    @Override
    public List<AnanVersionRolePermissionRespDto> findByRoleId(Long roleId) {
        return BeanUtil.copyCollectionProperties(getRepository().findByRoleId(roleId), AnanVersionRolePermissionRespDto.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<AnanVersionRolePermissionRespDto> updateInBatch(String deleteCol, Long roleId, Collection<AnanVersionRolePermissionReqDto> dtos) {
        Assert.isTrue(roleId != null && dtos.size() > 0., "传入的版本ID或entities不能为空!");
        Assert.isTrue(dtos.stream().allMatch(entity -> entity.getRoleId().equals(roleId)), "需要更新的数据集中有与版本ID不匹配的数据!");
        versionRoleRepo.findById(roleId).ifPresent(versionRoleEntity -> {
            Long versionId = versionRoleEntity.getVersionId();
            String roleValue = versionRoleEntity.getValue();

            List<AnanOrganizationAuthEntity> organizationAuthEntities = organizationAuthRepo.findAllByVersionId(versionId);
            organizationAuthEntities.forEach(authEntity -> {
                Long organizId = authEntity.getOrganizId();

                //角色权限同步
                AnanRoleEntity role = roleRepository.findByOrganizIdAndValue(organizId, roleValue);
                Long roleId1 = role.getId();
                List<AnanRolePermissionEntity> afterRolePermissions = dtos.stream().map(permission -> {
                    AnanRolePermissionEntity entity = new AnanRolePermissionEntity();
                    entity.setRoleId(roleId1);
                    entity.setPermissionId(permission.getPermissionId());
                    return entity;
                }).collect(Collectors.toList());
                List<AnanRolePermissionEntity> rolePermissions = rolePermissionRepo.findByRoleId(roleId1);
                PermissionUtil.deletePermission(rolePermissions, afterRolePermissions,
                        rolePermissionRepo);
                PermissionUtil.saveNewPermission(rolePermissions, afterRolePermissions, rolePermissionRepo);
            });
            ananCacheManger.clear(PlatformRedisConstant.ANAN_USER_ALL_PERMISSIONS);

            //同步版本角色权限
            List<AnanVersionRolePermissionEntity> beforeVersionRolePermissions = versionRolePermissionRepo.findByRoleId(roleId);
            List<AnanVersionRolePermissionEntity> afterVersionRolePermissions = dtos.stream().map(dto -> {
                AnanVersionRolePermissionEntity entity = new AnanVersionRolePermissionEntity();
                entity.setRoleId(roleId);
                entity.setPermissionId(dto.getPermissionId());
                return entity;
            }).collect(Collectors.toList());
            PermissionUtil.deletePermission(beforeVersionRolePermissions, afterVersionRolePermissions,
                    versionRolePermissionRepo);
            PermissionUtil.saveNewPermission(beforeVersionRolePermissions, afterVersionRolePermissions, versionRolePermissionRepo);
        });

        return BeanUtil.copyCollectionProperties
                (dtos, AnanVersionRolePermissionRespDto.class);
    }
}
