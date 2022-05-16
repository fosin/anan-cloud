package top.fosin.anan.platform.modules.pub.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import top.fosin.anan.platform.modules.pub.dao.ServiceDao;
import top.fosin.anan.platform.modules.pub.service.inter.ServiceService;

/**
 * 系统服务表服务实现类
 *
 * @author fosin
 * @date 2020-12-04 17:47:51
 */
@Service
@Lazy
public class ServiceServiceImpl implements ServiceService {

    private final ServiceDao serviceDao;

    public ServiceServiceImpl(ServiceDao serviceDao) {
        this.serviceDao = serviceDao;
    }
    /**
     * 获取DAO
     */
    @Override
    public ServiceDao getRepository() {
        return serviceDao;
    }
}
