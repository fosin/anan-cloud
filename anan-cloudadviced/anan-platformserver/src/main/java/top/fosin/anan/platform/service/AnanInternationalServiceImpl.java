package top.fosin.anan.platform.service;

import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.fosin.anan.cloudresource.constant.RedisConstant;
import top.fosin.anan.platform.dto.res.AnanInternationalRespDto;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.platform.dto.req.AnanInternationalCreateDto;
import top.fosin.anan.platform.dto.req.AnanInternationalUpdateDto;
import top.fosin.anan.platform.repository.AnanInternationalRepository;
import top.fosin.anan.platform.service.inter.AnanInternationalService;

import java.util.List;

/**
 * 国际化语言服务实现类
 *
 * @author fosin
 * @date 2020-12-03 21:11:42
 */
@Service
@Lazy
public class AnanInternationalServiceImpl implements AnanInternationalService {

    private final AnanInternationalRepository defaultRepository;

    public AnanInternationalServiceImpl(AnanInternationalRepository defaultRepository) {
        this.defaultRepository = defaultRepository;
    }

    @Override
    @Cacheable(value = RedisConstant.ANAN_INTERNATIONAL_STATUS, key = "#status")
    public List<AnanInternationalRespDto> findAllByStatus(Integer status) {
        return BeanUtil.copyCollectionProperties(
                this.getRepository().findAllByStatus(status), AnanInternationalRespDto.class);
    }

    @Override
    @Cacheable(value = RedisConstant.ANAN_INTERNATIONAL_CODE, key = "#code")
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
                    @CacheEvict(value = RedisConstant.ANAN_INTERNATIONAL_STATUS, allEntries = true),
                    @CacheEvict(value = RedisConstant.ANAN_INTERNATIONAL_CODE, allEntries = true)
            }
    )
    @Transactional(rollbackFor = Exception.class)
    public AnanInternationalRespDto create(AnanInternationalCreateDto entity) {
        return AnanInternationalService.super.create(entity);
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = RedisConstant.ANAN_INTERNATIONAL_STATUS, allEntries = true),
                    @CacheEvict(value = RedisConstant.ANAN_INTERNATIONAL_CODE, allEntries = true)
            }
    )
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        AnanInternationalService.super.deleteById(id);
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = RedisConstant.ANAN_INTERNATIONAL_STATUS, allEntries = true),
                    @CacheEvict(value = RedisConstant.ANAN_INTERNATIONAL_CODE, allEntries = true)
            }
    )
    @Transactional(rollbackFor = Exception.class)
    public void deleteByDto(AnanInternationalUpdateDto entity) {
        AnanInternationalService.super.deleteByDto(entity);
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = RedisConstant.ANAN_INTERNATIONAL_STATUS, allEntries = true),
                    @CacheEvict(value = RedisConstant.ANAN_INTERNATIONAL_CODE, allEntries = true)
            }
    )
    @Transactional(rollbackFor = Exception.class)
    public void update(AnanInternationalUpdateDto entity) {
        AnanInternationalService.super.update(entity);
    }

    /**
     * 获取DAO
     */
    @Override
    public AnanInternationalRepository getRepository() {
        return defaultRepository;
    }
}
