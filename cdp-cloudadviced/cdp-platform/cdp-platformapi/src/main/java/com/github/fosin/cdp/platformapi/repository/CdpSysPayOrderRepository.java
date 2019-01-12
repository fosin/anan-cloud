package com.github.fosin.cdp.platformapi.repository;

import com.github.fosin.cdp.platformapi.entity.CdpSysPayOrderEntity;
import com.github.fosin.cdp.jpa.repository.IJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Lazy;
/**
 * 系统支付订单表(cdp_sys_pay_order)表数据库访问层
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Repository
@Lazy
public interface CdpSysPayOrderRepository extends IJpaRepository<CdpSysPayOrderEntity, Long>{
}