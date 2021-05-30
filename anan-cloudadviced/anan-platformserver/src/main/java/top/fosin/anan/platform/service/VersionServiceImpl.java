package top.fosin.anan.platform.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import top.fosin.anan.platform.repository.VersionRepository;
import top.fosin.anan.platform.service.inter.VersionService;

/**
 * 系统版本表服务实现类
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Service
@Lazy
public class VersionServiceImpl implements VersionService {
    private final VersionRepository ananSysVersionRepository;

    public VersionServiceImpl(VersionRepository ananSysVersionRepository) {
        this.ananSysVersionRepository = ananSysVersionRepository;
    }

    /**
     * 获取DAO
     */
    @Override
    public VersionRepository getRepository() {
        return ananSysVersionRepository;
    }
}
