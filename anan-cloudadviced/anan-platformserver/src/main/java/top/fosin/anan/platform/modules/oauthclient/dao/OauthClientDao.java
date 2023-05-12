package top.fosin.anan.platform.modules.oauthclient.dao;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import top.fosin.anan.jpa.repository.IJpaRepository;
import top.fosin.anan.platform.modules.oauthclient.po.OauthClientDetails;

/**
 * @author fosin
 * @date 2017/12/27
 */
@Repository
@Lazy
public interface OauthClientDao extends IJpaRepository<String, OauthClientDetails> {
}
