package top.fosin.anan.platform.service;

import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.fosin.anan.cloudresource.constant.PlatformRedisConstant;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.platform.dto.req.AnanInternationalReqDto;
import top.fosin.anan.platform.dto.res.AnanInternationalRespDto;
import top.fosin.anan.platform.repository.InternationalRepository;
import top.fosin.anan.platform.service.inter.InternationalService;

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

    private final InternationalRepository defaultRepository;

    public InternationalServiceImpl(InternationalRepository defaultRepository) {
        this.defaultRepository = defaultRepository;
    }

    @Override
    @Cacheable(value = PlatformRedisConstant.ANAN_INTERNATIONAL_STATUS, key = "#status")
    public List<AnanInternationalRespDto> listByStatus(Integer status) {
        return BeanUtil.copyCollectionProperties(
                this.getRepository().findAllByStatus(status), AnanInternationalRespDto.class);
    }

    @Override
    @Cacheable(value = PlatformRedisConstant.ANAN_INTERNATIONAL_CODE, key = "#code")
    public AnanInternationalRespDto findByCode(String code) {
        AnanInternationalRespDto respDto = new AnanInternationalRespDto();
        BeanUtils.copyProperties(this.getRepository().findByCode(code), respDto);
        return respDto;
    }

    @Override
    public AnanInternationalRespDto findByDefaultFlag() {
        AnanInternationalRespDto respDto = new AnanInternationalRespDto();
        BeanUtils.copyProperties(this.getRepository().findByDefaultFlag(1), respDto);
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
    public AnanInternationalRespDto create(AnanInternationalReqDto entity) {
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
    public void update(AnanInternationalReqDto entity) {
        InternationalService.super.update(entity);
    }

    /**
     * 获取DAO
     */
    @Override
    public InternationalRepository getRepository() {
        return defaultRepository;
    }
}
