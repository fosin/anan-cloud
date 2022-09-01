package top.fosin.anan.platform.modules.version;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import top.fosin.anan.cloudresource.constant.FieldConstant;
import top.fosin.anan.cloudresource.constant.PathPrefixConstant;
import top.fosin.anan.cloudresource.constant.PathSuffixConstant;
import top.fosin.anan.data.controller.ISimpleController;
import top.fosin.anan.data.result.MultResult;
import top.fosin.anan.data.result.ResultUtils;
import top.fosin.anan.data.result.SingleResult;
import top.fosin.anan.platform.modules.version.dto.VersionRolePermissionReqDto;
import top.fosin.anan.platform.modules.version.dto.VersionRolePermissionRespDto;
import top.fosin.anan.platform.modules.version.dto.VersionRoleReqDto;
import top.fosin.anan.platform.modules.version.dto.VersionRoleRespDto;
import top.fosin.anan.platform.modules.version.service.inter.VersionRolePermissionService;
import top.fosin.anan.platform.modules.version.service.inter.VersionRoleService;

import java.util.List;

/**
 * 系统版本角色表(table:anan_version_role)表控制层
 *
 * @author fosin
 * @date 2018-11-18 17:50:28
 */
@RestController
@RequestMapping(value = PathPrefixConstant.VERSION_ROLE, params = PathPrefixConstant.DEFAULT_VERSION_PARAM)
@Api(value = PathPrefixConstant.VERSION_ROLE, tags = "版本角色管理")
public class VersionRoleController implements ISimpleController<VersionRoleReqDto,VersionRoleRespDto, Long> {
    /**
     * 服务对象
     */
    private final VersionRoleService versionRoleService;
    private final VersionRolePermissionService versionRolePermissionService;

    public VersionRoleController(VersionRoleService versionRoleService, VersionRolePermissionService versionRolePermissionService) {
        this.versionRoleService = versionRoleService;
        this.versionRolePermissionService = versionRolePermissionService;
    }

    @ApiOperation("根据角色ID获取版本权限")
    @ApiImplicitParam(name = FieldConstant.ROLE_ID, value = "版本ID,取值于VersionRole.id", required = true, dataTypeClass = Long.class, paramType = "path")
    @GetMapping(value = "/permissions" + PathSuffixConstant.ROLE_ID)
    public MultResult<VersionRolePermissionRespDto> permissions(@PathVariable Long roleId) {
        return ResultUtils.success(versionRolePermissionService.listByForeingKey(roleId));
    }

    @ApiOperation(value = "根据版本ID更新版本权限", notes = "根据版本ID更新版本权限，此操作将先删除原权限，再新增新权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "entities", value = "版本权限集合(List<VersionRolePermission>)", required = true, dataTypeClass = List.class, paramType = "body"),
            @ApiImplicitParam(name = FieldConstant.ROLE_ID, value = "版本ID,取值于VersionRole.id", required = true, dataTypeClass = Long.class, paramType = "path")
    })
    @PutMapping(value = "/permissions" + PathSuffixConstant.ROLE_ID)
    public SingleResult<Boolean> permissions(@RequestBody List<VersionRolePermissionReqDto> entities,
                                             @PathVariable(FieldConstant.ROLE_ID) Long roleId) {
        //更新版本权限
        versionRolePermissionService.processInBatch(roleId, entities, false);
        return ResultUtils.success(true);
    }

    @Override
    public VersionRoleService getService() {
        return versionRoleService;
    }
}
