package top.fosin.anan.platform.modules.parameter.service;


import com.google.protobuf.BoolValue;
import com.google.protobuf.Empty;
import com.google.protobuf.StringValue;
import com.google.protobuf.Timestamp;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import top.fosin.anan.cloudresource.constant.PlatformRedisConstant;
import top.fosin.anan.cloudresource.entity.req.ParameterCreateDTO;
import top.fosin.anan.cloudresource.entity.res.ParameterDTO;
import top.fosin.anan.cloudresource.entity.req.ParameterUpdateDTO;
import top.fosin.anan.cloudresource.grpc.parameter.*;
import top.fosin.anan.cloudresource.grpc.util.StringUtil;
import top.fosin.anan.cloudresource.parameter.ParameterStatus;
import top.fosin.anan.cloudresource.parameter.ParameterType;
import top.fosin.anan.cloudresource.service.CurrentUserService;
import top.fosin.anan.core.exception.AnanServiceException;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.data.entity.req.PageQuery;
import top.fosin.anan.data.result.PageResult;
import top.fosin.anan.data.result.ResultUtils;
import top.fosin.anan.platform.modules.organization.dao.OrgDao;
import top.fosin.anan.platform.modules.organization.po.Organization;
import top.fosin.anan.platform.modules.parameter.dao.ParameterDao;
import top.fosin.anan.platform.modules.parameter.po.Parameter;
import top.fosin.anan.platform.modules.parameter.query.ParameterQuery;
import top.fosin.anan.platform.modules.parameter.service.inter.ParameterService;
import top.fosin.anan.redis.cache.AnanCacheManger;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 字典表服务
 *
 * @author fosin
 * @date 2018-7-29
 */
@GrpcService
@Lazy
@Slf4j
@AllArgsConstructor
public class ParameterServiceImpl extends ParameterServiceGrpc.ParameterServiceImplBase implements ParameterService {
    private final ParameterDao parameterDao;
    private final CurrentUserService currentUserService;
    private final OrgDao orgDao;
    private final AnanCacheManger ananCacheManger;

    @Override
    public PageResult<ParameterDTO> findPage(PageQuery<?> PageQuery) {
        ParameterQuery params = (ParameterQuery) PageQuery.getParams();

        String name = params.getName();
        String search = "%" + (name == null ? "" : name) + "%";
        boolean sysAdminUser = currentUserService.hasSysAdminRole();
        byte type = 2;
        String code = "";
        if (!sysAdminUser) {
            type = 1;
            Organization organization = orgDao.findById(currentUserService.getOrganizId())
                    .orElseThrow(() -> new IllegalArgumentException("未找到你的机构信息!"));
            code = organization.getCode();
        }

        //分页查找
        PageRequest pageable = toPage(PageQuery);
        Page<Parameter> page = parameterDao.findPage(search, code, type, pageable);
        return ResultUtils.success(page.getTotalElements(), page.getTotalPages(),
                BeanUtil.copyProperties(page.getContent(), ParameterDTO.class));
    }

    @Override
    public void postCreate(ParameterCreateDTO reqDto, ParameterDTO respDto) {
        ananCacheManger.put(PlatformRedisConstant.ANAN_PARAMETER, getCacheKey(respDto), respDto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ParameterUpdateDTO reqDto, String[] ignoreProperties) {
        Long id = reqDto.getId();
        Assert.notNull(id, "ID不能为空!");
        Parameter cEntity = parameterDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("通过ID：" + id + "未能找到对应的数据!"));
        if (!currentUserService.hasSysAdminRole()) {
            Assert.isTrue(StringUtils.hasText(cEntity.getScope()), "非超级管理员不能修改公共参数!");
        }
        String oldCacheKey = getCacheKey(cEntity);
        String newCacheKey = getCacheKey(reqDto.getType(), reqDto.getScope(), reqDto.getName());

        BeanUtil.copyProperties(reqDto, cEntity, ignoreProperties);
        cEntity.setStatus(ParameterStatus.Modified.getTypeValue());
        parameterDao.save(cEntity);
        //如果修改了type、scope、name则需要删除以前的缓存并设置新缓存
        if (!oldCacheKey.equals(newCacheKey)) {
            //新key设置旧值，需要发布以后才刷新缓存换成本次更新的新值
            ParameterDTO respDto = ananCacheManger.get(PlatformRedisConstant.ANAN_PARAMETER, oldCacheKey, ParameterDTO.class);
            if (respDto != null) {
                ananCacheManger.put(PlatformRedisConstant.ANAN_PARAMETER, newCacheKey, respDto);
            }
            ananCacheManger.evict(PlatformRedisConstant.ANAN_PARAMETER, oldCacheKey);
        }
    }

