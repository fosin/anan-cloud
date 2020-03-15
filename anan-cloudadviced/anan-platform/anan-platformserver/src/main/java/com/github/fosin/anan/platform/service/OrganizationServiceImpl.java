package com.github.fosin.anan.platform.service;

import com.github.fosin.anan.jpa.repository.IJpaRepository;
import com.github.fosin.anan.model.module.PageModule;
import com.github.fosin.anan.model.result.Result;
import com.github.fosin.anan.model.result.ResultUtils;
import com.github.fosin.anan.platform.repository.OrganizationRepository;
import com.github.fosin.anan.platform.service.inter.OrganizationService;
import com.github.fosin.anan.platformapi.constant.TableNameConstant;
import com.github.fosin.anan.pojo.dto.request.AnanOrganizationCreateDto;
import com.github.fosin.anan.pojo.dto.request.AnanOrganizationUpdateDto;
import com.github.fosin.anan.platformapi.entity.AnanOrganizationEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.persistence.criteria.Path;
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

    public OrganizationServiceImpl(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Override
    @CachePut(value = TableNameConstant.ANAN_ORGANIZATION, key = "#result.id")
    public AnanOrganizationEntity create(AnanOrganizationCreateDto entity) {
        Assert.notNull(entity, "传入的创建数据实体对象不能为空!");
        AnanOrganizationEntity createEntity = new AnanOrganizationEntity();
        BeanUtils.copyProperties(entity, createEntity);
        Long pid = entity.getPid();
        int level = 1;
        if (pid != 0) {
            AnanOrganizationEntity parentEntity = organizationRepository.findById(pid).orElse(null);
            Assert.notNull(parentEntity, "传入的创建数据实体找不到对于的父节点数据!");
            level = parentEntity.getLevel() + 1;
        }
        createEntity.setLevel(level);
        return organizationRepository.save(createEntity);
    }

    @Override
    @CachePut(value = TableNameConstant.ANAN_ORGANIZATION, key = "#entity.id")
    public AnanOrganizationEntity update(AnanOrganizationUpdateDto entity) {
        Assert.notNull(entity, "无效的更新数据");
        Long id = entity.getId();
        Assert.notNull(id, "无效的字典代码code");
        AnanOrganizationEntity updateEntity = organizationRepository.findById(id).orElse(null);
        BeanUtils.copyProperties(entity, Objects.requireNonNull(updateEntity, "根据传入的机构ID" + id + "在数据库中未能找到对于数据!"));

        Long pid = entity.getPid();
        if (!updateEntity.getPid().equals(pid)) {
            AnanOrganizationEntity parentEntity = organizationRepository.findById(pid).orElse(null);
            updateEntity.setLevel(Objects.requireNonNull(parentEntity,
                    "传入的创建数据实体找不到对于的父节点数据!").getLevel() + 1);
        }
        return organizationRepository.save(updateEntity);
    }

    @Override
    @Cacheable(value = TableNameConstant.ANAN_ORGANIZATION, key = "#id")
    public AnanOrganizationEntity findById(Long id) {
        return organizationRepository.findById(id).orElse(null);
    }

    @Override
    @CacheEvict(value = TableNameConstant.ANAN_ORGANIZATION, key = "#id")
    public AnanOrganizationEntity deleteById(Long id) {
        Assert.notNull(id, "传入了空ID!");
        List<AnanOrganizationEntity> entities = findByPid(id);
        Assert.isTrue(entities == null || entities.size() == 0, "该节点还存在子节点不能直接删除!");
        organizationRepository.deleteById(id);
        return null;
    }

    @Override
    @CacheEvict(value = TableNameConstant.ANAN_ORGANIZATION, key = "#entity.id")
    public AnanOrganizationEntity deleteByEntity(AnanOrganizationEntity entity) {
        Assert.notNull(entity, "传入了空对象!");
        Assert.notNull(entity.getId(), "传入了空ID!");
        List<AnanOrganizationEntity> entities = findByPid(entity.getId());
        Assert.isTrue(entities == null || entities.size() == 0, "该节点还存在子节点不能直接删除!");
        organizationRepository.delete(entity);
        return entity;
    }

    @Override
    public List<AnanOrganizationEntity> findAllByTopId(Long topId) {
        Assert.isTrue(topId != null && topId >= 0, "顶级机构编码无效!");
        return organizationRepository.findAllByTopId(topId);
    }

    public String getCacheName() {
        return "AllData";
    }

    @Override
    public Result findAllByPageSort(PageModule pageModule) {
        PageRequest pageable = PageRequest.of(pageModule.getPageNumber() - 1, pageModule.getPageSize(), Sort.Direction.fromString(pageModule.getSortOrder()), pageModule.getSortName());
        String searchCondition = pageModule.getSearchText();

        Specification<AnanOrganizationEntity> condition = (Specification<AnanOrganizationEntity>) (root, query, cb) -> {
            if (StringUtils.isBlank(searchCondition)) {
                return query.getRestriction();
            }
            Path<String> id = root.get("id");
            Path<String> name = root.get("name");
            Path<String> fullname = root.get("fullname");
            Path<String> address = root.get("address");
            return cb.or(cb.like(id, "%" + searchCondition + "%"), cb.like(name, "%" + searchCondition + "%"), cb.like(fullname, "%" + searchCondition + "%"), cb.like(address, "%" + searchCondition + "%"));
        };
        //分页查找
        Page<AnanOrganizationEntity> page = organizationRepository.findAll(condition, pageable);

        return ResultUtils.success(page.getTotalElements(), page.getContent());
    }

    @Override
    public List<AnanOrganizationEntity> findByPid(Long pid) {
        return organizationRepository.findByPidOrderByCodeAsc(pid);
    }

    @Override
    public List<AnanOrganizationEntity> findByCodeStartingWith(String code) {
        return organizationRepository.findByCodeStartingWithOrderByCodeAsc(code);
    }

    @Override
    public IJpaRepository<AnanOrganizationEntity, Long> getRepository() {
        return organizationRepository;
    }
}
