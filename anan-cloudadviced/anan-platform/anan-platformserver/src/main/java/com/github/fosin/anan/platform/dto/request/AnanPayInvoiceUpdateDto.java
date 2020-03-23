package com.github.fosin.anan.platform.dto.request;

import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import com.github.fosin.anan.util.DateTimeUtil;

/**
 * 系统支付发票表(AnanPayInvoice)更新DTO
 *
 * @author fosin
 * @date 2019-02-19 18:14:31
 * @since 1.0.0
 */
@Data
@ApiModel(value = "系统支付发票表更新DTO", description = "表(anan_pay_invoice)的对应的更新DTO")
public class AnanPayInvoiceUpdateDto implements Serializable {
    private static final long serialVersionUID = 595865760081308395L;

    @NotNull(message = "发票ID" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "发票ID, 主键", example = "Long", required = true)
    private Long id;

    @NotNull(message = "支付ID" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "支付ID", example = "Long", required = true)
    private Long payId;

    @NotBlank(message = "发票号码" + "{javax.validation.constraints.NotBlank.message}")
    @ApiModelProperty(value = "发票号码", example = "String", required = true)
    private String invoceNo;

    @ApiModelProperty(value = "出票时间", example = "Date", required = true)
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    private Date invoceTime;

    @NotNull(message = "操作人" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "操作人", example = "Long", required = true)
    private Long crreateBy;

}