    /**
     * 不直接删除， 先修改为删除状态，应用后才真正删除
     *
     * @param id 主键
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        parameterDao.findById(id).ifPresent(entity -> {
            if (!currentUserService.hasSysAdminRole()) {
                Assert.isTrue(StringUtils.hasText(entity.getScope()), "非超级管理员不能删除公共参数!");
            }
            entity.setStatus(ParameterStatus.Deleted.getTypeValue());
            parameterDao.save(entity);
        });
    }

    /**
     * 不直接删除， 先修改为删除状态，应用后才真正删除
     *
     * @param ids 主键编号集合
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByIds(Collection<Long> ids) {
        List<Parameter> entities = parameterDao.findAllById(ids);
        for (Parameter entity : entities) {
            if (!currentUserService.hasSysAdminRole()) {
                Assert.isTrue(StringUtils.hasText(entity.getScope()), "非超级管理员不能删除公共参数!");
            }
            entity.setStatus(ParameterStatus.Deleted.getTypeValue());
        }
        parameterDao.saveAll(entities);
    }

    /**
     * 取消删除
     *
     * @param ids 主键
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelDelete(List<Long> ids) {
        List<Parameter> entities = parameterDao.findAllById(ids);
        for (Parameter entity : entities) {
            if (!currentUserService.hasSysAdminRole()) {
                Assert.isTrue(StringUtils.hasText(entity.getScope()), "非超级管理员不能取消删除公共参数!");
            }
            entity.setStatus(ParameterStatus.Normal.getTypeValue());
        }
        parameterDao.saveAll(entities);
    }

    protected String getCacheKey(Parameter entity) {
        return getCacheKey(entity.getType(), entity.getScope(), entity.getName());
    }


    @Override
    public ParameterDTO getParameter(Byte type, String scope, String name) {
        String cacheKey = getCacheKey(type, scope, name);
        ParameterDTO respDto = ananCacheManger.get(PlatformRedisConstant.ANAN_PARAMETER, cacheKey, ParameterDTO.class);
        if (respDto == null) {
            Parameter entity = findByTypeAndScopeAndName(type, scope, name);
            //因为参数会逐级上上级机构查找，为减少没有必要的查询，该代码为解决Spring Cache默认不缓存null值问题
            if (entity == null) {
                respDto = new ParameterDTO();
            } else {
                respDto = BeanUtil.copyProperties(entity, ParameterDTO.class);
                ananCacheManger.put(PlatformRedisConstant.ANAN_PARAMETER, cacheKey, respDto);
            }
        }
        return respDto;
    }

    /**
     * 得到机构链中最接近的参数
     *
     * @param scope 机构ID Parameter.scope
     * @param name  参数名称 Parameter.name
     * @return 参数
     */
    @Override
    public ParameterDTO getNearestParameter(Byte type, String scope, String name) {
        String cacheKey = getCacheKey(type, scope, name);
        ParameterDTO respDto = ananCacheManger.get(PlatformRedisConstant.ANAN_PARAMETER, cacheKey, ParameterDTO.class);
        if (respDto == null) {
            Parameter parameter = findByTypeAndScopeAndName(type, scope, name);
            boolean finded = parameter != null && parameter.getId() != null;
            if (finded) {
                respDto = BeanUtil.copyProperties(parameter, ParameterDTO.class);
                ananCacheManger.put(PlatformRedisConstant.ANAN_PARAMETER, cacheKey, respDto);
            } else {
                //parameter为空表示没有参数记录，则依次向上找父机构的参数
                Assert.isTrue(StringUtils.hasText(scope), "没有从参数[" + "type:" + type + " scope:" + scope + " name:" + name + "]中查询到参数");
                respDto = getNearestParameter(type, getNearestScope(type, scope), name);
            }
        }
        return respDto;
    }

    private Parameter findByTypeAndScopeAndName(byte type, String scope, String name) {
        //如果传入了空字符串，则设置成null，因为数据库中是空值
        if (!StringUtils.hasText(scope)) {
            scope = null;
        }
        return parameterDao.findByTypeAndScopeAndName(type, scope, name);
    }

