package top.fosin.anan.platform.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fosin.anan.cloudresource.constant.UrlPrefixConstant;
import top.fosin.anan.cloudresource.dto.request.AnanPermissionCreateDto;
import top.fosin.anan.cloudresource.dto.request.AnanPermissionRetrieveDto;
import top.fosin.anan.cloudresource.dto.request.AnanPermissionUpdateDto;
import top.fosin.anan.cloudresource.dto.res.AnanPermissionRespDto;
import top.fosin.anan.model.controller.AbstractBaseController;
import top.fosin.anan.model.controller.IRetrieveTreeController;
import top.fosin.anan.model.controller.ISimpleController;
import top.fosin.anan.model.dto.TreeDto;
import top.fosin.anan.platform.service.inter.PermissionService;
import top.fosin.anan.platformapi.entity.AnanPermissionEntity;

import java.util.Collection;
import java.util.List;

/**
 * Description
 *
 * @author fosin
 */
@RestController
@RequestMapping(UrlPrefixConstant.PERMISSION)
@Api(value = UrlPrefixConstant.PERMISSION, tags = "权限管理")
public class PermissionController extends AbstractBaseController
        implements ISimpleController<AnanPermissionEntity, Long,
        AnanPermissionCreateDto, AnanPermissionRetrieveDto,
        AnanPermissionUpdateDto>,
        IRetrieveTreeController<AnanPermissionEntity,
                AnanPermissionRespDto, Long,
                AnanPermissionRetrieveDto> {

    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @ApiOperation(value = "根据父权限ID获取其孩子数据列表")
    @ApiImplicitParam(name = TreeDto.PID_NAME, value = "父权限ID,AnanPermissionEntity.pid",
            required = true, dataTypeClass = Long.class, paramType = "path")
    @PostMapping("/listChild/{pid}")
    public ResponseEntity<Collection<AnanPermissionEntity>> getListChild(@PathVariable Long pid) {
        return ResponseEntity.ok(permissionService.findByPid(pid));
    }

    @PostMapping("/serviceCode/{serviceCode}")
    @ApiImplicitParam(name = "serviceCode", value = "服务标识，等同于anan_service.code",
            required = true, dataTypeClass = String.class, paramType = "path")
    @ApiOperation(value = "查询应用权限", notes = "根据服务标识(anan_service.code)查询其权限列表")
    public ResponseEntity<List<AnanPermissionEntity>> findByServiceCode(@PathVariable String serviceCode) {
        return ResponseEntity.ok(permissionService.findByServiceCode(serviceCode));
    }

    @Override
    public PermissionService getService() {
        return permissionService;
    }
}
