package com.github.fosin.anan.platform.repository;

import com.github.fosin.anan.platform.entity.AnanPayOrderEntity;
import com.github.fosin.anan.jpa.repository.IJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Lazy;
/**
 * 系统支付订单表(anan_pay_order)表数据库访问层
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Repository
@Lazy
public interface AnanPayOrderRepository extends IJpaRepository<AnanPayOrderEntity, Long>{
}
