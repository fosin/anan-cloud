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
 * 系统支付表(CdpSysPay)查询DTO
 *
 * @author fosin
 * @date 2019-01-28 11:45:18
 * @since 1.0.0
 */
@Data
@ApiModel(value = "系统支付表查询DTO", description = "表(cdp_sys_pay)的对应的查询DTO")
public class CdpSysPayRetrieveDto implements Serializable {
    private static final long serialVersionUID = 100876508103890651L;
    
    @NotNull
    @ApiModelProperty(value = "支付ID", example = "Long")
    private Long payId;

    @NotNull
    @ApiModelProperty(value = "付款机构", example = "Long")
    private Long organizId;

    @NotNull
    @ApiModelProperty(value = "付款用户", example = "Long")
    private Long userId;

    @NotNull
    @ApiModelProperty(value = "订单ID", example = "Long")
    private Long orderId;

    @NotNull
    @ApiModelProperty(value = "发票ID", example = "Long")
    private Long invoiceId;

    @NotNull
    @ApiModelProperty(value = "交易类型：0=正交易 1：负交易", example = "Integer")
    private Integer payType;

    @NotNull
    @ApiModelProperty(value = "应收金额", example = "Double")
    private Double totalMoney;

    @NotNull
    @ApiModelProperty(value = "支付金额", example = "Double")
    private Double payMoney;

    @NotNull
    @ApiModelProperty(value = "优惠金额", example = "Double")
    private Double discountMonery;

    @NotNull
    @ApiModelProperty(value = "待收金额", example = "Double")
    private Double uncollectMoney;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "付款日期", example = "Date")
    private Date payTime;

    @NotNull
    @ApiModelProperty(value = "付款标志：0=未付款，1=分期，2=付全款", example = "Integer")
    private Integer payFlag;

}