package top.fosin.anan.platform.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fosin.anan.cloudresource.constant.UrlPrefixConstant;
import top.fosin.anan.model.controller.ISimpleController;
import top.fosin.anan.platform.dto.req.OauthClientDetailsReqDto;
import top.fosin.anan.platform.dto.res.OauthClientDetailsRespDto;
import top.fosin.anan.platform.service.inter.OauthClientService;


/**
 * 授权客户端控制器
 *
 * @author fosin
 */
@RestController
@RequestMapping(UrlPrefixConstant.OAUTH_CLIENT)
@Api(value = UrlPrefixConstant.OAUTH_CLIENT, tags = "OAuth2.0客户端授权管理")
public class OauthClientController implements ISimpleController<OauthClientDetailsRespDto, String,
        OauthClientDetailsReqDto, OauthClientDetailsReqDto, OauthClientDetailsReqDto> {
    private final OauthClientService oauthClientService;

    public OauthClientController(OauthClientService oauthClientService) {
        this.oauthClientService = oauthClientService;
    }

    @Override
    public OauthClientService getService() {
        return oauthClientService;
    }
}
