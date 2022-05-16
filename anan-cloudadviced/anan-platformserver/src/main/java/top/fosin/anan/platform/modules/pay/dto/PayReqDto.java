package top.fosin.anan.platform.modules.pay.dto;

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

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;

/**
 * 支付表(AnanPay)请求DTO
 *
 * @author fosin
 * @date 2019-02-19 18:14:31
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "支付表请求DTO", description = "支付的请求DTO")
public class PayReqDto extends QuerySortRuleDto<LogicalQueryRule, SortRule, Long> {
    private static final long serialVersionUID = 154780790568751897L;

    @NotNull(message = "付款机构" + "{javax.validation.constraints.NotNull.message}",
            groups = {Create.class, Update.class})
    @Positive(message = "付款机构" + "{javax.validation.constraints.Positive.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "付款机构", required = true)
    private Long organizId;

    @NotNull(message = "付款用户" + "{javax.validation.constraints.NotNull.message}",
            groups = {Create.class, Update.class})
    @Positive(message = "付款用户" + "{javax.validation.constraints.Positive.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "付款用户", required = true)
    private Long userId;

    @NotNull(message = "订单序号" + "{javax.validation.constraints.NotNull.message}",
            groups = {Create.class, Update.class})
    @Positive(message = "订单序号" + "{javax.validation.constraints.Positive.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "订单序号", required = true)
    private Long orderId;

    @NotNull(message = "发票序号" + "{javax.validation.constraints.NotNull.message}",
            groups = {Create.class, Update.class})
    @Positive(message = "发票序号" + "{javax.validation.constraints.Positive.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "发票序号", required = true)
    private Long invoiceId;

    @NotNull(message = "交易类型" + "{javax.validation.constraints.NotNull.message}",
            groups = {Create.class, Update.class})
    @PositiveOrZero(message = "交易类型" + "{javax.validation.constraints.Positive.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "交易类型：0=正交易 1：负交易", required = true)
    private Integer payType;

    @NotNull(message = "应收金额" + "{javax.validation.constraints.NotNull.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "应收金额", required = true)
    private Double totalMoney;

    @NotNull(message = "支付金额" + "{javax.validation.constraints.NotNull.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "支付金额", required = true)
    private Double payMoney;

    @NotNull(message = "优惠金额" + "{javax.validation.constraints.NotNull.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "优惠金额", required = true)
    private Double discountMonery;

    @NotNull(message = "待收金额" + "{javax.validation.constraints.NotNull.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "待收金额", required = true)
    private Double uncollectMoney;

    @ApiModelProperty(value = "付款时间", required = true)
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    private Date payTime;

    @NotNull(message = "付款标志" + "{javax.validation.constraints.NotNull.message}",
            groups = {Create.class, Update.class})
    @PositiveOrZero(message = "付款标志" + "{javax.validation.constraints.Positive.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "付款标志：0=未付款，1=分期，2=付全款", required = true)
    private Integer payFlag;

}
