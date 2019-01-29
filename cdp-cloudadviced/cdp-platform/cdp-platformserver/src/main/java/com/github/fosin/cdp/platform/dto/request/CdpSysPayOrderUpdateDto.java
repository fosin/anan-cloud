package com.github.fosin.cdp.platform.dto.request;

import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import com.github.fosin.cdp.util.DateTimeUtil;

/**
 * 系统支付订单表(CdpSysPayOrder)更新DTO
 *
 * @author fosin
 * @date 2019-01-28 11:45:17
 * @since 1.0.0
 */
@Data
@ApiModel(value = "系统支付订单表更新DTO", description = "表(cdp_sys_pay_order)的对应的更新DTO")
public class CdpSysPayOrderUpdateDto implements Serializable {
    private static final long serialVersionUID = -15398246059701476L;
    
    @NotNull
    @ApiModelProperty(value = "订单ID", example = "Long", required = true)
    private Long orderId;

    @NotNull
    @ApiModelProperty(value = "订单机构", example = "Long", required = true)
    private Long organizId;

    @NotNull
    @ApiModelProperty(value = "订单用户", example = "Long", required = true)
    private Long userId;

    @NotNull
    @ApiModelProperty(value = "版本ID", example = "Long", required = true)
    private Long versionId;

    @NotNull
    @ApiModelProperty(value = "版本金额", example = "Double", required = true)
    private Double money;

    @ApiModelProperty(value = "订单日期", example = "Date", required = true)
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    private Date orderTime;

    @NotNull
    @ApiModelProperty(value = "订单状态：0=新建，1=支付，2=取消，3=作废", example = "Integer", required = true)
    private Integer status;

    @ApiModelProperty(value = "支付日期", example = "Date")
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    private Date payTime;

    @ApiModelProperty(value = "取消日期", example = "Date")
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    private Date cancleTime;

    @ApiModelProperty(value = "作废日期", example = "Date")
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    private Date invalidTime;

}