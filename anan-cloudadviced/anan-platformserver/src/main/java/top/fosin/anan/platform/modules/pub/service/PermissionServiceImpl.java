package top.fosin.anan.platform.modules.pub.service;

import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import top.fosin.anan.cloudresource.constant.PlatformRedisConstant;
import top.fosin.anan.cloudresource.entity.req.PermissionReqDTO;
import top.fosin.anan.cloudresource.entity.res.PermissionRespDTO;
import top.fosin.anan.cloudresource.entity.res.PermissionRespTreeDTO;
import top.fosin.anan.cloudresource.grpc.permission.*;
import top.fosin.anan.cloudresource.grpc.util.StringUtil;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.data.constant.ValueConstant;
import top.fosin.anan.platform.modules.organization.dao.OrgPermissionDao;
import top.fosin.anan.platform.modules.pub.dao.PermissionDao;
import top.fosin.anan.platform.modules.pub.dao.ServiceDao;
import top.fosin.anan.platform.modules.pub.po.Permission;
import top.fosin.anan.platform.modules.pub.po.Service;
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
@Lazy
@AllArgsConstructor
@GrpcService
public class PermissionServiceImpl extends PermissionServiceGrpc.PermissionServiceImplBase implements PermissionService {
    private final PermissionDao permissionDao;
    private final UserPermissionDao userPermissionDao;
    private final RolePermissionDao rolePermissionDao;
    private final VersionPermissionDao versionPermissionDao;
    private final VersionRolePermissionDao versionRolePermissionDao;
    private final OrgPermissionDao orgPermissionDao;
    private final ServiceDao serviceDao;

