package com.github.fosin.cdp.platform.controller;

import com.github.fosin.cdp.core.exception.CdpControllerException;
import com.github.fosin.cdp.mvc.controller.AbstractBaseController;
import com.github.fosin.cdp.mvc.controller.ISimpleController;
import com.github.fosin.cdp.mvc.service.ISimpleService;
import com.github.fosin.cdp.platformapi.constant.SystemConstant;
import com.github.fosin.cdp.platformapi.constant.UrlPrefixConstant;
import com.github.fosin.cdp.platformapi.dto.request.CdpPermissionCreateDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpPermissionRetrieveDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpPermissionUpdateDto;
import com.github.fosin.cdp.platformapi.entity.CdpPermissionEntity;
import com.github.fosin.cdp.platform.service.inter.IPermissionService;
import com.github.fosin.cdp.util.TreeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping(UrlPrefixConstant.PERMISSION)
@Api(value = UrlPrefixConstant.PERMISSION, tags = "权限管理", description = "权限管理相关操作")
public class PermissionController extends AbstractBaseController
        implements ISimpleController<CdpPermissionEntity, Long, CdpPermissionCreateDto, CdpPermissionRetrieveDto, CdpPermissionUpdateDto> {
    @Autowired
    private IPermissionService permissionService;

    @ApiOperation(value = "根据权限类型type获取权限树", notes = "如果权限类型type在0-4之内的任意值则返回对应的权限树，否则返回所有权限树")
    @ApiImplicitParam(name = "type", value = "权限类型type,CdpPermissionEntity.type")
    @PostMapping("/tree/{type}")
    public ResponseEntity<List<CdpPermissionEntity>> getListTree(@PathVariable Integer type) throws CdpControllerException {
        Collection<CdpPermissionEntity> list;
        switch (type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                list = permissionService.findByType(type);
                break;
            default:
                list = permissionService.findAll();
                break;
        }

        CdpPermissionEntity root = null;
        for (CdpPermissionEntity entity : list) {
            if (SystemConstant.ROOT_PERMISSION_PID.equals(entity.getPId())) {
                root = entity;
                break;
            }
        }
        if (root == null) {
            throw new CdpControllerException("未找到根节点数据!");
        }
        TreeUtil.createTree(list, root, "id", "pId", "children");
        List<CdpPermissionEntity> resultList = new ArrayList<>();
        resultList.add(root);
        return ResponseEntity.ok(resultList);
    }

    @ApiOperation(value = "根据父权限ID获取其孩子数据列表")
    @ApiImplicitParam(name = "pId", value = "父权限ID,CdpPermissionEntity.pId")
    @PostMapping("/listChild/{pId}")
    public ResponseEntity<Object> getListChild(@PathVariable Long pId) {
        List<CdpPermissionEntity> list = permissionService.findByPId(pId);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/findByAppName")
    @ApiImplicitParam(name = "appName", value = "应用名称,spring.application.name")
    @ApiOperation(value = "查询应用权限", notes = "根据应用名称(spring.application.name)查询其权限列表")
    public ResponseEntity<List<CdpPermissionEntity>> findByAppName(@PathVariable String appName) {
        return ResponseEntity.ok(permissionService.findByAppName(appName));
    }

    @Override
    public ISimpleService<CdpPermissionEntity, Long, CdpPermissionCreateDto, CdpPermissionRetrieveDto, CdpPermissionUpdateDto> getService() {
        return permissionService;
    }
}
