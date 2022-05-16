package top.fosin.anan.platform.modules.pub.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.fosin.anan.cloudresource.constant.PlatformRedisConstant;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.platform.modules.pub.dto.InternationalCharsetReqDto;
import top.fosin.anan.platform.modules.pub.dto.InternationalCharsetRespDto;
import top.fosin.anan.platform.modules.pub.dao.InternationalCharsetDao;
import top.fosin.anan.platform.modules.pub.service.inter.InternationalCharsetService;
import top.fosin.anan.redis.cache.AnanCacheManger;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 国际化明细服务实现类
 *
 * @author fosin
 * @date 2020-12-03 21:18:05
 */
@Service
@Lazy
public class InternationalCharsetServiceImpl implements InternationalCharsetService {

    private final InternationalCharsetDao defaultRepository;
    private final AnanCacheManger ananCacheManger;

    public InternationalCharsetServiceImpl(InternationalCharsetDao defaultRepository, AnanCacheManger ananCacheManger) {
        this.defaultRepository = defaultRepository;
        this.ananCacheManger = ananCacheManger;
    }

    /**
     * 获取DAO
     */
    @Override
    public InternationalCharsetDao getRepository() {
        return defaultRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = PlatformRedisConstant.ANAN_INTERNATIONAL_CHARSET_ALL, key = "#dto.internationalId")
    public InternationalCharsetRespDto create(InternationalCharsetReqDto dto) {
        return InternationalCharsetService.super.create(dto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = PlatformRedisConstant.ANAN_INTERNATIONAL_CHARSET_ALL, key = "#result.internationalId")
    public void deleteById(Long id) {
        InternationalCharsetService.super.deleteById(id);
    }

    /**
     * 根据主键删除多条数据
     *
     * @param ids 主键编号集合
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = PlatformRedisConstant.ANAN_INTERNATIONAL_CHARSET_ALL, allEntries = true)
    public void deleteByIds(Collection<Long> ids) {
        InternationalCharsetService.super.deleteByIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = PlatformRedisConstant.ANAN_INTERNATIONAL_CHARSET_ALL, key = "#dto.internationalId")
    public void update(InternationalCharsetReqDto dto) {
        InternationalCharsetService.super.update(dto);
    }

    @Override
    @Cacheable(value = PlatformRedisConstant.ANAN_INTERNATIONAL_CHARSET_ALL, key = "#internationalId")
    public List<InternationalCharsetRespDto> findAllByInternationalId(Long internationalId) {
        return BeanUtil.copyProperties(
                this.getRepository().findAllByInternationalId(internationalId),
                InternationalCharsetRespDto.class);
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
        long count = InternationalCharsetService.super.updateOneField(name, value, ids);
        ids.forEach(id -> this.ananCacheManger.evict(PlatformRedisConstant.ANAN_INTERNATIONAL_CHARSET_ALL, id + ""));
        return count;
    }
}
