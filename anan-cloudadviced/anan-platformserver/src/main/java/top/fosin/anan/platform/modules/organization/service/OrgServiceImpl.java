package top.fosin.anan.platform.modules.organization.service;

import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import top.fosin.anan.cloudresource.constant.PlatformRedisConstant;
import top.fosin.anan.cloudresource.entity.res.OrganizRespDTO;
import top.fosin.anan.cloudresource.entity.res.OrganizTreeDTO;
import top.fosin.anan.cloudresource.grpc.organiz.*;
import top.fosin.anan.cloudresource.grpc.service.OrganizGrpcServiceImpl;
import top.fosin.anan.cloudresource.grpc.util.StringUtil;
import top.fosin.anan.cloudresource.service.CurrentUserService;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.data.converter.translate.StringTranslateCacheUtil;
import top.fosin.anan.data.service.ICrudBatchService;
import top.fosin.anan.jpa.repository.IJpaRepository;
import top.fosin.anan.platform.modules.organization.dao.OrgDao;
import top.fosin.anan.platform.modules.organization.dto.OrgReqDto;
import top.fosin.anan.platform.modules.organization.po.Organization;
import top.fosin.anan.platform.modules.organization.service.inter.OrgService;
import top.fosin.anan.redis.cache.AnanCacheManger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author fosin
 * @date 2017/12/29
 */
@GrpcService
@Lazy
@AllArgsConstructor
@Slf4j
public class OrgServiceImpl extends OrganizServiceGrpc.OrganizServiceImplBase implements OrgService {
    private final OrgDao orgDao;
    private final CurrentUserService currentUserService;
    private final AnanCacheManger ananCacheManger;

    @Override
    public OrganizRespDTO create(OrgReqDto reqDto) {
        Organization createEntity = new Organization();
        BeanUtil.copyProperties(reqDto, createEntity);
        Long pid = reqDto.getPid();
        int level = 1;
        if (pid == 0) {
            Assert.isTrue(currentUserService.hasSysAdminRole(), "只有超级管理员才能创建顶级机构!");
            createEntity.setTopId(0L);
        } else {
            Organization parentEntity = orgDao.findById(pid).orElse(null);
            Assert.notNull(parentEntity, "传入的创建数据实体找不到对于的父节点数据!");
            level = parentEntity.getLevel() + 1;
        }
        createEntity.setLevel(level);
        Organization result = orgDao.save(createEntity);
        if (pid == 0) {
            result.setTopId(result.getId());
            result = orgDao.save(result);
        }
        OrganizRespDTO organizRespDTO = BeanUtil.copyProperties(result, OrganizRespDTO.class);
        ananCacheManger.put(PlatformRedisConstant.ANAN_ORGANIZATION, organizRespDTO.getId() + "", organizRespDTO);
        return organizRespDTO;
    }

    @Override
    public void update(OrgReqDto updateDto, String[] ignoreProperties) {
        Long id = updateDto.getId();
        Assert.notNull(id, "无效的字典代码code");
        Organization updateEntity = orgDao.findById(id).orElseThrow(() -> new IllegalArgumentException("根据传入的机构序号" + id + "在数据库中未能找到对于数据!"));
        boolean changedName = !updateDto.getName().equals(updateEntity.getName());
        BeanUtil.copyProperties(updateDto, updateEntity, ignoreProperties);

        //如果修改了上级机构，则需要同步修改层级level
        Long pid = updateDto.getPid();
        if (!updateEntity.getPid().equals(pid)) {
            orgDao.findById(pid).ifPresent(sentity -> updateEntity.setLevel(sentity.getLevel() + 1));
        }
        orgDao.save(updateEntity);
        ananCacheManger.evict(PlatformRedisConstant.ANAN_ORGANIZATION, updateDto.getId() + "");
        if (changedName)
            StringTranslateCacheUtil.put(OrganizGrpcServiceImpl.class, "", updateDto.getId(), updateDto.getName());
    }

    @Override
    public OrganizRespDTO findOneById(Long id, boolean... findRefs) {
        OrganizRespDTO respDTO = ananCacheManger.get(PlatformRedisConstant.ANAN_ORGANIZATION, id + "", OrganizRespDTO.class);
        if (respDTO == null) {
            respDTO = OrgService.super.findOneById(id, findRefs);
            ananCacheManger.put(PlatformRedisConstant.ANAN_ORGANIZATION, id + "", respDTO);
        }
        return respDTO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = PlatformRedisConstant.ANAN_ORGANIZATION, key = "#id")
    public void deleteById(Long id) {
        List<OrganizTreeDTO> dtos = this.listChild(id);
        Assert.isTrue(dtos == null || dtos.size() == 0, "该节点还存在子节点不能直接删除!");
        //先删除关联表数据
        List<ICrudBatchService<?, ?, Long>> batchServices = getRefBatchServices();
        for (ICrudBatchService<?, ?, Long> batchService : batchServices) {
            batchService.deleteInBatch(id);
        }
        orgDao.deleteById(id);
    }

