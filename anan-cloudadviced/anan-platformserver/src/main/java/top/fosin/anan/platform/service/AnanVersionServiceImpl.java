package top.fosin.anan.platform.service;

import top.fosin.anan.platform.repository.AnanVersionRepository;
import top.fosin.anan.platform.service.inter.AnanVersionService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * 系统版本表(anan_version)表服务实现类
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Service
@Lazy
public class AnanVersionServiceImpl implements AnanVersionService {
    private final AnanVersionRepository ananSysVersionRepository;

    public AnanVersionServiceImpl(AnanVersionRepository ananSysVersionRepository) {
        this.ananSysVersionRepository = ananSysVersionRepository;
    }

    /**
     * 获取DAO
     */
    @Override
    public AnanVersionRepository getRepository() {
        return ananSysVersionRepository;
    }
}
