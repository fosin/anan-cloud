package top.fosin.anan.platform.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import top.fosin.anan.platform.dto.res.AnanVersionPermissionRespDto;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.platform.dto.req.AnanVersionPermissionCreateDto;
import top.fosin.anan.platform.entity.*;
import top.fosin.anan.platform.repository.*;
import top.fosin.anan.platform.service.inter.AnanVersionPermissionService;

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
public class AnanVersionPermissionServiceImpl implements AnanVersionPermissionService {
    private final AnanVersionPermissionRepository versionPermissionRepository;
    private final AnanVersionRolePermissionRepository versionRolePermissionRepository;
    private final AnanVersionRoleRepository versionRoleRepository;
    private final RoleRepository roleRepository;
    private final RolePermissionRepository rolePermissionRepository;
    private final UserPermissionRepository userPermissionRepository;
    private final AnanOrganizationAuthRepository organizationAuthRepository;
    private final AnanOrganizationPermissionRepository organizationPermissionRepository;

    public AnanVersionPermissionServiceImpl(AnanVersionPermissionRepository versionPermissionRepository,
                                            AnanVersionRolePermissionRepository versionRolePermissionRepository,
                                            AnanVersionRoleRepository versionRoleRepository,
                                            RoleRepository roleRepository,
                                            RolePermissionRepository rolePermissionRepository,
                                            UserPermissionRepository userPermissionRepository,
                                            AnanOrganizationAuthRepository organizationAuthRepository,
                                            AnanOrganizationPermissionRepository organizationPermissionRepository) {
        this.versionPermissionRepository = versionPermissionRepository;
        this.versionRolePermissionRepository = versionRolePermissionRepository;
        this.versionRoleRepository = versionRoleRepository;
        this.roleRepository = roleRepository;
        this.rolePermissionRepository = rolePermissionRepository;
        this.userPermissionRepository = userPermissionRepository;
        this.organizationAuthRepository = organizationAuthRepository;
        this.organizationPermissionRepository = organizationPermissionRepository;
    }

    /**
     * 获取DAO
     */
    @Override
    public AnanVersionPermissionRepository getRepository() {
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

        List<AnanOrganizationAuthEntity> organizationAuthEntities = organizationAuthRepository.findAllByVersionId(versionId);
        organizationAuthEntities.forEach(authEntity -> {
            Long organizId = authEntity.getOrganizId();

            //同步删除角色权限中删除的版本权限
            List<AnanRoleEntity> roleEntities = roleRepository.findAllByOrganizId(organizId);
            roleEntities.forEach(role -> {
                Long roleId = role.getId();
                List<AnanRolePermissionEntity> rolePermissionEntities = rolePermissionRepository.findByRoleId(roleId);
                rolePermissionEntities.forEach(rolePermission -> {
                    boolean find = versionPermissionEntities.stream().anyMatch(entity -> entity.getPermissionId().equals(rolePermission.getPermissionId()));
                    if (!find) {
                        rolePermissionRepository.deleteById(rolePermission.getId());
                    }
                });
            });

            //同步删除用户权限中删除的版本权限
            List<AnanUserPermissionEntity> userEntities = userPermissionRepository.findByOrganizId(organizId);
            userEntities.forEach(userPermission -> {
                boolean find = versionPermissionEntities.stream().anyMatch(entity -> entity.getPermissionId().equals(userPermission.getPermissionId()));
                if (!find) {
                    userPermissionRepository.deleteById(userPermission.getId());
                }
            });

            //重建机构权限
            Collection<AnanOrganizationPermissionEntity> organizationPermissionEntities = versionPermissionEntities.stream().map(versionPermissionEntity -> {
                AnanOrganizationPermissionEntity entity = new AnanOrganizationPermissionEntity();
                entity.setOrganizId(organizId);
                entity.setPermissionId(versionPermissionEntity.getPermissionId());
                return entity;
            }).collect(Collectors.toList());
            organizationPermissionRepository.deleteByOrganizId(organizId);
            organizationPermissionRepository.saveAll(organizationPermissionEntities);

        });

        //同步删除版本角色权限中删除的版本权限
        List<AnanVersionRoleEntity> versionRoleEntities = versionRoleRepository.findByVersionId(versionId);
        versionRoleEntities.forEach(versionRoleEntity -> {
            Long versionRoleId = versionRoleEntity.getId();
            List<AnanVersionRolePermissionEntity> versionRolePermissionEntities = versionRolePermissionRepository.findByRoleId(versionRoleId);
            versionRolePermissionEntities.forEach(versionRolePermissionEntity -> {
                boolean find = versionPermissionEntities.stream().anyMatch(entity -> entity.getPermissionId().equals(versionRolePermissionEntity.getPermissionId()));
                if (!find) {
                    versionRolePermissionRepository.deleteById(versionRolePermissionEntity.getId());
                }
            });
        });

        versionPermissionRepository.deleteByVersionId(versionId);

        return BeanUtil.copyCollectionProperties(versionPermissionRepository.saveAll(versionPermissionEntities), AnanVersionPermissionRespDto.class);
    }

}
