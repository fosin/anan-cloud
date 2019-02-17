package com.github.fosin.cdp.platform.repository;

import com.github.fosin.cdp.jpa.repository.IJpaRepository;
import com.github.fosin.cdp.platform.entity.CdpVersionRoleEntity;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
/**
 * 系统版本角色表(cdp_version_role)表数据库访问层
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Repository
@Lazy
public interface CdpVersionRoleRepository extends IJpaRepository<CdpVersionRoleEntity, Long> {
}
