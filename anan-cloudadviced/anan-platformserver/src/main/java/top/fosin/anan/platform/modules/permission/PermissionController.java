package top.fosin.anan.platform.modules.permission;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import top.fosin.anan.cloudresource.constant.FieldConstant;
import top.fosin.anan.cloudresource.constant.PathPrefixConstant;
import top.fosin.anan.cloudresource.constant.PathSuffixConstant;
import top.fosin.anan.cloudresource.entity.res.PermissionDTO;
import top.fosin.anan.cloudresource.entity.res.PermissionTreeDTO;
import top.fosin.anan.data.controller.*;
import top.fosin.anan.data.result.MultResult;
import top.fosin.anan.data.result.ResultUtils;
import top.fosin.anan.platform.modules.permission.dto.PermissionCreateDTO;
import top.fosin.anan.platform.modules.permission.dto.PermissionUpdateDTO;
import top.fosin.anan.platform.modules.permission.query.PermissionQuery;
import top.fosin.anan.platform.modules.permission.service.inter.PermissionService;
import top.fosin.anan.platform.modules.permission.vo.PermissionVO;

import java.util.List;

/**
 * @author fosin
 */
@RestController
@RequestMapping(value = PathPrefixConstant.PERMISSION, params = PathPrefixConstant.DEFAULT_VERSION_PARAM)
@Api(value = PathPrefixConstant.PERMISSION, tags = "权限管理")
public class PermissionController implements ICreateController<PermissionCreateDTO, Long>,
        IFindOneByIdController<PermissionVO, Long>,
        IUpdateController<PermissionUpdateDTO, Long>,
        IDeleteController<Long>,
        IRetrieveTreeController<PermissionQuery, PermissionTreeDTO, Long> {

    private final PermissionService permissionService;
    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @GetMapping(PathSuffixConstant.SERVICE_CODE_VARIBALE)
    @ApiImplicitParam(name = FieldConstant.SERVICE_CODE, value = "服务标识，等同于anan_service.code",
            required = true, dataTypeClass = String.class, paramType = "path")
    @ApiOperation(value = "查询服务对应权限", notes = "根据服务标识(anan_service.code)查询其权限列表")
    public MultResult<PermissionDTO> findByServiceCode(@PathVariable(FieldConstant.SERVICE_CODE) String serviceCode) {
        return ResultUtils.success(permissionService.findByServiceCode(serviceCode));
    }

    @PostMapping(PathSuffixConstant.SERVICE_CODES)
    @ApiOperation(value = "查询多个服务对应权限", notes = "根据服务标识(anan_service.code)查询其权限列表")
    @ApiImplicitParam(name = "serviceCodes", value = "服务标识，等同于anan_service.code",
            required = true, dataTypeClass = List.class, paramType = "body")
    public MultResult<PermissionDTO> findByServiceCodes(@RequestBody List<String> serviceCodes) {
        return ResultUtils.success(permissionService.findByServiceCodes(serviceCodes));
    }

    @Override
    public PermissionService getService() {
        return permissionService;
    }
}
