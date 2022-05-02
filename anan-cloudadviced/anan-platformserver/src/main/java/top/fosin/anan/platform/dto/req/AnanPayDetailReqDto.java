package top.fosin.anan.platform.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.fosin.anan.model.dto.QuerySortRuleDto;
import top.fosin.anan.model.module.LogicalQueryRule;
import top.fosin.anan.model.module.SortRule;
import top.fosin.anan.model.valid.group.Create;
import top.fosin.anan.model.valid.group.Update;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 支付明细表(AnanPayDetail)请求DTO
 *
 * @author fosin
 * @date 2019-02-19 18:14:31
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "支付明细表请求DTO", description = "支付明细的请求DTO")
public class AnanPayDetailReqDto extends QuerySortRuleDto<LogicalQueryRule, SortRule, Long> {
    private static final long serialVersionUID = 318623987516262809L;

    @NotNull(message = "支付序号" + "{javax.validation.constraints.NotNull.message}",
            groups = {Create.class, Update.class})
    @Min(value = 0, message = "使用状态" + "{javax.validation.constraints.Min.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "支付序号", required = true)
    private Long payId;

    @NotNull(message = "付款方式" + "{javax.validation.constraints.NotNull.message}")
    @Min(value = 0, message = "使用状态" + "{javax.validation.constraints.Min.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "付款方式", required = true)
    private Integer payway;

    @NotNull(message = "付款金额" + "{javax.validation.constraints.NotNull.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "付款金额", required = true)
    private Double money;

}
