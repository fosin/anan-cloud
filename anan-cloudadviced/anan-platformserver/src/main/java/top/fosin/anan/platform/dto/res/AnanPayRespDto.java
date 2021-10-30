package top.fosin.anan.platform.dto.res;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.model.dto.IdDto;

/**
 * 支付表(AnanPay)响应DTO
 *
 * @author fosin
 * @date 2021-05-16 14:32:14
 * @since 2.6.0
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "支付表响应DTO", description = "支付的响应DTO")
public class AnanPayRespDto extends IdDto<Long> {
    private static final long serialVersionUID = 822309690652216835L;
    @ApiModelProperty(value = "付款机构", example = "Long")
    private Long organizId;

    @ApiModelProperty(value = "付款用户", example = "Long")
    private Long userId;

    @ApiModelProperty(value = "订单ID", example = "Long")
    private Long orderId;

    @ApiModelProperty(value = "发票ID", example = "Long")
    private Long invoiceId;

    @ApiModelProperty(value = "交易类型：0=正交易 1：负交易", example = "Integer")
    private Integer payType;

    @ApiModelProperty(value = "应收金额", example = "Double")
    private Double totalMoney;

    @ApiModelProperty(value = "支付金额", example = "Double")
    private Double payMoney;

    @ApiModelProperty(value = "优惠金额", example = "Double")
    private Double discountMonery;

    @ApiModelProperty(value = "待收金额", example = "Double")
    private Double uncollectMoney;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "付款时间", example = DateTimeUtil.DATETIME_PATTERN)
    private Date payTime;

    @ApiModelProperty(value = "付款标志：0=未付款，1=分期，2=付全款", example = "Integer")
    private Integer payFlag;

}
