package top.fosin.anan.platform.modules.pay.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.data.valid.group.Create;

import java.math.BigDecimal;
import java.util.Date;
        
/**
 * 系统支付表(anan_pay)创建DTO
 *
 * @author fosin
 * @date 2023-05-11
 */
@Data
@EqualsAndHashCode
@ToString(callSuper = true)
@Schema(description = "系统支付表(anan_pay)创建DTO")
public class PayCreateDTO {
    private static final long serialVersionUID = -39624962622940835L;
    
    @NotNull(message = "付款机构" + "{jakarta.validation.constraints.NotNull.message}", groups = Create.class)
    @Positive(message = "付款机构" + "{jakarta.validation.constraints.Positive.message}", groups = Create.class)
    @Schema(description = "付款机构")
    private Long organizId;

    @NotNull(message = "付款用户" + "{jakarta.validation.constraints.NotNull.message}", groups = Create.class)
    @Positive(message = "付款用户" + "{jakarta.validation.constraints.Positive.message}", groups = Create.class)
    @Schema(description = "付款用户")
    private Long userId;

    @NotNull(message = "订单序号" + "{jakarta.validation.constraints.NotNull.message}", groups = Create.class)
    @Positive(message = "订单序号" + "{jakarta.validation.constraints.Positive.message}", groups = Create.class)
    @Schema(description = "订单序号")
    private Long orderId;

    @NotNull(message = "发票序号" + "{jakarta.validation.constraints.NotNull.message}", groups = Create.class)
    @Positive(message = "发票序号" + "{jakarta.validation.constraints.Positive.message}", groups = Create.class)
    @Schema(description = "发票序号")
    private Long invoiceId;

    @NotNull(message = "交易类型" + "{jakarta.validation.constraints.NotNull.message}", groups = Create.class)
    @PositiveOrZero(message = "交易类型" + "{jakarta.validation.constraints.Positive.message}", groups = Create.class)
    @Schema(description = "交易类型：0=正交易 1：负交易")
    private Integer payType;

    @NotNull(message = "应收金额" + "{jakarta.validation.constraints.NotNull.message}", groups = Create.class)
    @Schema(description = "应收金额")
    private BigDecimal totalMoney;

    @NotNull(message = "支付金额" + "{jakarta.validation.constraints.NotNull.message}", groups = Create.class)
    @Schema(description = "支付金额")
    private BigDecimal payMoney;

    @NotNull(message = "优惠金额" + "{jakarta.validation.constraints.NotNull.message}", groups = Create.class)
    @Schema(description = "优惠金额")
    private BigDecimal discountMonery;

    @NotNull(message = "待收金额" + "{jakarta.validation.constraints.NotNull.message}", groups = Create.class)
    @Schema(description = "待收金额")
    private BigDecimal uncollectMoney;

    @Schema(description = "付款时间")
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    private Date payTime;

    @NotNull(message = "付款标志" + "{jakarta.validation.constraints.NotNull.message}", groups = Create.class)
    @PositiveOrZero(message = "付款标志" + "{jakarta.validation.constraints.Positive.message}", groups = Create.class)
    @Schema(description = "付款标志：0=未付款，1=分期，2=付全款")
    private Integer payFlag;

}
