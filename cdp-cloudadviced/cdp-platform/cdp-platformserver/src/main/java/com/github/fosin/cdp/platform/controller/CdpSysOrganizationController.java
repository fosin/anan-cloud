package com.github.fosin.cdp.platform.controller;

import com.github.fosin.cdp.core.exception.CdpControllerException;
import com.github.fosin.cdp.core.exception.CdpServiceException;
import com.github.fosin.cdp.mvc.controller.AbstractBaseController;
import com.github.fosin.cdp.mvc.controller.ISimpleController;
import com.github.fosin.cdp.mvc.service.ISimpleService;
import com.github.fosin.cdp.platform.entity.CdpSysOrganizationAuthEntity;
import com.github.fosin.cdp.platform.entity.CdpSysOrganizationPermissionEntity;
import com.github.fosin.cdp.platform.service.inter.ICdpSysOrganizationAuthService;
import com.github.fosin.cdp.platform.service.inter.ICdpSysOrganizationPermissionService;
import com.github.fosin.cdp.platformapi.dto.RegisterDto;
import com.github.fosin.cdp.platformapi.entity.CdpSysOrganizationEntity;
import com.github.fosin.cdp.platformapi.service.inter.IOrganizationService;
import com.github.fosin.cdp.util.TreeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Description
 *
 * @author fosin
 */
@RestController
@Slf4j
@RequestMapping("v1/organiz")
@Api(value = "v1/organiz", tags = "机构管理", description = "机构管理相关操作(增删改查)")
public class CdpSysOrganizationController extends AbstractBaseController
        implements ISimpleController<CdpSysOrganizationEntity, Long, CdpSysOrganizationEntity, CdpSysOrganizationEntity, CdpSysOrganizationEntity> {
    @Autowired
    private IOrganizationService organizationService;

    @Autowired
    private ICdpSysOrganizationAuthService organizationAuthService;

    @Autowired
    private ICdpSysOrganizationPermissionService organizationPermissionService;

    @ApiOperation("根据机构ID获取机构权限")
    @ApiImplicitParam(name = "organizId", value = "机构ID,取值于CdpSysOrganizationEntity.id")
    @RequestMapping(value = "/permissions/{organizId}", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<List<CdpSysOrganizationPermissionEntity>> permissions(@PathVariable Long organizId) {
        return ResponseEntity.ok(organizationPermissionService.findByOrganizId(organizId));
    }

    @ApiOperation(value = "根据版本ID更新版本权限", notes = "根据版本ID更新版本权限，此操作将先删除原权限，再新增新权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "entities", value = "版本权限集合(List<CdpSysOrganizationPermissionEntity>)"),
            @ApiImplicitParam(name = "organizId", value = "版本ID,取值于CdpSysOrganizationEntity.id")
    })
    @PutMapping(value = "/permissions/{organizId}")
    public ResponseEntity<Collection<CdpSysOrganizationPermissionEntity>> permissions(@RequestBody List<CdpSysOrganizationPermissionEntity> entities,
                                                                                      @PathVariable("organizId") Long organizId) {
        return ResponseEntity.ok(organizationPermissionService.updateInBatch(organizId, entities));
    }


    @ApiOperation("根据父机构ID获取其孩子节点数据")
    @ApiImplicitParam(name = "pId", value = "父节点ID,CdpSysOrganizationEntity.pid")
    @PostMapping("/listChild/{pId}")
    public ResponseEntity<List<CdpSysOrganizationEntity>> listChild(@PathVariable("pId") Long pId) throws CdpServiceException {
        return ResponseEntity.ok(organizationService.findByPid(pId));
    }

    @ApiOperation("根据父机构ID获取其所有后代节点数据")
    @ApiImplicitParam(name = "pId", value = "父节点ID,CdpSysOrganizationEntity.pid")
    @PostMapping("/listAllChild/{pId}")
    public ResponseEntity<List<CdpSysOrganizationEntity>> listAllChild(@PathVariable("pId") Long pId) throws CdpServiceException {
        List<String> codes = new ArrayList<>();
        if (pId == 0) {
            List<CdpSysOrganizationEntity> list = organizationService.findByPid(pId);
            for (CdpSysOrganizationEntity organizationEntity : list) {
                codes.add(organizationEntity.getCode());
            }
        } else {
            CdpSysOrganizationEntity organizationEntity = organizationService.findOne(pId);
            if (organizationEntity != null) {
                codes.add(organizationEntity.getCode());
            }
        }
        List<CdpSysOrganizationEntity> result = new ArrayList<>();
        for (String code : codes) {
            List<CdpSysOrganizationEntity> byCodeStartingWith = organizationService.findByCodeStartingWith(code);
            result.addAll(byCodeStartingWith);
        }
        return ResponseEntity.ok(result);

    }

    @ApiOperation("生成机构树")
    @RequestMapping(value = "/tree/{topId}", method = {RequestMethod.POST})
    public ResponseEntity<List<CdpSysOrganizationEntity>> tree(@PathVariable Long topId) throws CdpControllerException {
        Collection<CdpSysOrganizationEntity> list = organizationService.findAllByTopId(topId);

        CdpSysOrganizationEntity root = null;
        for (CdpSysOrganizationEntity entity : list) {
            if (0 == entity.getPId()) {
                root = entity;
                break;
            }
        }
        if (list.size() < 1) {
            throw new CdpControllerException("没有找到任何机构数据!");
        }
        if (root == null) {
            throw new CdpControllerException("未找到根节点数据!");
        }

        TreeUtil.createTree(list, root, "id", "pId", "children");
        List<CdpSysOrganizationEntity> result = new ArrayList<>();
        result.add(root);
        return ResponseEntity.ok(result);
    }

    @ApiOperation(value = "机构注册", notes = "用户自助注册机构")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "registerDto", value = "注册新机构、新用户"),
    })
    @PutMapping(value = "/register")
    public ResponseEntity<Boolean> register(@RequestBody RegisterDto registerDto) {
        return ResponseEntity.ok(organizationAuthService.register(registerDto));
    }


    @ApiOperation("根据父机构ID获取其孩子节点数据")
    @ApiImplicitParam(name = "organizId", value = "机构ID,取值于CdpSysOrganizationEntity.id")
    @PostMapping("/auth/{organizId}")
    public ResponseEntity<CdpSysOrganizationAuthEntity> getOrganizAuth(@PathVariable("organizId") Long organizId) throws CdpServiceException {
        List<CdpSysOrganizationAuthEntity> organizationAuthEntities = organizationAuthService.findAllByOrganizId(organizId);
        return ResponseEntity.ok(organizationAuthEntities.get(0));
    }

    @Override
    public ISimpleService<CdpSysOrganizationEntity, Long, CdpSysOrganizationEntity, CdpSysOrganizationEntity, CdpSysOrganizationEntity> getService() {
        return organizationService;
    }
}
