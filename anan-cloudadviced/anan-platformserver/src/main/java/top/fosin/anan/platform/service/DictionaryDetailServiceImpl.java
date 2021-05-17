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
import top.fosin.anan.platform.dto.req.AnanDictionaryDetailCreateDto;
import top.fosin.anan.platform.dto.req.AnanDictionaryDetailUpdateDto;
import top.fosin.anan.cloudresource.dto.res.AnanDictionaryDetailRespDto;
import top.fosin.anan.core.exception.AnanServiceException;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.platform.entity.AnanDictionaryDetailEntity;
import top.fosin.anan.platform.entity.AnanDictionaryEntity;
import top.fosin.anan.platform.repository.DictionaryDetailRepository;
import top.fosin.anan.platform.repository.DictionaryRepository;
import top.fosin.anan.platform.service.inter.DictionaryDetailService;
import top.fosin.anan.cloudresource.service.AnanUserDetailService;

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
    public AnanDictionaryDetailRespDto create(AnanDictionaryDetailCreateDto entity) {
        Assert.notNull(entity, "传入的创建数据实体对象不能为空!");
        AnanDictionaryDetailEntity createEntiy = new AnanDictionaryDetailEntity();
        BeanUtils.copyProperties(entity, createEntiy);
        hasModifiedPrivileges(createEntiy.getDictionaryId());
        return BeanUtil.copyProperties(getRepository().save(createEntiy), AnanDictionaryDetailRespDto.class);
    }

    @Override
    @CacheEvict(value = RedisConstant.ANAN_DICTIONARY_DETAIL, key = "#entity.dictionaryId")
    public void update(AnanDictionaryDetailUpdateDto entity) {
        Assert.notNull(entity, "传入的更新数据实体对象不能为空!");
        Long id = entity.getId();
        Assert.notNull(id, "传入的更新数据实体对象主键不能为空!");
        AnanDictionaryDetailEntity findEntity = dictionaryDetailRepository.findById(id).orElse(null);
        Assert.notNull(findEntity, "根据传入的主键[" + id + "]在数据库中未能找到数据!");
        hasModifiedPrivileges(entity.getDictionaryId());
        BeanUtils.copyProperties(entity, findEntity);
        dictionaryDetailRepository.save(findEntity);
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
    public void deleteById(Long id) {
        Assert.notNull(id, "传入了空的ID!");
        dictionaryDetailRepository.findById(id).ifPresent(entity -> {
            hasModifiedPrivileges(entity.getDictionaryId());
            dictionaryDetailRepository.deleteById(id);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new AnanServiceException(e);
            }
        });
    }

    @Override
    @CacheEvict(value = RedisConstant.ANAN_DICTIONARY_DETAIL, key = "#dto.dictionaryId")
    public void deleteByDto(AnanDictionaryDetailUpdateDto dto) {
        Assert.notNull(dto, "传入了空的对象!");
        hasModifiedPrivileges(dto.getDictionaryId());
        dictionaryDetailRepository.findById(dto.getId()).ifPresent(dictionaryDetailRepository::delete);
    }

    @Override
    @Cacheable(value = RedisConstant.ANAN_DICTIONARY_DETAIL, key = "#dictionaryId")
    public List<AnanDictionaryDetailRespDto> findByDictionaryId(Long dictionaryId) {
        Sort sort = Sort.by(Sort.Direction.fromString("ASC"), "sort");
        return BeanUtil.copyCollectionProperties(dictionaryDetailRepository.findAllByDictionaryId(dictionaryId, sort), AnanDictionaryDetailRespDto.class);
    }


    @Override
    public DictionaryDetailRepository getRepository() {
        return dictionaryDetailRepository;
    }
}
