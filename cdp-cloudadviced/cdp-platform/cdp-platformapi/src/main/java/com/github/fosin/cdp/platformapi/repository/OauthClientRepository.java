package com.github.fosin.cdp.platformapi.repository;

import com.github.fosin.cdp.platformapi.entity.OauthClientDetailsEntity;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Lazy;

/**
 *  2017/12/27.
 * Time:16:09
 * @author fosin
 */
@Repository
@Lazy
public interface OauthClientRepository extends JpaRepository<OauthClientDetailsEntity,String>,
                                    JpaSpecificationExecutor<OauthClientDetailsEntity> {
}
