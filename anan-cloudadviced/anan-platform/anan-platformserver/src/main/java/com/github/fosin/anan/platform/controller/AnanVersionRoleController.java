package com.github.fosin.anan.platform.controller;


import com.github.fosin.anan.mvc.controller.ISimpleController;
import com.github.fosin.anan.mvc.service.ISimpleService;
import com.github.fosin.anan.platform.dto.request.AnanVersionRoleCreateDto;
import com.github.fosin.anan.platform.dto.request.AnanVersionRolePermissionUpdateDto;
import com.github.fosin.anan.platform.dto.request.AnanVersionRoleRetrieveDto;
import com.github.fosin.anan.platform.dto.request.AnanVersionRoleUpdateDto;
import com.github.fosin.anan.platform.entity.AnanVersionRoleEntity;
import com.github.fosin.anan.platform.entity.AnanVersionRolePermissionEntity;
import com.github.fosin.anan.platform.service.inter.AnanVersionRolePermissionService;
import com.github.fosin.anan.platform.service.inter.AnanVersionRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统版本角色表(table:anan_version_role)表控制层
 *
 * @author fosin
 * @date 2018-11-18 17:50:28
 */
@RestController
@RequestMapping("v1/version/role")
@Api(value = "v1/version/role", tags = "系统版本角色表接入层API", description = "系统版本角色表(anan_version_role)接入层API")
public class AnanVersionRoleController implements ISimpleController<AnanVersionRoleEntity, Long, AnanVersionRoleCreateDto, AnanVersionRoleRetrieveDto, AnanVersionRoleUpdateDto> {
    /**
     * 服务对象
     */
    private final AnanVersionRoleService ananSysVersionRoleService;
    private final AnanVersionRolePermissionService versionRolePermissionService;
    public AnanVersionRoleController(AnanVersionRoleService ananSysVersionRoleService, AnanVersionRolePermissionService versionRolePermissionService) {
        this.ananSysVersionRoleService = ananSysVersionRoleService;
        this.versionRolePermissionService = versionRolePermissionService;
    }

    @ApiOperation("根据角色ID获取版本权限")
    @ApiImplicitParam(name = "roleId", value = "版本ID,取值于AnanVersionRoleEntity.id")
    @RequestMapping(value = "/permissions/{roleId}", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<List<AnanVersionRolePermissionEntity>> permissions(@PathVariable Long roleId) {
        return ResponseEntity.ok(versionRolePermissionService.findByRoleId(roleId));
    }

    @ApiOperation(value = "根据版本ID更新版本权限", notes = "根据版本ID更新版本权限，此操作将先删除原权限，再新增新权限")
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "entities", value = "版本权限集合(List<AnanVersionRolePermissionEntity>)"),
            @ApiImplicitParam(name = "roleId", value = "版本ID,取值于AnanVersionRoleEntity.id")
    })
    @PutMapping(value = "/permissions/{roleId}")
    public ResponseEntity<Boolean> permissions(@RequestBody List<AnanVersionRolePermissionUpdateDto> entities,
                                               @PathVariable("roleId") Long roleId) {
        //更新版本权限
        versionRolePermissionService.updateInBatch(roleId, entities);
        return ResponseEntity.ok(true);
    }

    @Override
    public ISimpleService<AnanVersionRoleEntity, Long, AnanVersionRoleCreateDto, AnanVersionRoleRetrieveDto, AnanVersionRoleUpdateDto> getService() {
        return ananSysVersionRoleService;
    }
}
