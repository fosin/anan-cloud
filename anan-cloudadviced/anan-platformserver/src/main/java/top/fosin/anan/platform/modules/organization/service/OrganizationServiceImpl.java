package top.fosin.anan.platform.modules.organization.service;

import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import top.fosin.anan.cloudresource.constant.PlatformRedisConstant;
import top.fosin.anan.cloudresource.dto.res.OrganizationRespDto;
import top.fosin.anan.cloudresource.dto.res.OrganizationTreeDto;
import top.fosin.anan.cloudresource.service.AnanUserDetailService;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.jpa.repository.IJpaRepository;
import top.fosin.anan.model.module.LogicalQueryRule;
import top.fosin.anan.model.module.RelaQueryRule;
import top.fosin.anan.model.module.RelationalOperator;
import top.fosin.anan.platform.modules.organization.dao.OrganizationDao;
import top.fosin.anan.platform.modules.organization.dto.OrganizationReqDto;
import top.fosin.anan.platform.modules.organization.entity.Organization;
import top.fosin.anan.platform.modules.organization.service.inter.OrganizationService;
import top.fosin.anan.redis.cache.AnanCacheManger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author fosin
 * @date 2017/12/29
 */
@Service
@Lazy
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationDao organizationDao;
    private final AnanUserDetailService ananUserDetailService;

    public OrganizationServiceImpl(OrganizationDao organizationDao, AnanUserDetailService ananUserDetailService, AnanCacheManger ananCacheManger) {
        this.organizationDao = organizationDao;
        this.ananUserDetailService = ananUserDetailService;
        this.ananCacheManger = ananCacheManger;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(value = PlatformRedisConstant.ANAN_ORGANIZATION, key = "#result.id")
    public OrganizationRespDto create(OrganizationReqDto reqDto) {
        Organization createEntity = new Organization();
        BeanUtils.copyProperties(reqDto, createEntity);
        Long pid = reqDto.getPid();
        int level = 1;
        if (pid == 0) {
            ananUserDetailService.clear();
            Assert.isTrue(ananUserDetailService.hasSysAdminRole(), "只有超级管理员才能创建顶级机构!");
            createEntity.setTopId(0L);
        } else {
            Organization parentEntity = organizationDao.findById(pid).orElse(null);
            Assert.notNull(parentEntity, "传入的创建数据实体找不到对于的父节点数据!");
            level = parentEntity.getLevel() + 1;
        }
        createEntity.setLevel(level);
        Organization result = organizationDao.save(createEntity);
        if (pid == 0) {
            result.setTopId(result.getId());
            result = organizationDao.save(result);
        }
        return BeanUtil.copyProperties(result, OrganizationRespDto.class);
    }

    @Override
    @CacheEvict(value = PlatformRedisConstant.ANAN_ORGANIZATION, key = "#updateDto.id")
    @Transactional(rollbackFor = Exception.class)
    public void update(OrganizationReqDto updateDto) {
        Long id = updateDto.getId();
        Assert.notNull(id, "无效的字典代码code");
        Organization updateEntity = organizationDao.findById(id).orElseThrow(() -> new IllegalArgumentException("根据传入的机构序号" + id + "在数据库中未能找到对于数据!"));
        BeanUtils.copyProperties(updateDto,updateEntity);
        Long pid = updateDto.getPid();
        if (!updateEntity.getPid().equals(pid)) {
            organizationDao.findById(pid).ifPresent(sentity -> updateEntity.setLevel(sentity.getLevel() + 1));
        }
        organizationDao.save(updateEntity);
    }

    @Override
    @Cacheable(value = PlatformRedisConstant.ANAN_ORGANIZATION, key = "#id")
    public OrganizationRespDto findOneById(Long id) {
        return OrganizationService.super.findOneById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = PlatformRedisConstant.ANAN_ORGANIZATION, key = "#id")
    public void deleteById(Long id) {
        List<OrganizationTreeDto> dtos = listChild(id);
        Assert.isTrue(dtos == null || dtos.size() == 0, "该节点还存在子节点不能直接删除!");
        organizationDao.deleteById(id);
    }

    /**
     * 根据主键删除多条数据
     *
     * @param ids 主键编号集合
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByIds(Collection<Long> ids) {
        List<Organization> entities = organizationDao.findAllById(ids);
        for (Organization entity : entities) {
            Long id = entity.getId();
            List<OrganizationTreeDto> dtos = listChild(id);
            Assert.isTrue(dtos == null || dtos.size() == 0, "该节点还存在子节点不能直接删除!");
            organizationDao.delete(entity);
        }
        for (Organization entity : entities) {
            ananCacheManger.evict(PlatformRedisConstant.ANAN_ORGANIZATION, entity.getId() + "");
        }
    }

    private final AnanCacheManger ananCacheManger;

    @Override
    public List<OrganizationTreeDto> listChild(Long pid) {
        return BeanUtil.copyProperties(organizationDao.findByPidOrderByCodeAsc(pid),
                OrganizationTreeDto.class);
    }

    @Override
    public List<OrganizationTreeDto> listAllChild(Long pid) {
        List<Organization> result = new ArrayList<>();
        if (pid == 0) {
            List<Organization> list = organizationDao.findByPidOrderByCodeAsc(pid);
            for (Organization organization : list) {
                List<Organization> byCodeStartingWith = organizationDao.findByTopIdAndCodeStartingWithOrderByCodeAsc(organization.getTopId(), organization.getCode());
                result.addAll(byCodeStartingWith);
            }
        } else {
            Organization organization = organizationDao.findById(pid).orElse(null);
            if (organization != null) {
                List<Organization> byCodeStartingWith = organizationDao.findByTopIdAndCodeStartingWithOrderByCodeAsc(organization.getTopId(), organization.getCode());
                result.addAll(byCodeStartingWith);
            }
        }
        return BeanUtil.copyProperties(result, OrganizationTreeDto.class);
    }

    @Override
    public OrganizationTreeDto treeAllChildByid(Long id) {
        Organization entity = organizationDao.findById(id).orElseThrow(() -> new IllegalArgumentException("根据ID查询不到数据!"));
        OrganizationReqDto dto = new OrganizationReqDto();
        dto.setTopId(entity.getTopId());
        dto.setCode(entity.getCode());
        LogicalQueryRule logicalQueryRule = new LogicalQueryRule();
        List<RelaQueryRule> relaQueryRules = new ArrayList<>();
        logicalQueryRule.setRelaRules(relaQueryRules);
        relaQueryRules.add(new RelaQueryRule(RelationalOperator.rightLike, "code"));
        relaQueryRules.add(new RelaQueryRule(RelationalOperator.equal, "topId"));
        dto.setQueryRule(logicalQueryRule);
        return this.treeAllChild(dto, id);
    }

    @Override
    public IJpaRepository<Organization, Long> getRepository() {
        return organizationDao;
    }
}
