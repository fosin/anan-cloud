package top.fosin.anan.platform.modules.pub.service;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.fosin.anan.cloudresource.constant.PlatformRedisConstant;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.platform.modules.pub.dao.InternationalDao;
import top.fosin.anan.platform.modules.pub.dto.InternationalReqDto;
import top.fosin.anan.platform.modules.pub.dto.InternationalRespDto;
import top.fosin.anan.platform.modules.pub.service.inter.InternationalService;
import top.fosin.anan.redis.cache.AnanCacheManger;

import java.util.Collection;
import java.util.List;

/**
 * 国际化语言服务实现类
 *
 * @author fosin
 * @date 2020-12-03 21:11:42
 */
@Service
@Lazy
@AllArgsConstructor
public class InternationalServiceImpl implements InternationalService {
    private final InternationalDao defaultRepository;
    private final AnanCacheManger ananCacheManger;

    @Override
    @Cacheable(value = PlatformRedisConstant.ANAN_INTERNATIONAL_STATUS, key = "#status")
    public List<InternationalRespDto> listByStatus(Integer status) {
        return BeanUtil.copyProperties(
                this.getDao().findAllByStatus(status), InternationalRespDto.class);
    }

    @Override
    @Cacheable(value = PlatformRedisConstant.ANAN_INTERNATIONAL_CODE, key = "#code")
    public InternationalRespDto findByCode(String code) {
        return BeanUtil.copyProperties(this.getDao().findByCode(code), InternationalRespDto.class);
    }

    @Override
    public InternationalRespDto findByDefaultFlag() {
        return BeanUtil.copyProperties(this.getDao().findByDefaultFlag(1), InternationalRespDto.class);
    }

    @Override
    public void postCreate(InternationalReqDto reqDto, InternationalRespDto respDto) {
        InternationalService.super.postCreate(reqDto, respDto);
        ananCacheManger.clear(PlatformRedisConstant.ANAN_INTERNATIONAL_STATUS);
        ananCacheManger.clear(PlatformRedisConstant.ANAN_INTERNATIONAL_CODE);
    }

    @Override
    public void postUpdate(InternationalReqDto reqDto) {
        InternationalService.super.postUpdate(reqDto);
        ananCacheManger.clear(PlatformRedisConstant.ANAN_INTERNATIONAL_STATUS);
        ananCacheManger.clear(PlatformRedisConstant.ANAN_INTERNATIONAL_CODE);
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = PlatformRedisConstant.ANAN_INTERNATIONAL_STATUS, allEntries = true),
                    @CacheEvict(value = PlatformRedisConstant.ANAN_INTERNATIONAL_CODE, allEntries = true)
            }
    )
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        InternationalService.super.deleteById(id);
    }

    /**
     * 根据主键删除多条数据
     *
     * @param ids 主键编号集合
     */
    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = PlatformRedisConstant.ANAN_INTERNATIONAL_STATUS, allEntries = true),
                    @CacheEvict(value = PlatformRedisConstant.ANAN_INTERNATIONAL_CODE, allEntries = true)
            }
    )
    @Transactional(rollbackFor = Exception.class)
    public void deleteByIds(Collection<Long> ids) {
        InternationalService.super.deleteByIds(ids);
    }


    /**
     * 获取DAO
     */
    @Override
    public InternationalDao getDao() {
        return defaultRepository;
    }
}
