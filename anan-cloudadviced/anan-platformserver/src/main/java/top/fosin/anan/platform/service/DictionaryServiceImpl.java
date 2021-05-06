package top.fosin.anan.platform.service;


import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import top.fosin.anan.cloudresource.constant.SystemConstant;
import top.fosin.anan.cloudresource.dto.request.AnanDictionaryCreateDto;
import top.fosin.anan.cloudresource.dto.request.AnanDictionaryRetrieveDto;
import top.fosin.anan.cloudresource.dto.request.AnanDictionaryUpdateDto;
import top.fosin.anan.core.exception.AnanServiceException;
import top.fosin.anan.jpa.repository.IJpaRepository;
import top.fosin.anan.platform.repository.DictionaryDetailRepository;
import top.fosin.anan.platform.repository.DictionaryRepository;
import top.fosin.anan.platform.service.inter.DictionaryService;
import top.fosin.anan.platformapi.entity.AnanDictionaryEntity;
import top.fosin.anan.platformapi.service.AnanUserDetailService;

/**
 * 字典表服务
 *
 * @author fosin
 * @date 2018-7-29
 */
@Service
@Lazy
public class DictionaryServiceImpl implements DictionaryService {
    private final DictionaryRepository dictionaryRepository;
    private final DictionaryDetailRepository dictionaryDetailRepository;
    private final AnanUserDetailService ananUserDetailService;

    public DictionaryServiceImpl(DictionaryRepository dictionaryRepository, DictionaryDetailRepository dictionaryDetailRepository, AnanUserDetailService ananUserDetailService) {
        this.dictionaryRepository = dictionaryRepository;
        this.dictionaryDetailRepository = dictionaryDetailRepository;
        this.ananUserDetailService = ananUserDetailService;
    }

    @Override
    public AnanDictionaryEntity create(AnanDictionaryCreateDto entity) {
        Assert.notNull(entity, "传入的创建数据实体对象不能为空!");
        AnanDictionaryEntity createEntity = new AnanDictionaryEntity();
        BeanUtils.copyProperties(entity, createEntity);
        hasModifiedPrivileges(createEntity.getType());
        return dictionaryRepository.save(createEntity);
    }

    private void hasModifiedPrivileges(int type) {
        if (SystemConstant.SYSTEM_DICTIONARY_TYPE.equals(type)) {
            //非超级管理员不能修改系统字典
            Assert.isTrue(ananUserDetailService.hasSysAdminRole(), "没有权限增删改系统字典!");
        }
    }

    @Override
    public AnanDictionaryEntity update(AnanDictionaryUpdateDto entity) {
        Assert.notNull(entity, "无效的更新数据");
        Long id = entity.getId();
        Assert.notNull(id, "无效的字典代码id");
        AnanDictionaryEntity updateEntity = dictionaryRepository.findById(id).orElse(null);
        Assert.notNull(updateEntity, "根据传入的字典code" + id + "在数据库中未能找到对于数据!");
        BeanUtils.copyProperties(entity, updateEntity);
        hasModifiedPrivileges(updateEntity.getType());
        return dictionaryRepository.save(updateEntity);
    }

    @Override
    @Transactional(rollbackFor = AnanServiceException.class)
    public AnanDictionaryEntity deleteById(Long code) {
        if (code == null) {
            throw new AnanServiceException("传入的code无效!");
        }
        AnanDictionaryEntity entity = dictionaryRepository.findById(code).orElse(null);
        if (entity != null) {
            hasModifiedPrivileges(entity.getType());
            dictionaryDetailRepository.deleteAllByDictionaryId(entity.getId());
            dictionaryRepository.deleteById(code);
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = AnanServiceException.class)
    public AnanDictionaryEntity deleteByEntity(AnanDictionaryRetrieveDto entity) {
        Assert.notNull(entity, "传入了空的对象!");
        hasModifiedPrivileges(entity.getType());
        dictionaryDetailRepository.deleteAllByDictionaryId(entity.getId());
        AnanDictionaryEntity deleteEntity = dictionaryRepository.findById(entity.getId()).orElse(null);
        if (deleteEntity != null) {
            dictionaryRepository.delete(deleteEntity);
        }
        return deleteEntity;
    }

    @Override
    public IJpaRepository<AnanDictionaryEntity, Long> getRepository() {
        return dictionaryRepository;
    }
}
