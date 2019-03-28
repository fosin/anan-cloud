package com.github.fosin.cdp.auth.controller;

import com.github.fosin.cdp.auth.service.inter.IPermissionService;
import com.github.fosin.cdp.platformapi.entity.CdpPermissionEntity;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
    private IPermissionService permissionService;

    @PostMapping("/findByAppName/{appName}")
    @ApiImplicitParam(name = "appName", value = "应用名称,spring.application.name")
    @ApiOperation(value = "查询应用权限", notes = "根据应用名称(spring.application.name)查询其权限列表")
    public ResponseEntity<List<CdpPermissionEntity>> findByAppName(@PathVariable("appName") String appName) {
        return ResponseEntity.ok(permissionService.findByAppName(appName));
    }
}