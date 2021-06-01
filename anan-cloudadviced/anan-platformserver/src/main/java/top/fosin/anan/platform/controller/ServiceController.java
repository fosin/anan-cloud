package top.fosin.anan.platform.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fosin.anan.cloudresource.constant.UrlPrefixConstant;
import top.fosin.anan.cloudresource.dto.res.AnanServiceRespDto;
import top.fosin.anan.model.controller.ISimpleController;
import top.fosin.anan.model.controller.IStatusController;
import top.fosin.anan.platform.dto.req.AnanServiceCreateDto;
import top.fosin.anan.platform.dto.req.AnanServiceRetrieveDto;
import top.fosin.anan.platform.dto.req.AnanServiceUpdateDto;
import top.fosin.anan.platform.service.inter.ServiceService;

/**
 * 系统服务表(anan_service: table)表控制层
 *
 * @author fosin
 * @date 2020-12-04 17:48:02
 */
@RestController
@RequestMapping(UrlPrefixConstant.SERVICE)
@Api(value = UrlPrefixConstant.SERVICE, tags = "系统服务管理")
public class ServiceController implements ISimpleController<AnanServiceRespDto, Long,
        AnanServiceCreateDto, AnanServiceRetrieveDto, AnanServiceUpdateDto>,
        IStatusController<AnanServiceRespDto, Long, Integer> {

    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @Override
    public ServiceService getService() {
        return serviceService;
    }
}
