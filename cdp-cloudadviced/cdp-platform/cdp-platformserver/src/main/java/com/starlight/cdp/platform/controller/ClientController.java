package com.starlight.cdp.platform.controller;

import com.starlight.cdp.mvc.controller.ISimpleController;
import com.starlight.cdp.mvc.service.ISimpleService;
import com.starlight.cdp.platformapi.entity.OauthClientDetailsEntity;
import com.starlight.cdp.platformapi.service.inter.IOauthClientService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description 授权客户端控制器
 *
 * @author fosin
 */
@RestController
@RequestMapping("v1/client")
@Api(value = "v1/client",tags = "OAuth2.0客户端授权管理",description = "OAuth2.0客户端授权管理相关操作")
public class ClientController implements ISimpleController<OauthClientDetailsEntity, String> {
    @Autowired
    private IOauthClientService oauthClientService;

    @Override
    public ISimpleService<OauthClientDetailsEntity, String> getService() {
        return oauthClientService;
    }
}
