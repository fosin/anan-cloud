package com.github.fosin.anan.auth.controller;

import com.github.fosin.anan.auth.service.inter.PermissionService;
import com.github.fosin.anan.model.constant.PathConstant;
import com.github.fosin.anan.platformapi.entity.AnanPermissionEntity;
import com.github.fosin.anan.cloudresource.constant.UrlPrefixConstant;
import com.github.fosin.anan.cloudresource.dto.request.AnanPermissionRetrieveDto;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @PostMapping("/findByAppName/{appName}")
    @ApiImplicitParam(name = "appName", value = "应用名称,spring.application.name",
            required = true, dataTypeClass = String.class, paramType = "path")
    @ApiOperation(value = "查询应用权限", notes = "根据应用名称(spring.application.name)查询其权限列表")
    public ResponseEntity<List<AnanPermissionRetrieveDto>> findByAppName(@PathVariable("appName") String appName) {
        return ResponseEntity.ok(permissionService.findByAppName(appName));
    }

    @PostMapping(PathConstant.PATH_LIST)
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

}
