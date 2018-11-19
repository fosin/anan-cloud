package com.github.fosin.cdp.platform.controller;


import com.github.fosin.cdp.mvc.controller.ISimpleController;
import com.github.fosin.cdp.mvc.service.ISimpleService;
import com.github.fosin.cdp.platformapi.entity.CdpSysVersionEntity;
import com.github.fosin.cdp.platformapi.service.inter.ICdpSysVersionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统版本表(table:cdp_sys_version)表控制层
 *
 * @author fosin
 * @date 2018-11-18 17:50:28
 */
@RestController
@RequestMapping("v1/version")
@Api(value = "v1/version",tags = "系统版本表接入层API",description = "系统版本表(cdp_sys_version)接入层API")
public class CdpSysVersionController implements ISimpleController<CdpSysVersionEntity, Long> {
    /**
     * 服务对象
     */
    @Autowired
    private ICdpSysVersionService cdpSysVersionService;

    @Override
    public ISimpleService<CdpSysVersionEntity, Long> getService() {
        return cdpSysVersionService;
    }
}