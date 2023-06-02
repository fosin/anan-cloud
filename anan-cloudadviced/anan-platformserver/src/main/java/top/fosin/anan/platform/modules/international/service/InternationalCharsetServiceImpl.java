package top.fosin.anan.platform.modules.international.service;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.fosin.anan.cloudresource.constant.PlatformRedisConstant;
import top.fosin.anan.platform.modules.international.dao.InternationalCharsetDao;
import top.fosin.anan.platform.modules.international.dto.InternationalCharsetCreateDTO;
import top.fosin.anan.platform.modules.international.dto.InternationalCharsetDTO;
import top.fosin.anan.platform.modules.international.dto.InternationalCharsetUpdateDTO;
import top.fosin.anan.platform.modules.international.service.inter.InternationalCharsetService;
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
@AllArgsConstructor
public class InternationalCharsetServiceImpl implements InternationalCharsetService {
    private final InternationalCharsetDao defaultRepository;
    private final AnanCacheManger ananCacheManger;

    @Override
    public InternationalCharsetDao getDao() {
        return defaultRepository;
    }

    @Override
    public void postCreate(InternationalCharsetCreateDTO reqDto, InternationalCharsetDTO respDto) {
        InternationalCharsetService.super.postCreate(reqDto, respDto);
        ananCacheManger.evict(PlatformRedisConstant.ANAN_INTERNATIONAL_CHARSET_ALL, reqDto.getInternationalId() + "");
    }

    @Override
    public void postUpdate(InternationalCharsetUpdateDTO reqDto) {
        InternationalCharsetService.super.postUpdate(reqDto);
        ananCacheManger.evict(PlatformRedisConstant.ANAN_INTERNATIONAL_CHARSET_ALL, reqDto.getInternationalId() + "");
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
    @Cacheable(value = PlatformRedisConstant.ANAN_INTERNATIONAL_CHARSET_ALL, key = "#fkValue")
    public List<InternationalCharsetDTO> listByForeingKey(Long fkValue) {
        return InternationalCharsetService.super.listByForeingKey(fkValue);
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
        ids.forEach(id -> this.ananCacheManger.evict(PlatformRedisConstant.ANAN_INTERNATIONAL_CHARSET_ALL, String.valueOf(id)));
        return count;
    }
}
