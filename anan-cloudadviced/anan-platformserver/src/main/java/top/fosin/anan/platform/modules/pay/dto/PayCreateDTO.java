package top.fosin.anan.platform.modules.pay.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.data.entity.req.LogiSortQuery;
import top.fosin.anan.data.module.LogiQueryRule;
import top.fosin.anan.data.module.SortRule;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;
        
/**
 * 系统支付表(anan_pay)创建DTO
 *
 * @author fosin
 * @date 2023-05-11 22:49:44
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@ApiModel(value = "系统支付表创建DTO", description = "系统支付表(anan_pay)创建DTO")
public class PayCreateDTO extends LogiSortQuery<LogiQueryRule, SortRule, Long> {
    private static final long serialVersionUID = -39624962622940835L;
    
    @NotNull(message = "付款机构" + "{javax.validation.constraints.NotNull.message}")
    @Positive(message = "付款机构" + "{javax.validation.constraints.Positive.message}")
    @ApiModelProperty(value = "付款机构", required = true)
    private Long organizId;

    @NotNull(message = "付款用户" + "{javax.validation.constraints.NotNull.message}")
    @Positive(message = "付款用户" + "{javax.validation.constraints.Positive.message}")
    @ApiModelProperty(value = "付款用户", required = true)
    private Long userId;

    @NotNull(message = "订单序号" + "{javax.validation.constraints.NotNull.message}")
    @Positive(message = "订单序号" + "{javax.validation.constraints.Positive.message}")
    @ApiModelProperty(value = "订单序号", required = true)
    private Long orderId;

    @NotNull(message = "发票序号" + "{javax.validation.constraints.NotNull.message}")
    @Positive(message = "发票序号" + "{javax.validation.constraints.Positive.message}")
    @ApiModelProperty(value = "发票序号", required = true)
    private Long invoiceId;

    @NotNull(message = "交易类型" + "{javax.validation.constraints.NotNull.message}")
    @PositiveOrZero(message = "交易类型" + "{javax.validation.constraints.Positive.message}")
    @ApiModelProperty(value = "交易类型：0=正交易 1：负交易", required = true)
    private Integer payType;

    @NotNull(message = "应收金额" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "应收金额", required = true)
    private Double totalMoney;

    @NotNull(message = "支付金额" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "支付金额", required = true)
    private Double payMoney;

    @NotNull(message = "优惠金额" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "优惠金额", required = true)
    private Double discountMonery;

    @NotNull(message = "待收金额" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "待收金额", required = true)
    private Double uncollectMoney;

    @ApiModelProperty(value = "付款时间", required = true)
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    private Date payTime;

    @NotNull(message = "付款标志" + "{javax.validation.constraints.NotNull.message}")
    @PositiveOrZero(message = "付款标志" + "{javax.validation.constraints.Positive.message}")
    @ApiModelProperty(value = "付款标志：0=未付款，1=分期，2=付全款", required = true)
    private Integer payFlag;

}
