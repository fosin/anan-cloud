package top.fosin.anan.platform.modules.pub;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.fosin.anan.cloudresource.constant.FieldConstant;
import top.fosin.anan.cloudresource.constant.PathPrefixConstant;
import top.fosin.anan.cloudresource.constant.PathSuffixConstant;
import top.fosin.anan.cloudresource.dto.req.PermissionReqDto;
import top.fosin.anan.cloudresource.dto.res.PermissionRespDto;
import top.fosin.anan.cloudresource.dto.res.PermissionRespTreeDto;
import top.fosin.anan.data.controller.BaseController;
import top.fosin.anan.data.controller.IRetrieveTreeController;
import top.fosin.anan.data.controller.ISimpleController;
import top.fosin.anan.data.result.MultResult;
import top.fosin.anan.data.result.ResultUtils;
import top.fosin.anan.platform.modules.pub.service.inter.PermissionService;

import java.util.List;

/**
 * @author fosin
 */
@RestController
@RequestMapping(value = PathPrefixConstant.PERMISSION, params = PathPrefixConstant.DEFAULT_VERSION_PARAM)
@Api(value = PathPrefixConstant.PERMISSION, tags = "权限管理")
@AllArgsConstructor
public class PermissionController extends BaseController
        implements ISimpleController<PermissionReqDto, PermissionRespDto, Long>,
        IRetrieveTreeController<PermissionReqDto, PermissionRespTreeDto, Long> {
    private final PermissionService permissionService;

    @GetMapping(PathSuffixConstant.SERVICE_CODE_VARIBALE)
    @ApiImplicitParam(name = FieldConstant.SERVICE_CODE, value = "服务标识，等同于anan_service.code",
            required = true, dataTypeClass = String.class, paramType = "path")
    @ApiOperation(value = "查询服务对应权限", notes = "根据服务标识(anan_service.code)查询其权限列表")
    public MultResult<PermissionRespDto> findByServiceCode(@PathVariable(FieldConstant.SERVICE_CODE) String serviceCode) {
        return ResultUtils.success(permissionService.findByServiceCode(serviceCode));
    }

    @PostMapping(PathSuffixConstant.SERVICE_CODES)
    @ApiOperation(value = "查询多个服务对应权限", notes = "根据服务标识(anan_service.code)查询其权限列表")
    @ApiImplicitParam(name = "serviceCodes", value = "服务标识，等同于anan_service.code",
            required = true, dataTypeClass = List.class, paramType = "body")
    public MultResult<PermissionRespDto> findByServiceCodes(@RequestBody List<String> serviceCodes) {
        return ResultUtils.success(permissionService.findByServiceCodes(serviceCodes));
    }

    @Override
    public PermissionService getService() {
        return permissionService;
    }
}
