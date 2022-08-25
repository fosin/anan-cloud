package top.fosin.anan.platform.modules.pub.service;


import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import top.fosin.anan.cloudresource.constant.SystemConstant;
import top.fosin.anan.cloudresource.dto.req.DictionaryReqDto;
import top.fosin.anan.cloudresource.service.AnanUserDetailService;
import top.fosin.anan.platform.modules.pub.dao.DictionaryDao;
import top.fosin.anan.platform.modules.pub.dao.DictionaryDetailDao;
import top.fosin.anan.platform.modules.pub.po.Dictionary;
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
@AllArgsConstructor
public class DictionaryServiceImpl implements DictionaryService {
    private final DictionaryDao dictionaryDao;
    private final DictionaryDetailDao dictionaryDetailDao;
    private final AnanUserDetailService ananUserDetailService;

    @Override
    public void preCreate(DictionaryReqDto reqDto) {
        DictionaryService.super.preCreate(reqDto);
        hasModifiedPrivileges(reqDto.getType());
    }

    private void hasModifiedPrivileges(int type) {
        if (SystemConstant.SYSTEM_DICTIONARY_TYPE.equals(type)) {
            //非超级管理员不能修改系统字典
            Assert.isTrue(ananUserDetailService.hasSysAdminRole(), "没有权限增删改系统字典!");
        }
    }

    @Override
    public void preUpdate(DictionaryReqDto reqDto) {
        DictionaryService.super.preUpdate(reqDto);
        hasModifiedPrivileges(reqDto.getType());
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
    public DictionaryDao getDao() {
        return dictionaryDao;
    }
}
