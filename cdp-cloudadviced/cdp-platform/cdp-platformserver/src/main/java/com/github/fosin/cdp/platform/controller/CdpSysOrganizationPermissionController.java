package com.github.fosin.cdp.platform.controller;

import com.github.fosin.cdp.mvc.controller.ISimpleController;
import com.github.fosin.cdp.mvc.service.ISimpleService;
import com.github.fosin.cdp.platformapi.entity.CdpSysOrganizationPermissionEntity;
import com.github.fosin.cdp.platformapi.service.inter.ICdpSysOrganizationPermissionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统机构权限表(table:cdp_sys_organization_permission)表控制层
 *
 * @author fosin
 * @date 2018-11-18 17:50:28
 */
@RestController
@RequestMapping("v1/organiz/permission")
@Api(value = "v1/organiz/permission",tags = "系统机构权限表接入层API",description = "系统机构权限表(cdp_sys_organization_permission)接入层API")
public class CdpSysOrganizationPermissionController implements ISimpleController<CdpSysOrganizationPermissionEntity, Long> {
    /**
     * 服务对象
     */
    @Autowired
    private ICdpSysOrganizationPermissionService cdpSysOrganizationPermissionService;

    @Override
    public ISimpleService<CdpSysOrganizationPermissionEntity, Long> getService() {
        return cdpSysOrganizationPermissionService;
    }
}