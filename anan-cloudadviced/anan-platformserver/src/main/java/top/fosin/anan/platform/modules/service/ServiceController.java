package top.fosin.anan.platform.modules.service;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fosin.anan.cloudresource.constant.PathPrefixConstant;
import top.fosin.anan.data.controller.ICreateController;
import top.fosin.anan.data.controller.IDeleteController;
import top.fosin.anan.data.controller.IRetrieveController;
import top.fosin.anan.data.controller.IUpdateController;
import top.fosin.anan.platform.modules.service.dto.ServiceCreateDTO;
import top.fosin.anan.platform.modules.service.dto.ServiceUpdateDTO;
import top.fosin.anan.platform.modules.service.query.ServiceQuery;
import top.fosin.anan.platform.modules.service.service.inter.ServiceService;
import top.fosin.anan.platform.modules.service.vo.ServiceListVO;
import top.fosin.anan.platform.modules.service.vo.ServicePageVO;
import top.fosin.anan.platform.modules.service.vo.ServiceVO;


/**
 * 系统服务表(anan_service)控制类
 *
 * @author fosin
 * @date 2023-05-12
 */
@RestController
@RequestMapping(value = PathPrefixConstant.SERVICE, params = PathPrefixConstant.DEFAULT_VERSION_PARAM)
@Api(value = PathPrefixConstant.SERVICE, tags = "服务管理")
public class ServiceController implements ICreateController<ServiceCreateDTO, Long>,
        IRetrieveController<ServiceQuery, ServiceVO, ServiceListVO, ServicePageVO, Long>,
        IUpdateController<ServiceUpdateDTO, Long>,
        IDeleteController<Long> {

    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @Override
    public ServiceService getService() {
        return serviceService;
    }
}
