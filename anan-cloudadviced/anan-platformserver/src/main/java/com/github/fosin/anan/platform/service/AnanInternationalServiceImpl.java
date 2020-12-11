package com.github.fosin.anan.platform.service;

import com.github.fosin.anan.platform.entity.AnanInternationalEntity;
import com.github.fosin.anan.platform.repository.AnanInternationalRepository;
import com.github.fosin.anan.platform.service.inter.AnanInternationalService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 国际化(anan_international)表服务实现类
 *
 * @author fosin
 * @date 2020-12-03 21:11:42
 */
@Service
@Lazy
public class AnanInternationalServiceImpl implements AnanInternationalService {

    private final AnanInternationalRepository ananInternationalRepository;

    public AnanInternationalServiceImpl(AnanInternationalRepository ananInternationalRepository) {
        this.ananInternationalRepository = ananInternationalRepository;
    }

    @Override
    public List<AnanInternationalEntity> findAllByStatus(Integer status) {
        return this.getRepository().findAllByStatus(status);
    }

    @Override
    public AnanInternationalEntity findByCode( String code) {
        return this.getRepository().findByCode(code);
    }

    @Override
    public AnanInternationalEntity findByDefaultFlag() {
        return this.getRepository().findByDefaultFlag(1);
    }

    /**
     * 获取DAO
     */
    @Override
    public AnanInternationalRepository getRepository() {
        return ananInternationalRepository;
    }
}