    /**
     * 根据主键删除多条数据
     *
     * @param ids 主键编号集合
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByIds(Collection<Long> ids) {
        List<Organization> entities = orgDao.findAllById(ids);
        for (Organization entity : entities) {
            Long id = entity.getId();
            List<OrganizTreeDTO> dtos = this.listChild(id);
            Assert.isTrue(dtos == null || dtos.size() == 0, "该节点还存在子节点不能直接删除!");
            orgDao.delete(entity);
        }
        for (Organization entity : entities) {
            ananCacheManger.evict(PlatformRedisConstant.ANAN_ORGANIZATION, entity.getId() + "");
        }
    }

    @Override
    public List<OrganizTreeDTO> listChild(Long pid) {
        return BeanUtil.copyProperties(orgDao.findByPidOrderByCodeAsc(pid),
                OrganizTreeDTO.class);
    }

    @Override
    public List<OrganizTreeDTO> listAllChild(Long pid) {
        List<Organization> organizations = listAllChildReal(pid);
        return BeanUtil.copyProperties(organizations, OrganizTreeDTO.class);
    }

    private List<Organization> listAllChildReal(Long pid) {
        List<Organization> organizations = new ArrayList<>();
        if (pid == 0) {
            List<Organization> list = orgDao.findByPidOrderByCodeAsc(pid);
            for (Organization organization : list) {
                List<Organization> byCodeStartingWith = orgDao.findByTopIdAndCodeStartingWithOrderByCodeAsc(organization.getTopId(), organization.getCode());
                organizations.addAll(byCodeStartingWith);
            }
        } else {
            Organization organization = orgDao.findById(pid).orElse(null);
            if (organization != null) {
                List<Organization> byCodeStartingWith = orgDao.findByTopIdAndCodeStartingWithOrderByCodeAsc(organization.getTopId(), organization.getCode());
                organizations.addAll(byCodeStartingWith);
            }
        }
        return organizations;
    }

    @Override
    public void findOneById(OrganizIdReq request, StreamObserver<OrganizResp> responseObserver) {
        long id = request.getId();
        OrganizRespDTO respDTO = this.findOneById(id);
        OrganizResp organizResp = toGrpcResp(respDTO);
        responseObserver.onNext(organizResp);
        responseObserver.onCompleted();
    }

    @NotNull
    private OrganizResp toGrpcResp(OrganizRespDTO dto) {
        return OrganizResp.newBuilder()
                .setCode(dto.getCode())
                .setName(dto.getName())
                .setAddress(StringUtil.getNonNullValue(dto.getAddress()))
                .setPid(dto.getPid())
                .setStatus(dto.getStatus())
                .setFullname(dto.getFullname())
                .setId(dto.getId())
                .setLevel(dto.getLevel())
                .setTelphone(StringUtil.getNonNullValue(dto.getTelphone()))
                .setTopId(dto.getTopId()).build();
    }

    @Override
    public void listByIds(OrganizIdsReq request, StreamObserver<OrganizsResp> responseObserver) {
        List<Long> ids = request.getIdList();
        List<Organization> organizations = this.getDao().findAllById(ids);
        toGrpcResps(responseObserver, organizations);
    }

    private void toGrpcResps(StreamObserver<OrganizsResp> responseObserver, List<Organization> organizations) {
        OrganizsResp organizsResp = OrganizsResp.newBuilder().addAllOrganiz(organizations.stream()
                .map(this::toGrpcResp).collect(Collectors.toList())).build();
        responseObserver.onNext(organizsResp);
        responseObserver.onCompleted();
    }

    @NotNull
    private OrganizResp toGrpcResp(Organization entity) {
        return OrganizResp.newBuilder()
                .setCode(entity.getCode())
                .setName(entity.getName())
                .setAddress(StringUtil.getNonNullValue(entity.getAddress()))
                .setPid(entity.getPid())
                .setStatus(entity.getStatus())
                .setFullname(StringUtil.getNonNullValue(entity.getFullname()))
                .setId(entity.getId())
                .setLevel(entity.getLevel())
                .setTelphone(StringUtil.getNonNullValue(entity.getTelphone()))
                .setTopId(entity.getTopId()).build();
    }

    @Override
    public void listChild(OrganizPidReq request, StreamObserver<OrganizsResp> responseObserver) {
        List<Organization> organizations = orgDao.findByPidOrderByCodeAsc(request.getPid());
        toGrpcResps(responseObserver, organizations);
    }

    @Override
    public void listAllChild(OrganizPidReq request, StreamObserver<OrganizsResp> responseObserver) {
        List<Organization> organizations = listAllChildReal(request.getPid());
        toGrpcResps(responseObserver, organizations);
    }

    @Override
    public IJpaRepository<Long, Organization> getDao() {
        return orgDao;
    }
}
