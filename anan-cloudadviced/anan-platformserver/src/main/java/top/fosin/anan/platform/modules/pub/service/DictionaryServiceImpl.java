package top.fosin.anan.platform.modules.pub.service;


import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import top.fosin.anan.cloudresource.constant.SystemConstant;
import top.fosin.anan.cloudresource.dto.req.DictionaryReqDto;
import top.fosin.anan.cloudresource.dto.res.DictionaryRespDto;
import top.fosin.anan.cloudresource.service.AnanUserDetailService;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.platform.modules.pub.entity.Dictionary;
import top.fosin.anan.platform.modules.pub.dao.DictionaryDetailDao;
import top.fosin.anan.platform.modules.pub.dao.DictionaryDao;
import top.fosin.anan.platform.modules.pub.service.inter.DictionaryService;

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
    private final DictionaryDao dictionaryDao;
    private final DictionaryDetailDao dictionaryDetailDao;
    private final AnanUserDetailService ananUserDetailService;

    public DictionaryServiceImpl(DictionaryDao dictionaryDao, DictionaryDetailDao dictionaryDetailDao, AnanUserDetailService ananUserDetailService) {
        this.dictionaryDao = dictionaryDao;
        this.dictionaryDetailDao = dictionaryDetailDao;
        this.ananUserDetailService = ananUserDetailService;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DictionaryRespDto create(DictionaryReqDto entity) {
        Dictionary createEntity = new Dictionary();
        BeanUtils.copyProperties(entity, createEntity);
        hasModifiedPrivileges(createEntity.getType());
        return BeanUtil.copyProperties(dictionaryDao.save(createEntity), DictionaryRespDto.class);
    }

    private void hasModifiedPrivileges(int type) {
        if (SystemConstant.SYSTEM_DICTIONARY_TYPE.equals(type)) {
            //非超级管理员不能修改系统字典
            Assert.isTrue(ananUserDetailService.hasSysAdminRole(), "没有权限增删改系统字典!");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(DictionaryReqDto entity) {
        Long id = entity.getId();
        Assert.notNull(id, "无效的字典代码id");
        Dictionary updateEntity = dictionaryDao.findById(id).orElse(null);
        Assert.notNull(updateEntity, "根据传入的字典code" + id + "在数据库中未能找到对于数据!");
        BeanUtils.copyProperties(entity, updateEntity);
        hasModifiedPrivileges(updateEntity.getType());
        dictionaryDao.save(updateEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        dictionaryDao.findById(id).ifPresent(entity -> {
            hasModifiedPrivileges(entity.getType());
            dictionaryDetailDao.deleteAllByDictionaryId(entity.getId());
            dictionaryDao.deleteById(id);
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
        List<Dictionary> entities = dictionaryDao.findAllById(ids);
        for (Dictionary entity : entities) {
            hasModifiedPrivileges(entity.getType());
            Long id = entity.getId();
            dictionaryDetailDao.deleteAllByDictionaryId(id);
            dictionaryDao.delete(entity);
        }
    }

    @Override
    public DictionaryDao getRepository() {
        return dictionaryDao;
    }
}
