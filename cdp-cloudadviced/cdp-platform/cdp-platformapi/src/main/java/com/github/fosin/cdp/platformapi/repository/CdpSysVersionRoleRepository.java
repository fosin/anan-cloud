package com.github.fosin.cdp.platformapi.repository;

import com.github.fosin.cdp.jpa.repository.IJpaRepository;
import com.github.fosin.cdp.platformapi.entity.CdpSysVersionRoleEntity;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
/**
 * 系统版本角色表(cdp_sys_version_role)表数据库访问层
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Repository
@Lazy
public interface CdpSysVersionRoleRepository extends IJpaRepository<CdpSysVersionRoleEntity, Long> {
}