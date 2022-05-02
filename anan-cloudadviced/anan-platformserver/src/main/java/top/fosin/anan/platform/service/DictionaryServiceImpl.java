package top.fosin.anan.platform.service;


import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import top.fosin.anan.cloudresource.constant.SystemConstant;
import top.fosin.anan.cloudresource.dto.req.AnanDictionaryReqDto;
import top.fosin.anan.cloudresource.dto.res.AnanDictionaryRespDto;
import top.fosin.anan.cloudresource.service.AnanUserDetailService;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.platform.entity.AnanDictionaryEntity;
import top.fosin.anan.platform.repository.DictionaryDetailRepository;
import top.fosin.anan.platform.repository.DictionaryRepository;
import top.fosin.anan.platform.service.inter.DictionaryService;

import java.util.Collection;
import java.util.List;

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
    @Transactional(rollbackFor = Exception.class)
    public AnanDictionaryRespDto create(AnanDictionaryReqDto entity) {
        AnanDictionaryEntity createEntity = new AnanDictionaryEntity();
        BeanUtils.copyProperties(entity, createEntity);
        hasModifiedPrivileges(createEntity.getType());
        return BeanUtil.copyProperties(dictionaryRepository.save(createEntity), AnanDictionaryRespDto.class);
    }

    private void hasModifiedPrivileges(int type) {
        if (SystemConstant.SYSTEM_DICTIONARY_TYPE.equals(type)) {
            //非超级管理员不能修改系统字典
            Assert.isTrue(ananUserDetailService.hasSysAdminRole(), "没有权限增删改系统字典!");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(AnanDictionaryReqDto entity) {
        Long id = entity.getId();
        Assert.notNull(id, "无效的字典代码id");
        AnanDictionaryEntity updateEntity = dictionaryRepository.findById(id).orElse(null);
        Assert.notNull(updateEntity, "根据传入的字典code" + id + "在数据库中未能找到对于数据!");
        BeanUtils.copyProperties(entity, updateEntity);
        hasModifiedPrivileges(updateEntity.getType());
        dictionaryRepository.save(updateEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        dictionaryRepository.findById(id).ifPresent(entity -> {
            hasModifiedPrivileges(entity.getType());
            dictionaryDetailRepository.deleteAllByDictionaryId(entity.getId());
            dictionaryRepository.deleteById(id);
        });
    }

    /**
     * 根据主键删除多条数据
     *
     * @param ids 主键编号集合
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByIds(Collection<Long> ids) {
        List<AnanDictionaryEntity> entities = dictionaryRepository.findAllById(ids);
        for (AnanDictionaryEntity entity : entities) {
            hasModifiedPrivileges(entity.getType());
            Long id = entity.getId();
            dictionaryDetailRepository.deleteAllByDictionaryId(id);
            dictionaryRepository.delete(entity);
        }
    }

    @Override
    public DictionaryRepository getRepository() {
        return dictionaryRepository;
    }
}
