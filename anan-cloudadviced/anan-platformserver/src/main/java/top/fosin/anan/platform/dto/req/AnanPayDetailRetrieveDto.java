package top.fosin.anan.platform.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.fosin.anan.model.dto.QuerySortRuleDto;
import top.fosin.anan.model.module.LogicalQueryRule;
import top.fosin.anan.model.module.SortRule;

/**
 * 支付明细表(AnanPayDetail)查询DTO
 *
 * @author fosin
 * @date 2019-02-19 18:14:31
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "支付明细表查询DTO", description = "支付明细的查询DTO")
public class AnanPayDetailRetrieveDto extends QuerySortRuleDto<LogicalQueryRule, SortRule> {
    private static final long serialVersionUID = 318623987516262809L;

    @ApiModelProperty(value = "支付ID")
    private Long payId;

    @ApiModelProperty(value = "付款方式")
    private Integer payway;

    @ApiModelProperty(value = "付款金额")
    private Double money;

}
