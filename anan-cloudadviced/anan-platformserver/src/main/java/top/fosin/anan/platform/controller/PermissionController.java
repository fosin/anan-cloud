package top.fosin.anan.platform.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.fosin.anan.cloudresource.constant.RequestPath;
import top.fosin.anan.cloudresource.constant.UrlPrefixConstant;
import top.fosin.anan.cloudresource.dto.req.AnanPermissionReqDto;
import top.fosin.anan.cloudresource.dto.res.AnanPermissionRespDto;
import top.fosin.anan.cloudresource.dto.res.AnanPermissionRespTreeDto;
import top.fosin.anan.model.controller.BaseController;
import top.fosin.anan.model.controller.IRetrieveTreeController;
import top.fosin.anan.model.controller.ISimpleController;
import top.fosin.anan.platform.service.inter.PermissionService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author fosin
 */
@RestController
@RequestMapping(UrlPrefixConstant.PERMISSION)
@Api(value = UrlPrefixConstant.PERMISSION, tags = "权限管理")
public class PermissionController extends BaseController
        implements ISimpleController<AnanPermissionRespDto, Long,
        AnanPermissionReqDto, AnanPermissionReqDto,
        AnanPermissionReqDto>,
        IRetrieveTreeController<AnanPermissionRespTreeDto, Long,
                AnanPermissionReqDto> {

    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PostMapping(RequestPath.SERVICE_CODE)
    @ApiImplicitParam(name = "serviceCode", value = "服务标识，等同于anan_service.code",
            required = true, dataTypeClass = String.class, paramType = "path")
    @ApiOperation(value = "查询服务对应权限", notes = "根据服务标识(anan_service.code)查询其权限列表")
    public ResponseEntity<List<AnanPermissionRespDto>> findByServiceCode(@NotBlank @PathVariable String serviceCode) {
        return ResponseEntity.ok(permissionService.findByServiceCode(serviceCode));
    }

    @PostMapping(RequestPath.SERVICE_CODES)
    @ApiOperation(value = "查询多个服务对应权限", notes = "根据服务标识(anan_service.code)查询其权限列表")
    @ApiImplicitParam(name = "serviceCodes", value = "服务标识，等同于anan_service.code",
            required = true, dataTypeClass = List.class, paramType = "body")
    public ResponseEntity<List<AnanPermissionRespDto>> findByServiceCodes(@NotEmpty @RequestBody List<String> serviceCodes) {
        return ResponseEntity.ok(permissionService.findByServiceCodes(serviceCodes));
    }

    @Override
    public PermissionService getService() {
        return permissionService;
    }
}
