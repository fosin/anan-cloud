package top.fosin.anan.platform.modules.pub;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.fosin.anan.cloudresource.constant.RequestPath;
import top.fosin.anan.cloudresource.constant.UrlPrefixConstant;
import top.fosin.anan.cloudresource.dto.req.PermissionReqDto;
import top.fosin.anan.cloudresource.dto.res.PermissionRespDto;
import top.fosin.anan.cloudresource.dto.res.PermissionRespTreeDto;
import top.fosin.anan.model.controller.BaseController;
import top.fosin.anan.model.controller.IRetrieveTreeController;
import top.fosin.anan.model.controller.ISimpleController;
import top.fosin.anan.model.result.MultResult;
import top.fosin.anan.model.result.ResultUtils;
import top.fosin.anan.platform.modules.pub.service.inter.PermissionService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author fosin
 */
@RestController
@RequestMapping(UrlPrefixConstant.PERMISSION)
@Api(value = UrlPrefixConstant.PERMISSION, tags = "权限管理")
@AllArgsConstructor
public class PermissionController extends BaseController
        implements ISimpleController<PermissionReqDto, PermissionRespDto, Long>,
        IRetrieveTreeController<PermissionReqDto, PermissionRespTreeDto, Long> {
    private final PermissionService permissionService;

    @PostMapping(RequestPath.SERVICE_CODE)
    @ApiImplicitParam(name = "serviceCode", value = "服务标识，等同于anan_service.code",
            required = true, dataTypeClass = String.class, paramType = "path")
    @ApiOperation(value = "查询服务对应权限", notes = "根据服务标识(anan_service.code)查询其权限列表")
    public MultResult<PermissionRespDto> findByServiceCode(@NotBlank @PathVariable String serviceCode) {
        return ResultUtils.success(permissionService.findByServiceCode(serviceCode));
    }

    @PostMapping(RequestPath.SERVICE_CODES)
    @ApiOperation(value = "查询多个服务对应权限", notes = "根据服务标识(anan_service.code)查询其权限列表")
    @ApiImplicitParam(name = "serviceCodes", value = "服务标识，等同于anan_service.code",
            required = true, dataTypeClass = List.class, paramType = "body")
    public MultResult<PermissionRespDto> findByServiceCodes(@NotEmpty @RequestBody List<String> serviceCodes) {
        return ResultUtils.success(permissionService.findByServiceCodes(serviceCodes));
    }

    @Override
    public PermissionService getService() {
        return permissionService;
    }
}
