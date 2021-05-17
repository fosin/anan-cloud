package top.fosin.anan.platform.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import top.fosin.anan.cloudresource.constant.RedisConstant;
import top.fosin.anan.platform.dto.res.AnanInternationalCharsetRespDto;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.platform.dto.req.AnanInternationalCharsetCreateDto;
import top.fosin.anan.platform.dto.req.AnanInternationalCharsetUpdateDto;
import top.fosin.anan.platform.repository.AnanInternationalCharsetRepository;
import top.fosin.anan.platform.service.inter.AnanInternationalCharsetService;

import java.util.List;

/**
 * 国际化明显(anan_international_charset)表服务实现类
 *
 * @author fosin
 * @date 2020-12-03 21:18:05
 */
@Service
@Lazy
public class AnanInternationalCharsetServiceImpl implements AnanInternationalCharsetService {

    private final AnanInternationalCharsetRepository defaultRepository;

    public AnanInternationalCharsetServiceImpl(AnanInternationalCharsetRepository defaultRepository) {
        this.defaultRepository = defaultRepository;
    }

    /**
     * 获取DAO
     */
    @Override
    public AnanInternationalCharsetRepository getRepository() {
        return defaultRepository;
    }

    @Override
    @CacheEvict(value = RedisConstant.ANAN_INTERNATIONAL_CHARSET + RedisConstant.ALL, key = "#entity.internationalId")
    public AnanInternationalCharsetRespDto create(AnanInternationalCharsetCreateDto entity) {
        return AnanInternationalCharsetService.super.create(entity);
    }

    @Override
    @CacheEvict(value = RedisConstant.ANAN_INTERNATIONAL_CHARSET + RedisConstant.ALL, key = "#result.internationalId")
    public void deleteById(Long id) {
        AnanInternationalCharsetService.super.deleteById(id);
    }

    @Override
    @CacheEvict(value = RedisConstant.ANAN_INTERNATIONAL_CHARSET + RedisConstant.ALL, key = "#dto.internationalId")
    public void deleteByDto(AnanInternationalCharsetUpdateDto dto) {
        AnanInternationalCharsetService.super.deleteByDto(dto);
    }

    @Override
    @CacheEvict(value = RedisConstant.ANAN_INTERNATIONAL_CHARSET + RedisConstant.ALL, key = "#dto.internationalId")
    public void update(AnanInternationalCharsetUpdateDto dto) {
        AnanInternationalCharsetService.super.update(dto);
    }

    @Override
    @Cacheable(value = RedisConstant.ANAN_INTERNATIONAL_CHARSET + RedisConstant.ALL, key = "#internationalId")
    public List<AnanInternationalCharsetRespDto> findAllByInternationalId(Long internationalId) {
        return BeanUtil.copyCollectionProperties(
                this.getRepository().findAllByInternationalId(internationalId),
                AnanInternationalCharsetRespDto.class);
    }

    @Override
    public List<AnanInternationalCharsetRespDto> findAllByInternationalIdAndServiceId(Long internationalId, Long serviceId) {
        return BeanUtil.copyCollectionProperties(
                this.getRepository().findAllByInternationalIdAndServiceId(internationalId, serviceId),
                AnanInternationalCharsetRespDto.class);
    }
}