    private String getNearestScope(int type, String scope) {
        String rc = null;
        //机构参数存在上下级关系，需要逐级向上查找
        if (type == ParameterType.Organization.getTypeValue()) {
            Long id = Long.parseLong(scope);
            Organization entity = orgDao.findById(id).orElse(null);
            if (entity != null && entity.getLevel() != 0) {
                rc = String.valueOf(entity.getPid());
            }
        }
        //用户参数、服务参数等直接返回null
        return rc;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String getOrCreateParameter(Byte type, String scope, String name, String defaultValue, String description) {
        String rc;
        try {
            rc = getNearestParameter(type, scope, name).getValue();
        } catch (IllegalArgumentException e) {
            ParameterCreateDTO reqDto = new ParameterCreateDTO();
            reqDto.setType(type);
            reqDto.setScope(scope);
            reqDto.setName(name);
            reqDto.setDefaultValue(defaultValue);
            reqDto.setValue(defaultValue);
            reqDto.setDescription(description);
            ParameterDTO respDto = processCreate(reqDto);
            rc = respDto.getValue();
            log.debug("报异常说明没有找到任何相关参数，则需要创建一个：" + respDto);
        }
        return rc;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean applyChange(Long id) {
        Parameter entity = parameterDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("该参数[" + id + "]已经不存在!"));
        if (!currentUserService.hasSysAdminRole()) {
            Assert.isTrue(StringUtils.hasText(entity.getScope()), "非超级管理员不能发布公共参数!");
        }
        String cacheKey = getCacheKey(entity);
        boolean success;
        switch (Objects.requireNonNull(ParameterStatus.valueOf(entity.getStatus()))) {
            case Modified:
                entity.setApplyBy(currentUserService.getUserId());
                entity.setApplyTime(new Date());
                entity.setStatus((byte) 0);
                success = ananCacheManger.put(PlatformRedisConstant.ANAN_PARAMETER, cacheKey, BeanUtil.copyProperties(entity, ParameterDTO.class));
                if (success) {
                    parameterDao.save(entity);
                }
                break;
            case Deleted:
                success = ananCacheManger.evict(PlatformRedisConstant.ANAN_PARAMETER, cacheKey);
                if (success) {
                    parameterDao.deleteById(id);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new AnanServiceException(e);
                    }
                    success = ananCacheManger.evict(PlatformRedisConstant.ANAN_PARAMETER, cacheKey);
                }
                break;
            default:
                success = ananCacheManger.put(PlatformRedisConstant.ANAN_PARAMETER, cacheKey, BeanUtil.copyProperties(entity, ParameterDTO.class));
        }
        return success;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean applyChangeAll() {
        List<Parameter> entities = parameterDao.findByStatusNot((byte) 0);
        Assert.isTrue(entities != null && entities.size() != 0, "没有更改过任何参数，不需要发布!");
        for (Parameter entity : entities) {
            applyChange(entity.getId());
        }
        return true;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean applyChanges(List<Long> ids) {
        for (Long id : ids) {
            applyChange(id);
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void applyChange(ParameterIdReq request, StreamObserver<BoolValue> responseObserver) {
        Boolean rs = applyChange(request.getId());
        responseObserver.onNext(BoolValue.of(rs));
        responseObserver.onCompleted();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void applyChanges(ParameterIdsReq request, StreamObserver<BoolValue> responseObserver) {
        Boolean rs = applyChanges(request.getIdList());
        responseObserver.onNext(BoolValue.of(rs));
        responseObserver.onCompleted();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void applyChangeAll(Empty request, StreamObserver<BoolValue> responseObserver) {
        Boolean rs = applyChangeAll();
        responseObserver.onNext(BoolValue.of(rs));
        responseObserver.onCompleted();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelDelete(ParameterIdsReq request, StreamObserver<Empty> responseObserver) {
        cancelDelete(request.getIdList());
        responseObserver.onNext(Empty.getDefaultInstance());
        responseObserver.onCompleted();
    }

    @Override
    public void getParameter(ParameterThreeArgsReq request, StreamObserver<ParameterResp> responseObserver) {
        ParameterDTO parameter = getParameter((byte) request.getType(), request.getScope(), request.getName());
        toGrpcResp(responseObserver, parameter);
    }

    @Override
    public void getNearestParameter(ParameterThreeArgsReq request, StreamObserver<ParameterResp> responseObserver) {
        ParameterDTO parameter = getNearestParameter((byte) request.getType(), request.getScope(), request.getName());
        toGrpcResp(responseObserver, parameter);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void processUpdate(ParameterReq request, StreamObserver<Empty> responseObserver) {
        ParameterUpdateDTO req = toParameterReqDto(request);
        processUpdate(req);
        responseObserver.onNext(Empty.getDefaultInstance());
        responseObserver.onCompleted();
    }

    @NotNull
    private ParameterUpdateDTO toParameterReqDto(ParameterReq request) {
        ParameterUpdateDTO req = new ParameterUpdateDTO();
        req.setDescription(request.getDescription());
        req.setValue(request.getValue());
        req.setDefaultValue(request.getDefaultValue());
        req.setName(request.getName());
        req.setType((byte) request.getType());
        req.setScope(request.getScope());
        return req;
    }

    private void toGrpcResp(StreamObserver<ParameterResp> responseObserver, ParameterDTO parameter) {
        ParameterResp parameterResp = ParameterResp.newBuilder()
                .setId(parameter.getId())
                .setStatus(parameter.getStatus())
                .setApplyTime(Timestamp.newBuilder().setSeconds(parameter.getApplyTime().getTime() / 1000))
                .setDescription(StringUtil.getNonNullValue(parameter.getDescription()))
                .setValue(StringUtil.getNonNullValue(parameter.getValue()))
                .setDefaultValue(StringUtil.getNonNullValue(parameter.getDefaultValue()))
                .setName(parameter.getName())
                .setType(parameter.getType())
                .setScope(StringUtil.getNonNullValue(parameter.getScope()))
                .build();
        responseObserver.onNext(parameterResp);
        responseObserver.onCompleted();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void getOrCreateParameter(getOrCreateReq request, StreamObserver<StringValue> responseObserver) {
        String value = getOrCreateParameter((byte) request.getType(), request.getScope(), request.getName(), request.getDefaultValue(), request.getDescription());
        responseObserver.onNext(StringValue.of(value));
        responseObserver.onCompleted();
    }

    @Override
    public ParameterDao getDao() {
        return parameterDao;
    }
}
