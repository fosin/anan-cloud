package top.fosin.anan.platform.modules.version.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import top.fosin.anan.platform.modules.version.dao.VersionDao;
import top.fosin.anan.platform.modules.version.service.inter.VersionService;

/**
 * 系统版本表服务实现类
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Service
@Lazy
public class VersionServiceImpl implements VersionService {
    private final VersionDao versionDao;

    public VersionServiceImpl(VersionDao versionDao) {
        this.versionDao = versionDao;
    }

    /**
     * 获取DAO
     */
    @Override
    public VersionDao getDao() {
        return versionDao;
    }
}
