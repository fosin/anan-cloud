package com.github.fosin.anan.platform.controller;


import com.github.fosin.anan.model.controller.ISimpleController;
import com.github.fosin.anan.model.service.ISimpleService;
import com.github.fosin.anan.platform.dto.request.*;
import com.github.fosin.anan.platform.entity.AnanOrganizationAuthEntity;
import com.github.fosin.anan.platform.entity.AnanVersionEntity;
import com.github.fosin.anan.platform.entity.AnanVersionPermissionEntity;
import com.github.fosin.anan.platform.service.inter.*;
import com.github.fosin.anan.platformapi.entity.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 系统版本表(table:anan_version)表控制层
 *
 * @author fosin
 * @date 2018-11-18 17:50:28
 */
@RestController
@RequestMapping("v1/version")
@Api(value = "v1/version", tags = "系统版本表(anan_version)接入层API")
public class AnanVersionController implements ISimpleController<AnanVersionEntity, Long, AnanVersionCreateDto, AnanVersionRetrieveDto, AnanVersionUpdateDto> {
    /**
     * 服务对象
     */
    private final AnanVersionService ananSysVersionService;
    private final AnanVersionPermissionService versionPermissionService;
    private final AnanOrganizationPermissionService organizationPermissionService;
    private final AnanOrganizationAuthService organizationAuthService;
    private final PermissionService permissionService;

    public AnanVersionController(AnanVersionService ananSysVersionService, AnanVersionPermissionService versionPermissionService, AnanOrganizationPermissionService organizationPermissionService, AnanOrganizationAuthService organizationAuthService, PermissionService permissionService) {
        this.ananSysVersionService = ananSysVersionService;
        this.versionPermissionService = versionPermissionService;
        this.organizationPermissionService = organizationPermissionService;
        this.organizationAuthService = organizationAuthService;
        this.permissionService = permissionService;
    }

    @ApiOperation(value = "根据父权限ID获取其孩子数据列表")
    @ApiImplicitParam(name = "pid", value = "父权限ID,AnanVersionPermissionEntity.id")
    @RequestMapping(value = "/listChild/{pid}", method = {RequestMethod.POST})
    public ResponseEntity<List<AnanPermissionEntity>> getListChild(@PathVariable Long pid, @RequestParam Long versionId) {
        List<AnanPermissionEntity> list = permissionService.findByPid(pid, versionId);
        return ResponseEntity.ok(list);
    }

    @ApiOperation("根据版本ID获取版本权限")
    @ApiImplicitParam(name = "versionId", value = "版本ID,取值于AnanRoleEntity.id")
    @RequestMapping(value = "/permissions/{versionId}", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<List<AnanVersionPermissionEntity>> permissions(@PathVariable Long versionId) {
        return ResponseEntity.ok(versionPermissionService.findByVersionId(versionId));
    }

    @ApiOperation(value = "根据版本ID更新版本权限", notes = "根据版本ID更新版本权限，此操作将先删除原权限，再新增新权限")
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "entities", value = "版本权限集合(List<AnanVersionPermissionEntity>)"),
            @ApiImplicitParam(name = "versionId", value = "版本ID,取值于AnanVersionEntity.id")
    })
    @PutMapping(value = "/permissions/{versionId}")
    public ResponseEntity<Boolean> permissions(@RequestBody List<AnanVersionPermissionUpdateDto> entities,
                                               @PathVariable("versionId") Long versionId) {

        //更新版本权限
        Collection<AnanVersionPermissionEntity> ananSysVersionPermissionEntities = versionPermissionService.updateInBatch(versionId, entities);

        //准备版本相关联的机构权限数据
        List<AnanOrganizationPermissionUpdateDto> organizationPermissionEntities = new ArrayList<>();
        for (AnanVersionPermissionEntity entity : ananSysVersionPermissionEntities) {
            AnanOrganizationPermissionUpdateDto organizationPermissionEntity = new AnanOrganizationPermissionUpdateDto();
            organizationPermissionEntity.setPermissionId(entity.getPermissionId());
            organizationPermissionEntities.add(organizationPermissionEntity);
        }

        //查询所有通过该版本生成的机构数据
        List<AnanOrganizationAuthEntity> organizationAuthEntities = organizationAuthService.findAllByVersionId(versionId);

        //更新所有机构权限数据
        for (AnanOrganizationAuthEntity entity : organizationAuthEntities) {
            Long organizId = entity.getOrganizId();
            for (AnanOrganizationPermissionUpdateDto entity1 : organizationPermissionEntities) {
                entity1.setOrganizId(organizId);
                entity1.setId(null);
            }
            organizationPermissionService.updateInBatch(organizId, organizationPermissionEntities);
        }
        return ResponseEntity.ok(true);
    }

    @Override
    public ISimpleService<AnanVersionEntity, Long, AnanVersionCreateDto, AnanVersionRetrieveDto, AnanVersionUpdateDto> getService() {
        return ananSysVersionService;
    }
}
