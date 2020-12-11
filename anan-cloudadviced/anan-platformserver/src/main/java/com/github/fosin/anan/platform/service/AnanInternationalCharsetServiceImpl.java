package com.github.fosin.anan.platform.service;

import com.github.fosin.anan.platform.entity.AnanInternationalCharsetEntity;
import com.github.fosin.anan.platform.repository.AnanInternationalCharsetRepository;
import com.github.fosin.anan.platform.service.inter.AnanInternationalCharsetService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

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

    private final AnanInternationalCharsetRepository ananInternationalCharsetRepository;

    public AnanInternationalCharsetServiceImpl(AnanInternationalCharsetRepository ananInternationalCharsetRepository) {
        this.ananInternationalCharsetRepository = ananInternationalCharsetRepository;
    }

    /**
     * 获取DAO
     */
    @Override
    public AnanInternationalCharsetRepository getRepository() {
        return ananInternationalCharsetRepository;
    }

    @Override
    public List<AnanInternationalCharsetEntity> findAllByInternationalId(Integer internationalId) {
        return this.getRepository().findAllByInternationalId(internationalId);
    }

    @Override
    public List<AnanInternationalCharsetEntity> findCharsetByInternationalId(Integer internationalId) {
        return this.getRepository().findAllByInternationalId(internationalId);
    }

    @Override
    public List<AnanInternationalCharsetEntity> findAllByInternationalIdAndServiceId(Integer internationalId, Integer serviceId) {
        return this.getRepository().findAllByInternationalIdAndServiceId(internationalId, serviceId);
    }
}
