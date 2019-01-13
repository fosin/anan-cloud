package com.github.fosin.cdp.platform.controller;


import com.github.fosin.cdp.mvc.controller.ISimpleController;
import com.github.fosin.cdp.mvc.service.ISimpleService;
import com.github.fosin.cdp.platform.entity.CdpSysOrganizationAuthEntity;
import com.github.fosin.cdp.platform.entity.CdpSysOrganizationPermissionEntity;
import com.github.fosin.cdp.platform.entity.CdpSysVersionEntity;
import com.github.fosin.cdp.platform.entity.CdpSysVersionPermissionEntity;
import com.github.fosin.cdp.platform.service.inter.ICdpSysOrganizationAuthService;
import com.github.fosin.cdp.platform.service.inter.ICdpSysOrganizationPermissionService;
import com.github.fosin.cdp.platform.service.inter.ICdpSysVersionPermissionService;
import com.github.fosin.cdp.platform.service.inter.ICdpSysVersionService;
import com.github.fosin.cdp.platformapi.entity.*;
import com.github.fosin.cdp.platformapi.service.inter.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 系统版本表(table:cdp_sys_version)表控制层
 *
 * @author fosin
 * @date 2018-11-18 17:50:28
 */
@RestController
@RequestMapping("v1/version")
@Api(value = "v1/version", tags = "系统版本表接入层API", description = "系统版本表(cdp_sys_version)接入层API")
public class CdpSysVersionController implements ISimpleController<CdpSysVersionEntity, Long, CdpSysVersionEntity, CdpSysVersionEntity, CdpSysVersionEntity> {
    /**
     * 服务对象
     */
    @Autowired
    private ICdpSysVersionService cdpSysVersionService;

    @Autowired
    private ICdpSysVersionPermissionService versionPermissionService;

    @Autowired
    private ICdpSysOrganizationPermissionService organizationPermissionService;

    @Autowired
    private ICdpSysOrganizationAuthService organizationAuthService;

    @Autowired
    private IPermissionService permissionService;

    @ApiOperation(value = "根据父权限ID获取其孩子数据列表")
    @ApiImplicitParam(name = "pId", value = "父权限ID,CdpSysVersionPermissionEntity.id")
    @RequestMapping(value = "/listChild/{pId}", method = {RequestMethod.POST})
    public ResponseEntity<List<CdpSysPermissionEntity>> getListChild(@PathVariable Long pId, @RequestParam Long versionId) {
        List<CdpSysPermissionEntity> list = permissionService.findByPId(pId, versionId);
        return ResponseEntity.ok(list);
    }

    @ApiOperation("根据版本ID获取版本权限")
    @ApiImplicitParam(name = "versionId", value = "版本ID,取值于CdpSysRoleEntity.id")
    @RequestMapping(value = "/permissions/{versionId}", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<List<CdpSysVersionPermissionEntity>> permissions(@PathVariable Long versionId) {
        return ResponseEntity.ok(versionPermissionService.findByVersionId(versionId));
    }

    @ApiOperation(value = "根据版本ID更新版本权限", notes = "根据版本ID更新版本权限，此操作将先删除原权限，再新增新权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "entities", value = "版本权限集合(List<CdpSysVersionPermissionEntity>)"),
            @ApiImplicitParam(name = "versionId", value = "版本ID,取值于CdpSysVersionEntity.id")
    })
    @PutMapping(value = "/permissions/{versionId}")
    public ResponseEntity<Boolean> permissions(@RequestBody List<CdpSysVersionPermissionEntity> entities,
                                               @PathVariable("versionId") Long versionId) {

        //更新版本权限
        Collection<CdpSysVersionPermissionEntity> cdpSysVersionPermissionEntities = versionPermissionService.updateInBatch(versionId, entities);

        //准备版本相关联的机构权限数据
        List<CdpSysOrganizationPermissionEntity> organizationPermissionEntities = new ArrayList<>();
        for (CdpSysVersionPermissionEntity entity : cdpSysVersionPermissionEntities) {
            CdpSysOrganizationPermissionEntity organizationPermissionEntity = new CdpSysOrganizationPermissionEntity();
            organizationPermissionEntity.setCreateBy(entity.getCreateBy());
            organizationPermissionEntity.setCreateTime(entity.getCreateTime());
            organizationPermissionEntity.setPermissionId(entity.getPermissionId());
            organizationPermissionEntities.add(organizationPermissionEntity);
        }

        //查询所有通过该版本生成的机构数据
        List<CdpSysOrganizationAuthEntity> organizationAuthEntities = organizationAuthService.findAllByVersionId(versionId);

        //更新所有机构权限数据
        for (CdpSysOrganizationAuthEntity entity : organizationAuthEntities) {
            Long organizId = entity.getOrganizId();
            for (CdpSysOrganizationPermissionEntity entity1 : organizationPermissionEntities) {
                entity1.setOrganizId(organizId);
                entity1.setId(null);
            }
            organizationPermissionService.updateInBatch(organizId, organizationPermissionEntities);
        }
        return ResponseEntity.ok(true);
    }

    @Override
    public ISimpleService<CdpSysVersionEntity, Long, CdpSysVersionEntity, CdpSysVersionEntity, CdpSysVersionEntity> getService() {
        return cdpSysVersionService;
    }
}