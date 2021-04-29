package top.fosin.anan.platform.service;

import top.fosin.anan.platformapi.entity.AnanServiceEntity;
import top.fosin.anan.platformapi.repository.AnanServiceRepository;
import top.fosin.anan.platform.service.inter.AnanServiceService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统服务表(anan_service)表服务实现类
 *
 * @author fosin
 * @date 2020-12-04 17:47:51
 */
@Service
@Lazy
public class AnanServiceServiceImpl implements AnanServiceService {

    private final AnanServiceRepository ananServiceRepository;

    public AnanServiceServiceImpl(AnanServiceRepository ananServiceRepository) {
        this.ananServiceRepository = ananServiceRepository;
    }

    @Override
    public List<AnanServiceEntity> findAllByStatus(Integer status) {
        return this.getRepository().findAllByStatus(status);
    }

    /**
     * 获取DAO
     */
    @Override
    public AnanServiceRepository getRepository() {
        return ananServiceRepository;
    }
}
