package top.fosin.anan.platform.modules.pay.service.inter;

import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platform.modules.pay.dto.PayReqDto;
import top.fosin.anan.platform.modules.pay.dto.PayRespDto;
import top.fosin.anan.platform.modules.pay.po.Pay;

/**
 * 支付表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface PayService extends ISimpleJpaService<PayReqDto, PayRespDto, Long,Pay> {
}
