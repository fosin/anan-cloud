package top.fosin.anan.platform.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import top.fosin.anan.cloudresource.constant.RedisConstant;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.platform.dto.req.AnanVersionPermissionCreateDto;
import top.fosin.anan.platform.dto.res.AnanVersionPermissionRespDto;
import top.fosin.anan.platform.entity.*;
import top.fosin.anan.platform.repository.*;
import top.fosin.anan.platform.service.inter.VersionPermissionService;
import top.fosin.anan.platform.util.PermissionUtil;
import top.fosin.anan.redis.cache.AnanCacheManger;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统版本权限系统版本权限表服务实现类
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Service
@Lazy
public class VersionPermissionServiceImpl implements VersionPermissionService {
    private final VersionPermissionRepository versionPermissionRepo;
    private final VersionRolePermissionRepository versionRolePermissionRepo;
    private final VersionRoleRepository versionRoleRepo;
    private final RoleRepository roleRepository;
    private final RolePermissionRepository rolePermissionRepo;
    private final UserPermissionRepository userPermissionRepo;
    private final OrganizationAuthRepository orgAuthRepository;
    private final OrganizationPermissionRepository orgPermissionRepo;
    private final AnanCacheManger ananCacheManger;

    public VersionPermissionServiceImpl(VersionPermissionRepository versionPermissionRepo,
                                        VersionRolePermissionRepository versionRolePermissionRepo,
                                        VersionRoleRepository versionRoleRepo,
                                        RoleRepository roleRepository,
                                        RolePermissionRepository rolePermissionRepo,
                                        UserPermissionRepository userPermissionRepo,
                                        OrganizationAuthRepository orgAuthRepository,
                                        OrganizationPermissionRepository orgPermissionRepo, AnanCacheManger ananCacheManger) {
        this.versionPermissionRepo = versionPermissionRepo;
        this.versionRolePermissionRepo = versionRolePermissionRepo;
        this.versionRoleRepo = versionRoleRepo;
        this.roleRepository = roleRepository;
        this.rolePermissionRepo = rolePermissionRepo;
        this.userPermissionRepo = userPermissionRepo;
        this.orgAuthRepository = orgAuthRepository;
        this.orgPermissionRepo = orgPermissionRepo;
        this.ananCacheManger = ananCacheManger;
    }

    /**
     * 获取DAO
     */
    @Override
    public VersionPermissionRepository getRepository() {
        return versionPermissionRepo;
    }

    @Override
    public List<AnanVersionPermissionRespDto> findByVersionId(Long versionId) {
        return BeanUtil.copyCollectionProperties(getRepository().findByVersionId(versionId), AnanVersionPermissionRespDto.class);
    }

    @Override
    public long countByPermissionId(Long permissionId) {
        return getRepository().countByPermissionId(permissionId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Collection<AnanVersionPermissionRespDto> updateInBatch(String deleteCol, Long versionId, Collection<AnanVersionPermissionCreateDto> entities) {

        Assert.notNull(versionId, "传入的版本ID不能为空!");
        Assert.isTrue(entities.stream().allMatch(entity -> entity.getVersionId().equals(versionId)), "需要更新的数据集中有与版本ID不匹配的数据!");

        Collection<AnanVersionPermissionEntity> afterVersionPermissions = BeanUtil.copyCollectionProperties(entities, AnanVersionPermissionEntity.class);

        List<AnanOrganizationAuthEntity> organizationAuthEntities = orgAuthRepository.findAllByVersionId(versionId);
        organizationAuthEntities.forEach(authEntity -> {
            Long organizId = authEntity.getOrganizId();

            //角色权限删除
            List<AnanRoleEntity> roleEntities = roleRepository.findAllByOrganizId(organizId);
            roleEntities.forEach(role -> {
                PermissionUtil.deletePermission(rolePermissionRepo.findByRoleId(role.getId()),
                        entities.stream().map(permission -> {
                            AnanRolePermissionEntity entity = new AnanRolePermissionEntity();
                            entity.setRoleId(role.getId());
                            entity.setPermissionId(permission.getPermissionId());
                            return entity;
                        }).collect(Collectors.toList()),
                        rolePermissionRepo);
            });

            //用户权限删除
            PermissionUtil.deletePermission(userPermissionRepo.findByOrganizId(organizId),
                    entities.stream().map(permission -> {
                        AnanUserPermissionEntity entity = new AnanUserPermissionEntity();
                        entity.setUserId(0L);
                        entity.setOrganizId(organizId);
                        entity.setAddMode(0);
                        entity.setPermissionId(permission.getPermissionId());
                        return entity;
                    }).collect(Collectors.toList()),
                    userPermissionRepo);

            //机构权限同步
            List<AnanOrganizationPermissionEntity> orgPermissions = orgPermissionRepo.findByOrganizId(organizId);
            PermissionUtil.deletePermission(orgPermissions,
                    BeanUtil.copyCollectionProperties(entities, AnanOrganizationPermissionEntity.class),
                    orgPermissionRepo);
            PermissionUtil.saveNewPermission(orgPermissions, afterVersionPermissions.stream().map(versionPermissionEntity -> {
                AnanOrganizationPermissionEntity entity = new AnanOrganizationPermissionEntity();
                entity.setOrganizId(organizId);
                entity.setPermissionId(versionPermissionEntity.getPermissionId());
                return entity;
            }).collect(Collectors.toList()), orgPermissionRepo);
        });
        ananCacheManger.clear(RedisConstant.ANAN_USER_ALL_PERMISSIONS);

        //版本角色权限删除
        List<AnanVersionRoleEntity> versionRoles = versionRoleRepo.findByVersionId(versionId);
        versionRoles.forEach(versionRole -> {
            PermissionUtil.deletePermission(versionRolePermissionRepo.findByRoleId(versionRole.getId()),
                    BeanUtil.copyCollectionProperties(entities, AnanVersionRolePermissionEntity.class),
                    versionRolePermissionRepo);
        });

        //版本权限同步
        List<AnanVersionPermissionEntity> beforeVersionPermissions = versionPermissionRepo.findByVersionId(versionId);
        PermissionUtil.deletePermission(beforeVersionPermissions, afterVersionPermissions, versionPermissionRepo);
        PermissionUtil.saveNewPermission(beforeVersionPermissions, afterVersionPermissions, versionPermissionRepo);

        return BeanUtil.copyCollectionProperties(afterVersionPermissions, AnanVersionPermissionRespDto.class);
    }
}
