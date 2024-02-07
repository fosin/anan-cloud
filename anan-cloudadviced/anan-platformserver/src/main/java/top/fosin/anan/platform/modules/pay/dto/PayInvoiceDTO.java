package top.fosin.anan.platform.modules.pay.dto;



import io.swagger.v3.oas.annotations.media.Schema;


import java.io.Serializable;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;

import java.util.Date;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.entity.Id;

/**
 * 系统支付发票表(anan_pay_invoice)DTO
 *
 * @author fosin
 * @date 2023-05-11
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "系统支付发票表(anan_pay_invoice)DTO")
public class PayInvoiceDTO extends Id<Long> {
    private static final long serialVersionUID = 547109070798445539L;
    @Schema(description = "支付ID", example = "Long")
    private Long payId;

    @Schema(description = "发票号码", example = "String")
    private String invoceNo;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Schema(description = "出票时间", example = "Date")
    private Date invoceTime;

    @Schema(description = "操作人", example = "Long")
    private Long createBy;

}
