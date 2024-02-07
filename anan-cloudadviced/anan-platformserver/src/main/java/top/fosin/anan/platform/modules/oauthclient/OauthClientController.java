package top.fosin.anan.platform.modules.oauthclient;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import top.fosin.anan.cloudresource.constant.PathPrefixConstant;
import top.fosin.anan.data.controller.ISimpleController;
import top.fosin.anan.platform.modules.oauthclient.dto.OauthClientDetailsReqDto;
import top.fosin.anan.platform.modules.oauthclient.dto.OauthClientDetailsRespDto;
import top.fosin.anan.platform.modules.oauthclient.service.inter.OauthClientService;


/**
 * 授权客户端控制器
 * 升级到2.7.x之后该类废弃了，改用了authserver中的Oauth2RegisteredClientController.class类
 * 下一个版本将移除
 *
 * @author fosin
 */
//@RestController
@Deprecated
@RequestMapping(value = PathPrefixConstant.OAUTH_CLIENT, params = PathPrefixConstant.DEFAULT_VERSION_PARAM)
@Tag(name = "OAuth2.0客户端授权管理", description = PathPrefixConstant.OAUTH_CLIENT)
public class OauthClientController implements ISimpleController<OauthClientDetailsReqDto, OauthClientDetailsRespDto, String> {
    private final OauthClientService oauthClientService;

    public OauthClientController(OauthClientService oauthClientService) {
        this.oauthClientService = oauthClientService;
    }

    @Override
    public OauthClientService getService() {
        return oauthClientService;
    }
}
