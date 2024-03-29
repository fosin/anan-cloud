package top.fosin.anan.platform.modules.pub;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fosin.anan.cloudresource.constant.PathPrefixConstant;
import top.fosin.anan.cloudresource.dto.res.ServiceRespDto;
import top.fosin.anan.data.controller.ISimpleController;
import top.fosin.anan.platform.modules.pub.dto.ServiceReqDto;
import top.fosin.anan.platform.modules.pub.service.inter.ServiceService;


/**
 * 系统服务表(anan_service: table)表控制层
 *
 * @author fosin
 * @date 2020-12-04 17:48:02
 */
@RestController
@RequestMapping(value = PathPrefixConstant.SERVICE, params = PathPrefixConstant.DEFAULT_VERSION_PARAM)
@Api(value = PathPrefixConstant.SERVICE, tags = "服务管理")
@AllArgsConstructor
public class ServiceController implements ISimpleController<ServiceReqDto, ServiceRespDto, Long> {
    private final ServiceService serviceService;

    @Override
    public ServiceService getService() {
        return serviceService;
    }
}
