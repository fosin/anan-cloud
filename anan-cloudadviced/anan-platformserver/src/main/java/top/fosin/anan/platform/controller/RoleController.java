package top.fosin.anan.platform.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.fosin.anan.cloudresource.constant.UrlPrefixConstant;
import top.fosin.anan.cloudresource.dto.req.AnanRoleReqDto;
import top.fosin.anan.cloudresource.dto.res.AnanRolePermissionRespDto;
import top.fosin.anan.cloudresource.dto.res.AnanRoleRespDto;
import top.fosin.anan.cloudresource.dto.res.AnanUserRespDto;
import top.fosin.anan.core.exception.AnanControllerException;
import top.fosin.anan.model.controller.ISimpleController;


import top.fosin.anan.platform.dto.req.*;
import top.fosin.anan.platform.entity.AnanRolePermissionEntity;
import top.fosin.anan.platform.service.inter.RolePermissionService;
import top.fosin.anan.platform.service.inter.RoleService;
import top.fosin.anan.platform.service.inter.UserRoleService;
import top.fosin.anan.platform.service.inter.UserService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

/**
 * 角色控制器
 *
 * @author fosin
 */
@RestController
@RequestMapping(UrlPrefixConstant.ROLE)
@Api(value = UrlPrefixConstant.ROLE, tags = "角色管理")
public class RoleController implements ISimpleController<AnanRoleRespDto, Long,
        AnanRoleReqDto, AnanRoleReqDto, AnanRoleReqDto> {
    private final RoleService roleService;
    private final RolePermissionService rolePermissionService;
    private final UserRoleService userRoleService;
    private final UserService userService;

    public RoleController(RoleService roleService, RolePermissionService rolePermissionService, UserRoleService userRoleService, UserService userService) {
        this.roleService = roleService;
        this.rolePermissionService = rolePermissionService;
        this.userRoleService = userRoleService;
        this.userService = userService;
    }

    @ApiOperation("根据角色ID获取角色权限")
    @ApiImplicitParam(name = "roleId", value = "角色ID,取值于AnanRoleEntity.id",
            required = true, dataTypeClass = Long.class, paramType = "path")
    @RequestMapping(value = "/permissions/{roleId}", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<List<AnanRolePermissionEntity>> permissions(@Positive @PathVariable Long roleId) {
        return ResponseEntity.ok(rolePermissionService.findByRoleId(roleId));
    }

    @ApiOperation(value = "根据角色ID更新角色权限", notes = "根据角色ID更新角色权限，此操作将先删除原权限，再新增新权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "entities", value = "角色权限集合(List<AnanRolePermissionEntity>)",
                    required = true, dataTypeClass = List.class, paramType = "body"),
            @ApiImplicitParam(name = "roleId", value = "角色ID,取值于AnanRoleEntity.id",
                    required = true, dataTypeClass = Long.class, paramType = "path")
    })
    @PutMapping(value = "/permissions/{roleId}")
    public ResponseEntity<Collection<AnanRolePermissionRespDto>> permissions(@NotNull @RequestBody List<AnanRolePermissionReqDto> entities,
                                                                             @Positive @PathVariable("roleId") Long roleId) {
        return ResponseEntity.ok(rolePermissionService.updateInBatch("roleId", roleId, entities));
    }

    @ApiOperation("根据角色唯一id查找该角色所有用户信息")
    @ApiImplicitParam(name = "roleId", value = "角色ID,取值于AnanRoleEntity.id",
            required = true, dataTypeClass = Long.class, paramType = "path")
    @RequestMapping(value = "/users/{roleId}", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<List<AnanUserRespDto>> getRoleUsers(@Positive @PathVariable("roleId") Long roleId) {
        return ResponseEntity.ok(userService.findRoleUsersByRoleId(roleId));
    }


    @ApiOperation(value = "根据角色ID更新角色拥有的用户", notes = "更新角色拥有的用户，此操作将先删除原用户集合，再新增新用户集合")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dtos", value = "角色用户集合(List<AnanUserRoleReqDto>)",
                    required = true, dataTypeClass = List.class, paramType = "body"),
            @ApiImplicitParam(name = "roleId", value = "角色ID,取值于AnanRoleEntity.id",
                    required = true, dataTypeClass = Long.class, paramType = "path"),
    })
    @PutMapping(value = "/users/{roleId}")
    public ResponseEntity<Boolean> putRoleUsers(@NotNull @RequestBody List<AnanUserRoleReqDto> dtos,
                                                @Positive @PathVariable("roleId") Long roleId) {
        userRoleService.updateInBatch("roleId", roleId, dtos);
        return ResponseEntity.ok(true);
    }

    @ApiOperation("根据用户唯一id查找用户目前不拥有的所有角色信息")
    @ApiImplicitParam(name = "roleId", value = "角色ID,取值于AnanRoleEntity.id",
            required = true, dataTypeClass = Long.class, paramType = "path")
    @RequestMapping(value = "/otherUsers/{roleId}", method = {RequestMethod.POST})
    public ResponseEntity<List<AnanUserRespDto>> getOtherUsers(@Positive @PathVariable("roleId") Long roleId) throws AnanControllerException {
        return ResponseEntity.ok(userService.findOtherUsersByRoleId(roleId));
    }

    @PostMapping({"/list/organizId/{organizId}"})
    @ApiOperation("根据机构ID查询该机构及子机构的所有角色")
    @ApiImplicitParam(name = "organizId", value = "机构序号",
            required = true, dataTypeClass = Long.class, paramType = "path")
    public ResponseEntity<List<AnanRoleRespDto>> findAllByOrganizId(@Positive @PathVariable("organizId") Long organizId) {
        return ResponseEntity.ok(roleService.findAllByOrganizId(organizId));
    }

    @Override
    public RoleService getService() {
        return roleService;
    }
}
