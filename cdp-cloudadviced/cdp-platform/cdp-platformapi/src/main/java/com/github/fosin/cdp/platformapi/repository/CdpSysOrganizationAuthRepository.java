package com.github.fosin.cdp.platformapi.repository;

import com.github.fosin.cdp.platformapi.entity.CdpSysOrganizationAuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Lazy;

import java.util.List;

/**
 * 系统机构授权表(cdp_sys_organization_auth)表数据库访问层
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Repository
@Lazy
public interface CdpSysOrganizationAuthRepository extends JpaRepository<CdpSysOrganizationAuthEntity, Long>,JpaSpecificationExecutor<CdpSysOrganizationAuthEntity>{
    List<CdpSysOrganizationAuthEntity> findAllByVersionId(Long versionId);

    List<CdpSysOrganizationAuthEntity> findAllByOrganizId(Long organizId);
}