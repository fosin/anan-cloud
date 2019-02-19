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
 * 系统支付表(CdpPay)创建DTO
 *
 * @author fosin
 * @date 2019-02-19 18:14:31
 * @since 1.0.0
 */
@Data
@ApiModel(value = "系统支付表创建DTO", description = "表(cdp_pay)的对应的创建DTO")
public class CdpPayCreateDto implements Serializable {
    private static final long serialVersionUID = -68212718429188184L;
    
    @NotNull(message = "付款机构" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "付款机构", example = "Long", required = true)
    private Long organizId;

    @NotNull(message = "付款用户" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "付款用户", example = "Long", required = true)
    private Long userId;

    @NotNull(message = "订单ID" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "订单ID", example = "Long", required = true)
    private Long orderId;

    @NotNull(message = "发票ID" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "发票ID", example = "Long", required = true)
    private Long invoiceId;

    @NotNull(message = "交易类型：0=正交易 1：负交易" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "交易类型：0=正交易 1：负交易", example = "Integer", required = true)
    private Integer payType;

    @NotNull(message = "应收金额" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "应收金额", example = "Double", required = true)
    private Double totalMoney;

    @NotNull(message = "支付金额" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "支付金额", example = "Double", required = true)
    private Double payMoney;

    @NotNull(message = "优惠金额" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "优惠金额", example = "Double", required = true)
    private Double discountMonery;

    @NotNull(message = "待收金额" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "待收金额", example = "Double", required = true)
    private Double uncollectMoney;

    @ApiModelProperty(value = "付款日期", example = "Date", required = true)
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    private Date payTime;

    @NotNull(message = "付款标志：0=未付款，1=分期，2=付全款" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "付款标志：0=未付款，1=分期，2=付全款", example = "Integer", required = true)
    private Integer payFlag;

}