    @Override
    public PermissionRespDTO create(PermissionReqDTO dto) {
        Permission createEntity = BeanUtil.copyProperties(dto, Permission.class);
        Long pid = dto.getPid();

        int level = 1;
        if (!Objects.equals(pid, ValueConstant.TREE_ROOT_PID)) {
            Permission parentEntity = permissionDao.findById(pid).orElseThrow(() -> new IllegalArgumentException("传入的创建数据实体找不到对于的父节点数据!"));
            level = parentEntity.getLevel() + 1;
        }
        createEntity.setLevel(level);
        return BeanUtil.copyProperties(permissionDao.save(createEntity), PermissionRespDTO.class);
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = PlatformRedisConstant.ANAN_USER_ALL_PERMISSIONS, allEntries = true),
                    @CacheEvict(value = PlatformRedisConstant.ANAN_USER_PERMISSION_TREE, allEntries = true)
            })
    public void update(PermissionReqDTO dto, String[] ignoreProperties) {
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

    protected List<Permission> getUpdateCascadeChild(PermissionReqDTO originEntity, Permission updateEntity) {
        Long id = updateEntity.getId();
        Collection<Permission> allByPid = permissionDao.findByPid(id);
        List<Permission> saves = new ArrayList<>();
        allByPid.forEach(entity -> {
            PermissionReqDTO entity2 = BeanUtil.copyProperties(entity, PermissionReqDTO.class);
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
    public List<PermissionRespTreeDTO> findByPidAndVersionId(Long pid, Long versionId) {
        List<Permission> entities = permissionDao.findAllByPidAndVersionId(pid, versionId);
        return BeanUtil.copyProperties(entities, PermissionRespTreeDTO.class);
    }

    @Override
    public void findByServiceCode(ServiceCodeReq request, StreamObserver<PermissionsResp> responseObserver) {
        String serviceCode = request.getServiceCode();
        List<Permission> entities = permissionDao.findAllByServiceId(serviceDao.findOneByCode(serviceCode).getId());
        PermissionsResp permissionsResp = PermissionsResp.newBuilder().addAllPermission(entities.stream().map(entity -> {
            PermissionResp.Builder builder = PermissionResp.newBuilder();
            //BeanUtil复制方式失败原因：TODO
            // 原因1：会将空字符串变成null值复制到新对象，这里由于GRPC不允许null值导致报错NullPointerException
            // 原因2：GRPC生成的实体类的属性名会自动附加“_”，而Orika默认不支持不同属性名复制，如要支持需要单独做属性名映射
//            BeanUtil.copyProperties(entity, builder, BeanUtil.getNullProperties(entity));
//            builder.setServiceCode(serviceCode);
            builder.setId(entity.getId())
                    .setPid(entity.getPid())
                    .setCode(entity.getCode())
                    .setName(entity.getName())
                    .setUrl(StringUtil.getNonNullValue(entity.getUrl()))
                    .setRoutePath(StringUtil.getNonNullValue(entity.getRoutePath()))
                    .setType(entity.getType())
                    .setLevel(entity.getLevel())
                    .setSort(entity.getSort())
                    .setStatus(entity.getStatus())
                    .setServiceId(entity.getServiceId())
                    .setServiceCode(serviceCode)
                    .setPath(StringUtil.getNonNullValue(entity.getPath()))
                    .setMethod(StringUtil.getNonNullValue(entity.getMethod()))
                    .setIcon(StringUtil.getNonNullValue(entity.getIcon()));
            return builder.build();
        }).collect(Collectors.toList())).build();
        responseObserver.onNext(permissionsResp);
        responseObserver.onCompleted();
    }
    
    @Override
    public void findByServiceCodes(ServiceCodesReq request, StreamObserver<PermissionsResp> responseObserver) {
        List<String> serviceCodes = new ArrayList<>(request.getServiceCodesList());
        List<Service> services = serviceDao.findByCodeIn(serviceCodes);
        List<Permission> permissions =
                permissionDao.findByServiceIdIn(services.stream().map(Service::getId).collect(Collectors.toList()));
        PermissionsResp permissionsResp = PermissionsResp.newBuilder().addAllPermission(permissions.stream().map(entity -> {
            PermissionResp.Builder builder = PermissionResp.newBuilder();
            builder.setId(entity.getId())
                    .setPid(entity.getPid())
                    .setCode(entity.getCode())
                    .setName(entity.getName())
                    .setUrl(StringUtil.getNonNullValue(entity.getUrl()))
                    .setRoutePath(StringUtil.getNonNullValue(entity.getRoutePath()))
                    .setType(entity.getType())
                    .setLevel(entity.getLevel())
                    .setSort(entity.getSort())
                    .setStatus(entity.getStatus())
                    .setServiceId(entity.getServiceId())
                    .setServiceCode(services.stream().filter(service -> service.getId().equals(entity.getServiceId())).findFirst().get().getCode())
                    .setPath(StringUtil.getNonNullValue(entity.getPath()))
                    .setMethod(StringUtil.getNonNullValue(entity.getMethod()))
                    .setIcon(StringUtil.getNonNullValue(entity.getIcon()));
            return builder.build();
        }).collect(Collectors.toList())).build();
        responseObserver.onNext(permissionsResp);
        responseObserver.onCompleted();
    }

    @Override
    public List<PermissionRespDTO> findByServiceCode(String serviceCode) {
        List<Permission> entities = permissionDao.findAllByServiceId(serviceDao.findOneByCode(serviceCode).getId());
        return BeanUtil.copyProperties(entities, PermissionRespDTO.class);
    }

    @Override
    public List<PermissionRespDTO> findByServiceCodes(List<String> serviceCodes) {
        List<Service> services = serviceDao.findByCodeIn(serviceCodes);
        List<Permission> permissionEntities =
                permissionDao.findByServiceIdIn(services.stream().map(Service::getId).collect(Collectors.toList()));
        List<PermissionRespDTO> permissionRespDTOS = BeanUtil.copyProperties(permissionEntities, PermissionRespDTO.class);
        for (PermissionRespDTO permissionRespDTO : permissionRespDTOS) {
            for (Service serviceEntity : services) {
                if (serviceEntity.getId().equals(permissionRespDTO.getServiceId())) {
                    permissionRespDTO.setServiceCode(serviceEntity.getCode());
                }
            }
        }
        return permissionRespDTOS;
    }

    @Override
    public PermissionDao getDao() {
        return permissionDao;
    }
}
