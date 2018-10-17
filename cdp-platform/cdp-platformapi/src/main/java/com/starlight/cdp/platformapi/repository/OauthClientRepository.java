package com.starlight.cdp.platformapi.repository;

import com.starlight.cdp.platformapi.entity.OauthClientDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 *  2017/12/27.
 * Time:16:09
 * @author fosin
 */
@Repository
public interface OauthClientRepository extends JpaRepository<OauthClientDetailsEntity,String>,
                                    JpaSpecificationExecutor<OauthClientDetailsEntity> {
}
