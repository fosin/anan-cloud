package com.github.fosin.cdp.platform.controller;

import com.github.fosin.cdp.mvc.controller.ISimpleController;
import com.github.fosin.cdp.mvc.service.ISimpleService;
import com.github.fosin.cdp.platformapi.entity.CdpSysVersionRolePermissionEntity;
import com.github.fosin.cdp.platformapi.service.inter.ICdpSysVersionRolePermissionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统版本角色权限表(table:cdp_sys_version_role_permission)表控制层
 *
 * @author fosin
 * @date 2018-11-18 17:50:28
 */
@RestController
@RequestMapping("v1/version/role/permission")
@Api(value = "v1/version/role/permission",tags = "系统版本角色权限表接入层API",description = "系统版本角色权限表(cdp_sys_version_role_permission)接入层API")
public class CdpSysVersionRolePermissionController implements ISimpleController<CdpSysVersionRolePermissionEntity, Long> {
    /**
     * 服务对象
     */
    @Autowired
    private ICdpSysVersionRolePermissionService cdpSysVersionRolePermissionService;

    @Override
    public ISimpleService<CdpSysVersionRolePermissionEntity, Long> getService() {
        return cdpSysVersionRolePermissionService;
    }
}