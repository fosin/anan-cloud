package top.fosin.anan.platform.modules.oauth2client.service.inter;

import top.fosin.anan.jpa.service.ICreateJpaService;
import top.fosin.anan.jpa.service.IDeleteJpaService;
import top.fosin.anan.jpa.service.IRetrieveJpaService;
import top.fosin.anan.jpa.service.IUpdateJpaService;
import top.fosin.anan.platform.modules.oauth2client.dto.Oauth2RegisteredClientCreateDTO;
import top.fosin.anan.platform.modules.oauth2client.dto.Oauth2RegisteredClientDTO;
import top.fosin.anan.platform.modules.oauth2client.dto.Oauth2RegisteredClientUpdateDTO;
import top.fosin.anan.platform.modules.oauth2client.po.Oauth2RegisteredClient;

        /**
 * OAUTH2认证客户端注册表(oauth2_registered_client)服务类
 *
 * @author fosin
 * @date 2023-05-13
 */
public interface Oauth2RegisteredClientService extends 
        ICreateJpaService<Oauth2RegisteredClientCreateDTO, Oauth2RegisteredClientDTO, String, Oauth2RegisteredClient>,
        IRetrieveJpaService<Oauth2RegisteredClientDTO, String, Oauth2RegisteredClient>,
        IUpdateJpaService<Oauth2RegisteredClientUpdateDTO, String, Oauth2RegisteredClient>,
        IDeleteJpaService<String, Oauth2RegisteredClient> {
}

