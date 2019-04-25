package com.github.fosin.anan.auth.controller;

import com.github.fosin.anan.auth.service.inter.PermissionService;
import com.github.fosin.anan.platformapi.entity.AnanPermissionEntity;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * Description:
 *
 * @author fosin
 * @date 2018.8.22
 */
@RestController
@ApiIgnore
@RequestMapping("v1/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @PostMapping("/findByAppName/{appName}")
    @ApiImplicitParam(name = "appName", value = "应用名称,spring.application.name")
    @ApiOperation(value = "查询应用权限", notes = "根据应用名称(spring.application.name)查询其权限列表")
    public ResponseEntity<List<AnanPermissionEntity>> findByAppName(@PathVariable("appName") String appName) {
        return ResponseEntity.ok(permissionService.findByAppName(appName));
    }
}