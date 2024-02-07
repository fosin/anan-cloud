package top.fosin.anan.platform.modules.pay.dto;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.data.valid.group.Create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.Date;
/**
 * 系统支付发票表(anan_pay_invoice)创建DTO
 *
 * @author fosin
 * @date 2023-05-11
 */
@Data
@EqualsAndHashCode
@ToString(callSuper = true)
@Schema(description = "系统支付发票表(anan_pay_invoice)创建DTO")
public class PayInvoiceCreateDTO {
    private static final long serialVersionUID = -87833039921063818L;

    @NotNull(message = "支付序号" + "{jakarta.validation.constraints.NotNull.message}", groups = Create.class)
    @Positive(message = "使用状态" + "{jakarta.validation.constraints.Positive.message}", groups = Create.class)
    @Schema(description = "支付序号")
    private Long payId;

    @NotBlank(message = "发票号码" + "{jakarta.validation.constraints.NotBlank.message}", groups = Create.class)
    @Schema(description = "发票号码")
    private String invoceNo;

    @Schema(description = "出票时间")
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    private Date invoceTime;

}
