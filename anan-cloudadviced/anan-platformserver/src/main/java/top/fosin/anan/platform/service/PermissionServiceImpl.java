package top.fosin.anan.platform.service;

import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import top.fosin.anan.cloudresource.constant.PlatformRedisConstant;
import top.fosin.anan.cloudresource.dto.req.AnanPermissionReqDto;
import top.fosin.anan.cloudresource.dto.res.AnanPermissionRespDto;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.platform.entity.AnanPermissionEntity;
import top.fosin.anan.platform.entity.AnanServiceEntity;
import top.fosin.anan.platform.repository.*;
import top.fosin.anan.platform.service.inter.PermissionService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author fosin
 * @date 2017/12/29
 */
@Service
@Lazy
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepository permissionRepository;
    private final UserPermissionRepository userPermissionRepository;
    private final RolePermissionRepository rolePermissionRepository;
    private final VersionPermissionRepository versionPermissionRepository;
    private final VersionRolePermissionRepository versionRolePermissionRepository;
    private final OrganizationPermissionRepository organizationPermissionRepository;
    private final ServiceRepository serviceRepository;

    public PermissionServiceImpl(PermissionRepository permissionRepository,
                                 UserPermissionRepository userPermissionRepository,
                                 RolePermissionRepository rolePermissionRepository,
                                 VersionPermissionRepository versionPermissionRepository,
                                 VersionRolePermissionRepository versionRolePermissionRepository,
                                 OrganizationPermissionRepository organizationPermissionRepository,
                                 ServiceRepository serviceRepository) {
        this.permissionRepository = permissionRepository;
        this.userPermissionRepository = userPermissionRepository;
        this.rolePermissionRepository = rolePermissionRepository;
        this.versionPermissionRepository = versionPermissionRepository;
        this.versionRolePermissionRepository = versionRolePermissionRepository;
        this.organizationPermissionRepository = organizationPermissionRepository;
        this.serviceRepository = serviceRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AnanPermissionRespDto create(AnanPermissionReqDto dto) {
        Assert.notNull(dto, "传入的创建数据实体对象不能为空!");

        AnanPermissionEntity createEntity = BeanUtil.copyProperties(dto, AnanPermissionEntity.class);
        Long pid = dto.getPid();

        int level = 1;
        if (pid != 0) {
            AnanPermissionEntity parentEntity = permissionRepository.findById(pid).orElse(null);
            Assert.notNull(parentEntity, "传入的创建数据实体找不到对于的父节点数据!");
            level = parentEntity.getLevel() + 1;
        }
        createEntity.setLevel(level);
        return BeanUtil.copyProperties(permissionRepository.save(createEntity), AnanPermissionRespDto.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Caching(
            evict = {
                    @CacheEvict(value = PlatformRedisConstant.ANAN_USER_ALL_PERMISSIONS, allEntries = true),
                    @CacheEvict(value = PlatformRedisConstant.ANAN_USER_PERMISSION_TREE, allEntries = true)

            })
    public void update(AnanPermissionReqDto dto) {
        Long id = dto.getId();
        Assert.notNull(id, "传入了空ID!");

        AnanPermissionEntity updateEntity = permissionRepository.findById(id).orElse(null);

        List<AnanPermissionEntity> saveEntities = new ArrayList<>();
        //如果是目录菜单，则需要级联修改子节点的数据
        if (Objects.requireNonNull(updateEntity).getType() == 3) {
            //只同步serviceId和code
            if (!updateEntity.getServiceId().equals(dto.getServiceId())
                    || !updateEntity.getCode().equals(dto.getCode())) {
                saveEntities.addAll(getUpdateCascadeChild(dto, updateEntity));
            }
        }

        BeanUtils.copyProperties(dto, Objects.requireNonNull(updateEntity, "通过ID：" + id + "未能找到对应的数据!"));
        Long pid = dto.getPid();
        if (!updateEntity.getPid().equals(pid)) {
            AnanPermissionEntity parentEntity = permissionRepository.findById(pid).orElse(null);
            Assert.notNull(parentEntity, "传入的创建数据实体找不到对于的父节点数据!");
            updateEntity.setLevel(parentEntity.getLevel() + 1);
        }
        saveEntities.add(updateEntity);

        permissionRepository.saveAll(saveEntities);
    }

    @Override
    public Collection<AnanPermissionRespDto> findByPid(Long pid) {
        return BeanUtil.copyCollectionProperties(permissionRepository.findByPid(pid), AnanPermissionRespDto.class);
    }

    protected List<AnanPermissionEntity> getUpdateCascadeChild(AnanPermissionReqDto originEntity, AnanPermissionEntity updateEntity) {
        Long id = updateEntity.getId();
        Collection<AnanPermissionEntity> allByPid = permissionRepository.findByPid(id);
        List<AnanPermissionEntity> saves = new ArrayList<>();
        allByPid.forEach(entity -> {
            AnanPermissionReqDto entity2 = new AnanPermissionReqDto();
            BeanUtils.copyProperties(entity, entity2);
            entity.setServiceId(originEntity.getServiceId());
            entity.setCode(entity.getCode().replace(updateEntity.getCode(), originEntity.getCode()));
            saves.add(entity);
            saves.addAll(getUpdateCascadeChild(entity2, entity));
        });
        return saves;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Caching(
            evict = {
                    @CacheEvict(value = PlatformRedisConstant.ANAN_USER_ALL_PERMISSIONS, allEntries = true),
                    @CacheEvict(value = PlatformRedisConstant.ANAN_USER_PERMISSION_TREE, allEntries = true)

            })
    public void deleteById(Long id) {
        Assert.notNull(id, "传入了空ID!");
        AnanPermissionEntity entity = permissionRepository.findById(id).orElse(null);
        deleteByEntity(Objects.requireNonNull(entity, "通过ID：" + id + "未能找到对应的数据!"));
    }

    private void deleteByEntity(AnanPermissionEntity entity) {
        long id = entity.getId();
        long countByPermissionId = userPermissionRepository.countByPermissionId(id);
        Assert.isTrue(countByPermissionId == 0, "还有用户在使用该权限，不能直接删除!");
        countByPermissionId = versionRolePermissionRepository.countByPermissionId(id);
        Assert.isTrue(countByPermissionId == 0, "还有版本角色在使用该权限，不能直接删除!");
        countByPermissionId = versionPermissionRepository.countByPermissionId(id);
        Assert.isTrue(countByPermissionId == 0, "还有版本在使用该权限，不能直接删除!");
        countByPermissionId = rolePermissionRepository.countByPermissionId(id);
        Assert.isTrue(countByPermissionId == 0, "还有角色在使用该权限，不能直接删除!");
        countByPermissionId = organizationPermissionRepository.countByPermissionId(id);
        Assert.isTrue(countByPermissionId == 0, "还有机构在使用该权限，不能直接删除!");
        Collection<AnanPermissionRespDto> entities = findByPid(id);
        Assert.isTrue(entities == null || entities.size() == 0, "该节点还存在子节点不能直接删除!");
        permissionRepository.delete(entity);
    }

    /**
     * 根据主键删除多条数据
     *
     * @param ids 主键编号集合
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @Caching(
            evict = {
                    @CacheEvict(value = PlatformRedisConstant.ANAN_USER_ALL_PERMISSIONS, allEntries = true),
                    @CacheEvict(value = PlatformRedisConstant.ANAN_USER_PERMISSION_TREE, allEntries = true)

            })
    public void deleteByIds(Collection<Long> ids) {
        List<AnanPermissionEntity> entities = permissionRepository.findAllById(ids);
        for (AnanPermissionEntity entity : entities) {
            deleteByEntity(entity);
        }
    }

    @Override
    public List<AnanPermissionRespDto> findByPidAndVersionId(Long pid, Long versionId) {
        List<AnanPermissionEntity> entities = permissionRepository.findAllByPidAndVersionId(pid, versionId);
        return BeanUtil.copyCollectionProperties(entities, AnanPermissionRespDto.class);
    }

    @Override
    public List<AnanPermissionRespDto> findByServiceCode(String serviceCode) {
        List<AnanPermissionEntity> entities = permissionRepository.findAllByServiceId(serviceRepository.findOneByCode(serviceCode).getId());
        return BeanUtil.copyCollectionProperties(entities, AnanPermissionRespDto.class);
    }

    @Override
    public List<AnanPermissionRespDto> findByServiceCodes(List<String> serviceCodes) {
        List<AnanServiceEntity> serviceEntities = serviceRepository.findByCodeIn(serviceCodes);
        List<AnanPermissionEntity> permissionEntities =
                permissionRepository.findByServiceIdIn(serviceEntities.stream().map(AnanServiceEntity::getId).collect(Collectors.toList()));
        List<AnanPermissionRespDto> permissionRespDtos = BeanUtil.copyCollectionProperties(permissionEntities, AnanPermissionRespDto.class);
        for (AnanPermissionRespDto permissionRespDto : permissionRespDtos) {
            for (AnanServiceEntity serviceEntity : serviceEntities) {
                if (serviceEntity.getId().equals(permissionRespDto.getServiceId())) {
                    permissionRespDto.setServiceCode(serviceEntity.getCode());
                }
            }
        }
        return permissionRespDtos;
    }

    @Override
    public PermissionRepository getRepository() {
        return permissionRepository;
    }
}
