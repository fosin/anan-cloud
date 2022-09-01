package top.fosin.anan.platform.modules.role;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.fosin.anan.cloudresource.constant.FieldConstant;
import top.fosin.anan.cloudresource.constant.PathPrefixConstant;
import top.fosin.anan.cloudresource.constant.PathSuffixConstant;
import top.fosin.anan.cloudresource.dto.req.RoleReqDto;
import top.fosin.anan.cloudresource.dto.res.RolePermissionRespDto;
import top.fosin.anan.cloudresource.dto.res.RoleRespDto;
import top.fosin.anan.cloudresource.dto.res.UserRespDto;
import top.fosin.anan.core.exception.AnanControllerException;
import top.fosin.anan.data.controller.ISimpleController;
import top.fosin.anan.data.result.MultResult;
import top.fosin.anan.data.result.ResultUtils;
import top.fosin.anan.data.result.SingleResult;
import top.fosin.anan.platform.modules.role.dto.RolePermissionReqDto;
import top.fosin.anan.platform.modules.role.dto.RoleUserReqDto;
import top.fosin.anan.platform.modules.role.service.inter.RolePermissionService;
import top.fosin.anan.platform.modules.role.service.inter.RoleService;
import top.fosin.anan.platform.modules.role.service.inter.RoleUserService;
import top.fosin.anan.platform.modules.user.service.inter.UserService;

import java.util.List;

/**
 * 角色控制器
 *
 * @author fosin
 */
@RestController
@RequestMapping(value = PathPrefixConstant.ROLE, params = PathPrefixConstant.DEFAULT_VERSION_PARAM)
@Api(value = PathPrefixConstant.ROLE, tags = "角色管理")
@AllArgsConstructor
public class RoleController implements ISimpleController<RoleReqDto, RoleRespDto, Long> {

    private final RoleService roleService;
    private final UserService userService;
    private final RolePermissionService rolePermissionService;
    private final RoleUserService roleUserService;

    @ApiOperation("根据角色ID获取角色权限")
    @ApiImplicitParam(name = FieldConstant.ROLE_ID, value = "角色ID,取值于Role.id",
            required = true, dataTypeClass = Long.class, paramType = "path")
    @GetMapping(value = "/permissions" + PathSuffixConstant.ROLE_ID)
    public MultResult<RolePermissionRespDto> permissions(@PathVariable(FieldConstant.ROLE_ID) Long roleId) {
        return ResultUtils.success(rolePermissionService.listByForeingKey(roleId));
    }

    @ApiOperation(value = "根据角色ID更新角色权限", notes = "根据角色ID更新角色权限，此操作将先删除原权限，再新增新权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "reqDtos", value = "角色权限集合(List<RolePermission>)",
                    required = true, dataTypeClass = List.class, paramType = "body"),
            @ApiImplicitParam(name = FieldConstant.ROLE_ID, value = "角色ID,取值于Role.id",
                    required = true, dataTypeClass = Long.class, paramType = "path")
    })
    @PutMapping(value = "/permissions" + PathSuffixConstant.ROLE_ID)
    public MultResult<RolePermissionRespDto> permissions(
            @RequestBody List<RolePermissionReqDto> reqDtos,
            @PathVariable(FieldConstant.ROLE_ID) Long roleId) {
        return ResultUtils.success(rolePermissionService.processInBatch(roleId, reqDtos, false));
    }

    @ApiOperation("根据角色序号查找该角色所有用户信息")
    @ApiImplicitParam(name = FieldConstant.ROLE_ID, value = "角色ID,取值于Role.id",
            required = true, dataTypeClass = Long.class, paramType = "path")
    @GetMapping(value = "/users" + PathSuffixConstant.ROLE_ID)
    public MultResult<UserRespDto> getRoleUsers(@PathVariable(FieldConstant.ROLE_ID) Long roleId) {
        return ResultUtils.success(userService.findRoleUsersByRoleId(roleId));
    }

    @ApiOperation(value = "根据角色ID更新角色拥有的用户", notes = "更新角色拥有的用户，此操作将先删除原用户集合，再新增新用户集合")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "reqDtos", value = "角色用户集合(List<RoleUserReqDto>)",
                    required = true, dataTypeClass = List.class, paramType = "body"),
            @ApiImplicitParam(name = FieldConstant.ROLE_ID, value = "角色ID,取值于Role.id",
                    required = true, dataTypeClass = Long.class, paramType = "path"),
    })
    @PutMapping(value = "/users" + PathSuffixConstant.ROLE_ID)
    public SingleResult<Boolean> putRoleUsers(@RequestBody List<RoleUserReqDto> reqDtos,
                                              @PathVariable(FieldConstant.ROLE_ID) Long roleId) {
        roleUserService.processInBatch(roleId, reqDtos);
        return ResultUtils.success(true);
    }

    @ApiOperation("根据用户序号查找用户目前不拥有的所有角色信息")
    @ApiImplicitParam(name = FieldConstant.ROLE_ID, value = "角色ID,取值于Role.id",
            required = true, dataTypeClass = Long.class, paramType = "path")
    @GetMapping(value = "/otherUsers" + PathSuffixConstant.ROLE_ID)
    public MultResult<UserRespDto> getOtherUsers(@PathVariable(FieldConstant.ROLE_ID) Long roleId) throws AnanControllerException {
        return ResultUtils.success(userService.findOtherUsersByRoleId(roleId));
    }

    @GetMapping({"/list/organizId/{organizId}"})
    @ApiOperation("根据机构ID查询该机构及子机构的所有角色")
    @ApiImplicitParam(name = FieldConstant.ORGANIZ_ID, value = "机构序号",
            required = true, dataTypeClass = Long.class, paramType = "path")
    public MultResult<RoleRespDto> findAllByOrganizId(@PathVariable(FieldConstant.ORGANIZ_ID) Long organizId) {
        return ResultUtils.success(roleService.findAllByOrganizId(organizId));
    }

    @Override
    public RoleService getService() {
        return roleService;
    }
}
