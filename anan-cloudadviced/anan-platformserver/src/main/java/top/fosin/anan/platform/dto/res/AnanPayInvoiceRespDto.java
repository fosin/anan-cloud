package top.fosin.anan.platform.dto.res;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.model.dto.IdDto;

/**
 * 支付发票表(AnanPayInvoice)响应DTO
 *
 * @author fosin
 * @date 2021-05-16 14:32:15
 * @since 2.6.0
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "支付发票表响应DTO", description = "支付发票的响应DTO")
public class AnanPayInvoiceRespDto extends IdDto<Long> {
    private static final long serialVersionUID = -74851260485641159L;
    @ApiModelProperty(value = "支付序号", example = "Long")
    private Long payId;

    @ApiModelProperty(value = "发票号码", example = "String")
    private String invoceNo;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "出票时间", example = DateTimeUtil.DATETIME_PATTERN)
    private Date invoceTime;

}
