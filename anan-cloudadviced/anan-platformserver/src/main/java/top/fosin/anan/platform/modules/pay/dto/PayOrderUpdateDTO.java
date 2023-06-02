package top.fosin.anan.platform.modules.pay.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.data.entity.Id;
import top.fosin.anan.data.valid.group.Update;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;
/**
 * 系统支付订单表(anan_pay_order)更新DTO
 *
 * @author fosin
 * @date 2023-05-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@ApiModel(value = "系统支付订单表更新DTO", description = "系统支付订单表(anan_pay_order)更新DTO")
public class PayOrderUpdateDTO extends Id<Long> {
    private static final long serialVersionUID = 822671144542018591L;

    @NotNull(message = "订单机构" + "{javax.validation.constraints.NotNull.message}", groups = Update.class)
    @Positive(message = "订单机构" + "{javax.validation.constraints.Positive.message}", groups = Update.class)
    @ApiModelProperty(value = "订单机构", required = true)
    private Long organizId;

    @NotNull(message = "订单用户" + "{javax.validation.constraints.NotNull.message}", groups = Update.class)
    @Positive(message = "订单用户" + "{javax.validation.constraints.Positive.message}", groups = Update.class)
    @ApiModelProperty(value = "订单用户", required = true)
    private Long userId;

    @NotNull(message = "版本序号" + "{javax.validation.constraints.NotNull.message}", groups = Update.class)
    @Positive(message = "版本序号" + "{javax.validation.constraints.Positive.message}", groups = Update.class)
    @ApiModelProperty(value = "版本序号", required = true)
    private Long versionId;

    @NotNull(message = "版本金额" + "{javax.validation.constraints.NotNull.message}", groups = Update.class)
    @ApiModelProperty(value = "版本金额", required = true)
    private Double money;

    @ApiModelProperty(value = "订单时间", required = true)
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    private Date orderTime;

    @NotNull(message = "订单状态" + "{javax.validation.constraints.NotNull.message}", groups = Update.class)
    @PositiveOrZero(message = "订单状态" + "{javax.validation.constraints.Positive.message}", groups = Update.class)
    @ApiModelProperty(value = "订单状态：0=新建，1=支付，2=取消，3=作废", required = true)
    private Byte status;

    @ApiModelProperty(value = "支付时间")
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    private Date payTime;

    @ApiModelProperty(value = "取消时间")
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    private Date cancleTime;

    @ApiModelProperty(value = "作废时间")
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    private Date invalidTime;

}
