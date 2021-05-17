package top.fosin.anan.platform.dto.req;

import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;

/**
 * 系统支付发票表(AnanPayInvoice)创建DTO
 *
 * @author fosin
 * @date 2019-02-19 18:14:31
 * @since 1.0.0
 */
@Data
@ApiModel(value = "系统支付发票表创建DTO", description = "表(anan_pay_invoice)的对应的创建DTO")
public class AnanPayInvoiceCreateDto implements Serializable {
    private static final long serialVersionUID = 216983964108957018L;

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
