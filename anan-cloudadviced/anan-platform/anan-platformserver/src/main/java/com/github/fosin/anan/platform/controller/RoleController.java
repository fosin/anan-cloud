package com.github.fosin.anan.platform.controller;

import com.github.fosin.anan.core.exception.AnanControllerException;
import com.github.fosin.anan.mvc.controller.ISimpleController;
import com.github.fosin.anan.mvc.module.PageModule;
import com.github.fosin.anan.mvc.result.Result;
import com.github.fosin.anan.mvc.service.ISimpleService;
import com.github.fosin.anan.platformapi.dto.request.*;
import com.github.fosin.anan.platformapi.entity.*;
import com.github.fosin.anan.platform.service.inter.RolePermissionService;
import com.github.fosin.anan.platform.service.inter.RoleService;
import com.github.fosin.anan.platform.service.inter.UserRoleService;
import com.github.fosin.anan.platform.service.inter.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("v1/role")
@Api(value = "v1/role", tags = "角色管理", description = "角色管理相关操作")
public class RoleController implements ISimpleController<AnanRoleEntity, Long, AnanRoleCreateDto, AnanRoleRetrieveDto, AnanRoleUpdateDto> {
    @Autowired
    private RoleService roleService;
    @Autowired
    private RolePermissionService rolePermissionService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private UserService userService;

    @ApiOperation("根据角色ID获取角色权限")
    @ApiImplicitParam(name = "roleId", value = "角色ID,取值于AnanRoleEntity.id")
    @RequestMapping(value = "/permissions/{roleId}", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<List<AnanRolePermissionEntity>> permissions(@PathVariable Long roleId) {
        return ResponseEntity.ok(rolePermissionService.findByRoleId(roleId));
    }

    @ApiOperation(value = "根据角色ID更新角色权限", notes = "根据角色ID更新角色权限，此操作将先删除原权限，再新增新权限")
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "entities", value = "角色权限集合(List<AnanRolePermissionEntity>)"),
            @ApiImplicitParam(name = "roleId", value = "角色ID,取值于AnanRoleEntity.id")
    })
    @PutMapping(value = "/permissions/{roleId}")
    public ResponseEntity<Collection<AnanRolePermissionEntity>> permissions(@RequestBody List<AnanRolePermissionUpdateDto> entities,
                                                                              @PathVariable("roleId") Long roleId) {
        return ResponseEntity.ok(rolePermissionService.updateInBatch(roleId, entities));
    }

    @ApiOperation("根据角色唯一id查找该角色所有用户信息")
    @ApiImplicitParam(name = "roleId", value = "角色ID,取值于AnanRoleEntity.id")
    @RequestMapping(value = "/users/{roleId}", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<List<AnanUserEntity>> getRoleUsers(@PathVariable("roleId") Long roleId) {
        return ResponseEntity.ok(userService.findRoleUsersByRoleId(roleId));
    }


    @ApiOperation(value = "根据角色ID更新角色拥有的用户", notes = "更新角色拥有的用户，此操作将先删除原用户集合，再新增新用户集合")
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "entities", value = "角色用户集合(List<AnanUserRoleEntity>)"),
            @ApiImplicitParam(name = "roleId", value = "角色ID,取值于AnanRoleEntity.id"),
    })
    @PutMapping(value = "/users/{roleId}")
    public ResponseEntity<List<AnanUserRoleEntity>> putUsers(@RequestBody List<AnanUserRoleCreateDto> entities,
                                                               @PathVariable("roleId") Long roleId) {
        return ResponseEntity.ok(userRoleService.updateInBatchByRoleId(roleId, entities));
    }

    @ApiOperation("根据用户唯一id查找用户目前不拥有的所有角色信息")
    @ApiImplicitParam(name = "roleId", value = "角色ID,取值于AnanRoleEntity.id")
    @RequestMapping(value = "/otherUsers/{roleId}", method = {RequestMethod.POST})
    public ResponseEntity<List<AnanUserEntity>> getOtherUsers(@PathVariable("roleId") Long roleId) throws AnanControllerException {
        return ResponseEntity.ok(userService.findOtherUsersByRoleId(roleId));
    }

    @ApiOperation("根据机构ID获取该机构及下级机构的角色分页列表")
    @ApiImplicitParam(name = "organizId", value = "机构ID")
    @RequestMapping(value = "/pageList/organizId/{organizId}", method = {RequestMethod.POST})
    public ResponseEntity<Result> findAllByOrganizId(@PathVariable("organizId") Long organizId, @RequestBody PageModule pageModule) throws AnanControllerException {
        return ResponseEntity.ok(roleService.findAllByOrganizId(organizId, pageModule));
    }

    @PostMapping({"/childList/organizId/{organizId}"})
    @ApiOperation("根据机构ID查询该机构及子机构的所有角色")
    @ApiImplicitParam(name = "organizId", value = "机构ID")
    public ResponseEntity<List<AnanRoleEntity>> findAllByOrganizId(@PathVariable("organizId") Long organizId) {
        return ResponseEntity.ok(roleService.findAllByOrganizId(organizId));
    }

    @Override
    public ISimpleService<AnanRoleEntity, Long, AnanRoleCreateDto, AnanRoleRetrieveDto, AnanRoleUpdateDto> getService() {
        return roleService;
    }
}
