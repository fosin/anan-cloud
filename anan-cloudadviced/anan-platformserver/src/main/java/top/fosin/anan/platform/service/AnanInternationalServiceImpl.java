package top.fosin.anan.platform.service;

import top.fosin.anan.cloudresource.constant.RedisConstant;
import top.fosin.anan.jpa.util.JpaUtil;
import top.fosin.anan.platform.dto.request.AnanInternationalCreateDto;
import top.fosin.anan.platform.dto.request.AnanInternationalUpdateDto;
import top.fosin.anan.platform.entity.AnanInternationalEntity;
import top.fosin.anan.platform.repository.AnanInternationalRepository;
import top.fosin.anan.platform.service.inter.AnanInternationalService;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * 国际化(anan_international)表服务实现类
 *
 * @author fosin
 * @date 2020-12-03 21:11:42
 */
@Service
@Lazy
public class AnanInternationalServiceImpl implements AnanInternationalService {

    private final AnanInternationalRepository defaultRepository;

    public AnanInternationalServiceImpl(AnanInternationalRepository defaultRepository) {
        this.defaultRepository = defaultRepository;
    }

    @Override
    @Cacheable(value = RedisConstant.ANAN_INTERNATIONAL_STATUS, key = "#status")
    public List<AnanInternationalEntity> findAllByStatus(Integer status) {
        return this.getRepository().findAllByStatus(status);
    }

    @Override
    @Cacheable(value = RedisConstant.ANAN_INTERNATIONAL_CODE, key = "#code")
    public AnanInternationalEntity findByCode(String code) {
        return this.getRepository().findByCode(code);
    }

    @Override
    public AnanInternationalEntity findByDefaultFlag() {
        return this.getRepository().findByDefaultFlag(1);
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = RedisConstant.ANAN_INTERNATIONAL_STATUS, allEntries = true),
                    @CacheEvict(value = RedisConstant.ANAN_INTERNATIONAL_CODE, allEntries = true)
            }
    )
    public AnanInternationalEntity create(AnanInternationalCreateDto entity) {
        Assert.notNull(entity, "传入的创建数据实体对象不能为空!");
        AnanInternationalEntity entityDynamic = JpaUtil.findOneByEntityDynamic(defaultRepository, entity);
        if (entityDynamic == null) {
            AnanInternationalEntity createEntiy = new AnanInternationalEntity();
            BeanUtils.copyProperties(entity, createEntiy);
            entityDynamic = defaultRepository.save(createEntiy);
        }

        return entityDynamic;
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = RedisConstant.ANAN_INTERNATIONAL_STATUS, allEntries = true),
                    @CacheEvict(value = RedisConstant.ANAN_INTERNATIONAL_CODE, allEntries = true)
            }
    )
    public AnanInternationalEntity deleteById(Integer id) {
        Assert.notNull(id, "需要删除的数据主键不能为空!");
        AnanInternationalEntity entity = defaultRepository.findById(id).orElse(null);
        if (entity != null) {
            defaultRepository.delete(entity);
        }
        return entity;
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = RedisConstant.ANAN_INTERNATIONAL_STATUS, allEntries = true),
                    @CacheEvict(value = RedisConstant.ANAN_INTERNATIONAL_CODE, allEntries = true)
            }
    )
    public AnanInternationalEntity deleteByEntity(AnanInternationalEntity entity) {
        Assert.notNull(entity, "删除数据的实体对象不能为空!");
        AnanInternationalEntity deleteEntity = JpaUtil.findOneByEntityDynamic(defaultRepository, entity);
        if (deleteEntity != null) {
            defaultRepository.delete(entity);
        }
        return entity;
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = RedisConstant.ANAN_INTERNATIONAL_STATUS, allEntries = true),
                    @CacheEvict(value = RedisConstant.ANAN_INTERNATIONAL_CODE, allEntries = true)
            }
    )
    public AnanInternationalEntity update(AnanInternationalUpdateDto entity) {
        Integer id = entity.getId();
        Assert.notNull(id, "更新的数据id不能为空或者小于1!");
        AnanInternationalEntity createUser = defaultRepository.findById(id).orElse(null);
        Assert.notNull(createUser, "在数据库中未找到该用户数据!");
        BeanUtils.copyProperties(entity, createUser);
        return defaultRepository.save(createUser);
    }

    /**
     * 获取DAO
     */
    @Override
    public AnanInternationalRepository getRepository() {
        return defaultRepository;
    }
}
