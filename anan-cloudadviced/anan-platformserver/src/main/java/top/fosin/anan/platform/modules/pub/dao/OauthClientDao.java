package top.fosin.anan.platform.modules.pub.dao;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import top.fosin.anan.jpa.repository.IJpaRepository;
import top.fosin.anan.platform.modules.pub.po.OauthClientDetails;

/**
 * @author fosin
 * @date 2017/12/27
 */
@Repository
@Lazy
public interface OauthClientDao extends IJpaRepository<String, OauthClientDetails> {
}
