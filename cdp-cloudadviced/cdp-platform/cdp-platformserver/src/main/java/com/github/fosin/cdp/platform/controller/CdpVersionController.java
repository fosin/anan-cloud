package com.github.fosin.cdp.platform.controller;


import com.github.fosin.cdp.mvc.controller.ISimpleController;
import com.github.fosin.cdp.mvc.service.ISimpleService;
import com.github.fosin.cdp.platform.dto.request.*;
import com.github.fosin.cdp.platform.entity.CdpOrganizationAuthEntity;
import com.github.fosin.cdp.platform.entity.CdpOrganizationPermissionEntity;
import com.github.fosin.cdp.platform.entity.CdpVersionEntity;
import com.github.fosin.cdp.platform.entity.CdpVersionPermissionEntity;
import com.github.fosin.cdp.platform.service.inter.ICdpOrganizationAuthService;
import com.github.fosin.cdp.platform.service.inter.ICdpOrganizationPermissionService;
import com.github.fosin.cdp.platform.service.inter.ICdpVersionPermissionService;
import com.github.fosin.cdp.platform.service.inter.ICdpVersionService;
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
 * 系统版本表(table:cdp_version)表控制层
 *
 * @author fosin
 * @date 2018-11-18 17:50:28
 */
@RestController
@RequestMapping("v1/version")
@Api(value = "v1/version", tags = "系统版本表接入层API", description = "系统版本表(cdp_version)接入层API")
public class CdpVersionController implements ISimpleController<CdpVersionEntity, Long, CdpVersionCreateDto, CdpVersionRetrieveDto, CdpVersionUpdateDto> {
    /**
     * 服务对象
     */
    @Autowired
    private ICdpVersionService cdpSysVersionService;

    @Autowired
    private ICdpVersionPermissionService versionPermissionService;

    @Autowired
    private ICdpOrganizationPermissionService organizationPermissionService;

    @Autowired
    private ICdpOrganizationAuthService organizationAuthService;

    @Autowired
    private IPermissionService permissionService;

    @ApiOperation(value = "根据父权限ID获取其孩子数据列表")
    @ApiImplicitParam(name = "pId", value = "父权限ID,CdpVersionPermissionEntity.id")
    @RequestMapping(value = "/listChild/{pId}", method = {RequestMethod.POST})
    public ResponseEntity<List<CdpPermissionEntity>> getListChild(@PathVariable Long pId, @RequestParam Long versionId) {
        List<CdpPermissionEntity> list = permissionService.findByPId(pId, versionId);
        return ResponseEntity.ok(list);
    }

    @ApiOperation("根据版本ID获取版本权限")
    @ApiImplicitParam(name = "versionId", value = "版本ID,取值于CdpRoleEntity.id")
    @RequestMapping(value = "/permissions/{versionId}", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<List<CdpVersionPermissionEntity>> permissions(@PathVariable Long versionId) {
        return ResponseEntity.ok(versionPermissionService.findByVersionId(versionId));
    }

    @ApiOperation(value = "根据版本ID更新版本权限", notes = "根据版本ID更新版本权限，此操作将先删除原权限，再新增新权限")
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "entities", value = "版本权限集合(List<CdpVersionPermissionEntity>)"),
            @ApiImplicitParam(name = "versionId", value = "版本ID,取值于CdpVersionEntity.id")
    })
    @PutMapping(value = "/permissions/{versionId}")
    public ResponseEntity<Boolean> permissions(@RequestBody List<CdpVersionPermissionUpdateDto> entities,
                                               @PathVariable("versionId") Long versionId) {

        //更新版本权限
        Collection<CdpVersionPermissionEntity> cdpSysVersionPermissionEntities = versionPermissionService.updateInBatch(versionId, entities);

        //准备版本相关联的机构权限数据
        List<CdpOrganizationPermissionUpdateDto> organizationPermissionEntities = new ArrayList<>();
        for (CdpVersionPermissionEntity entity : cdpSysVersionPermissionEntities) {
            CdpOrganizationPermissionUpdateDto organizationPermissionEntity = new CdpOrganizationPermissionUpdateDto();
            organizationPermissionEntity.setPermissionId(entity.getPermissionId());
            organizationPermissionEntities.add(organizationPermissionEntity);
        }

        //查询所有通过该版本生成的机构数据
        List<CdpOrganizationAuthEntity> organizationAuthEntities = organizationAuthService.findAllByVersionId(versionId);

        //更新所有机构权限数据
        for (CdpOrganizationAuthEntity entity : organizationAuthEntities) {
            Long organizId = entity.getOrganizId();
            for (CdpOrganizationPermissionUpdateDto entity1 : organizationPermissionEntities) {
                entity1.setOrganizId(organizId);
                entity1.setId(null);
            }
            organizationPermissionService.updateInBatch(organizId, organizationPermissionEntities);
        }
        return ResponseEntity.ok(true);
    }

    @Override
    public ISimpleService<CdpVersionEntity, Long, CdpVersionCreateDto, CdpVersionRetrieveDto, CdpVersionUpdateDto> getService() {
        return cdpSysVersionService;
    }
}
