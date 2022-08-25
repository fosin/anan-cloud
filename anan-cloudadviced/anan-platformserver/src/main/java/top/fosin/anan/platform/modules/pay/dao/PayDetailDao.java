package top.fosin.anan.platform.modules.pay.dao;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import top.fosin.anan.jpa.repository.IJpaRepository;
import top.fosin.anan.platform.modules.pay.po.PayDetail;

/**
 * 支付明细支付明细表数据库访问层
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Repository
@Lazy
public interface PayDetailDao extends IJpaRepository<Long, PayDetail> {
}
