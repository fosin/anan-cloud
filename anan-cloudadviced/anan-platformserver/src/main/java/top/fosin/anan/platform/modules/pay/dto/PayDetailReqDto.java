package top.fosin.anan.platform.modules.pay.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.fosin.anan.data.entity.req.IdLogiSortQuery;
import top.fosin.anan.data.module.LogiQueryRule;
import top.fosin.anan.data.module.SortRule;
import top.fosin.anan.data.valid.group.Create;
import top.fosin.anan.data.valid.group.Update;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

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
public class PayDetailReqDto extends IdLogiSortQuery<LogiQueryRule, SortRule, Long> {
    private static final long serialVersionUID = 318623987516262809L;

    @NotNull(message = "支付序号" + "{javax.validation.constraints.NotNull.message}",
            groups = {Create.class, Update.class})
    @PositiveOrZero(message = "使用状态" + "{javax.validation.constraints.Positive.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "支付序号", required = true)
    private Long payId;

    @NotNull(message = "付款方式" + "{javax.validation.constraints.NotNull.message}")
    @PositiveOrZero(message = "使用状态" + "{javax.validation.constraints.Positive.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "付款方式", required = true)
    private Integer payway;

    @NotNull(message = "付款金额" + "{javax.validation.constraints.NotNull.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "付款金额", required = true)
    private Double money;

}
