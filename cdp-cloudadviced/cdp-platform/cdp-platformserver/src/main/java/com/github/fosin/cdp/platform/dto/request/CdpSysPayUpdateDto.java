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
 * 系统支付表(CdpSysPay)更新DTO
 *
 * @author fosin
 * @date 2019-01-28 11:45:18
 * @since 1.0.0
 */
@Data
@ApiModel(value = "系统支付表更新DTO", description = "表(cdp_sys_pay)的对应的更新DTO")
public class CdpSysPayUpdateDto implements Serializable {
    private static final long serialVersionUID = -53125386518136358L;
    
    @NotNull
    @ApiModelProperty(value = "支付ID", example = "Long", required = true)
    private Long payId;

    @NotNull
    @ApiModelProperty(value = "付款机构", example = "Long", required = true)
    private Long organizId;

    @NotNull
    @ApiModelProperty(value = "付款用户", example = "Long", required = true)
    private Long userId;

    @NotNull
    @ApiModelProperty(value = "订单ID", example = "Long", required = true)
    private Long orderId;

    @NotNull
    @ApiModelProperty(value = "发票ID", example = "Long", required = true)
    private Long invoiceId;

    @NotNull
    @ApiModelProperty(value = "交易类型：0=正交易 1：负交易", example = "Integer", required = true)
    private Integer payType;

    @NotNull
    @ApiModelProperty(value = "应收金额", example = "Double", required = true)
    private Double totalMoney;

    @NotNull
    @ApiModelProperty(value = "支付金额", example = "Double", required = true)
    private Double payMoney;

    @NotNull
    @ApiModelProperty(value = "优惠金额", example = "Double", required = true)
    private Double discountMonery;

    @NotNull
    @ApiModelProperty(value = "待收金额", example = "Double", required = true)
    private Double uncollectMoney;

    @ApiModelProperty(value = "付款日期", example = "Date", required = true)
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    private Date payTime;

    @NotNull
    @ApiModelProperty(value = "付款标志：0=未付款，1=分期，2=付全款", example = "Integer", required = true)
    private Integer payFlag;

}