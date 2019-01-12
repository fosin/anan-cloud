package com.github.fosin.cdp.platformapi.repository;

import com.github.fosin.cdp.jpa.repository.IJpaRepository;
import com.github.fosin.cdp.platformapi.entity.CdpSysOrganizationAuthEntity;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统机构授权表(cdp_sys_organization_auth)表数据库访问层
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Repository
@Lazy
public interface CdpSysOrganizationAuthRepository extends IJpaRepository<CdpSysOrganizationAuthEntity, Long>{
    List<CdpSysOrganizationAuthEntity> findAllByVersionId(Long versionId);

    List<CdpSysOrganizationAuthEntity> findAllByOrganizId(Long organizId);
}