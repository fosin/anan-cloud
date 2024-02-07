package top.fosin.anan.platform.modules.pay.dto;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.data.entity.Id;

import java.util.Date;

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
@Schema(description = "支付的响应DTO")
public class PayDTO extends Id<Long> {
    private static final long serialVersionUID = 822309690652216835L;
    @Schema(description = "付款机构", example = "Long")
    private Long organizId;

    @Schema(description = "付款用户", example = "Long")
    private Long userId;

    @Schema(description = "订单序号", example = "Long")
    private Long orderId;

    @Schema(description = "发票序号", example = "Long")
    private Long invoiceId;

    @Schema(description = "交易类型：0=正交易 1：负交易", example = "Integer")
    private Integer payType;

    @Schema(description = "应收金额", example = "Double")
    private Double totalMoney;

    @Schema(description = "支付金额", example = "Double")
    private Double payMoney;

    @Schema(description = "优惠金额", example = "Double")
    private Double discountMonery;

    @Schema(description = "待收金额", example = "Double")
    private Double uncollectMoney;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Schema(description = "付款时间", example = DateTimeUtil.DATETIME_PATTERN)
    private Date payTime;

    @Schema(description = "付款标志：0=未付款，1=分期，2=付全款", example = "Integer")
    private Integer payFlag;

}
