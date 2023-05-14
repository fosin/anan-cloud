package top.fosin.anan.platform.modules.oauth2client.service;

import top.fosin.anan.platform.modules.oauth2client.dao.Oauth2RegisteredClientDao;
import top.fosin.anan.platform.modules.oauth2client.service.inter.Oauth2RegisteredClientService;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
/**
 * OAUTH2认证客户端注册表(oauth2_registered_client)服务实现类
 *
 * @author fosin
 * @date 2023-05-13
 */
@Service
@Lazy
public class Oauth2RegisteredClientServiceImpl implements Oauth2RegisteredClientService {
    
    private final Oauth2RegisteredClientDao oauth2RegisteredClientDao;
    public Oauth2RegisteredClientServiceImpl (Oauth2RegisteredClientDao oauth2RegisteredClientDao) {
        this.oauth2RegisteredClientDao = oauth2RegisteredClientDao;
    }
    
    /**
     * 默认DAO
     */
    @Override
    public Oauth2RegisteredClientDao getDao() {
        return oauth2RegisteredClientDao;
    }
}
