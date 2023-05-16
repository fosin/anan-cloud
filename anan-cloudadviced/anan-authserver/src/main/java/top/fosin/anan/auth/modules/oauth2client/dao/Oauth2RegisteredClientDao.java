package top.fosin.anan.auth.modules.oauth2client.dao;

import top.fosin.anan.auth.modules.oauth2client.po.Oauth2RegisteredClient;
import top.fosin.anan.jpa.repository.IJpaRepository;
import org.springframework.stereotype.Repository;

        /**
 * OAUTH2认证客户端注册表(oauth2_registered_client)存储仓库类
 *
 * @author fosin
 * @date 2023-05-13
 */
@Repository
public interface Oauth2RegisteredClientDao extends IJpaRepository<String, Oauth2RegisteredClient> {
}
