package top.fosin.anan.platform.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.fosin.anan.cloudresource.constant.UrlPrefixConstant;
import top.fosin.anan.model.controller.ISimpleController;


import top.fosin.anan.platform.dto.req.AnanVersionRoleCreateDto;
import top.fosin.anan.platform.dto.req.AnanVersionRolePermissionCreateDto;
import top.fosin.anan.platform.dto.req.AnanVersionRoleRetrieveDto;
import top.fosin.anan.platform.dto.req.AnanVersionRoleUpdateDto;
import top.fosin.anan.platform.dto.res.AnanVersionRolePermissionRespDto;
import top.fosin.anan.platform.dto.res.AnanVersionRoleRespDto;
import top.fosin.anan.platform.service.inter.VersionRolePermissionService;
import top.fosin.anan.platform.service.inter.VersionRoleService;

import java.util.List;

/**
 * 系统版本角色表(table:anan_version_role)表控制层
 *
 * @author fosin
 * @date 2018-11-18 17:50:28
 */
@RestController
@RequestMapping(UrlPrefixConstant.VERSION_ROLE)
@Api(value = UrlPrefixConstant.VERSION_ROLE, tags = "版本角色管理")
public class VersionRoleController implements ISimpleController<AnanVersionRoleRespDto,
        Long, AnanVersionRoleCreateDto, AnanVersionRoleRetrieveDto, AnanVersionRoleUpdateDto> {
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
    @ApiImplicitParam(name = "roleId", value = "版本ID,取值于AnanVersionRoleEntity.id", required = true, dataTypeClass = Long.class, paramType = "path")
    @RequestMapping(value = "/permissions/{roleId}", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<List<AnanVersionRolePermissionRespDto>> permissions(@PathVariable Long roleId) {
        return ResponseEntity.ok(versionRolePermissionService.findByRoleId(roleId));
    }

    @ApiOperation(value = "根据版本ID更新版本权限", notes = "根据版本ID更新版本权限，此操作将先删除原权限，再新增新权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "entities", value = "版本权限集合(List<AnanVersionRolePermissionEntity>)", required = true, dataTypeClass = List.class, paramType = "body"),
            @ApiImplicitParam(name = "roleId", value = "版本ID,取值于AnanVersionRoleEntity.id", required = true, dataTypeClass = Long.class, paramType = "path")
    })
    @PutMapping(value = "/permissions/{roleId}")
    public ResponseEntity<Boolean> permissions(@RequestBody List<AnanVersionRolePermissionCreateDto> entities,
                                               @PathVariable("roleId") Long roleId) {
        //更新版本权限
        versionRolePermissionService.updateInBatch("roleId", roleId, entities);
        return ResponseEntity.ok(true);
    }

    @Override
    public VersionRoleService getService() {
        return versionRoleService;
    }
}
