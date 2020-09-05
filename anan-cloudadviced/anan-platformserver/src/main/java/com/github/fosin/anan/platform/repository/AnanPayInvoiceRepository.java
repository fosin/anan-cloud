package com.github.fosin.anan.platform.repository;

import com.github.fosin.anan.platform.entity.AnanPayInvoiceEntity;
import com.github.fosin.anan.jpa.repository.IJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Lazy;
/**
 * 系统支付发票表(anan_pay_invoice)表数据库访问层
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Repository
@Lazy
public interface AnanPayInvoiceRepository extends IJpaRepository<AnanPayInvoiceEntity, Long>{
}
