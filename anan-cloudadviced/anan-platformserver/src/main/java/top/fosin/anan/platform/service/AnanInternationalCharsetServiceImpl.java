package top.fosin.anan.platform.service;

import top.fosin.anan.cloudresource.constant.RedisConstant;
import top.fosin.anan.jpa.util.JpaUtil;
import top.fosin.anan.model.module.PageModule;
import top.fosin.anan.model.result.ListResult;
import top.fosin.anan.model.result.ResultUtils;
import top.fosin.anan.platform.dto.request.AnanInternationalCharsetCreateDto;
import top.fosin.anan.platform.dto.request.AnanInternationalCharsetUpdateDto;
import top.fosin.anan.platform.entity.AnanInternationalCharsetEntity;
import top.fosin.anan.platform.repository.AnanInternationalCharsetRepository;
import top.fosin.anan.platform.service.inter.AnanInternationalCharsetService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.persistence.criteria.Path;
import java.util.List;

/**
 * 国际化明显(anan_international_charset)表服务实现类
 *
 * @author fosin
 * @date 2020-12-03 21:18:05
 */
@Service
@Lazy
public class AnanInternationalCharsetServiceImpl implements AnanInternationalCharsetService {

    private final AnanInternationalCharsetRepository defaultRepository;

    public AnanInternationalCharsetServiceImpl(AnanInternationalCharsetRepository defaultRepository) {
        this.defaultRepository = defaultRepository;
    }

    /**
     * 获取DAO
     */
    @Override
    public AnanInternationalCharsetRepository getRepository() {
        return defaultRepository;
    }

    @Override
    @CacheEvict(value = RedisConstant.ANAN_INTERNATIONAL_CHARSET + RedisConstant.ALL, key = "#entity.internationalId")
    public AnanInternationalCharsetEntity create(AnanInternationalCharsetCreateDto entity) {
        Assert.notNull(entity, "传入的创建数据实体对象不能为空!");
        AnanInternationalCharsetEntity entityDynamic = JpaUtil.findOneByEntityDynamic(defaultRepository, entity);
        if (entityDynamic == null) {
            AnanInternationalCharsetEntity createEntiy =new AnanInternationalCharsetEntity();
            BeanUtils.copyProperties(entity, createEntiy);
            entityDynamic = defaultRepository.save(createEntiy);
        }

        return entityDynamic;
    }
    @Override
    @CacheEvict(value = RedisConstant.ANAN_INTERNATIONAL_CHARSET + RedisConstant.ALL, key = "#result.internationalId")
    public AnanInternationalCharsetEntity deleteById(Long id) {
        Assert.notNull(id, "需要删除的数据主键不能为空!");
        AnanInternationalCharsetEntity entity = defaultRepository.findById(id).orElse(null);
        if (entity != null) {
            defaultRepository.delete(entity);
        }
        return entity;
    }

    @Override
    @CacheEvict(value = RedisConstant.ANAN_INTERNATIONAL_CHARSET + RedisConstant.ALL, key = "#entity.internationalId")
    public AnanInternationalCharsetEntity deleteByEntity(AnanInternationalCharsetEntity entity) {
        Assert.notNull(entity, "删除数据的实体对象不能为空!");
        AnanInternationalCharsetEntity deleteEntity = JpaUtil.findOneByEntityDynamic(defaultRepository, entity);
        if (deleteEntity != null) {
            defaultRepository.delete(entity);
        }
        return entity;
    }

    @Override
    @CacheEvict(value = RedisConstant.ANAN_INTERNATIONAL_CHARSET + RedisConstant.ALL, key = "#entity.internationalId")
    public AnanInternationalCharsetEntity update(AnanInternationalCharsetUpdateDto entity) {
        Long id = entity.getId();
        Assert.notNull(id, "更新的数据id不能为空或者小于1!");
        AnanInternationalCharsetEntity createUser = defaultRepository.findById(id).orElse(null);
        Assert.notNull(createUser, "在数据库中未找到该用户数据!");
        BeanUtils.copyProperties(entity, createUser);
        return defaultRepository.save(createUser);
    }

    @Override
    @Cacheable(value = RedisConstant.ANAN_INTERNATIONAL_CHARSET + RedisConstant.ALL, key = "#internationalId")
    public List<AnanInternationalCharsetEntity> findAllByInternationalId(Integer internationalId) {
        return this.getRepository().findAllByInternationalId(internationalId);
    }

    @Override
    public ListResult<AnanInternationalCharsetEntity> findAllCharsetPageByinternationalId(PageModule pageModule, Integer internationalId) {
        PageRequest pageable = PageRequest.of(pageModule.getPageNumber() - 1, pageModule.getPageSize(), Direction.fromString(pageModule.getSortOrder()), pageModule.getSortName());
        String searchCondition = pageModule.getSearchText();
        Specification<AnanInternationalCharsetEntity> condition = (root, query, cb) -> {
            Path<Integer> dictionaryIdPath = root.get("internationalId");
            return StringUtils.isBlank(searchCondition) ? cb.equal(dictionaryIdPath, internationalId) : query.getRestriction();
        };
        Page<AnanInternationalCharsetEntity> page = this.getRepository().findAll(condition, pageable);
        return ResultUtils.success(page.getTotalElements(), page.getContent());
    }

    @Override
    public List<AnanInternationalCharsetEntity> findAllByInternationalIdAndServiceId(Integer internationalId, Integer serviceId) {
        return this.getRepository().findAllByInternationalIdAndServiceId(internationalId, serviceId);
    }
}
