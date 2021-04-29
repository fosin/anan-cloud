package top.fosin.anan.platform.service;

import top.fosin.anan.cloudresource.constant.RedisConstant;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.jpa.service.batch.IUpdateInBatchJpaService;
import top.fosin.anan.platform.dto.request.AnanVersionRolePermissionUpdateDto;
import top.fosin.anan.platform.entity.AnanOrganizationAuthEntity;
import top.fosin.anan.platform.entity.AnanVersionRoleEntity;
import top.fosin.anan.platform.entity.AnanVersionRolePermissionEntity;
import top.fosin.anan.platform.repository.AnanOrganizationAuthRepository;
import top.fosin.anan.platform.repository.AnanVersionRolePermissionRepository;
import top.fosin.anan.platform.repository.AnanVersionRoleRepository;
import top.fosin.anan.platform.repository.RoleRepository;
import top.fosin.anan.platform.service.inter.AnanVersionRolePermissionService;
import top.fosin.anan.platformapi.entity.AnanRoleEntity;
import top.fosin.anan.platformapi.entity.AnanRolePermissionEntity;
import top.fosin.anan.platformapi.repository.RolePermissionRepository;
import top.fosin.anan.redis.cache.AnanCacheManger;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统版本角色权限表(anan_version_role_permission)表服务实现类
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Service
@Lazy
public class AnanVersionRolePermissionServiceImpl implements AnanVersionRolePermissionService {
    private final AnanVersionRolePermissionRepository versionRolePermissionRepository;
    private final AnanOrganizationAuthRepository organizationAuthRepository;
    private final AnanVersionRoleRepository versionRoleRepository;
    private final RoleRepository roleRepository;
    private final RolePermissionRepository rolePermissionRepository;
    private final AnanCacheManger ananCacheManger;

    public AnanVersionRolePermissionServiceImpl(AnanVersionRolePermissionRepository versionRolePermissionRepository, AnanOrganizationAuthRepository organizationAuthRepository, AnanVersionRoleRepository versionRoleRepository, RoleRepository roleRepository, RolePermissionRepository rolePermissionRepository, AnanCacheManger ananCacheManger) {
        this.versionRolePermissionRepository = versionRolePermissionRepository;
        this.organizationAuthRepository = organizationAuthRepository;
        this.versionRoleRepository = versionRoleRepository;
        this.roleRepository = roleRepository;
        this.rolePermissionRepository = rolePermissionRepository;
        this.ananCacheManger = ananCacheManger;
    }

    /**
     * 获取DAOs
     */
    @Override
    public AnanVersionRolePermissionRepository getRepository() {
        return versionRolePermissionRepository;
    }

    @Override
    public List<AnanVersionRolePermissionEntity> findByRoleId(Long roleId) {
        return getRepository().findByRoleId(roleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<AnanVersionRolePermissionEntity> updateInBatch(Long roleId, Collection<AnanVersionRolePermissionUpdateDto> entities) {

        Assert.isTrue(roleId != null && entities.size() > 0., "传入的版本ID或entities不能为空!");
        Assert.isTrue(entities.stream().allMatch(entity -> entity.getRoleId().equals(roleId)), "需要更新的数据集中有与版本ID不匹配的数据!");
        AnanVersionRoleEntity versionRoleEntity = versionRoleRepository.findById(roleId).orElse(null);
        Long versionId = versionRoleEntity.getVersionId();
        String roleValue = versionRoleEntity.getValue();

        List<AnanOrganizationAuthEntity> organizationAuthEntities = organizationAuthRepository.findAllByVersionId(versionId);
        organizationAuthEntities.forEach(authEntity -> {
            Long organizId = authEntity.getOrganizId();

            AnanRoleEntity role = roleRepository.findByOrganizIdAndValue(organizId, roleValue);
            Long roleId1 = role.getId();
            Collection<AnanRolePermissionEntity> rolePermissionEntities = entities.stream().map(versionPermissionEntity -> {
                AnanRolePermissionEntity entity = new AnanRolePermissionEntity();
                entity.setRoleId(roleId1);
                entity.setPermissionId(versionPermissionEntity.getPermissionId());
                return entity;
            }).collect(Collectors.toList());
            ananCacheManger.evict(RedisConstant.ANAN_ROLE_PERMISSION, roleId1 + "");

            rolePermissionRepository.deleteByRoleId(roleId1);
            rolePermissionRepository.saveAll(rolePermissionEntities);
        });

        ananCacheManger.clear(RedisConstant.ANAN_USER_ALL_PERMISSIONS);

        versionRolePermissionRepository.deleteByRoleId(roleId);

        Collection<AnanVersionRolePermissionEntity> saveEntities = BeanUtil.copyCollectionProperties(this.getClass(), IUpdateInBatchJpaService.class, entities);

        return versionRolePermissionRepository.saveAll(saveEntities);
    }
}
