package top.fosin.anan.platform.modules.pub.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import top.fosin.anan.cloudresource.constant.PlatformRedisConstant;
import top.fosin.anan.cloudresource.dto.req.ParameterReqDto;
import top.fosin.anan.cloudresource.dto.res.ParameterRespDto;
import top.fosin.anan.cloudresource.parameter.OrganStrategy;
import top.fosin.anan.cloudresource.parameter.ParameterStatus;
import top.fosin.anan.cloudresource.service.AnanUserDetailService;
import top.fosin.anan.core.exception.AnanServiceException;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.jpa.util.JpaUtil;
import top.fosin.anan.model.dto.req.PageDto;
import top.fosin.anan.model.result.PageResult;
import top.fosin.anan.model.result.ResultUtils;
import top.fosin.anan.platform.modules.organization.dao.OrgDao;
import top.fosin.anan.platform.modules.organization.entity.Organization;
import top.fosin.anan.platform.modules.pub.dao.ParameterDao;
import top.fosin.anan.platform.modules.pub.entity.Parameter;
import top.fosin.anan.platform.modules.pub.service.inter.ParameterService;
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
    private final ParameterDao parameterDao;
    private final AnanUserDetailService ananUserDetailService;
    private final OrgDao orgDao;
    private final AnanCacheManger ananCacheManger;

    public ParameterServiceImpl(ParameterDao parameterDao, AnanUserDetailService ananUserDetailService, OrgDao orgDao, AnanCacheManger ananCacheManger) {
        this.parameterDao = parameterDao;
        this.ananUserDetailService = ananUserDetailService;
        this.orgDao = orgDao;
        this.ananCacheManger = ananCacheManger;
    }

    @Override
    public PageResult<ParameterRespDto> findPage(PageDto<ParameterReqDto> PageDto) {
        ParameterReqDto params = PageDto.getParams();
        PageRequest pageable = PageRequest.of(PageDto.getPageNumber() - 1, PageDto.getPageSize(),
                JpaUtil.buildSortRules(params.getSortRules()));
        String search = "%" + params.getName() + "%";
        Long organizId = ananUserDetailService.getAnanOrganizId();
        boolean sysAdminUser = ananUserDetailService.isSysAdminUser();
        int type = 2;
        String code = "";
        if (!sysAdminUser) {
            type = 1;
            Organization organization = orgDao.findById(organizId)
                    .orElseThrow(() -> new IllegalArgumentException("未找到你的机构信息!"));
            code = organization.getCode();
        }

        //分页查找
        Page<Parameter> page = parameterDao.findPage(search, code, type, pageable);
        return ResultUtils.success(page.getTotalElements(), page.getTotalPages(),
                BeanUtil.copyProperties(page.getContent(), ParameterRespDto.class));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(value = PlatformRedisConstant.ANAN_PARAMETER, key = "#root.target.getCacheKey(#result)")
    public ParameterRespDto create(ParameterReqDto dto) {
        Parameter createEntiy = BeanUtil.copyProperties(dto, Parameter.class);
        return BeanUtil.copyProperties(getDao().save(createEntiy), ParameterRespDto.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ParameterReqDto dto) {
        Long id = dto.getId();
        Assert.notNull(id, "ID不能为空!");
        Parameter cEntity = parameterDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("通过ID：" + id + "未能找到对应的数据!"));
        if (!ananUserDetailService.isSysAdminUser()) {
            Assert.isTrue(StringUtils.hasText(cEntity.getScope()), "非超级管理员不能修改公共参数!");
        }
        String oldCacheKey = getCacheKey(cEntity);
        String newCacheKey = getCacheKey(dto.getType(), dto.getScope(), dto.getName());

        BeanUtils.copyProperties(dto, cEntity);
        cEntity.setStatus(ParameterStatus.Modified.getTypeValue());
        parameterDao.save(cEntity);
        //如果修改了type、scope、name则需要删除以前的缓存并设置新缓存
        if (!oldCacheKey.equals(newCacheKey)) {
            //新key设置旧值，需要发布以后才刷新缓存换成本次更新的新值
            ParameterRespDto respDto = ananCacheManger.get(PlatformRedisConstant.ANAN_PARAMETER, oldCacheKey, ParameterRespDto.class);
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
            if (!ananUserDetailService.isSysAdminUser()) {
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
            if (!ananUserDetailService.isSysAdminUser()) {
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
    public void cancelDelete(Collection<Long> ids) {
        List<Parameter> entities = parameterDao.findAllById(ids);
        for (Parameter entity : entities) {
            if (!ananUserDetailService.isSysAdminUser()) {
                Assert.isTrue(StringUtils.hasText(entity.getScope()), "非超级管理员不能取消删除公共参数!");
            }
            entity.setStatus(ParameterStatus.Normal.getTypeValue());
        }
        parameterDao.saveAll(entities);
    }

    protected String getCacheKey(Parameter entity) {
        return getCacheKey(entity.getType(), entity.getScope(), entity.getName());
    }

    public String getCacheKey(ParameterRespDto dto) {
        return getCacheKey(dto.getType(), dto.getScope(), dto.getName());
    }

    public String getCacheKey(Integer type, String scope, String name) {
        if (!StringUtils.hasText(scope)) {
            scope = "";
        }
        return type + "-" + scope + "-" + name;
    }

    @Override
    @Cacheable(value = PlatformRedisConstant.ANAN_PARAMETER, key = "#root.target.getCacheKey(#type,#scope,#name)")
    public ParameterRespDto getParameter(Integer type, String scope, String name) {
        Parameter entity = parameterDao.findByTypeAndScopeAndName(type, scope, name);
        ParameterRespDto respDto = BeanUtil.copyProperties(entity, ParameterRespDto.class);
        //因为参数会逐级上上级机构查找，为减少没有必要的查询，该代码为解决Spring Cache默认不缓存null值问题
        if (respDto == null) {
            respDto = new ParameterRespDto();
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
    @Cacheable(value = PlatformRedisConstant.ANAN_PARAMETER, key = "#root.target.getCacheKey(#type,#scope,#name)")
    @Transactional(rollbackFor = Exception.class)
    public ParameterRespDto getNearestParameter(int type, String scope, String name) {
        Parameter parameter = parameterDao.findByTypeAndScopeAndName(type, scope, name);
        boolean finded = parameter != null && parameter.getId() != null;
        ParameterRespDto respDto;
        if (finded) {
            respDto = BeanUtil.copyProperties(parameter, ParameterRespDto.class);
        } else {
            //parameter为空表示没有参数记录，则依次向上找父机构的参数
            Assert.isTrue(StringUtils.hasText(scope), "没有从参数[" + "type:" + type + " scope:" + scope + " name:" + name + "]中查询到参数");
            respDto = getNearestParameter(type, getNearestScope(type, scope), name);
        }

        return respDto;
    }

    private String getNearestScope(int type, String scope) {
        String rc = null;
        //机构参数存在上下级关系，需要逐级向上查找
        OrganStrategy organStrategy = new OrganStrategy(ananUserDetailService);
        if (type == organStrategy.getType()) {
            Long id = Long.parseLong(scope);
            Organization entity = orgDao.findById(id).orElse(null);
            if (entity != null && entity.getLevel() != 0) {
                rc = entity.getPid() + "";
            }
        }
        //用户参数、服务参数等直接返回null
        return rc;
    }

    @Override
    @Cacheable(value = PlatformRedisConstant.ANAN_PARAMETER, key = "#root.target.getCacheKey(#type,#scope,#name)")
    @Transactional(rollbackFor = Exception.class)
    public ParameterRespDto getOrCreateParameter(int type, String scope, String name, String defaultValue, String description) {
        ParameterRespDto respDto;
        try {
            respDto = getNearestParameter(type, scope, name);
        } catch (IllegalArgumentException e) {
            log.debug("报异常说明没有找到任何相关参数，则需要创建一个无域参数，这样默认所有机构共享这一个参数，如果需要设置机构个性化参数则需要在前端手动创建");
            ParameterReqDto createDto = new ParameterReqDto();
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
        Parameter entity = parameterDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("该参数[" + id + "]已经不存在!"));
        if (!ananUserDetailService.isSysAdminUser()) {
            Assert.isTrue(StringUtils.hasText(entity.getScope()), "非超级管理员不能发布公共参数!");
        }
        String cacheKey = getCacheKey(entity);
        boolean success;
        switch (Objects.requireNonNull(ParameterStatus.valueOf(entity.getStatus()))) {
            case Modified:
                entity.setApplyBy(ananUserDetailService.getAnanUserId());
                entity.setApplyTime(new Date());
                entity.setStatus(0);
                success = ananCacheManger.put(PlatformRedisConstant.ANAN_PARAMETER, cacheKey, BeanUtil.copyProperties(entity, ParameterRespDto.class));
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
                success = ananCacheManger.put(PlatformRedisConstant.ANAN_PARAMETER, cacheKey, BeanUtil.copyProperties(entity, ParameterRespDto.class));
        }
        return success;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean applyChangeAll() {
        List<Parameter> entities = parameterDao.findByStatusNot(0);
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
    public ParameterDao getDao() {
        return parameterDao;
    }
}
