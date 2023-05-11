package top.fosin.anan.platform.modules.pay.service.inter;

import top.fosin.anan.jpa.service.ICreateJpaService;
import top.fosin.anan.jpa.service.IDeleteJpaService;
import top.fosin.anan.jpa.service.IRetrieveJpaService;
import top.fosin.anan.jpa.service.IUpdateJpaService;
import top.fosin.anan.platform.modules.pay.dto.PayOrderCreateDTO;
import top.fosin.anan.platform.modules.pay.dto.PayOrderDTO;
import top.fosin.anan.platform.modules.pay.dto.PayOrderUpdateDTO;
import top.fosin.anan.platform.modules.pay.po.PayOrder;

/**
 * 系统支付订单表(anan_pay_order)服务类
 *
 * @author fosin
 * @date 2023-05-11 22:57:03
 */
public interface PayOrderService extends
        ICreateJpaService<PayOrderCreateDTO, PayOrderDTO, Long, PayOrder>,
        IRetrieveJpaService<PayOrderDTO, Long, PayOrder>,
        IUpdateJpaService<PayOrderUpdateDTO, Long, PayOrder>,
        IDeleteJpaService<Long, PayOrder> {
}

