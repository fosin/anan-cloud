package top.fosin.anan.platform.service;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import top.fosin.anan.cloudresource.constant.RedisConstant;
import top.fosin.anan.cloudresource.dto.req.AnanParameterCreateDto;
import top.fosin.anan.cloudresource.dto.req.AnanParameterUpdateDto;
import top.fosin.anan.cloudresource.dto.res.AnanParameterRespDto;
import top.fosin.anan.cloudresource.parameter.OrganStrategy;
import top.fosin.anan.cloudresource.parameter.ParameterStatus;
import top.fosin.anan.cloudresource.service.AnanUserDetailService;
import top.fosin.anan.core.exception.AnanServiceException;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.platform.entity.AnanOrganizationEntity;
import top.fosin.anan.platform.entity.AnanParameterEntity;
import top.fosin.anan.platform.repository.OrganizationRepository;
import top.fosin.anan.platform.repository.ParameterRepository;
import top.fosin.anan.platform.service.inter.ParameterService;
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
@Service
@Lazy
@Slf4j
public class ParameterServiceImpl implements ParameterService {
    private final ParameterRepository parameterRepository;
    private final AnanUserDetailService ananUserDetailService;
    private final OrganizationRepository organizationRepository;
    private final AnanCacheManger ananCacheManger;

