package top.fosin.anan.platform.modules.pay.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.data.entity.Id;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

/**
 * 系统支付发票表(anan_pay_invoice)更新DTO
 *
 * @author fosin
 * @date 2023-05-11 22:57:03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@ApiModel(value = "系统支付发票表更新DTO", description = "系统支付发票表(anan_pay_invoice)更新DTO")
public class PayInvoiceUpdateDTO extends Id<Long> {
    private static final long serialVersionUID = -51160534162545457L;

    @NotNull(message = "支付序号" + "{javax.validation.constraints.NotNull.message}")
    @Positive(message = "使用状态" + "{javax.validation.constraints.Positive.message}")
    @ApiModelProperty(value = "支付序号", required = true)
    private Long payId;

    @NotBlank(message = "发票号码" + "{javax.validation.constraints.NotBlank.message}")
    @ApiModelProperty(value = "发票号码", required = true)
    private String invoceNo;

    @ApiModelProperty(value = "出票时间", required = true)
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    private Date invoceTime;

}
