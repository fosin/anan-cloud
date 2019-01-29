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
 * 系统支付订单表(CdpSysPayOrder)查询DTO
 *
 * @author fosin
 * @date 2019-01-28 11:45:17
 * @since 1.0.0
 */
@Data
@ApiModel(value = "系统支付订单表查询DTO", description = "表(cdp_sys_pay_order)的对应的查询DTO")
public class CdpSysPayOrderRetrieveDto implements Serializable {
    private static final long serialVersionUID = 854822890555773308L;
    
    @NotNull
    @ApiModelProperty(value = "订单ID", example = "Long")
    private Long orderId;

    @NotNull
    @ApiModelProperty(value = "订单机构", example = "Long")
    private Long organizId;

    @NotNull
    @ApiModelProperty(value = "订单用户", example = "Long")
    private Long userId;

    @NotNull
    @ApiModelProperty(value = "版本ID", example = "Long")
    private Long versionId;

    @NotNull
    @ApiModelProperty(value = "版本金额", example = "Double")
    private Double money;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "订单日期", example = "Date")
    private Date orderTime;

    @NotNull
    @ApiModelProperty(value = "订单状态：0=新建，1=支付，2=取消，3=作废", example = "Integer")
    private Integer status;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "支付日期", example = "Date")
    private Date payTime;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "取消日期", example = "Date")
    private Date cancleTime;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "作废日期", example = "Date")
    private Date invalidTime;

}