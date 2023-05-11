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
 * 系统支付订单表(anan_pay_order)创建DTO
 *
 * @author fosin
 * @date 2023-05-11 22:57:03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@ApiModel(value = "系统支付订单表创建DTO", description = "系统支付订单表(anan_pay_order)创建DTO")
public class PayOrderCreateDTO extends LogiSortQuery<LogiQueryRule, SortRule, Long> {
    private static final long serialVersionUID = -41696001441779586L;

    @NotNull(message = "订单机构" + "{javax.validation.constraints.NotNull.message}")
    @Positive(message = "订单机构" + "{javax.validation.constraints.Positive.message}")
    @ApiModelProperty(value = "订单机构", required = true)
    private Long organizId;

    @NotNull(message = "订单用户" + "{javax.validation.constraints.NotNull.message}")
    @Positive(message = "订单用户" + "{javax.validation.constraints.Positive.message}")
    @ApiModelProperty(value = "订单用户", required = true)
    private Long userId;

    @NotNull(message = "版本序号" + "{javax.validation.constraints.NotNull.message}")
    @Positive(message = "版本序号" + "{javax.validation.constraints.Positive.message}")
    @ApiModelProperty(value = "版本序号", required = true)
    private Long versionId;

    @NotNull(message = "版本金额" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "版本金额", required = true)
    private Double money;

    @ApiModelProperty(value = "订单时间", required = true)
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    private Date orderTime;

    @NotNull(message = "订单状态" + "{javax.validation.constraints.NotNull.message}")
    @PositiveOrZero(message = "订单状态" + "{javax.validation.constraints.Positive.message}")
    @ApiModelProperty(value = "订单状态：0=新建，1=支付，2=取消，3=作废", required = true)
    private Integer status;

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
