package top.fosin.anan.platform.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.model.dto.IdDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 支付发票表(AnanPayInvoice)更新DTO
 *
 * @author fosin
 * @date 2019-02-19 18:14:31
 * @since 1.0.0
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "支付发票表更新DTO", description = "支付发票的更新DTO")
public class AnanPayInvoiceUpdateDto extends IdDto<Long> implements Serializable {
    private static final long serialVersionUID = 595865760081308395L;

    @NotNull(message = "支付ID" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "支付ID", required = true)
    private Long payId;

    @NotBlank(message = "发票号码" + "{javax.validation.constraints.NotBlank.message}")
    @ApiModelProperty(value = "发票号码", required = true)
    private String invoceNo;

    @ApiModelProperty(value = "出票时间", required = true)
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    private Date invoceTime;

    @NotNull(message = "操作人" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "操作人", required = true)
    private Long createBy;

}
