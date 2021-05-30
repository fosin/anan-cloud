package top.fosin.anan.platform.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.fosin.anan.cloudresource.constant.RedisConstant;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.platform.dto.req.AnanInternationalCharsetCreateDto;
import top.fosin.anan.platform.dto.req.AnanInternationalCharsetUpdateDto;
import top.fosin.anan.platform.dto.res.AnanInternationalCharsetRespDto;
import top.fosin.anan.platform.repository.InternationalCharsetRepository;
import top.fosin.anan.platform.service.inter.InternationalCharsetService;

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

    private final InternationalCharsetRepository defaultRepository;

    public InternationalCharsetServiceImpl(InternationalCharsetRepository defaultRepository) {
        this.defaultRepository = defaultRepository;
    }

    /**
     * 获取DAO
     */
    @Override
    public InternationalCharsetRepository getRepository() {
        return defaultRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = RedisConstant.ANAN_INTERNATIONAL_CHARSET + RedisConstant.ALL, key = "#dto.internationalId")
    public AnanInternationalCharsetRespDto create(AnanInternationalCharsetCreateDto dto) {
        return InternationalCharsetService.super.create(dto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = RedisConstant.ANAN_INTERNATIONAL_CHARSET + RedisConstant.ALL, key = "#result.internationalId")
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
    @CacheEvict(value = RedisConstant.ANAN_INTERNATIONAL_CHARSET + RedisConstant.ALL, allEntries = true)
    public void deleteByIds(Collection<Long> ids) {
        InternationalCharsetService.super.deleteByIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = RedisConstant.ANAN_INTERNATIONAL_CHARSET + RedisConstant.ALL, key = "#dto.internationalId")
    public void update(AnanInternationalCharsetUpdateDto dto) {
        InternationalCharsetService.super.update(dto);
    }

    @Override
    @Cacheable(value = RedisConstant.ANAN_INTERNATIONAL_CHARSET + RedisConstant.ALL, key = "#internationalId")
    public List<AnanInternationalCharsetRespDto> findAllByInternationalId(Long internationalId) {
        return BeanUtil.copyCollectionProperties(
                this.getRepository().findAllByInternationalId(internationalId),
                AnanInternationalCharsetRespDto.class);
    }

}
