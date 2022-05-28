package top.fosin.anan.platform.modules.pub.service;

import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.fosin.anan.cloudresource.constant.PlatformRedisConstant;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.platform.modules.pub.dto.InternationalReqDto;
import top.fosin.anan.platform.modules.pub.dto.InternationalRespDto;
import top.fosin.anan.platform.modules.pub.dao.InternationalDao;
import top.fosin.anan.platform.modules.pub.service.inter.InternationalService;

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
public class InternationalServiceImpl implements InternationalService {

    private final InternationalDao defaultRepository;

    public InternationalServiceImpl(InternationalDao defaultRepository) {
        this.defaultRepository = defaultRepository;
    }

    @Override
    @Cacheable(value = PlatformRedisConstant.ANAN_INTERNATIONAL_STATUS, key = "#status")
    public List<InternationalRespDto> listByStatus(Integer status) {
        return BeanUtil.copyProperties(
                this.getDao().findAllByStatus(status), InternationalRespDto.class);
    }

    @Override
    @Cacheable(value = PlatformRedisConstant.ANAN_INTERNATIONAL_CODE, key = "#code")
    public InternationalRespDto findByCode(String code) {
        InternationalRespDto respDto = new InternationalRespDto();
        BeanUtils.copyProperties(this.getDao().findByCode(code), respDto);
        return respDto;
    }

    @Override
    public InternationalRespDto findByDefaultFlag() {
        InternationalRespDto respDto = new InternationalRespDto();
        BeanUtils.copyProperties(this.getDao().findByDefaultFlag(1), respDto);
        return respDto;
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = PlatformRedisConstant.ANAN_INTERNATIONAL_STATUS, allEntries = true),
                    @CacheEvict(value = PlatformRedisConstant.ANAN_INTERNATIONAL_CODE, allEntries = true)
            }
    )
    @Transactional(rollbackFor = Exception.class)
    public InternationalRespDto create(InternationalReqDto entity) {
        return InternationalService.super.create(entity);
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

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = PlatformRedisConstant.ANAN_INTERNATIONAL_STATUS, allEntries = true),
                    @CacheEvict(value = PlatformRedisConstant.ANAN_INTERNATIONAL_CODE, allEntries = true)
            }
    )
    @Transactional(rollbackFor = Exception.class)
    public void update(InternationalReqDto entity) {
        InternationalService.super.update(entity);
    }

    /**
     * 获取DAO
     */
    @Override
    public InternationalDao getDao() {
        return defaultRepository;
    }
}
