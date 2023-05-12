package top.fosin.anan.platform.modules.pay.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.data.entity.Id;

import java.util.Date;

/**
 * 系统支付订单表(anan_pay_order)DTO
 *
 * @author fosin
 * @date 2023-05-11
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "系统支付订单表DTO", description = "系统支付订单表(anan_pay_order)DTO")
public class PayOrderDTO extends Id<Long> {
    private static final long serialVersionUID = 430986997104560125L;

    @ApiModelProperty(value = "订单机构", example = "Long")
    private Long organizId;

    @ApiModelProperty(value = "订单用户", example = "Long")
    private Long userId;

    @ApiModelProperty(value = "版本序号", example = "Long")
    private Long versionId;

    @ApiModelProperty(value = "版本金额", example = "Double")
    private Double money;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "订单时间", example = DateTimeUtil.DATETIME_PATTERN)
    private Date orderTime;

    @ApiModelProperty(value = "订单状态：0=新建，1=支付，2=取消，3=作废", example = "Integer")
    private Integer status;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "支付时间", example = DateTimeUtil.DATETIME_PATTERN)
    private Date payTime;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "取消时间", example = DateTimeUtil.DATETIME_PATTERN)
    private Date cancleTime;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "作废时间", example = DateTimeUtil.DATETIME_PATTERN)
    private Date invalidTime;

}
