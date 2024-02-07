package top.fosin.anan.platform.modules.pay.query;


import io.swagger.v3.oas.annotations.media.Schema;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

import lombok.Data;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.data.entity.req.LogiSortQuery;
import top.fosin.anan.data.module.SortRule;
import top.fosin.anan.data.module.LogiQueryRule;

import java.util.Date;

/**
 * 系统支付发票表(anan_pay_invoice)通用查询DTO
 *
 * @author fosin
 * @date 2023-05-11
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "系统支付发票表(anan_pay_invoice)通用查询DTO")
public class PayInvoiceQuery extends LogiSortQuery<LogiQueryRule, SortRule, Long> {
    private static final long serialVersionUID = 194036415171177655L;

    @Schema(description = "支付ID", example = "Long")
    private Long payId;

    @Schema(description = "发票号码", example = "String")
    private String invoceNo;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Schema(description = "出票时间", example = "Date")
    private Date invoceTime;

}
