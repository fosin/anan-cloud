package top.fosin.anan.platform.service;

import top.fosin.anan.cloudresource.constant.RedisConstant;
import top.fosin.anan.cloudresource.dto.request.AnanPermissionCreateDto;
import top.fosin.anan.cloudresource.dto.request.AnanPermissionUpdateDto;
import top.fosin.anan.jpa.repository.IJpaRepository;
import top.fosin.anan.model.module.PageModule;
import top.fosin.anan.model.result.Result;
import top.fosin.anan.model.result.ResultUtils;
import top.fosin.anan.platform.repository.AnanOrganizationPermissionRepository;
import top.fosin.anan.platform.repository.AnanVersionPermissionRepository;
import top.fosin.anan.platform.repository.AnanVersionRolePermissionRepository;
import top.fosin.anan.platform.service.inter.PermissionService;
import top.fosin.anan.platformapi.entity.AnanPermissionEntity;
import top.fosin.anan.platformapi.repository.AnanServiceRepository;
import top.fosin.anan.platformapi.repository.PermissionRepository;
import top.fosin.anan.platformapi.repository.RolePermissionRepository;
import top.fosin.anan.platformapi.repository.UserPermissionRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.criteria.Path;
import java.util.ArrayList;
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
    public AnanPermissionEntity create(AnanPermissionCreateDto entity) {
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
        return permissionRepository.save(createEntity);
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = RedisConstant.ANAN_USER_ALL_PERMISSIONS, allEntries = true),
                    @CacheEvict(value = RedisConstant.ANAN_PERMISSION, allEntries = true)
            }
    )
    @Transactional(rollbackFor = Exception.class)
    public AnanPermissionEntity update(AnanPermissionUpdateDto entity) {
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
        return updateEntity;
    }

    protected List<AnanPermissionEntity> getUpdateCascadeChild(AnanPermissionUpdateDto originEntity, AnanPermissionEntity updateEntity) {
        Long id = updateEntity.getId();
        Sort sort = Sort.by(Sort.Direction.fromString("ASC"), "sort");
        List<AnanPermissionEntity> allByPid = permissionRepository.findAllByPid(id, sort);
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
    public AnanPermissionEntity deleteById(Long id) {
        Assert.notNull(id, "传入了空ID!");
        AnanPermissionEntity entity = permissionRepository.findById(id).orElse(null);
        deleteById(id, Objects.requireNonNull(entity, "通过ID：" + id + "未能找到对应的数据!"));
        return null;
    }

    private void deleteById(Long id, AnanPermissionEntity entity) {
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
        List<AnanPermissionEntity> entities = findByPid(id);
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
    public AnanPermissionEntity deleteByEntity(AnanPermissionEntity entity) {
        Assert.notNull(entity, "传入了空对象!");
        Long id = entity.getId();
        Assert.notNull(id, "传入了空ID!");
        deleteById(id, entity);
        return entity;
    }

    @Override
    public Result findAllByPageSort(PageModule pageModule) {
        PageRequest pageable = PageRequest.of(pageModule.getPageNumber() - 1, pageModule.getPageSize(), Sort.Direction.fromString(pageModule.getSortOrder()), pageModule.getSortName());
        String searchCondition = pageModule.getSearchText();

        Specification<AnanPermissionEntity> condition = (Specification<AnanPermissionEntity>) (root, query, cb) -> {
            if (StringUtils.isBlank(searchCondition)) {
                return query.getRestriction();
            }
            Path<String> name = root.get("name");
            Path<String> code = root.get("code");
            Path<String> url = root.get("url");
            Path<String> type = root.get("type");
            return cb.or(cb.like(name, "%" + searchCondition + "%"), cb.like(code, "%" + searchCondition + "%"), cb.like(url, "%" + searchCondition + "%"), cb.like(type, "%" + searchCondition + "%"));
        };
        //分页查找
        Page<AnanPermissionEntity> page = permissionRepository.findAll(condition, pageable);

        return ResultUtils.success(page.getTotalElements(), page.getContent());
    }

    @Override
    @Cacheable(value = RedisConstant.ANAN_PERMISSION, key = "#id")
    public AnanPermissionEntity findById(Long id) {
        return permissionRepository.findById(id).orElse(null);
    }

    @Override
    public List<AnanPermissionEntity> findByPid(Long pid) {
        Sort sort = Sort.by(Sort.Direction.fromString("ASC"), "sort");
        return permissionRepository.findAllByPid(pid, sort);
    }

    @Override
    public List<AnanPermissionEntity> findByPid(Long pid, Long versionId) {
        return permissionRepository.findAllByPidAndVersionId(pid, versionId);
    }

    @Override
    public List<AnanPermissionEntity> findByType(Integer type) {
        return permissionRepository.findAllByType(type);
    }

    @Override
    public List<AnanPermissionEntity> findByServiceCode(String serviceCode) {
        return permissionRepository.findAllByServiceId(serviceRepository.findOneByCode(serviceCode).getId());
    }

    @Override
    public IJpaRepository<AnanPermissionEntity, Long> getRepository() {
        return permissionRepository;
    }
}
