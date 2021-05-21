package top.fosin.anan.platform.dto.req;

import lombok.EqualsAndHashCode;
import top.fosin.anan.core.util.DateTimeUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.model.dto.QuerySortRuleDto;
import top.fosin.anan.model.module.SortRule;
import top.fosin.anan.model.module.LogicalQueryRule;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统支付发票表(AnanPayInvoice)查询DTO
 *
 * @author fosin
 * @date 2019-02-19 18:14:31
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "系统支付发票表查询DTO", description = "系统支付发票的查询DTO")
public class AnanPayInvoiceRetrieveDto extends QuerySortRuleDto<LogicalQueryRule,SortRule> implements Serializable {
    private static final long serialVersionUID = 805808354567024792L;

    @ApiModelProperty(value = "支付ID")
    private Long payId;

    @ApiModelProperty(value = "发票号码")
    private String invoceNo;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "出票时间")
    private Date invoceTime;

    @ApiModelProperty(value = "操作人")
    private Long createBy;

}
