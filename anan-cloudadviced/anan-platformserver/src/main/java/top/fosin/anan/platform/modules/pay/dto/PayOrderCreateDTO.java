package top.fosin.anan.platform.modules.pay.dto;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.data.valid.group.Create;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.util.Date;

/**
 * 系统支付订单表(anan_pay_order)创建DTO
 *
 * @author fosin
 * @date 2023-05-11
 */
@Data
@EqualsAndHashCode
@ToString(callSuper = true)
@Schema(description = "系统支付订单表(anan_pay_order)创建DTO")
public class PayOrderCreateDTO {
    private static final long serialVersionUID = -41696001441779586L;

    @NotNull(message = "订单机构" + "{jakarta.validation.constraints.NotNull.message}", groups = Create.class)
    @Positive(message = "订单机构" + "{jakarta.validation.constraints.Positive.message}", groups = Create.class)
    @Schema(description = "订单机构")
    private Long organizId;

    @NotNull(message = "订单用户" + "{jakarta.validation.constraints.NotNull.message}", groups = Create.class)
    @Positive(message = "订单用户" + "{jakarta.validation.constraints.Positive.message}", groups = Create.class)
    @Schema(description = "订单用户")
    private Long userId;

    @NotNull(message = "版本序号" + "{jakarta.validation.constraints.NotNull.message}", groups = Create.class)
    @Positive(message = "版本序号" + "{jakarta.validation.constraints.Positive.message}", groups = Create.class)
    @Schema(description = "版本序号")
    private Long versionId;

    @NotNull(message = "版本金额" + "{jakarta.validation.constraints.NotNull.message}", groups = Create.class)
    @Schema(description = "版本金额")
    private Double money;

    @Schema(description = "订单时间")
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    private Date orderTime;

    @NotNull(message = "订单状态" + "{jakarta.validation.constraints.NotNull.message}", groups = Create.class)
    @PositiveOrZero(message = "订单状态" + "{jakarta.validation.constraints.Positive.message}", groups = Create.class)
    @Schema(description = "订单状态：0=新建，1=支付，2=取消，3=作废")
    private Byte status;

    @Schema(description = "支付时间")
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    private Date payTime;

    @Schema(description = "取消时间")
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    private Date cancleTime;

    @Schema(description = "作废时间")
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    private Date invalidTime;

}
