package top.fosin.anan.platform.modules.pay.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.data.entity.Id;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 系统支付表(anan_pay)集合VO
 *
 * @author fosin
 * @date 2023-05-11
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "系统支付表(anan_pay)集合VO")
public class PayListVO extends Id<Long> {
    private static final long serialVersionUID = 363788573574672700L;
    @Schema(description = "付款机构")
    private Long organizId;

    @Schema(description = "付款用户")
    private Long userId;

    @Schema(description = "订单ID")
    private Long orderId;

    @Schema(description = "发票ID")
    private Long invoiceId;

    @Schema(description = "交易类型：0=正交易 1：负交易")
    private Integer payType;

    @Schema(description = "应收金额")
    private BigDecimal totalMoney;

    @Schema(description = "支付金额")
    private BigDecimal payMoney;

    @Schema(description = "优惠金额")
    private BigDecimal discountMonery;

    @Schema(description = "待收金额")
    private BigDecimal uncollectMoney;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Schema(description = "付款日期")
    private Date payTime;

    @Schema(description = "付款标志：0=未付款，1=分期，2=付全款")
    private Integer payFlag;

}
