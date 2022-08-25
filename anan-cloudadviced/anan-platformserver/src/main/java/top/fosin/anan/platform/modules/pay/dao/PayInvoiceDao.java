package top.fosin.anan.platform.modules.pay.dao;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import top.fosin.anan.jpa.repository.IJpaRepository;
import top.fosin.anan.platform.modules.pay.po.PayInvoice;

/**
 * 支付发票表数据库访问层
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Repository
@Lazy
public interface PayInvoiceDao extends IJpaRepository<Long, PayInvoice> {
}
