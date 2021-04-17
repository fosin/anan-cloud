package com.github.fosin.anan.platform.controller;

import com.github.fosin.anan.core.exception.AnanControllerException;
import com.github.fosin.anan.model.controller.AbstractBaseController;
import com.github.fosin.anan.model.controller.ISimpleController;
import com.github.fosin.anan.model.service.ISimpleService;
import com.github.fosin.anan.platform.service.inter.PermissionService;
import com.github.fosin.anan.cloudresource.constant.SystemConstant;
import com.github.fosin.anan.cloudresource.constant.UrlPrefixConstant;
import com.github.fosin.anan.platformapi.entity.AnanPermissionEntity;
import com.github.fosin.anan.cloudresource.dto.request.AnanPermissionCreateDto;
import com.github.fosin.anan.cloudresource.dto.request.AnanPermissionRetrieveDto;
import com.github.fosin.anan.cloudresource.dto.request.AnanPermissionUpdateDto;
import com.github.fosin.anan.core.util.TreeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping(UrlPrefixConstant.PERMISSION)
@Api(value = UrlPrefixConstant.PERMISSION, tags = "权限管理")
public class PermissionController extends AbstractBaseController
        implements ISimpleController<AnanPermissionEntity, Long, AnanPermissionCreateDto, AnanPermissionRetrieveDto, AnanPermissionUpdateDto> {
    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @ApiOperation(value = "根据权限类型type获取权限树", notes = "如果权限类型type在0-4之内的任意值则返回对应的权限树，否则返回所有权限树")
    @ApiImplicitParam(name = "type", value = "权限类型type,AnanPermissionEntity.type",
            required = true, dataTypeClass = Integer.class, paramType = "path")
    @PostMapping("/tree/{type}")
    public ResponseEntity<List<AnanPermissionEntity>> getListTree(@PathVariable Integer type) throws AnanControllerException {
        Collection<AnanPermissionEntity> list;
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


        AnanPermissionEntity root = list.stream().filter(entity -> SystemConstant.ROOT_PERMISSION_PID.equals(entity.getPid())).findFirst().orElse(null);
        if (root == null) {
            throw new AnanControllerException("未找到根节点数据!");
        }
        TreeUtil.createTree(list, root, SystemConstant.ID_NAME, SystemConstant.PID_NAME, SystemConstant.CHILDREN_NAME);
        List<AnanPermissionEntity> resultList = new ArrayList<>();
        resultList.add(root);
        return ResponseEntity.ok(resultList);
    }

    @ApiOperation(value = "根据父权限ID获取其孩子数据列表")
    @ApiImplicitParam(name = SystemConstant.PID_NAME, value = "父权限ID,AnanPermissionEntity.pid",
            required = true, dataTypeClass = Long.class, paramType = "path")
    @PostMapping("/listChild/{pid}")
    public ResponseEntity<Object> getListChild(@PathVariable Long pid) {
        List<AnanPermissionEntity> list = permissionService.findByPid(pid);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/serviceCode/{serviceCode}")
    @ApiImplicitParam(name = "serviceCode", value = "服务标识，等同于anan_service.code",
            required = true, dataTypeClass = String.class, paramType = "path")
    @ApiOperation(value = "查询应用权限", notes = "根据服务标识(anan_service.code)查询其权限列表")
    public ResponseEntity<List<AnanPermissionEntity>> findByServiceCode(@PathVariable String serviceCode) {
        return ResponseEntity.ok(permissionService.findByServiceCode(serviceCode));
    }

    @Override
    public ISimpleService<AnanPermissionEntity, Long, AnanPermissionCreateDto, AnanPermissionRetrieveDto, AnanPermissionUpdateDto> getService() {
        return permissionService;
    }
}
