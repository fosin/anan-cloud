package top.fosin.anan.platform.modules.pay.vo;



import io.swagger.v3.oas.annotations.media.Schema;


import java.io.Serializable;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;

import java.util.Date;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.entity.Id;

/**
 * 系统支付订单表(anan_pay_order)集合VO
 *
 * @author fosin
 * @date 2023-05-11
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "系统支付订单表(anan_pay_order)集合VO")
public class PayOrderListVO extends Id<Long> {
    private static final long serialVersionUID = 493643065183146558L;
    @Schema(description = "订单机构")
    private Long organizId;

    @Schema(description = "订单用户")
    private Long userId;

    @Schema(description = "版本ID")
    private Long versionId;

    @Schema(description = "版本金额")
    private Double money;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Schema(description = "订单日期")
    private Date orderTime;

    @Schema(description = "订单状态：0=新建，1=支付，2=取消，3=作废")
    private Byte status;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Schema(description = "支付日期")
    private Date payTime;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Schema(description = "取消日期")
    private Date cancleTime;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Schema(description = "作废日期")
    private Date invalidTime;

}
