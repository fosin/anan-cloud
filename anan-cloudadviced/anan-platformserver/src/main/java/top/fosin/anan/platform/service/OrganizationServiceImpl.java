package top.fosin.anan.platform.service;

import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import top.fosin.anan.cloudresource.constant.RedisConstant;
import top.fosin.anan.platform.dto.request.AnanOrganizationCreateDto;
import top.fosin.anan.platform.dto.request.AnanOrganizationRetrieveDto;
import top.fosin.anan.platform.dto.request.AnanOrganizationUpdateDto;
import top.fosin.anan.cloudresource.dto.res.AnanOrganizationRespDto;
import top.fosin.anan.cloudresource.dto.res.AnanOrganizationTreeDto;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.jpa.repository.IJpaRepository;
import top.fosin.anan.model.module.OperatorMode;
import top.fosin.anan.model.module.QueryRule;
import top.fosin.anan.platform.entity.AnanOrganizationEntity;
import top.fosin.anan.platform.repository.OrganizationRepository;
import top.fosin.anan.platform.service.inter.OrganizationService;
import top.fosin.anan.cloudresource.service.AnanUserDetailService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 2017/12/29.
 * Time:12:31
 *
 * @author fosin
 */
@Service
@Lazy
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationRepository organizationRepository;
    private final AnanUserDetailService ananUserDetailService;

    public OrganizationServiceImpl(OrganizationRepository organizationRepository, AnanUserDetailService ananUserDetailService) {
        this.organizationRepository = organizationRepository;
        this.ananUserDetailService = ananUserDetailService;
    }

    @Override
    @CachePut(value = RedisConstant.ANAN_ORGANIZATION, key = "#result.id")
    public AnanOrganizationRespDto create(AnanOrganizationCreateDto entity) {
        Assert.notNull(entity, "传入的创建数据实体对象不能为空!");
        AnanOrganizationEntity createEntity = new AnanOrganizationEntity();
        BeanUtils.copyProperties(entity, createEntity);
        Long pid = entity.getPid();
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
    @CacheEvict(value = RedisConstant.ANAN_ORGANIZATION, key = "#entity.id")
    public void update(AnanOrganizationUpdateDto entity) {
        Assert.notNull(entity, "无效的更新数据");
        Long id = entity.getId();
        Assert.notNull(id, "无效的字典代码code");
        AnanOrganizationEntity updateEntity = organizationRepository.findById(id).orElse(null);
        BeanUtils.copyProperties(entity, Objects.requireNonNull(updateEntity, "根据传入的机构ID" + id + "在数据库中未能找到对于数据!"));

        Long pid = entity.getPid();
        if (!updateEntity.getPid().equals(pid)) {
            organizationRepository.findById(pid).ifPresent(sentity -> updateEntity.setLevel(sentity.getLevel() + 1));
        }
        organizationRepository.save(updateEntity);
    }

    @Override
    @Cacheable(value = RedisConstant.ANAN_ORGANIZATION, key = "#id")
    public AnanOrganizationRespDto findOneById(Long id) {
        return OrganizationService.super.findOneById(id);
    }

    @Override
    @CacheEvict(value = RedisConstant.ANAN_ORGANIZATION, key = "#id")
    public void deleteById(Long id) {
        Assert.notNull(id, "传入了空ID!");
        List<AnanOrganizationRespDto> dtos = findChildByPid(id);
        Assert.isTrue(dtos == null || dtos.size() == 0, "该节点还存在子节点不能直接删除!");
        organizationRepository.deleteById(id);
    }

    @Override
    @CacheEvict(value = RedisConstant.ANAN_ORGANIZATION, key = "#dto.id")
    public void deleteByDto(AnanOrganizationUpdateDto dto) {
        Assert.notNull(dto, "传入了空对象!");
        Long id = dto.getId();
        Assert.notNull(id, "传入了空ID!");
        List<AnanOrganizationRespDto> dtos = findChildByPid(id);
        Assert.isTrue(dtos == null || dtos.size() == 0, "该节点还存在子节点不能直接删除!");
        organizationRepository.findById(id).ifPresent(organizationRepository::delete);
    }

    @Override
    public List<AnanOrganizationRespDto> findChildByPid(Long pid) {
        return BeanUtil.copyCollectionProperties(organizationRepository.findByPidOrderByCodeAsc(pid), AnanOrganizationRespDto.class);
    }

    @Override
    public List<AnanOrganizationRespDto> findAllChildByPid(Long pid) {
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
        return BeanUtil.copyCollectionProperties(result, AnanOrganizationRespDto.class);
    }

    @Override
    public AnanOrganizationTreeDto treeAllChildByid(Long id) {
        AnanOrganizationEntity entity = organizationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("根据ID查询不到数据!"));
        AnanOrganizationRetrieveDto dto = new AnanOrganizationRetrieveDto();
        dto.setTopId(entity.getTopId());
        dto.setCode(entity.getCode());
        ArrayList<QueryRule> queryRules = new ArrayList<>();
        queryRules.add(new QueryRule(OperatorMode.rightLike, "code"));
        queryRules.add(new QueryRule(OperatorMode.equal, "topId"));
        dto.setQueryRules(queryRules);
        return this.findTree(dto, id);
    }

    @Override
    public IJpaRepository<AnanOrganizationEntity, Long> getRepository() {
        return organizationRepository;
    }
}
