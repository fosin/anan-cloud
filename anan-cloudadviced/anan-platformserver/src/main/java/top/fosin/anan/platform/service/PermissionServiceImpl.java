package top.fosin.anan.platform.service;

import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import top.fosin.anan.cloudresource.constant.RedisConstant;
import top.fosin.anan.platform.dto.req.AnanPermissionCreateDto;
import top.fosin.anan.platform.dto.req.AnanPermissionUpdateDto;
import top.fosin.anan.cloudresource.dto.res.AnanPermissionRespDto;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.platform.entity.AnanPermissionEntity;
import top.fosin.anan.platform.repository.*;
import top.fosin.anan.platform.service.inter.PermissionService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * 2017/12/29.
 * Time:12:38
 *
 * @author fosin
 */
@Service
@Lazy
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepository permissionRepository;
    private final UserPermissionRepository userPermissionRepository;
    private final RolePermissionRepository rolePermissionRepository;
    private final AnanVersionPermissionRepository versionPermissionRepository;
    private final AnanVersionRolePermissionRepository versionRolePermissionRepository;
    private final AnanOrganizationPermissionRepository organizationPermissionRepository;
    private final AnanServiceRepository serviceRepository;

    public PermissionServiceImpl(PermissionRepository permissionRepository,
                                 UserPermissionRepository userPermissionRepository,
                                 RolePermissionRepository rolePermissionRepository,
                                 AnanVersionPermissionRepository versionPermissionRepository,
                                 AnanVersionRolePermissionRepository versionRolePermissionRepository,
                                 AnanOrganizationPermissionRepository organizationPermissionRepository,
                                 AnanServiceRepository serviceRepository) {
        this.permissionRepository = permissionRepository;
        this.userPermissionRepository = userPermissionRepository;
        this.rolePermissionRepository = rolePermissionRepository;
        this.versionPermissionRepository = versionPermissionRepository;
        this.versionRolePermissionRepository = versionRolePermissionRepository;
        this.organizationPermissionRepository = organizationPermissionRepository;
        this.serviceRepository = serviceRepository;
    }

    @Override
    @CachePut(value = RedisConstant.ANAN_PERMISSION, key = "#result.id")
    public AnanPermissionRespDto create(AnanPermissionCreateDto entity) {
        Assert.notNull(entity, "传入的创建数据实体对象不能为空!");

        AnanPermissionEntity createEntity = new AnanPermissionEntity();
        BeanUtils.copyProperties(entity, createEntity);
        Long pid = entity.getPid();

        int level = 1;
        if (pid != 0) {
            AnanPermissionEntity parentEntity = permissionRepository.findById(pid).orElse(null);
            Assert.notNull(parentEntity, "传入的创建数据实体找不到对于的父节点数据!");
            level = parentEntity.getLevel() + 1;
        }
        createEntity.setLevel(level);
        AnanPermissionRespDto respDto = new AnanPermissionRespDto();
        BeanUtils.copyProperties(permissionRepository.save(createEntity), respDto);
        return respDto;
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = RedisConstant.ANAN_USER_ALL_PERMISSIONS, allEntries = true),
                    @CacheEvict(value = RedisConstant.ANAN_PERMISSION, allEntries = true)
            }
    )
    @Transactional(rollbackFor = Exception.class)
    public void update(AnanPermissionUpdateDto entity) {
        Assert.notNull(entity, "传入了空对象!");
        Long id = entity.getId();
        Assert.notNull(id, "传入了空ID!");

        AnanPermissionEntity updateEntity = permissionRepository.findById(id).orElse(null);

        List<AnanPermissionEntity> saveEntities = new ArrayList<>();
        //如果是目录菜单，则需要级联修改子节点的数据
        if (Objects.requireNonNull(updateEntity).getType() == 3) {
            //只同步serviceId和code
            if (!updateEntity.getServiceId().equals(entity.getServiceId())
                    || !updateEntity.getCode().equals(entity.getCode())) {
                saveEntities.addAll(getUpdateCascadeChild(entity, updateEntity));
            }
        }

        BeanUtils.copyProperties(entity, Objects.requireNonNull(updateEntity, "通过ID：" + id + "未能找到对应的数据!"));
        Long pid = entity.getPid();
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

    protected List<AnanPermissionEntity> getUpdateCascadeChild(AnanPermissionUpdateDto originEntity, AnanPermissionEntity updateEntity) {
        Long id = updateEntity.getId();
        Sort sort = Sort.by(Sort.Direction.fromString("ASC"), "sort");
        Collection<AnanPermissionEntity> allByPid = permissionRepository.findByPid(id);
        List<AnanPermissionEntity> saves = new ArrayList<>();
        allByPid.forEach(entity -> {
            AnanPermissionUpdateDto entity2 = new AnanPermissionUpdateDto();
            BeanUtils.copyProperties(entity, entity2);
            entity.setServiceId(originEntity.getServiceId());
            entity.setCode(entity.getCode().replace(updateEntity.getCode(), originEntity.getCode()));
            saves.add(entity);
            saves.addAll(getUpdateCascadeChild(entity2, entity));
        });
        return saves;
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = RedisConstant.ANAN_USER_ALL_PERMISSIONS, allEntries = true),
                    @CacheEvict(value = RedisConstant.ANAN_PERMISSION, key = "#id"),
                    @CacheEvict(value = RedisConstant.ANAN_USER_PERMISSION, allEntries = true),
                    @CacheEvict(value = RedisConstant.ANAN_ROLE_PERMISSION, allEntries = true)
            }
    )
    public void deleteById(Long id) {
        Assert.notNull(id, "传入了空ID!");
        AnanPermissionEntity entity = permissionRepository.findById(id).orElse(null);
        deleteByDto(Objects.requireNonNull(entity, "通过ID：" + id + "未能找到对应的数据!"));
    }

    private void deleteByDto(AnanPermissionEntity entity) {
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

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = RedisConstant.ANAN_USER_ALL_PERMISSIONS, allEntries = true),
                    @CacheEvict(value = RedisConstant.ANAN_PERMISSION, key = "#entity.id"),
                    @CacheEvict(value = RedisConstant.ANAN_USER_PERMISSION, allEntries = true),
                    @CacheEvict(value = RedisConstant.ANAN_ROLE_PERMISSION, allEntries = true)
            }
    )
    public void deleteByDto(AnanPermissionUpdateDto entity) {
        AnanPermissionEntity deleteEntity = permissionRepository.findById(entity.getId()).orElse(null);
        deleteByDto(Objects.requireNonNull(deleteEntity, "传入了空对象!"));
    }

    @Override
    @Cacheable(value = RedisConstant.ANAN_PERMISSION, key = "#id")
    public AnanPermissionRespDto findOneById(Long id) {
        return PermissionService.super.findOneById(id);
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
    public PermissionRepository getRepository() {
        return permissionRepository;
    }
}