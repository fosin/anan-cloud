package top.fosin.anan.platform.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fosin.anan.platform.dto.res.OauthClientDetailsRespDto;
import top.fosin.anan.model.controller.ISimpleController;
import top.fosin.anan.platform.dto.request.OauthClientDetailsCreateDto;
import top.fosin.anan.platform.dto.request.OauthClientDetailsRetrieveDto;
import top.fosin.anan.platform.dto.request.OauthClientDetailsUpdateDto;
import top.fosin.anan.platform.service.inter.OauthClientService;

/**
 *  授权客户端控制器
 *
 * @author fosin
 */
@RestController
@RequestMapping("v1/client")
@Api(value = "v1/client", tags = "OAuth2.0客户端授权管理相关操作")
public class ClientController implements ISimpleController<OauthClientDetailsRespDto, String,
        OauthClientDetailsCreateDto, OauthClientDetailsRetrieveDto, OauthClientDetailsUpdateDto> {
    private final OauthClientService oauthClientService;

    public ClientController(OauthClientService oauthClientService) {
        this.oauthClientService = oauthClientService;
    }

    @Override
    public OauthClientService getService() {
        return oauthClientService;
    }
}
