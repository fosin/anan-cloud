package top.fosin.anan.platform.service;

import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import top.fosin.anan.cloudresource.constant.PlatformRedisConstant;
import top.fosin.anan.cloudresource.dto.res.AnanOrganizationRespDto;
import top.fosin.anan.cloudresource.dto.res.AnanOrganizationTreeDto;
import top.fosin.anan.cloudresource.service.AnanUserDetailService;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.jpa.repository.IJpaRepository;
import top.fosin.anan.model.module.LogicalQueryRule;
import top.fosin.anan.model.module.RelaQueryRule;
import top.fosin.anan.model.module.RelationalOperator;
import top.fosin.anan.platform.dto.req.AnanOrganizationReqDto;
import top.fosin.anan.platform.entity.AnanOrganizationEntity;
import top.fosin.anan.platform.repository.OrganizationRepository;
import top.fosin.anan.platform.service.inter.OrganizationService;
import top.fosin.anan.redis.cache.AnanCacheManger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * @author fosin
 * @date 2017/12/29
 */
@Service
@Lazy
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationRepository organizationRepository;
    private final AnanUserDetailService ananUserDetailService;

    public OrganizationServiceImpl(OrganizationRepository organizationRepository, AnanUserDetailService ananUserDetailService, AnanCacheManger ananCacheManger) {
        this.organizationRepository = organizationRepository;
        this.ananUserDetailService = ananUserDetailService;
        this.ananCacheManger = ananCacheManger;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(value = PlatformRedisConstant.ANAN_ORGANIZATION, key = "#result.id")
    public AnanOrganizationRespDto create(AnanOrganizationReqDto reqDto) {
        AnanOrganizationEntity createEntity = new AnanOrganizationEntity();
        BeanUtils.copyProperties(reqDto, createEntity);
        Long pid = reqDto.getPid();
        int level = 1;
        if (pid == 0) {
            ananUserDetailService.clear();
            Assert.isTrue(ananUserDetailService.hasSysAdminRole(), "只有超级管理员才能创建顶级机构!");
            createEntity.setTopId(0L);
        } else {
            AnanOrganizationEntity parentEntity = organizationRepository.findById(pid).orElse(null);
            Assert.notNull(parentEntity, "传入的创建数据实体找不到对于的父节点数据!");
            level = parentEntity.getLevel() + 1;
        }
        createEntity.setLevel(level);
        AnanOrganizationEntity result = organizationRepository.save(createEntity);
        if (pid == 0) {
            result.setTopId(result.getId());
            result = organizationRepository.save(result);
        }
        return BeanUtil.copyProperties(result, AnanOrganizationRespDto.class);
    }

    @Override
    @CacheEvict(value = PlatformRedisConstant.ANAN_ORGANIZATION, key = "#updateDto.id")
    @Transactional(rollbackFor = Exception.class)
    public void update(AnanOrganizationReqDto updateDto) {
        Long id = updateDto.getId();
        Assert.notNull(id, "无效的字典代码code");
        AnanOrganizationEntity updateEntity = organizationRepository.findById(id).orElse(null);
        BeanUtils.copyProperties(updateDto, Objects.requireNonNull(updateEntity, "根据传入的机构序号" + id + "在数据库中未能找到对于数据!"));

        Long pid = updateDto.getPid();
        if (!updateEntity.getPid().equals(pid)) {
            organizationRepository.findById(pid).ifPresent(sentity -> updateEntity.setLevel(sentity.getLevel() + 1));
        }
        organizationRepository.save(updateEntity);
    }

    @Override
    @Cacheable(value = PlatformRedisConstant.ANAN_ORGANIZATION, key = "#id")
    public AnanOrganizationRespDto findOneById(Long id) {
        return OrganizationService.super.findOneById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = PlatformRedisConstant.ANAN_ORGANIZATION, key = "#id")
    public void deleteById(Long id) {
        List<AnanOrganizationTreeDto> dtos = listChild(id);
        Assert.isTrue(dtos == null || dtos.size() == 0, "该节点还存在子节点不能直接删除!");
        organizationRepository.deleteById(id);
    }

    /**
     * 根据主键删除多条数据
     *
     * @param ids 主键编号集合
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByIds(Collection<Long> ids) {
        List<AnanOrganizationEntity> entities = organizationRepository.findAllById(ids);
        for (AnanOrganizationEntity entity : entities) {
            Long id = entity.getId();
            List<AnanOrganizationTreeDto> dtos = listChild(id);
            Assert.isTrue(dtos == null || dtos.size() == 0, "该节点还存在子节点不能直接删除!");
            organizationRepository.delete(entity);
        }
        for (AnanOrganizationEntity entity : entities) {
            ananCacheManger.evict(PlatformRedisConstant.ANAN_ORGANIZATION, entity.getId() + "");
        }
    }

    private final AnanCacheManger ananCacheManger;

    @Override
    public List<AnanOrganizationTreeDto> listChild(Long pid) {
        return BeanUtil.copyCollectionProperties(organizationRepository.findByPidOrderByCodeAsc(pid),
                AnanOrganizationTreeDto.class);
    }

    @Override
    public List<AnanOrganizationTreeDto> listAllChild(Long pid) {
        List<AnanOrganizationEntity> result = new ArrayList<>();
        if (pid == 0) {
            List<AnanOrganizationEntity> list = organizationRepository.findByPidOrderByCodeAsc(pid);
            for (AnanOrganizationEntity organizationEntity : list) {
                List<AnanOrganizationEntity> byCodeStartingWith = organizationRepository.findByTopIdAndCodeStartingWithOrderByCodeAsc(organizationEntity.getTopId(), organizationEntity.getCode());
                result.addAll(byCodeStartingWith);
            }
        } else {
            AnanOrganizationEntity organizationEntity = organizationRepository.findById(pid).orElse(null);
            if (organizationEntity != null) {
                List<AnanOrganizationEntity> byCodeStartingWith = organizationRepository.findByTopIdAndCodeStartingWithOrderByCodeAsc(organizationEntity.getTopId(), organizationEntity.getCode());
                result.addAll(byCodeStartingWith);
            }
        }
        return BeanUtil.copyCollectionProperties(result, AnanOrganizationTreeDto.class);
    }

    @Override
    public AnanOrganizationTreeDto treeAllChildByid(Long id) {
        AnanOrganizationEntity entity = organizationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("根据ID查询不到数据!"));
        AnanOrganizationReqDto dto = new AnanOrganizationReqDto();
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
    public IJpaRepository<AnanOrganizationEntity, Long> getRepository() {
        return organizationRepository;
    }
}
