package top.fosin.anan.platform.modules.version.service;


import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import top.fosin.anan.cloudresource.constant.PlatformRedisConstant;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.platform.modules.version.dto.VersionRolePermissionReqDto;
import top.fosin.anan.platform.modules.version.dto.VersionRolePermissionRespDto;
import top.fosin.anan.platform.modules.organization.entity.OrganizationAuth;
import top.fosin.anan.platform.modules.role.entity.Role;
import top.fosin.anan.platform.modules.role.entity.RolePermission;
import top.fosin.anan.platform.modules.version.entity.VersionRolePermission;
import top.fosin.anan.platform.modules.organization.dao.OrganizationAuthDao;
import top.fosin.anan.platform.modules.role.dao.RolePermissionDao;
import top.fosin.anan.platform.modules.role.dao.RoleDao;
import top.fosin.anan.platform.modules.version.dao.VersionRolePermissionDao;
import top.fosin.anan.platform.modules.version.dao.VersionRoleDao;
import top.fosin.anan.platform.modules.version.service.inter.VersionRolePermissionService;
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
    private final VersionRolePermissionDao versionRolePermissionRepo;
    private final OrganizationAuthDao organizationAuthRepo;
    private final VersionRoleDao versionRoleRepo;
    private final RoleDao roleDao;
    private final RolePermissionDao rolePermissionRepo;
    private final AnanCacheManger ananCacheManger;

    public VersionRolePermissionServiceImpl(VersionRolePermissionDao versionRolePermissionRepo, OrganizationAuthDao organizationAuthRepo, VersionRoleDao versionRoleRepo, RoleDao roleDao, RolePermissionDao rolePermissionRepo, AnanCacheManger ananCacheManger) {
        this.versionRolePermissionRepo = versionRolePermissionRepo;
        this.organizationAuthRepo = organizationAuthRepo;
        this.versionRoleRepo = versionRoleRepo;
        this.roleDao = roleDao;
        this.rolePermissionRepo = rolePermissionRepo;
        this.ananCacheManger = ananCacheManger;
    }

    /**
     * 获取DAOs
     */
    @Override
    public VersionRolePermissionDao getRepository() {
        return versionRolePermissionRepo;
    }

    @Override
    public List<VersionRolePermissionRespDto> findByRoleId(Long roleId) {
        return BeanUtil.copyProperties(getRepository().findByRoleId(roleId), VersionRolePermissionRespDto.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<VersionRolePermissionRespDto> updateInBatch(String deleteCol, Long roleId, Collection<VersionRolePermissionReqDto> dtos) {
        Assert.isTrue(roleId != null && dtos.size() > 0., "传入的版本ID或entities不能为空!");
        Assert.isTrue(dtos.stream().allMatch(entity -> entity.getRoleId().equals(roleId)), "需要更新的数据集中有与版本ID不匹配的数据!");
        versionRoleRepo.findById(roleId).ifPresent(versionRoleEntity -> {
            Long versionId = versionRoleEntity.getVersionId();
            String roleValue = versionRoleEntity.getValue();

            List<OrganizationAuth> organizationAuthEntities = organizationAuthRepo.findAllByVersionId(versionId);
            organizationAuthEntities.forEach(authEntity -> {
                Long organizId = authEntity.getOrganizId();

                //角色权限同步
                Role role = roleDao.findByOrganizIdAndValue(organizId, roleValue);
                Long roleId1 = role.getId();
                List<RolePermission> afterRolePermissions = dtos.stream().map(permission -> {
                    RolePermission entity = new RolePermission();
                    entity.setRoleId(roleId1);
                    entity.setPermissionId(permission.getPermissionId());
                    return entity;
                }).collect(Collectors.toList());
                List<RolePermission> rolePermissions = rolePermissionRepo.findByRoleId(roleId1);
                PermissionUtil.deletePermission(rolePermissions, afterRolePermissions,
                        rolePermissionRepo);
                PermissionUtil.saveNewPermission(rolePermissions, afterRolePermissions, rolePermissionRepo);
            });
            ananCacheManger.clear(PlatformRedisConstant.ANAN_USER_ALL_PERMISSIONS);

            //同步版本角色权限
            List<VersionRolePermission> beforeVersionRolePermissions = versionRolePermissionRepo.findByRoleId(roleId);
            List<VersionRolePermission> afterVersionRolePermissions = dtos.stream().map(dto -> {
                VersionRolePermission entity = new VersionRolePermission();
                entity.setRoleId(roleId);
                entity.setPermissionId(dto.getPermissionId());
                return entity;
            }).collect(Collectors.toList());
            PermissionUtil.deletePermission(beforeVersionRolePermissions, afterVersionRolePermissions,
                    versionRolePermissionRepo);
            PermissionUtil.saveNewPermission(beforeVersionRolePermissions, afterVersionRolePermissions, versionRolePermissionRepo);
        });

        return BeanUtil.copyProperties
                (dtos, VersionRolePermissionRespDto.class);
    }
}