    public ParameterServiceImpl(ParameterRepository parameterRepository, AnanUserDetailService ananUserDetailService, OrganizationRepository organizationRepository, AnanCacheManger ananCacheManger) {
        this.parameterRepository = parameterRepository;
        this.ananUserDetailService = ananUserDetailService;
        this.organizationRepository = organizationRepository;
        this.ananCacheManger = ananCacheManger;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(value = RedisConstant.ANAN_PARAMETER, key = "#root.target.getCacheKey(#result)")
    public AnanParameterRespDto create(AnanParameterCreateDto dto) {
        AnanParameterEntity createEntiy = BeanUtil.copyProperties(dto, AnanParameterEntity.class);
        return BeanUtil.copyProperties(getRepository().save(createEntiy), AnanParameterRespDto.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(AnanParameterUpdateDto dto) {
        Long id = dto.getId();
        Assert.notNull(id, "ID不能为空!");
        AnanParameterEntity cEntity = parameterRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("通过ID：" + id + "未能找到对应的数据!"));
        String oldCacheKey = getCacheKey(cEntity);
        String newCacheKey = getCacheKey(dto.getType(), dto.getScope(), dto.getName());

        BeanUtils.copyProperties(dto, cEntity);
        cEntity.setStatus(ParameterStatus.Modified.getTypeValue());
        parameterRepository.save(cEntity);
        //如果修改了type、scope、name则需要删除以前的缓存并设置新缓存
        if (!oldCacheKey.equals(newCacheKey)) {
            //新key设置旧值，需要发布以后才刷新缓存换成本次更新的新值
            ananCacheManger.put(RedisConstant.ANAN_PARAMETER, newCacheKey,
                    ananCacheManger.get(RedisConstant.ANAN_PARAMETER, oldCacheKey,AnanParameterRespDto.class));
            ananCacheManger.evict(RedisConstant.ANAN_PARAMETER, oldCacheKey);
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
        parameterRepository.findById(id).ifPresent(entity -> {
            entity.setStatusValue(ParameterStatus.Deleted.getTypeValue());
            parameterRepository.save(entity);
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
        List<AnanParameterEntity> entities = parameterRepository.findAllById(ids);
        for (AnanParameterEntity entity : entities) {
            entity.setStatusValue(ParameterStatus.Deleted.getTypeValue());
        }
        parameterRepository.saveAll(entities);
    }

    /**
     * 取消删除
     *
     * @param ids 主键
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelDelete(Collection<Long> ids) {
        List<AnanParameterEntity> entities = parameterRepository.findAllById(ids);
        for (AnanParameterEntity entity : entities) {
            entity.setStatusValue(ParameterStatus.Normal.getTypeValue());
        }
        parameterRepository.saveAll(entities);
    }

    protected String getCacheKey(AnanParameterEntity entity) {
        return getCacheKey(entity.getType(), entity.getScope(), entity.getName());
    }

    public String getCacheKey(AnanParameterRespDto dto) {
        return getCacheKey(dto.getType(), dto.getScope(), dto.getName());
    }

    protected String getCacheKey(Integer type, String scope, String name) {
        if (StringUtils.isEmpty(scope)) {
            scope = "";
        }
        return type + "-" + scope + "-" + name;
    }

    @Override
    @Cacheable(value = RedisConstant.ANAN_PARAMETER, key = "#root.target.getCacheKey(#type,#scope,#name)")
    public AnanParameterRespDto getParameter(Integer type, String scope, String name) {
        AnanParameterEntity entity = parameterRepository.findByTypeAndScopeAndName(type, scope, name);
        AnanParameterRespDto respDto = BeanUtil.copyProperties(entity, AnanParameterRespDto.class);
        //因为参数会逐级上上级机构查找，为减少没有必要的查询，该代码为解决Spring Cache默认不缓存null值问题
        if (respDto == null) {
            respDto = new AnanParameterRespDto();
        }
        return respDto;
    }

    /**
     * 得到机构链中最接近的参数
     *
     * @param scope 机构ID AnanParameterEntity.scope
     * @param name  参数名称 AnanParameterEntity.name
     * @return 参数
     */
    @Override
    @Cacheable(value = RedisConstant.ANAN_PARAMETER, key = "#root.target.getCacheKey(#type,#scope,#name)")
    @Transactional(rollbackFor = Exception.class)
    public AnanParameterRespDto getNearestParameter(int type, String scope, String name) {
        AnanParameterEntity parameter = parameterRepository.findByTypeAndScopeAndName(type, scope, name);
        boolean finded = parameter != null && parameter.getId() != null;
        AnanParameterRespDto respDto;
        if (finded) {
            respDto = BeanUtil.copyProperties(parameter, AnanParameterRespDto.class);
        } else {
            //parameter为空表示没有参数记录，则依次向上找父机构的参数
            Assert.isTrue(!StringUtils.isEmpty(scope), "没有从参数[" + "type:" + type + " scope:" + scope + " name:" + name + "]中查询到参数");
            respDto = getNearestParameter(type, getNearestScope(type, scope), name);
        }

        return respDto;
    }

    private String getNearestScope(int type, String scope) {
        String rc = null;
        OrganStrategy organStrategy = new OrganStrategy(ananUserDetailService);
        if (type == organStrategy.getType()) {
            Long id = Long.parseLong(scope);
            AnanOrganizationEntity entity = organizationRepository.findById(id).orElse(null);
            if (entity != null && entity.getLevel() != 0) {
                rc = entity.getPid() + "";
            }
        }
        return rc;
    }

    @Override
    @Cacheable(value = RedisConstant.ANAN_PARAMETER, key = "#root.target.getCacheKey(#type,#scope,#name)")
    @Transactional(rollbackFor = Exception.class)
    public AnanParameterRespDto getOrCreateParameter(int type, String scope, String name, String defaultValue, String description) {
        AnanParameterRespDto respDto;
        try {
            respDto = getNearestParameter(type, scope, name);
        } catch (IllegalArgumentException e) {
            log.debug("报异常说明没有找到任何相关参数，则需要创建一个无域参数，这样默认所有机构共享这一个参数，如果需要设置机构个性化参数则需要在前端手动创建");
            AnanParameterCreateDto createDto = new AnanParameterCreateDto();
            createDto.setValue(defaultValue);
            createDto.setType(type);
            createDto.setScope(scope);
            createDto.setName(name);
            createDto.setDefaultValue(defaultValue);
            createDto.setDescription(description);
            createDto.setStatus(0);
            respDto = create(createDto);
        }
        return respDto;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean applyChange(Long id) {
        AnanParameterEntity entity = parameterRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("该参数[" + id + "]已经不存在!"));
        String cacheKey = getCacheKey(entity);
        boolean success;
        switch (Objects.requireNonNull(ParameterStatus.valueOf(entity.getStatus()))) {
            case Modified:
                entity.setApplyBy(ananUserDetailService.getAnanUserId());
                entity.setApplyTime(new Date());
                entity.setStatus(0);
                success = ananCacheManger.put(RedisConstant.ANAN_PARAMETER, cacheKey, BeanUtil.copyProperties(entity, AnanParameterRespDto.class));
                if (success) {
                    parameterRepository.save(entity);
                }
                break;
            case Deleted:
                success = ananCacheManger.evict(RedisConstant.ANAN_PARAMETER, cacheKey);
                if (success) {
                    parameterRepository.deleteById(id);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new AnanServiceException(e);
                    }
                    success = ananCacheManger.evict(RedisConstant.ANAN_PARAMETER, cacheKey);
                }
                break;
            default:
                success = ananCacheManger.put(RedisConstant.ANAN_PARAMETER, cacheKey, BeanUtil.copyProperties(entity, AnanParameterRespDto.class));
        }
        return success;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean applyChanges() {
        List<AnanParameterEntity> entities = parameterRepository.findByStatusNot(0);
        Assert.isTrue(entities != null && entities.size() != 0, "没有更改过任何参数，不需要发布!");
        for (AnanParameterEntity entity : entities) {
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
    public ParameterRepository getRepository() {
        return parameterRepository;
    }
}
