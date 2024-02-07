package top.fosin.anan.platform.modules.pay.dto;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.data.entity.Id;
import top.fosin.anan.data.valid.group.Update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.Date;
/**
 * 系统支付发票表(anan_pay_invoice)更新DTO
 *
 * @author fosin
 * @date 2023-05-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Schema(description = "系统支付发票表(anan_pay_invoice)更新DTO")
public class PayInvoiceUpdateDTO extends Id<Long> {
    private static final long serialVersionUID = -51160534162545457L;

    @NotNull(message = "支付序号" + "{jakarta.validation.constraints.NotNull.message}", groups = Update.class)
    @Positive(message = "使用状态" + "{jakarta.validation.constraints.Positive.message}", groups = Update.class)
    @Schema(description = "支付序号")
    private Long payId;

    @NotBlank(message = "发票号码" + "{jakarta.validation.constraints.NotBlank.message}", groups = Update.class)
    @Schema(description = "发票号码")
    private String invoceNo;

    @Schema(description = "出票时间")
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    private Date invoceTime;

}
