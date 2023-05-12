package top.fosin.anan.platform.modules.service.service.inter;

import top.fosin.anan.jpa.service.ICreateJpaService;
import top.fosin.anan.jpa.service.IDeleteJpaService;
import top.fosin.anan.jpa.service.IRetrieveJpaService;
import top.fosin.anan.jpa.service.IUpdateJpaService;
import top.fosin.anan.platform.modules.service.dto.ServiceCreateDTO;
import top.fosin.anan.platform.modules.service.dto.ServiceDTO;
import top.fosin.anan.platform.modules.service.dto.ServiceUpdateDTO;
import top.fosin.anan.platform.modules.service.po.Service;

        /**
 * 系统服务表(anan_service)服务类
 *
 * @author fosin
 * @date 2023-05-12
 */
public interface ServiceService extends 
        ICreateJpaService<ServiceCreateDTO, ServiceDTO, Long, Service>,
        IRetrieveJpaService<ServiceDTO, Long, Service>,
        IUpdateJpaService<ServiceUpdateDTO, Long, Service>,
        IDeleteJpaService<Long, Service> {
}

