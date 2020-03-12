package com.github.fosin.anan.platform.controller;

import com.github.fosin.anan.mvc.controller.ISimpleController;
import com.github.fosin.anan.mvc.service.ISimpleService;
import com.github.fosin.anan.platform.entity.OauthClientDetailsEntity;
import com.github.fosin.anan.platform.service.inter.OauthClientService;
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
@Api(value = "v1/client", tags = "OAuth2.0客户端授权管理", description = "OAuth2.0客户端授权管理相关操作")
public class ClientController implements ISimpleController<OauthClientDetailsEntity, String, OauthClientDetailsEntity, OauthClientDetailsEntity, OauthClientDetailsEntity> {
    private final OauthClientService oauthClientService;

    public ClientController(OauthClientService oauthClientService) {
        this.oauthClientService = oauthClientService;
    }

    @Override
    public ISimpleService<OauthClientDetailsEntity, String, OauthClientDetailsEntity, OauthClientDetailsEntity, OauthClientDetailsEntity> getService() {
        return oauthClientService;
    }
}
