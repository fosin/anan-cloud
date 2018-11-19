package com.github.fosin.cdp.platformapi.repository;

import com.github.fosin.cdp.platformapi.entity.CdpSysPayDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Lazy;

/**
 * 系统支付明细表(cdp_sys_pay_detail)表数据库访问层
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Repository
@Lazy
public interface CdpSysPayDetailRepository extends JpaRepository<CdpSysPayDetailEntity, Long>, JpaSpecificationExecutor<CdpSysPayDetailEntity> {
}