package top.fosin.anan.platform.modules.pay.service.inter;

import top.fosin.anan.jpa.service.ICreateJpaService;
import top.fosin.anan.jpa.service.IDeleteJpaService;
import top.fosin.anan.jpa.service.IRetrieveJpaService;
import top.fosin.anan.jpa.service.IUpdateJpaService;
import top.fosin.anan.platform.modules.pay.dto.PayDetailCreateDTO;
import top.fosin.anan.platform.modules.pay.dto.PayDetailDTO;
import top.fosin.anan.platform.modules.pay.dto.PayDetailUpdateDTO;
import top.fosin.anan.platform.modules.pay.po.PayDetail;

/**
 * 系统支付明细表(anan_pay_detail)服务类
 *
 * @author fosin
 * @date 2023-05-11
 */
public interface PayDetailService extends
        ICreateJpaService<PayDetailCreateDTO, PayDetailDTO, Long, PayDetail>,
        IRetrieveJpaService<PayDetailDTO, Long, PayDetail>,
        IUpdateJpaService<PayDetailUpdateDTO, Long, PayDetail>,
        IDeleteJpaService<Long, PayDetail> {
}