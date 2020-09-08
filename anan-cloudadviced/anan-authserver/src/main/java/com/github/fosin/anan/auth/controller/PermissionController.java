package com.github.fosin.anan.auth.controller;

import com.github.fosin.anan.auth.service.inter.PermissionService;
import com.github.fosin.anan.cloudresource.constant.UrlPrefixConstant;
import com.github.fosin.anan.cloudresource.dto.AnanUserAllPermissionDto;
import com.github.fosin.anan.cloudresource.dto.request.AnanPermissionRetrieveDto;
import com.github.fosin.anan.model.constant.PathConstant;
import com.github.fosin.anan.platformapi.entity.AnanPermissionEntity;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Description:
 *
 * @author fosin
 * @date 2018.8.22
 */
@RestController
@ApiIgnore
@RequestMapping(UrlPrefixConstant.PERMISSION)
public class PermissionController {
    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @RequestMapping(value = "/appName/{appName}", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiImplicitParam(name = "appName", value = "应用名称,spring.application.name",
            required = true, dataTypeClass = String.class, paramType = "path")
    @ApiOperation(value = "查询应用权限", notes = "根据应用名称(spring.application.name)查询其权限列表")
    public ResponseEntity<List<AnanPermissionRetrieveDto>> findByAppName(@PathVariable("appName") String appName) {
        return ResponseEntity.ok(permissionService.findByAppName(appName));
    }

    @RequestMapping(value = PathConstant.PATH_LIST, method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "查询应用权限", notes = "查询所有权限列表")
    public ResponseEntity<List<AnanPermissionRetrieveDto>> findAll() {
        Collection<AnanPermissionEntity> all = permissionService.findAll();
        List<AnanPermissionRetrieveDto> retrieveDtos = new ArrayList<>();
        for (AnanPermissionEntity ananPermissionEntity : all) {
            AnanPermissionRetrieveDto retrieveDto = new AnanPermissionRetrieveDto();
            BeanUtils.copyProperties(ananPermissionEntity, retrieveDto);
            retrieveDto.setId(ananPermissionEntity.getId());
            retrieveDtos.add(retrieveDto);
        }

        return ResponseEntity.ok(retrieveDtos);
    }

    @RequestMapping(value = "/user/tree/{userId}", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "查询用户权限树", notes = "查询用户权限树")
    @ApiImplicitParam(name = "userId", value = "用户的唯一ID",
            required = true, dataTypeClass = Long.class, paramType = "path")
    public ResponseEntity<AnanUserAllPermissionDto> findTree(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(permissionService.findTreeByUserId(userId));
    }

}
