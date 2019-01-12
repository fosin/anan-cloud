package com.github.fosin.cdp.platformapi.repository;

import com.github.fosin.cdp.jpa.repository.IJpaRepository;
import com.github.fosin.cdp.platformapi.entity.OauthClientDetailsEntity;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *  2017/12/27.
 * Time:16:09
 * @author fosin
 */
@Repository
@Lazy
public interface OauthClientRepository extends IJpaRepository<OauthClientDetailsEntity,String>{
}
