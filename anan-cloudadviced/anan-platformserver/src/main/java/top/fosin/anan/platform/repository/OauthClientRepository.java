package top.fosin.anan.platform.repository;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import top.fosin.anan.jpa.repository.IJpaRepository;
import top.fosin.anan.platform.entity.OauthClientDetailsEntity;

/**
 * @author fosin
 * @date 2017/12/27
 */
@Repository
@Lazy
public interface OauthClientRepository extends IJpaRepository<OauthClientDetailsEntity, String> {
}
