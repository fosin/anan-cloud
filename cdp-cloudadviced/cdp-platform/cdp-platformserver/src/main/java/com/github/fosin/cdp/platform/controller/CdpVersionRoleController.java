package com.github.fosin.cdp.platform.controller;


import com.github.fosin.cdp.mvc.controller.ISimpleController;
import com.github.fosin.cdp.mvc.service.ISimpleService;
import com.github.fosin.cdp.platform.dto.request.CdpVersionRoleCreateDto;
import com.github.fosin.cdp.platform.dto.request.CdpVersionRolePermissionUpdateDto;
import com.github.fosin.cdp.platform.dto.request.CdpVersionRoleRetrieveDto;
import com.github.fosin.cdp.platform.dto.request.CdpVersionRoleUpdateDto;
import com.github.fosin.cdp.platform.entity.CdpVersionRoleEntity;
import com.github.fosin.cdp.platform.entity.CdpVersionRolePermissionEntity;
import com.github.fosin.cdp.platform.service.inter.ICdpVersionRolePermissionService;
import com.github.fosin.cdp.platform.service.inter.ICdpVersionRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统版本角色表(table:cdp_version_role)表控制层
 *
 * @author fosin
 * @date 2018-11-18 17:50:28
 */
@RestController
@RequestMapping("v1/version/role")
@Api(value = "v1/version/role", tags = "系统版本角色表接入层API", description = "系统版本角色表(cdp_version_role)接入层API")
public class CdpVersionRoleController implements ISimpleController<CdpVersionRoleEntity, Long, CdpVersionRoleCreateDto, CdpVersionRoleRetrieveDto, CdpVersionRoleUpdateDto> {
    /**
     * 服务对象
     */
    @Autowired
    private ICdpVersionRoleService cdpSysVersionRoleService;

    @Autowired
    private ICdpVersionRolePermissionService versionRolePermissionService;

    @ApiOperation("根据角色ID获取版本权限")
    @ApiImplicitParam(name = "roleId", value = "版本ID,取值于CdpVersionRoleEntity.id")
    @RequestMapping(value = "/permissions/{roleId}", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<List<CdpVersionRolePermissionEntity>> permissions(@PathVariable Long roleId) {
        return ResponseEntity.ok(versionRolePermissionService.findByRoleId(roleId));
    }

    @ApiOperation(value = "根据版本ID更新版本权限", notes = "根据版本ID更新版本权限，此操作将先删除原权限，再新增新权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "entities", value = "版本权限集合(List<CdpVersionRolePermissionEntity>)"),
            @ApiImplicitParam(name = "roleId", value = "版本ID,取值于CdpVersionRoleEntity.id")
    })
    @PutMapping(value = "/permissions/{roleId}")
    public ResponseEntity<Boolean> permissions(@RequestBody List<CdpVersionRolePermissionUpdateDto> entities,
                                               @PathVariable("roleId") Long roleId) {
        //更新版本权限
        versionRolePermissionService.updateInBatch(roleId, entities);
        return ResponseEntity.ok(true);
    }

    @Override
    public ISimpleService<CdpVersionRoleEntity, Long, CdpVersionRoleCreateDto, CdpVersionRoleRetrieveDto, CdpVersionRoleUpdateDto> getService() {
        return cdpSysVersionRoleService;
    }
}
