package com.github.fosin.cdp.platform.controller;

import com.github.fosin.cdp.mvc.controller.ISimpleController;
import com.github.fosin.cdp.mvc.service.ISimpleService;
import com.github.fosin.cdp.platformapi.entity.CdpSysOrganizationAuthEntity;
import com.github.fosin.cdp.platformapi.service.inter.ICdpSysOrganizationAuthService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统机构授权表(table:cdp_sys_organization_auth)表控制层
 *
 * @author fosin
 * @date 2018-11-18 17:49:48
 */
@RestController
@RequestMapping("v1/organiz/auth")
@Api(value = "v1/organiz/auth",tags = "系统机构授权表接入层API",description = "系统机构授权表(cdp_sys_organization_auth)接入层API")
public class CdpSysOrganizationAuthController implements ISimpleController<CdpSysOrganizationAuthEntity, Long> {
    /**
     * 服务对象
     */
    @Autowired
    private ICdpSysOrganizationAuthService cdpSysOrganizationAuthService;

    @Override
    public ISimpleService<CdpSysOrganizationAuthEntity, Long> getService() {
        return cdpSysOrganizationAuthService;
    }
}