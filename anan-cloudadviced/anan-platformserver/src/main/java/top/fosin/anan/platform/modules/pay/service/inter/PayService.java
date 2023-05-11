package top.fosin.anan.platform.modules.pay.service.inter;

import top.fosin.anan.jpa.service.ICreateJpaService;
import top.fosin.anan.jpa.service.IDeleteJpaService;
import top.fosin.anan.jpa.service.IRetrieveJpaService;
import top.fosin.anan.jpa.service.IUpdateJpaService;
import top.fosin.anan.platform.modules.pay.dto.PayCreateDTO;
import top.fosin.anan.platform.modules.pay.dto.PayDTO;
import top.fosin.anan.platform.modules.pay.dto.PayUpdateDTO;
import top.fosin.anan.platform.modules.pay.po.Pay;

        /**
 * 系统支付表(anan_pay)服务类
 *
 * @author fosin
 * @date 2023-05-11 22:49:44
 */
public interface PayService extends 
        ICreateJpaService<PayCreateDTO, PayDTO, Long, Pay>,
        IRetrieveJpaService<PayDTO, Long, Pay>,
        IUpdateJpaService<PayUpdateDTO, Long, Pay>,
        IDeleteJpaService<Long, Pay> {
}

