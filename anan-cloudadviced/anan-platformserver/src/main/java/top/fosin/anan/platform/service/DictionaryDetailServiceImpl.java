package top.fosin.anan.platform.service;

import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import top.fosin.anan.cloudresource.constant.RedisConstant;
import top.fosin.anan.cloudresource.constant.SystemConstant;
import top.fosin.anan.cloudresource.dto.request.AnanDictionaryDetailCreateDto;
import top.fosin.anan.cloudresource.dto.request.AnanDictionaryDetailRetrieveDto;
import top.fosin.anan.cloudresource.dto.request.AnanDictionaryDetailUpdateDto;
import top.fosin.anan.core.exception.AnanServiceException;
import top.fosin.anan.jpa.repository.IJpaRepository;
import top.fosin.anan.platform.repository.DictionaryDetailRepository;
import top.fosin.anan.platform.repository.DictionaryRepository;
import top.fosin.anan.platform.service.inter.DictionaryDetailService;
import top.fosin.anan.platformapi.entity.AnanDictionaryDetailEntity;
import top.fosin.anan.platformapi.entity.AnanDictionaryEntity;
import top.fosin.anan.platformapi.service.AnanUserDetailService;

import java.util.List;

/**
 * 字典表服务
 *
 * @author fosin
 * @date 2018-7-29
 */
@Service
@Lazy
public class DictionaryDetailServiceImpl implements DictionaryDetailService {

    private final DictionaryDetailRepository dictionaryDetailRepository;
    private final AnanUserDetailService ananUserDetailService;
    private final DictionaryRepository dictionaryRepository;

    public DictionaryDetailServiceImpl(DictionaryDetailRepository dictionaryDetailRepository,
                                       AnanUserDetailService ananUserDetailService,
                                       DictionaryRepository dictionaryRepository) {
        this.dictionaryDetailRepository = dictionaryDetailRepository;
        this.ananUserDetailService = ananUserDetailService;
        this.dictionaryRepository = dictionaryRepository;
    }

    @Override
    @CacheEvict(value = RedisConstant.ANAN_DICTIONARY_DETAIL, key = "#result.dictionaryId")
    public AnanDictionaryDetailEntity create(AnanDictionaryDetailCreateDto entity) {
        Assert.notNull(entity, "传入的创建数据实体对象不能为空!");
        AnanDictionaryDetailEntity createEntiy = new AnanDictionaryDetailEntity();
        BeanUtils.copyProperties(entity, createEntiy);
        hasModifiedPrivileges(createEntiy.getDictionaryId());
        return getRepository().save(createEntiy);
    }

    @Override
    @CacheEvict(value = RedisConstant.ANAN_DICTIONARY_DETAIL, key = "#entity.dictionaryId")
    public AnanDictionaryDetailEntity update(AnanDictionaryDetailUpdateDto entity) {
        Assert.notNull(entity, "传入的更新数据实体对象不能为空!");
        Long id = entity.getId();
        Assert.notNull(id, "传入的更新数据实体对象主键不能为空!");
        AnanDictionaryDetailEntity findEntity = dictionaryDetailRepository.findById(id).orElse(null);
        Assert.notNull(findEntity, "根据传入的主键[" + id + "]在数据库中未能找到数据!");
        hasModifiedPrivileges(entity.getDictionaryId());
        BeanUtils.copyProperties(entity, findEntity);
        return dictionaryDetailRepository.save(findEntity);
    }

    private void hasModifiedPrivileges(Long dictionaryId) {
        AnanDictionaryEntity dictionaryEntity = dictionaryRepository.findById(dictionaryId).orElse(new AnanDictionaryEntity());
        if (SystemConstant.SYSTEM_DICTIONARY_TYPE.equals(dictionaryEntity.getType())) {
            //非超级管理员不能修改系统字典
            Assert.isTrue(ananUserDetailService.hasSysAdminRole(), "没有权限增删改系统字典!");
        }
    }

    @Override
    @CacheEvict(value = RedisConstant.ANAN_DICTIONARY_DETAIL, key = "#result.dictionaryId")
    public AnanDictionaryDetailEntity deleteById(Long id) {
        Assert.notNull(id, "传入了空的ID!");
        AnanDictionaryDetailEntity entity = dictionaryDetailRepository.findById(id).orElse(null);
        Assert.notNull(entity, "传入的ID找不到数据!");
        hasModifiedPrivileges(entity.getDictionaryId());
        dictionaryDetailRepository.deleteById(id);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new AnanServiceException(e);
        }
        return null;
    }

    @Override
    @CacheEvict(value = RedisConstant.ANAN_DICTIONARY_DETAIL, key = "#entity.dictionaryId")
    public AnanDictionaryDetailEntity deleteByEntity(AnanDictionaryDetailRetrieveDto entity) {
        Assert.notNull(entity, "传入了空的对象!");
        hasModifiedPrivileges(entity.getDictionaryId());
        AnanDictionaryDetailEntity deleteEntity = dictionaryDetailRepository.findById(entity.getId()).orElse(null);
        if (deleteEntity != null) {
            dictionaryDetailRepository.delete(deleteEntity);
        }
        return deleteEntity;
    }

    @Override
    @Cacheable(value = RedisConstant.ANAN_DICTIONARY_DETAIL, key = "#dictionaryId")
    public List<AnanDictionaryDetailEntity> findByDictionaryId(Long dictionaryId) {
        Sort sort = Sort.by(Sort.Direction.fromString("ASC"), "sort");
        return dictionaryDetailRepository.findAllByDictionaryId(dictionaryId, sort);
    }


    @Override
    public IJpaRepository<AnanDictionaryDetailEntity, Long> getRepository() {
        return dictionaryDetailRepository;
    }
}
