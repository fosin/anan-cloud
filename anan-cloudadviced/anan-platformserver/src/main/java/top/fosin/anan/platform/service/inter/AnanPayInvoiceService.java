package top.fosin.anan.platform.service.inter;

import top.fosin.anan.platform.dto.res.AnanPayInvoiceRespDto;
import top.fosin.anan.platform.dto.req.AnanPayInvoiceCreateDto;
import top.fosin.anan.platform.dto.req.AnanPayInvoiceRetrieveDto;
import top.fosin.anan.platform.dto.req.AnanPayInvoiceUpdateDto;
import top.fosin.anan.platform.entity.AnanPayInvoiceEntity;
import top.fosin.anan.jpa.service.ISimpleJpaService;

/**
 * 系统支付发票表(anan_pay_invoice)表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface AnanPayInvoiceService extends ISimpleJpaService<AnanPayInvoiceEntity, AnanPayInvoiceRespDto,
        Long, AnanPayInvoiceCreateDto, AnanPayInvoiceRetrieveDto, AnanPayInvoiceUpdateDto> {
}
