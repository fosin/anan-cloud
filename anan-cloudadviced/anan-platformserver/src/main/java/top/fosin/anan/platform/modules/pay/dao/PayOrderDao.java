package top.fosin.anan.platform.modules.pay.dao;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import top.fosin.anan.jpa.repository.IJpaRepository;
import top.fosin.anan.platform.modules.pay.po.PayOrder;

/**
 * 支付订单表数据库访问层
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Repository
@Lazy
public interface PayOrderDao extends IJpaRepository<Long, PayOrder> {
}
