package top.fosin.anan.platform.modules.pay.service.inter;

import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platform.modules.pay.dto.PayDetailReqDto;
import top.fosin.anan.platform.modules.pay.dto.PayDetailRespDto;
import top.fosin.anan.platform.modules.pay.entity.PayDetail;

/**
 * 支付明细支付明细表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface PayDetailService extends ISimpleJpaService<PayDetail, PayDetailRespDto,
        Long, PayDetailReqDto, PayDetailReqDto, PayDetailReqDto> {
}
