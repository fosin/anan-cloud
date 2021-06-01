package top.fosin.anan.platform.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import top.fosin.anan.platform.repository.ServiceRepository;
import top.fosin.anan.platform.service.inter.ServiceService;

/**
 * 系统服务表服务实现类
 *
 * @author fosin
 * @date 2020-12-04 17:47:51
 */
@Service
@Lazy
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;

    public ServiceServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }
    /**
     * 获取DAO
     */
    @Override
    public ServiceRepository getRepository() {
        return serviceRepository;
    }
}
