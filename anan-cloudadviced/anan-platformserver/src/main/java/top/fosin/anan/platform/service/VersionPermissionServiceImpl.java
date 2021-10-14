package top.fosin.anan.platform.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.platform.dto.req.AnanVersionPermissionCreateDto;
import top.fosin.anan.platform.dto.res.AnanVersionPermissionRespDto;
import top.fosin.anan.platform.entity.*;
import top.fosin.anan.platform.repository.*;
import top.fosin.anan.platform.service.inter.VersionPermissionService;

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
    private final VersionPermissionRepository versionPermissionRepository;
    private final VersionRolePermissionRepository versionRolePermissionRepository;
    private final VersionRoleRepository versionRoleRepository;
    private final RoleRepository roleRepository;
    private final RolePermissionRepository rolePermissionRepository;
    private final UserPermissionRepository userPermissionRepository;
    private final OrganizationAuthRepository orgAuthRepository;
    private final OrganizationPermissionRepository orgPermissionRepository;

    public VersionPermissionServiceImpl(VersionPermissionRepository versionPermissionRepository,
                                        VersionRolePermissionRepository versionRolePermissionRepository,
                                        VersionRoleRepository versionRoleRepository,
                                        RoleRepository roleRepository,
                                        RolePermissionRepository rolePermissionRepository,
                                        UserPermissionRepository userPermissionRepository,
                                        OrganizationAuthRepository orgAuthRepository,
                                        OrganizationPermissionRepository orgPermissionRepository) {
        this.versionPermissionRepository = versionPermissionRepository;
        this.versionRolePermissionRepository = versionRolePermissionRepository;
        this.versionRoleRepository = versionRoleRepository;
        this.roleRepository = roleRepository;
        this.rolePermissionRepository = rolePermissionRepository;
        this.userPermissionRepository = userPermissionRepository;
        this.orgAuthRepository = orgAuthRepository;
        this.orgPermissionRepository = orgPermissionRepository;
    }

    /**
     * 获取DAO
     */
    @Override
    public VersionPermissionRepository getRepository() {
        return versionPermissionRepository;
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

        Collection<AnanVersionPermissionEntity> versionPermissionEntities = BeanUtil.copyCollectionProperties(entities, AnanVersionPermissionEntity.class);

        List<AnanOrganizationAuthEntity> organizationAuthEntities = orgAuthRepository.findAllByVersionId(versionId);
        organizationAuthEntities.forEach(authEntity -> {
            Long organizId = authEntity.getOrganizId();

            //同步删除角色权限中删除的版本权限
            List<AnanRoleEntity> roleEntities = roleRepository.findAllByOrganizId(organizId);
            roleEntities.forEach(role -> {
                List<AnanRolePermissionEntity> entityList = rolePermissionRepository.findByRoleId(role.getId())
                        .stream().filter(rolePermission -> versionPermissionEntities.stream()
                                .anyMatch(entity -> entity.getPermissionId().equals(rolePermission.getPermissionId())))
                        .collect(Collectors.toList());
                if (entityList.size() > 0) {
                    rolePermissionRepository.deleteAll(entityList);
                }
            });

            //同步删除用户权限中删除的版本权限
            List<AnanUserPermissionEntity> userEntities =
                    userPermissionRepository.findByOrganizId(organizId).stream().filter(userPermission -> versionPermissionEntities.stream().anyMatch(entity -> entity.getPermissionId().equals(userPermission.getPermissionId()))).collect(Collectors.toList());
            if (userEntities.size() > 0) {
                userPermissionRepository.deleteAll(userEntities);
            }

            //重建机构权限
            Collection<AnanOrganizationPermissionEntity> organizationPermissionEntities = versionPermissionEntities.stream().map(versionPermissionEntity -> {
                AnanOrganizationPermissionEntity entity = new AnanOrganizationPermissionEntity();
                entity.setOrganizId(organizId);
                entity.setPermissionId(versionPermissionEntity.getPermissionId());
                return entity;
            }).collect(Collectors.toList());
            orgPermissionRepository.deleteByOrganizId(organizId);
            orgPermissionRepository.saveAll(organizationPermissionEntities);

        });

        //同步删除版本角色权限中删除的版本权限
        List<AnanVersionRoleEntity> versionRoleEntities = versionRoleRepository.findByVersionId(versionId);
        versionRoleEntities.forEach(versionRoleEntity -> {
            List<AnanVersionRolePermissionEntity> entityList = versionRolePermissionRepository.findByRoleId(versionRoleEntity.getId()).stream().filter(permissionEntity -> versionPermissionEntities.stream().anyMatch(entity -> entity.getPermissionId().equals(permissionEntity.getPermissionId()))).collect(Collectors.toList());
            if (entityList.size() > 0) {
                versionRolePermissionRepository.deleteAll(entityList);
            }
        });

        versionPermissionRepository.deleteByVersionId(versionId);

        return BeanUtil.copyCollectionProperties(versionPermissionRepository.saveAll(versionPermissionEntities), AnanVersionPermissionRespDto.class);
    }

}
