package com.github.fosin.cdp.platform.controller;

import com.github.fosin.cdp.core.exception.CdpControllerException;
import com.github.fosin.cdp.core.exception.CdpServiceException;
import com.github.fosin.cdp.mvc.controller.AbstractBaseController;
import com.github.fosin.cdp.mvc.controller.ISimpleController;
import com.github.fosin.cdp.mvc.service.ISimpleService;
import com.github.fosin.cdp.platformapi.constant.SystemConstant;
import com.github.fosin.cdp.platformapi.dto.request.CdpSysPermissionCreateDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpSysPermissionRetrieveDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpSysPermissionUpdateDto;
import com.github.fosin.cdp.platformapi.entity.CdpSysPermissionEntity;
import com.github.fosin.cdp.platformapi.service.inter.IPermissionService;
import com.github.fosin.cdp.util.TreeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Description
 *
 * @author fosin
 */
@RestController
@RequestMapping("v1/permission")
@Api(value = "v1/permission", tags = "权限管理", description = "权限管理相关操作")
public class PermissionController extends AbstractBaseController implements ISimpleController<CdpSysPermissionEntity, Long, CdpSysPermissionCreateDto, CdpSysPermissionRetrieveDto, CdpSysPermissionUpdateDto> {
    @Autowired
    private IPermissionService permissionService;

    @ApiOperation(value = "根据权限类型type获取权限树", notes = "如果权限类型type在0-4之内的任意值则返回对应的权限树，否则返回所有权限树")
    @ApiImplicitParam(name = "type", value = "权限类型type,CdpSysPermissionEntity.type")
    @RequestMapping(value = "/tree/{type}", method = {RequestMethod.POST})
    public ResponseEntity<Object> getListTree(@PathVariable Integer type) throws CdpControllerException, CdpServiceException {
        Collection<CdpSysPermissionEntity> list;
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

        CdpSysPermissionEntity root = null;
        for (CdpSysPermissionEntity entity : list) {
            if (SystemConstant.ROOT_PERMISSION_PID.equals(entity.getPId())) {
                root = entity;
                break;
            }
        }
        if (root == null) {
            throw new CdpControllerException("未找到根节点数据!");
        }
        TreeUtil.createTree(list, root, "id", "pId", "children");
        List<CdpSysPermissionEntity> resultList = new ArrayList<>();
        resultList.add(root);
        return ResponseEntity.ok(resultList);
    }

    @ApiOperation(value = "根据父权限ID获取其孩子数据列表")
    @ApiImplicitParam(name = "pId", value = "父权限ID,CdpSysPermissionEntity.pId")
    @RequestMapping(value = "/listChild/{pId}", method = {RequestMethod.POST})
    public ResponseEntity<Object> getListChild(@PathVariable Long pId) {
        List<CdpSysPermissionEntity> list = permissionService.findByPId(pId);
        return ResponseEntity.ok(list);
    }

//    @RequestMapping("/userPermissionsTree/{userId}/{type}")
//    public ResponseEntity<CdpSysPermissionEntity> getUserPermissionsTree(@PathVariable("userId") Long id, @PathVariable("type") Integer type) throws CdpControllerException {
//        List<CdpSysUserRoleEntity> userRoles = userRoleService.findByUserId(id);
//        List<CdpSysPermissionEntity> userPermissions = new ArrayList<>();
//        for (CdpSysUserRoleEntity role : userRoles) {
//            Long roleId = role.getRole().getId();
//
//            //获取角色权限
//            List<CdpSysRolePermissionEntity> rolePrivilegeList = rolePermissionService.findByRoleId(roleId);
//            for (CdpSysRolePermissionEntity rolePrivilegeEntity : rolePrivilegeList) {
//                userPermissions.add(rolePrivilegeEntity.getPermission());
//            }
//
//        }
//
//        //获取用户权限
//        List<CdpSysUserPermissionEntity> userPrivilegeList = userPermissionService.findByUserId(id);
//        for (CdpSysUserPermissionEntity userPrivilegeEntity : userPrivilegeList) {
//            int addmode = userPrivilegeEntity.getAddMode();
//            //获取用户增权限
//            if (addmode == 0) {
//                userPermissions.add(userPrivilegeEntity.getPermission());
//            } else { //移除用户减权限
//                userPermissions.add(userPrivilegeEntity.getPermission());
//            }
//        }
//
//        CdpSysPermissionEntity root = null;
//        for (CdpSysPermissionEntity entity : userPermissions) {
//            if (entity.getpId().equals(SystemConstant.ROOT_PERMISSION_PID)) {
//                root = entity;
//                break;
//            }
//        }
//        if (root == null) {
//            throw new CdpControllerException("未找到根节点数据!");
//        }
//        TreeUtil.createTree(userPermissions, root, "id", "pId", "children");
//        return ResponseEntity.ok(root);
//    }

    @Override
    public ISimpleService<CdpSysPermissionEntity, Long, CdpSysPermissionCreateDto, CdpSysPermissionRetrieveDto, CdpSysPermissionUpdateDto> getService() {
        return permissionService;
    }
}
