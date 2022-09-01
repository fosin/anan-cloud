package top.fosin.anan.platform.modules.pub;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fosin.anan.cloudresource.constant.PathPrefixConstant;
import top.fosin.anan.data.controller.ISimpleController;
import top.fosin.anan.platform.modules.pub.dto.OauthClientDetailsReqDto;
import top.fosin.anan.platform.modules.pub.dto.OauthClientDetailsRespDto;
import top.fosin.anan.platform.modules.pub.service.inter.OauthClientService;


/**
 * 授权客户端控制器
 *
 * @author fosin
 */
@RestController
@RequestMapping(value = PathPrefixConstant.OAUTH_CLIENT, params = PathPrefixConstant.DEFAULT_VERSION_PARAM)
@Api(value = PathPrefixConstant.OAUTH_CLIENT, tags = "OAuth2.0客户端授权管理")
public class OauthClientController implements ISimpleController< OauthClientDetailsReqDto, OauthClientDetailsRespDto,String> {
    private final OauthClientService oauthClientService;

    public OauthClientController(OauthClientService oauthClientService) {
        this.oauthClientService = oauthClientService;
    }

    @Override
    public OauthClientService getService() {
        return oauthClientService;
    }
}
