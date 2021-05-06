package top.fosin.anan.platform.controller;

import top.fosin.anan.core.exception.AnanControllerException;
import top.fosin.anan.model.controller.ISimpleController;
import top.fosin.anan.model.module.PageModule;
import top.fosin.anan.model.result.Result;
import top.fosin.anan.model.service.ISimpleService;
import top.fosin.anan.cloudresource.constant.UrlPrefixConstant;
import top.fosin.anan.cloudresource.dto.request.*;
import top.fosin.anan.platformapi.entity.*;
import top.fosin.anan.platform.service.inter.RolePermissionService;
import top.fosin.anan.platform.service.inter.RoleService;
import top.fosin.anan.platform.service.inter.UserRoleService;
import top.fosin.anan.platform.service.inter.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

/**
 * Description 角色控制器
 *
 * @author fosin
 */
@RestController
@RequestMapping(UrlPrefixConstant.ROLE)
@Api(value = UrlPrefixConstant.ROLE, tags = "角色管理相关操作")
public class RoleController implements ISimpleController<AnanRoleEntity, Long,
        AnanRoleCreateDto, AnanRoleRetrieveDto, AnanRoleUpdateDto> {
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
    public ResponseEntity<List<AnanRolePermissionEntity>> permissions(@PathVariable Long roleId) {
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
    public ResponseEntity<Collection<AnanRolePermissionEntity>> permissions(@RequestBody List<AnanRolePermissionUpdateDto> entities,
                                                                            @PathVariable("roleId") Long roleId) {
        return ResponseEntity.ok(rolePermissionService.updateInBatch(roleId, entities));
    }

    @ApiOperation("根据角色唯一id查找该角色所有用户信息")
    @ApiImplicitParam(name = "roleId", value = "角色ID,取值于AnanRoleEntity.id",
            required = true, dataTypeClass = Long.class, paramType = "path")
    @RequestMapping(value = "/users/{roleId}", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<List<AnanUserEntity>> getRoleUsers(@PathVariable("roleId") Long roleId) {
        return ResponseEntity.ok(userService.findRoleUsersByRoleId(roleId));
    }


    @ApiOperation(value = "根据角色ID更新角色拥有的用户", notes = "更新角色拥有的用户，此操作将先删除原用户集合，再新增新用户集合")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "entities", value = "角色用户集合(List<AnanUserRoleEntity>)",
                    required = true, dataTypeClass = List.class, paramType = "body"),
            @ApiImplicitParam(name = "roleId", value = "角色ID,取值于AnanRoleEntity.id",
                    required = true, dataTypeClass = Long.class, paramType = "path"),
    })
    @PutMapping(value = "/users/{roleId}")
    public ResponseEntity<List<AnanUserRoleEntity>> putUsers(@RequestBody List<AnanUserRoleCreateDto> entities,
                                                             @PathVariable("roleId") Long roleId) {
        return ResponseEntity.ok(userRoleService.updateInBatchByRoleId(roleId, entities));
    }

    @ApiOperation("根据用户唯一id查找用户目前不拥有的所有角色信息")
    @ApiImplicitParam(name = "roleId", value = "角色ID,取值于AnanRoleEntity.id",
            required = true, dataTypeClass = Long.class, paramType = "path")
    @RequestMapping(value = "/otherUsers/{roleId}", method = {RequestMethod.POST})
    public ResponseEntity<List<AnanUserEntity>> getOtherUsers(@PathVariable("roleId") Long roleId) throws AnanControllerException {
        return ResponseEntity.ok(userService.findOtherUsersByRoleId(roleId));
    }

    @PostMapping({"/childList/organizId/{organizId}"})
    @ApiOperation("根据机构ID查询该机构及子机构的所有角色")
    @ApiImplicitParam(name = "organizId", value = "机构ID",
            required = true, dataTypeClass = Long.class, paramType = "path")
    public ResponseEntity<List<AnanRoleEntity>> findAllByOrganizId(@PathVariable("organizId") Long organizId) {
        return ResponseEntity.ok(roleService.findAllByOrganizId(organizId));
    }

    @Override
    public ISimpleService<AnanRoleEntity, Long, AnanRoleCreateDto, AnanRoleRetrieveDto, AnanRoleUpdateDto> getService() {
        return roleService;
    }
}
