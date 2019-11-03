package com.github.fosin.anan.platform.controller;

import com.github.fosin.anan.core.exception.AnanControllerException;
import com.github.fosin.anan.mvc.controller.AbstractBaseController;
import com.github.fosin.anan.mvc.controller.ISimpleController;
import com.github.fosin.anan.mvc.service.ISimpleService;
import com.github.fosin.anan.platform.service.inter.AnanOrganizationAuthService;
import com.github.fosin.anan.platform.service.inter.AnanOrganizationPermissionService;
import com.github.fosin.anan.platform.service.inter.OrganizationService;
import com.github.fosin.anan.platformapi.constant.UrlPrefixConstant;
import com.github.fosin.anan.platformapi.dto.RegisterDto;
import com.github.fosin.anan.platformapi.dto.request.AnanOrganizationCreateDto;
import com.github.fosin.anan.platform.dto.request.AnanOrganizationPermissionUpdateDto;
import com.github.fosin.anan.platformapi.dto.request.AnanOrganizationRetrieveDto;
import com.github.fosin.anan.platformapi.dto.request.AnanOrganizationUpdateDto;
import com.github.fosin.anan.platform.entity.AnanOrganizationAuthEntity;
import com.github.fosin.anan.platformapi.entity.AnanOrganizationEntity;
import com.github.fosin.anan.platform.entity.AnanOrganizationPermissionEntity;
import com.github.fosin.anan.util.TreeUtil;
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
@RequestMapping(UrlPrefixConstant.ORGANIZATION)
@Api(value = UrlPrefixConstant.ORGANIZATION, tags = "机构管理", description = "机构管理相关操作(增删改查)")
public class AnanOrganizationController extends AbstractBaseController
        implements ISimpleController<AnanOrganizationEntity, Long, AnanOrganizationCreateDto, AnanOrganizationRetrieveDto, AnanOrganizationUpdateDto> {
    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private AnanOrganizationAuthService organizationAuthService;

    @Autowired
    private AnanOrganizationPermissionService organizationPermissionService;

    @ApiOperation("根据机构ID获取机构权限")
    @ApiImplicitParam(name = "organizId", value = "机构ID,取值于AnanOrganizationEntity.id")
    @RequestMapping(value = "/permissions/{organizId}", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<List<AnanOrganizationPermissionEntity>> permissions(@PathVariable Long organizId) {
        return ResponseEntity.ok(organizationPermissionService.findByOrganizId(organizId));
    }

    @ApiOperation(value = "根据版本ID更新版本权限", notes = "根据版本ID更新版本权限，此操作将先删除原权限，再新增新权限")
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "entities", value = "版本权限集合(List<AnanOrganizationPermissionEntity>)"),
            @ApiImplicitParam(name = "organizId", value = "版本ID,取值于AnanOrganizationEntity.id")
    })
    @PutMapping(value = "/permissions/{organizId}")
    public ResponseEntity<Collection<AnanOrganizationPermissionEntity>> permissions(@RequestBody List<AnanOrganizationPermissionUpdateDto> entities,
                                                                                   @PathVariable("organizId") Long organizId) {
        return ResponseEntity.ok(organizationPermissionService.updateInBatch(organizId, entities));
    }


    @ApiOperation("根据父机构ID获取其孩子节点数据")
    @ApiImplicitParam(name = "pid", value = "父节点ID,AnanOrganizationEntity.pid")
    @PostMapping("/listChild/{pid}")
    public ResponseEntity<List<AnanOrganizationEntity>> listChild(@PathVariable("pid") Long pid) {
        return ResponseEntity.ok(organizationService.findByPid(pid));
    }

    @ApiOperation("根据父机构ID获取其所有后代节点数据")
    @ApiImplicitParam(name = "pid", value = "父节点ID,AnanOrganizationEntity.pid")
    @PostMapping("/listAllChild/{pid}")
    public ResponseEntity<List<AnanOrganizationEntity>> listAllChild(@PathVariable("pid") Long pid) {
        List<String> codes = new ArrayList<>();
        if (pid == 0) {
            List<AnanOrganizationEntity> list = organizationService.findByPid(pid);
            for (AnanOrganizationEntity organizationEntity : list) {
                codes.add(organizationEntity.getCode());
            }
        } else {
            AnanOrganizationEntity organizationEntity = organizationService.findById(pid);
            if (organizationEntity != null) {
                codes.add(organizationEntity.getCode());
            }
        }
        List<AnanOrganizationEntity> result = new ArrayList<>();
        for (String code : codes) {
            List<AnanOrganizationEntity> byCodeStartingWith = organizationService.findByCodeStartingWith(code);
            result.addAll(byCodeStartingWith);
        }
        return ResponseEntity.ok(result);

    }

    @ApiOperation("生成机构树")
    @RequestMapping(value = "/tree/{topId}", method = {RequestMethod.POST})
    public ResponseEntity<List<AnanOrganizationEntity>> tree(@PathVariable Long topId) throws AnanControllerException {
        Collection<AnanOrganizationEntity> list = organizationService.findAllByTopId(topId);

        AnanOrganizationEntity root = null;
        for (AnanOrganizationEntity entity : list) {
            if (0 == entity.getPid()) {
                root = entity;
                break;
            }
        }
        if (list.size() < 1) {
            throw new AnanControllerException("没有找到任何机构数据!");
        }
        if (root == null) {
            throw new AnanControllerException("未找到根节点数据!");
        }

        TreeUtil.createTree(list, root, "id", "pid", "children");
        List<AnanOrganizationEntity> result = new ArrayList<>();
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
    @ApiImplicitParam(name = "organizId", value = "机构ID,取值于AnanOrganizationEntity.id")
    @PostMapping("/auth/{organizId}")
    public ResponseEntity<AnanOrganizationAuthEntity> getOrganizAuth(@PathVariable("organizId") Long organizId) {
        List<AnanOrganizationAuthEntity> organizationAuthEntities = organizationAuthService.findAllByOrganizId(organizId);
        return ResponseEntity.ok(organizationAuthEntities.get(0));
    }

    @Override
    public ISimpleService<AnanOrganizationEntity, Long, AnanOrganizationCreateDto, AnanOrganizationRetrieveDto, AnanOrganizationUpdateDto> getService() {
        return organizationService;
    }
}
