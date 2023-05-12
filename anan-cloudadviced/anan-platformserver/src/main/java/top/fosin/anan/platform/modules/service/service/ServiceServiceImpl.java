package top.fosin.anan.platform.modules.service.service;

import top.fosin.anan.platform.modules.service.dao.ServiceDao;
import top.fosin.anan.platform.modules.service.service.inter.ServiceService;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
/**
 * 系统服务表(anan_service)服务实现类
 *
 * @author fosin
 * @date 2023-05-12
 */
@Service
@Lazy
public class ServiceServiceImpl implements ServiceService {
    
    private final ServiceDao serviceDao;
    public ServiceServiceImpl (ServiceDao serviceDao) {
        this.serviceDao = serviceDao;
    }
    
    /**
     * 默认DAO
     */
    @Override
    public ServiceDao getDao() {
        return serviceDao;
    }
}
