package top.fosin.anan.platform.modules.pub.service;

import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import top.fosin.anan.cloudresource.constant.PlatformRedisConstant;
import top.fosin.anan.cloudresource.constant.SystemConstant;
import top.fosin.anan.cloudresource.dto.req.DictionaryDetailReqDto;
import top.fosin.anan.cloudresource.dto.res.DictionaryDetailRespDto;
import top.fosin.anan.cloudresource.service.AnanUserDetailService;
import top.fosin.anan.core.exception.AnanServiceException;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.platform.modules.pub.entity.Dictionary;
import top.fosin.anan.platform.modules.pub.entity.DictionaryDetail;
import top.fosin.anan.platform.modules.pub.dao.DictionaryDetailDao;
import top.fosin.anan.platform.modules.pub.dao.DictionaryDao;
import top.fosin.anan.platform.modules.pub.service.inter.DictionaryDetailService;
import top.fosin.anan.redis.cache.AnanCacheManger;

import java.io.Serializable;
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
public class DictionaryDetailServiceImpl implements DictionaryDetailService {

    private final DictionaryDetailDao dictionaryDetailDao;
    private final AnanUserDetailService ananUserDetailService;
    private final DictionaryDao dictionaryDao;
    private final AnanCacheManger ananCacheManger;

    public DictionaryDetailServiceImpl(DictionaryDetailDao dictionaryDetailDao,
                                       AnanUserDetailService ananUserDetailService,
                                       DictionaryDao dictionaryDao, AnanCacheManger ananCacheManger) {
        this.dictionaryDetailDao = dictionaryDetailDao;
        this.ananUserDetailService = ananUserDetailService;
        this.dictionaryDao = dictionaryDao;
        this.ananCacheManger = ananCacheManger;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = PlatformRedisConstant.ANAN_DICTIONARY_DETAIL, key = "#result.dictionaryId")
    public DictionaryDetailRespDto create(DictionaryDetailReqDto entity) {
        Assert.notNull(entity, "传入的创建数据实体对象不能为空!");
        DictionaryDetail createEntiy = new DictionaryDetail();
        BeanUtils.copyProperties(entity, createEntiy);
        hasModifiedPrivileges(createEntiy.getDictionaryId());
        return BeanUtil.copyProperties(getRepository().save(createEntiy), DictionaryDetailRespDto.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = PlatformRedisConstant.ANAN_DICTIONARY_DETAIL, key = "#reqDto.dictionaryId")
    public void update(DictionaryDetailReqDto reqDto) {
        Assert.notNull(reqDto, "传入的更新数据实体对象不能为空!");
        Long id = reqDto.getId();
        Assert.notNull(id, "传入的更新数据实体对象主键不能为空!");
        DictionaryDetail findEntity = dictionaryDetailDao.findById(id).orElse(null);
        Assert.notNull(findEntity, "根据传入的主键[" + id + "]在数据库中未能找到数据!");
        hasModifiedPrivileges(reqDto.getDictionaryId());
        BeanUtils.copyProperties(reqDto, findEntity);
        dictionaryDetailDao.save(findEntity);
    }

    private void hasModifiedPrivileges(Long dictionaryId) {
        Dictionary dictionaryEntity = dictionaryDao.findById(dictionaryId).orElse(new Dictionary());
        if (SystemConstant.SYSTEM_DICTIONARY_TYPE.equals(dictionaryEntity.getType())) {
            //非超级管理员不能修改系统字典
            Assert.isTrue(ananUserDetailService.hasSysAdminRole(), "没有权限增删改系统字典!");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = PlatformRedisConstant.ANAN_DICTIONARY_DETAIL, key = "#result.dictionaryId")
    public void deleteById(Long id) {
        Assert.notNull(id, "传入了空的ID!");
        dictionaryDetailDao.findById(id).ifPresent(entity -> {
            hasModifiedPrivileges(entity.getDictionaryId());
            dictionaryDetailDao.deleteById(id);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new AnanServiceException(e);
            }
        });
    }

    /**
     * 根据主键删除多条数据
     *
     * @param ids 主键编号集合
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = PlatformRedisConstant.ANAN_DICTIONARY_DETAIL, allEntries = true)
    public void deleteByIds(Collection<Long> ids) {
        DictionaryDetailService.super.deleteByIds(ids);
    }

    @Override
    @Cacheable(value = PlatformRedisConstant.ANAN_DICTIONARY_DETAIL, key = "#dictionaryId")
    public List<DictionaryDetailRespDto> findByDictionaryId(Long dictionaryId) {
        Sort sort = Sort.by(Sort.Direction.fromString("ASC"), "sort");
        return BeanUtil.copyProperties(dictionaryDetailDao.findAllByDictionaryId(dictionaryId, sort), DictionaryDetailRespDto.class);
    }

    /**
     * 根据主键集合批量更新一个字段
     *
     * @param name  更新的字段名
     * @param value 更新的值
     * @param ids   主键集合
     * @return 更新的数量
     */
    @Override
    public long updateOneField(String name, Serializable value, Collection<Long> ids) {
        long count = DictionaryDetailService.super.updateOneField(name, value, ids);
        ids.forEach(id -> ananCacheManger.evict(PlatformRedisConstant.ANAN_DICTIONARY_DETAIL, id + ""));
        return count;
    }

    @Override
    public DictionaryDetailDao getRepository() {
        return dictionaryDetailDao;
    }
}
