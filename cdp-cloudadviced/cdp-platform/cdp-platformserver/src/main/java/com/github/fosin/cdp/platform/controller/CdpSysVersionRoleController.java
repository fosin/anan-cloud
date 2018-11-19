package com.github.fosin.cdp.platform.controller;


import com.github.fosin.cdp.mvc.controller.ISimpleController;
import com.github.fosin.cdp.mvc.service.ISimpleService;
import com.github.fosin.cdp.platformapi.entity.CdpSysVersionRoleEntity;
import com.github.fosin.cdp.platformapi.service.inter.ICdpSysVersionRoleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统版本角色表(table:cdp_sys_version_role)表控制层
 *
 * @author fosin
 * @date 2018-11-18 17:50:28
 */
@RestController
@RequestMapping("v1/version/role")
@Api(value = "v1/version/role",tags = "系统版本角色表接入层API",description = "系统版本角色表(cdp_sys_version_role)接入层API")
public class CdpSysVersionRoleController implements ISimpleController<CdpSysVersionRoleEntity, Long> {
    /**
     * 服务对象
     */
    @Autowired
    private ICdpSysVersionRoleService cdpSysVersionRoleService;

    @Override
    public ISimpleService<CdpSysVersionRoleEntity, Long> getService() {
        return cdpSysVersionRoleService;
    }
}