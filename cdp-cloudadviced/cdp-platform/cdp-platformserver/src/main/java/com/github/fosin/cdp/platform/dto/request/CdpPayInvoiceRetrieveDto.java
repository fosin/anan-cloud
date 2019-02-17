package com.github.fosin.cdp.platform.dto.request;

import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import com.github.fosin.cdp.util.DateTimeUtil;

/**
 * 系统支付发票表(CdpPayInvoice)查询DTO
 *
 * @author fosin
 * @date 2019-01-28 11:45:18
 * @since 1.0.0
 */
@Data
@ApiModel(value = "系统支付发票表查询DTO", description = "表(cdp_pay_invoice)的对应的查询DTO")
public class CdpPayInvoiceRetrieveDto implements Serializable {
    private static final long serialVersionUID = 709933326873520394L;

    @NotNull
    @ApiModelProperty(value = "发票ID", example = "Long")
    private Long invoceId;

    @NotNull
    @ApiModelProperty(value = "支付ID", example = "Long")
    private Long payId;

    @NotBlank
    @ApiModelProperty(value = "发票号码", example = "String")
    private String invoceNo;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "出票时间", example = "Date")
    private Date invoceTime;

    @NotNull
    @ApiModelProperty(value = "操作人", example = "Long")
    private Long crreateBy;

}
