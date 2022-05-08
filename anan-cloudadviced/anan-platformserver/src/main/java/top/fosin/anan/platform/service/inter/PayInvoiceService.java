package top.fosin.anan.platform.service.inter;

import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platform.dto.req.AnanPayInvoiceReqDto;
import top.fosin.anan.platform.dto.res.AnanPayInvoiceRespDto;
import top.fosin.anan.platform.entity.AnanPayInvoiceEntity;

/**
 * 支付发票表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface PayInvoiceService extends ISimpleJpaService<AnanPayInvoiceEntity, AnanPayInvoiceRespDto,
        Long, AnanPayInvoiceReqDto, AnanPayInvoiceReqDto, AnanPayInvoiceReqDto> {
}
