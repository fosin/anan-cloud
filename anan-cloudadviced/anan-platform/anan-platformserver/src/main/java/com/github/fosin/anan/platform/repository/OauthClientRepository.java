package com.github.fosin.anan.platform.repository;

import com.github.fosin.anan.jpa.repository.IJpaRepository;
import com.github.fosin.anan.platform.entity.OauthClientDetailsEntity;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 * 2017/12/27.
 * Time:16:09
 *
 * @author fosin
 */
@Repository
@Lazy
public interface OauthClientRepository extends IJpaRepository<OauthClientDetailsEntity, String> {
}
