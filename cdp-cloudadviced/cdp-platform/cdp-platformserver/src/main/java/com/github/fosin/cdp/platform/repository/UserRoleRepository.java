package com.github.fosin.cdp.platform.repository;

import com.github.fosin.cdp.platformapi.entity.CdpUserRoleEntity;
import com.github.fosin.cdp.jpa.repository.IJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Lazy;

import java.util.List;

/**
 * 2017/12/27.
 * Time:16:09
 *
 * @author fosin
 */
@Repository
@Lazy
public interface UserRoleRepository extends IJpaRepository<CdpUserRoleEntity, Long> {
    List<CdpUserRoleEntity> findByUserId(Long userId);
    List<CdpUserRoleEntity> findByRoleId(Long roleId);
    void deleteByUserId(Long userId);
    void deleteByRoleId(Long roleId);
}
