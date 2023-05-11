package top.fosin.anan.platform.modules.pay.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

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
 * @date 2023-05-11 22:57:04
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "系统支付订单表集合VO", description = "系统支付订单表(anan_pay_order)集合VO")
public class PayOrderListVO extends Id<Long> {
    private static final long serialVersionUID = 493643065183146558L;
    @ApiModelProperty(value = "订单机构")
    private Long organizId;

    @ApiModelProperty(value = "订单用户")
    private Long userId;

    @ApiModelProperty(value = "版本ID")
    private Long versionId;

    @ApiModelProperty(value = "版本金额")
    private Double money;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "订单日期")
    private Date orderTime;

    @ApiModelProperty(value = "订单状态：0=新建，1=支付，2=取消，3=作废")
    private Integer status;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "支付日期")
    private Date payTime;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "取消日期")
    private Date cancleTime;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "作废日期")
    private Date invalidTime;

}
