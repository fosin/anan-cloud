package top.fosin.anan.platform.service;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
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
import top.fosin.anan.core.exception.AnanServiceException;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.platform.entity.AnanOrganizationEntity;
import top.fosin.anan.platform.entity.AnanParameterEntity;
import top.fosin.anan.platform.repository.OrganizationRepository;
import top.fosin.anan.platform.repository.ParameterRepository;
import top.fosin.anan.platform.service.inter.ParameterService;
import top.fosin.anan.cloudresource.parameter.OrganStrategy;
import top.fosin.anan.cloudresource.service.AnanUserDetailService;
import top.fosin.anan.redis.cache.AnanCacheManger;

import java.util.Date;
import java.util.List;

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
    @CachePut(value = RedisConstant.ANAN_PARAMETER, key = "#root.target.getCacheKey(#result)")
    public AnanParameterRespDto create(AnanParameterCreateDto entity) {
        Assert.notNull(entity, "传入的创建数据实体对象不能为空!");
        AnanParameterEntity createEntiy = new AnanParameterEntity();
        BeanUtils.copyProperties(entity, createEntiy);
        return BeanUtil.copyProperties(getRepository().save(createEntiy), AnanParameterRespDto.class);
    }

    @Override
    public void update(AnanParameterUpdateDto entity) {
        Assert.notNull(entity, "传入了空对象!");
        Long id = entity.getId();
        Assert.notNull(id, "ID不能为空!");
        AnanParameterEntity cEntity = parameterRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("通过ID：" + id + "未能找到对应的数据!"));
        String oldCacheKey = getCacheKey(cEntity);
        String newCacheKey = getCacheKey(entity.getType(), entity.getScope(), entity.getName());

        BeanUtils.copyProperties(entity, cEntity,
                BeanUtil.getNullProperties(entity));
        parameterRepository.save(cEntity);
        //如果修改了type、scope、name则需要删除以前的缓存并设置新缓存
        if (!oldCacheKey.equals(newCacheKey)) {
            //新key设置旧值，需要发布以后才刷新缓存换成本次更新的新值
            ananCacheManger.put(RedisConstant.ANAN_PARAMETER, newCacheKey, ananCacheManger.getCache(oldCacheKey));
            ananCacheManger.evict(RedisConstant.ANAN_PARAMETER, oldCacheKey);
        }
    }

    @Override
    public void deleteById(Long id) {
        AnanParameterEntity entity = parameterRepository.findById(id).orElse(null);
        Assert.notNull(entity, "通过ID没有能找到参数数据,删除被取消!");
        String cacheKey = getCacheKey(entity);
        ananCacheManger.evict(RedisConstant.ANAN_PARAMETER, cacheKey);
        parameterRepository.deleteById(id);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new AnanServiceException(e);
        }
        ananCacheManger.evict(RedisConstant.ANAN_PARAMETER, cacheKey);
    }

    @Override
    @CacheEvict(value = RedisConstant.ANAN_PARAMETER, key = "#root.target.getCacheKey(#entity)")
    public void deleteByDto(AnanParameterUpdateDto entity) {
        Assert.notNull(entity, "传入了空对象!");
        parameterRepository.findById(entity.getId()).ifPresent(parameterRepository::delete);
    }

    public String getCacheKey(AnanParameterEntity entity) {
        return getCacheKey(entity.getType(), entity.getScope(), entity.getName());
    }

    public String getCacheKey(Integer type, String scope, String name) {
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
    @Transactional(rollbackFor = AnanServiceException.class)
    public Boolean applyChange(Long id) {
        AnanParameterEntity entity = parameterRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("该参数已经不存在!"));
        String cacheKey = getCacheKey(entity);
        boolean success;
        switch (entity.getStatus()) {
            case 1:
                entity.setApplyBy(ananUserDetailService.getAnanUserId());
                entity.setApplyTime(new Date());
                entity.setStatus(0);
                success = ananCacheManger.put(RedisConstant.ANAN_PARAMETER, cacheKey, BeanUtil.copyProperties(entity, AnanParameterRespDto.class));
                if (success) {
                    parameterRepository.save(entity);
                }
                break;
            case 2:
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
    @Transactional(rollbackFor = AnanServiceException.class)
    public Boolean applyChanges() {
        List<AnanParameterEntity> entities = parameterRepository.findByStatusNot(0);
        Assert.isTrue(entities != null && entities.size() != 0, "没有更改过任何参数，不需要发布!");
        for (AnanParameterEntity entity : entities) {
            applyChange(entity.getId());
        }
        return true;
    }

    @Override
    public ParameterRepository getRepository() {
        return parameterRepository;
    }
}
