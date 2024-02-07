package top.fosin.anan.auth.modules.oauth2client;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fosin.anan.auth.modules.oauth2client.dto.Oauth2RegisteredClientCreateDTO;
import top.fosin.anan.auth.modules.oauth2client.dto.Oauth2RegisteredClientUpdateDTO;
import top.fosin.anan.auth.modules.oauth2client.query.Oauth2RegisteredClientQuery;
import top.fosin.anan.auth.modules.oauth2client.service.inter.Oauth2RegisteredClientService;
import top.fosin.anan.auth.modules.oauth2client.vo.Oauth2RegisteredClientPageVO;
import top.fosin.anan.auth.modules.oauth2client.vo.Oauth2RegisteredClientVO;
import top.fosin.anan.cloudresource.constant.PathPrefixConstant;
import top.fosin.anan.data.controller.*;


/**
 * OAUTH2认证客户端注册表(oauth2_registered_client)控制类
 *
 * @author fosin
 * @date 2023-05-13
 */
@RestController
@RequestMapping(value = PathPrefixConstant.OAUTH2_CLIENT, params = PathPrefixConstant.DEFAULT_VERSION_PARAM)
@Tag(name = "Spring OAuth2.1客户端授权管理", description = PathPrefixConstant.OAUTH2_CLIENT)
public class Oauth2RegisteredClientController
        implements ICreateController<Oauth2RegisteredClientCreateDTO, String>,
        IFindOneByIdController<Oauth2RegisteredClientVO, String>,
        IPageController<Oauth2RegisteredClientQuery, Oauth2RegisteredClientPageVO>,
        IUpdateController<Oauth2RegisteredClientUpdateDTO, String>,
        IDeleteController<String> {

    private final Oauth2RegisteredClientService oauth2RegisteredClientService;

    public Oauth2RegisteredClientController(Oauth2RegisteredClientService oauth2RegisteredClientService) {
        this.oauth2RegisteredClientService = oauth2RegisteredClientService;
    }

    @Override
    public Oauth2RegisteredClientService getService() {
        return oauth2RegisteredClientService;
    }
}
