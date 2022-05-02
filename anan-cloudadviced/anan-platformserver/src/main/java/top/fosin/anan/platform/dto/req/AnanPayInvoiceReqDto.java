package top.fosin.anan.platform.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.model.dto.QuerySortRuleDto;
import top.fosin.anan.model.module.LogicalQueryRule;
import top.fosin.anan.model.module.SortRule;
import top.fosin.anan.model.valid.group.Create;
import top.fosin.anan.model.valid.group.Update;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 支付发票表(AnanPayInvoice)请求DTO
 *
 * @author fosin
 * @date 2019-02-19 18:14:31
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "支付发票表请求DTO", description = "支付发票的请求DTO")
public class AnanPayInvoiceReqDto extends QuerySortRuleDto<LogicalQueryRule, SortRule, Long> {
    private static final long serialVersionUID = 805808354567024792L;

    @NotNull(message = "支付序号" + "{javax.validation.constraints.NotNull.message}",
            groups = {Create.class, Update.class})
    @Min(value = 1, message = "使用状态" + "{javax.validation.constraints.Min.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "支付序号", required = true)
    private Long payId;

    @NotBlank(message = "发票号码" + "{javax.validation.constraints.NotBlank.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "发票号码", required = true)
    private String invoceNo;

    @ApiModelProperty(value = "出票时间", required = true)
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    private Date invoceTime;

}
