package top.fosin.anan.platform.modules.pub.service;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import top.fosin.anan.cloudresource.constant.PlatformRedisConstant;
import top.fosin.anan.cloudresource.dto.req.PermissionReqDto;
import top.fosin.anan.cloudresource.dto.res.PermissionRespDto;
import top.fosin.anan.cloudresource.dto.res.PermissionRespTreeDto;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.model.constant.ValueConstant;
import top.fosin.anan.platform.modules.organization.dao.OrgPermissionDao;
import top.fosin.anan.platform.modules.pub.dao.PermissionDao;
import top.fosin.anan.platform.modules.pub.dao.ServiceDao;
import top.fosin.anan.platform.modules.pub.entity.Permission;
import top.fosin.anan.platform.modules.pub.entity.Service;
import top.fosin.anan.platform.modules.pub.service.inter.PermissionService;
import top.fosin.anan.platform.modules.role.dao.RolePermissionDao;
import top.fosin.anan.platform.modules.user.dao.UserPermissionDao;
import top.fosin.anan.platform.modules.version.dao.VersionPermissionDao;
import top.fosin.anan.platform.modules.version.dao.VersionRolePermissionDao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author fosin
 * @date 2017/12/29
 */
@org.springframework.stereotype.Service
@Lazy
@AllArgsConstructor
public class PermissionServiceImpl implements PermissionService {
    private final PermissionDao permissionDao;
    private final UserPermissionDao userPermissionDao;
    private final RolePermissionDao rolePermissionDao;
    private final VersionPermissionDao versionPermissionDao;
    private final VersionRolePermissionDao versionRolePermissionDao;
    private final OrgPermissionDao orgPermissionDao;
    private final ServiceDao serviceDao;

    @Override
    public PermissionRespDto create(PermissionReqDto dto) {
        Permission createEntity = BeanUtil.copyProperties(dto, Permission.class);
        Long pid = dto.getPid();

        int level = 1;
        if (!Objects.equals(pid, ValueConstant.TREE_ROOT_PID)) {
            Permission parentEntity = permissionDao.findById(pid).orElseThrow(() -> new IllegalArgumentException("传入的创建数据实体找不到对于的父节点数据!"));
            level = parentEntity.getLevel() + 1;
        }
        createEntity.setLevel(level);
        return BeanUtil.copyProperties(permissionDao.save(createEntity), PermissionRespDto.class);
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = PlatformRedisConstant.ANAN_USER_ALL_PERMISSIONS, allEntries = true),
                    @CacheEvict(value = PlatformRedisConstant.ANAN_USER_PERMISSION_TREE, allEntries = true)
            })
    public void update(PermissionReqDto dto, String[] ignoreProperties) {
        Long id = dto.getId();
        Assert.notNull(id, "传入了空ID!");

        Permission updateEntity = permissionDao.findById(id).orElse(null);

        List<Permission> saveEntities = new ArrayList<>();
        //如果是目录菜单，则需要级联修改子节点的数据
        if (Objects.requireNonNull(updateEntity).getType() == 3) {
            //只同步serviceId和code
            if (!updateEntity.getServiceId().equals(dto.getServiceId())
                    || !updateEntity.getCode().equals(dto.getCode())) {
                saveEntities.addAll(getUpdateCascadeChild(dto, updateEntity));
            }
        }

        BeanUtil.copyProperties(dto, Objects.requireNonNull(updateEntity, "通过ID：" + id + "未能找到对应的数据!"), ignoreProperties);
        Long pid = dto.getPid();
        if (!updateEntity.getPid().equals(pid)) {
            Permission parentEntity = permissionDao.findById(pid).orElse(null);
            Assert.notNull(parentEntity, "传入的创建数据实体找不到对于的父节点数据!");
            updateEntity.setLevel(parentEntity.getLevel() + 1);
        }
        saveEntities.add(updateEntity);

        permissionDao.saveAll(saveEntities);
    }

    protected List<Permission> getUpdateCascadeChild(PermissionReqDto originEntity, Permission updateEntity) {
        Long id = updateEntity.getId();
        Collection<Permission> allByPid = permissionDao.findByPid(id);
        List<Permission> saves = new ArrayList<>();
        allByPid.forEach(entity -> {
            PermissionReqDto entity2 = BeanUtil.copyProperties(entity, PermissionReqDto.class);
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
        Permission entity = permissionDao.findById(id).orElse(null);
        deleteByEntity(Objects.requireNonNull(entity, "通过ID：" + id + "未能找到对应的数据!"));
    }

    private void deleteByEntity(Permission entity) {
        long id = entity.getId();
        Assert.isTrue(permissionDao.countByPid(id) == 0, "该节点还存在子节点不能直接删除!");
        Assert.isTrue(versionRolePermissionDao.countByPermissionId(id) == 0, "还有版本角色在使用该权限，不能直接删除!");
        Assert.isTrue(versionPermissionDao.countByPermissionId(id) == 0, "还有版本在使用该权限，不能直接删除!");
        Assert.isTrue(rolePermissionDao.countByPermissionId(id) == 0, "还有角色在使用该权限，不能直接删除!");
        Assert.isTrue(orgPermissionDao.countByPermissionId(id) == 0, "还有机构在使用该权限，不能直接删除!");
        Assert.isTrue(userPermissionDao.countByPermissionId(id) == 0, "还有用户在使用该权限，不能直接删除!");
        permissionDao.delete(entity);
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
        List<Permission> entities = permissionDao.findAllById(ids);
        for (Permission entity : entities) {
            deleteByEntity(entity);
        }
    }

    @Override
    public List<PermissionRespTreeDto> findByPidAndVersionId(Long pid, Long versionId) {
        List<Permission> entities = permissionDao.findAllByPidAndVersionId(pid, versionId);
        return BeanUtil.copyProperties(entities, PermissionRespTreeDto.class);
    }

    @Override
    public List<PermissionRespDto> findByServiceCode(String serviceCode) {
        List<Permission> entities = permissionDao.findAllByServiceId(serviceDao.findOneByCode(serviceCode).getId());
        return BeanUtil.copyProperties(entities, PermissionRespDto.class);
    }

    @Override
    public List<PermissionRespDto> findByServiceCodes(List<String> serviceCodes) {
        List<Service> serviceEntities = serviceDao.findByCodeIn(serviceCodes);
        List<Permission> permissionEntities =
                permissionDao.findByServiceIdIn(serviceEntities.stream().map(Service::getId).collect(Collectors.toList()));
        List<PermissionRespDto> permissionRespDtos = BeanUtil.copyProperties(permissionEntities, PermissionRespDto.class);
        for (PermissionRespDto permissionRespDto : permissionRespDtos) {
            for (Service serviceEntity : serviceEntities) {
                if (serviceEntity.getId().equals(permissionRespDto.getServiceId())) {
                    permissionRespDto.setServiceCode(serviceEntity.getCode());
                }
            }
        }
        return permissionRespDtos;
    }

    @Override
    public PermissionDao getDao() {
        return permissionDao;
    }
}
