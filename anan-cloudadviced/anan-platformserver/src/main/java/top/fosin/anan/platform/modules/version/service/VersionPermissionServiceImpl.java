package top.fosin.anan.platform.modules.version.service;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import top.fosin.anan.cloudresource.constant.PlatformRedisConstant;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.platform.modules.organization.dao.OrgAuthDao;
import top.fosin.anan.platform.modules.organization.dao.OrgPermissionDao;
import top.fosin.anan.platform.modules.organization.po.OrganizationAuth;
import top.fosin.anan.platform.modules.organization.po.OrganizationPermission;
import top.fosin.anan.platform.modules.role.dao.RoleDao;
import top.fosin.anan.platform.modules.role.dao.RolePermissionDao;
import top.fosin.anan.platform.modules.role.po.Role;
import top.fosin.anan.platform.modules.role.po.RolePermission;
import top.fosin.anan.platform.modules.user.dao.UserPermissionDao;
import top.fosin.anan.platform.modules.user.po.UserPermission;
import top.fosin.anan.platform.modules.version.dao.VersionPermissionDao;
import top.fosin.anan.platform.modules.version.dao.VersionRoleDao;
import top.fosin.anan.platform.modules.version.dao.VersionRolePermissionDao;
import top.fosin.anan.platform.modules.version.dto.VersionPermissionReqDto;
import top.fosin.anan.platform.modules.version.dto.VersionPermissionRespDto;
import top.fosin.anan.platform.modules.version.po.VersionPermission;
import top.fosin.anan.platform.modules.version.po.VersionRole;
import top.fosin.anan.platform.modules.version.po.VersionRolePermission;
import top.fosin.anan.platform.modules.version.service.inter.VersionPermissionService;
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
@AllArgsConstructor
public class VersionPermissionServiceImpl implements VersionPermissionService {
    private final VersionPermissionDao versionPermissionRepo;
    private final VersionRolePermissionDao versionRolePermissionRepo;
    private final VersionRoleDao versionRoleRepo;
    private final RoleDao roleDao;
    private final RolePermissionDao rolePermissionRepo;
    private final UserPermissionDao userPermissionRepo;
    private final OrgAuthDao orgAuthRepository;
    private final OrgPermissionDao orgPermissionRepo;
    private final AnanCacheManger ananCacheManger;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<VersionPermissionRespDto> processInBatch(Long versionId, Collection<VersionPermissionReqDto> entities, boolean... processAction) {
        Assert.isTrue(entities.stream().allMatch(entity -> entity.getVersionId().equals(versionId)), "需要更新的数据集中有与版本ID不匹配的数据!");

        Collection<VersionPermission> afterVersionPermissions = BeanUtil.copyProperties(entities, VersionPermission.class);

        List<OrganizationAuth> organizationAuthEntities = orgAuthRepository.findAllByVersionId(versionId);
        organizationAuthEntities.forEach(authEntity -> {
            Long organizId = authEntity.getOrganizId();

            //角色权限删除
            List<Role> roleEntities = roleDao.findAllByOrganizId(organizId);
            roleEntities.forEach(role -> {
                PermissionUtil.deletePermission(rolePermissionRepo.findByRoleId(role.getId()),
                        entities.stream().map(permission -> {
                            RolePermission entity = new RolePermission();
                            entity.setRoleId(role.getId());
                            entity.setPermissionId(permission.getPermissionId());
                            return entity;
                        }).collect(Collectors.toList()),
                        rolePermissionRepo);
            });

            //用户权限删除
            PermissionUtil.deletePermission(userPermissionRepo.findByOrganizId(organizId),
                    entities.stream().map(permission -> {
                        UserPermission entity = new UserPermission();
                        entity.setUserId(0L);
                        entity.setOrganizId(organizId);
                        entity.setAddMode(0);
                        entity.setPermissionId(permission.getPermissionId());
                        return entity;
                    }).collect(Collectors.toList()),
                    userPermissionRepo);

            //机构权限同步
            List<OrganizationPermission> orgPermissions = orgPermissionRepo.findByOrganizId(organizId);
            PermissionUtil.deletePermission(orgPermissions,
                    BeanUtil.copyProperties(entities, OrganizationPermission.class),
                    orgPermissionRepo);
            PermissionUtil.saveNewPermission(orgPermissions, afterVersionPermissions.stream().map(versionPermissionEntity -> {
                OrganizationPermission entity = new OrganizationPermission();
                entity.setOrganizId(organizId);
                entity.setPermissionId(versionPermissionEntity.getPermissionId());
                return entity;
            }).collect(Collectors.toList()), orgPermissionRepo);
        });
        ananCacheManger.clear(PlatformRedisConstant.ANAN_USER_ALL_PERMISSIONS);

        //版本角色权限删除
        List<VersionRole> versionRoles = versionRoleRepo.findByVersionId(versionId);
        versionRoles.forEach(versionRole -> {
            PermissionUtil.deletePermission(versionRolePermissionRepo.findByRoleId(versionRole.getId()),
                    BeanUtil.copyProperties(entities, VersionRolePermission.class),
                    versionRolePermissionRepo);
        });

        //版本权限同步
        List<VersionPermission> beforeVersionPermissions = versionPermissionRepo.findByVersionId(versionId);
        PermissionUtil.deletePermission(beforeVersionPermissions, afterVersionPermissions, versionPermissionRepo);
        PermissionUtil.saveNewPermission(beforeVersionPermissions, afterVersionPermissions, versionPermissionRepo);

        return BeanUtil.copyProperties(afterVersionPermissions, VersionPermissionRespDto.class);
    }

    /**
     * 获取DAO
     */
    @Override
    public VersionPermissionDao getDao() {
        return versionPermissionRepo;
    }
}
