package top.fosin.anan.platform.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fosin.anan.cloudresource.constant.UrlPrefixConstant;
import top.fosin.anan.cloudresource.dto.res.AnanServiceRespDto;
import top.fosin.anan.model.controller.ISimpleController;
import top.fosin.anan.platform.dto.req.AnanServiceCreateDto;
import top.fosin.anan.platform.dto.req.AnanServiceRetrieveDto;
import top.fosin.anan.platform.dto.req.AnanServiceUpdateDto;
import top.fosin.anan.platform.service.inter.AnanServiceService;

import java.util.List;

/**
 * 系统服务表(anan_service: table)表控制层
 *
 * @author fosin
 * @date 2020-12-04 17:48:02
 */
@RestController
@RequestMapping(UrlPrefixConstant.SERVICE)
@Api(value = UrlPrefixConstant.SERVICE, tags = "系统服务表(anan_service)接入层API")
public class AnanServiceController implements ISimpleController<AnanServiceRespDto, Long,
        AnanServiceCreateDto, AnanServiceRetrieveDto, AnanServiceUpdateDto> {

    private final AnanServiceService ananServiceService;

    public AnanServiceController(AnanServiceService ananServiceService) {
        this.ananServiceService = ananServiceService;
    }

    @RequestMapping(path = "/status/{status}")
    @ApiOperation("根据状态码查找所有服务数据")
    @ApiImplicitParam(
            name = "status",
            value = "状态: 0=有效，1=无效",
            paramType = "path",
            required = true,
            dataTypeClass = Integer.class
    )
    public ResponseEntity<List<AnanServiceRespDto>> findAllByStatus(@PathVariable Integer status) {
        return ResponseEntity.ok(ananServiceService.findAllByStatus(status));
    }

    @Override
    public AnanServiceService getService() {
        return ananServiceService;
    }
}
