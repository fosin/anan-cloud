package top.fosin.anan.platform.modules.pay.query;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.data.entity.req.LogiSortQuery;
import top.fosin.anan.data.module.LogiQueryRule;
import top.fosin.anan.data.module.SortRule;

import java.math.BigDecimal;
import java.util.Date;

        /**
 * 系统支付表(anan_pay)通用查询DTO
 *
 * @author fosin
 * @date 2023-05-11
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "系统支付表(anan_pay)通用查询DTO")
public class PayQuery extends LogiSortQuery<LogiQueryRule,SortRule,Long> {
    private static final long serialVersionUID = -67216313896488426L;
    
    @Schema(description = "付款机构", example = "Long")
    private Long organizId;

    @Schema(description = "付款用户", example = "Long")
    private Long userId;

    @Schema(description = "订单ID", example = "Long")
    private Long orderId;

    @Schema(description = "发票ID", example = "Long")
    private Long invoiceId;

    @Schema(description = "交易类型：0=正交易 1：负交易", example = "Integer")
    private Integer payType;

    @Schema(description = "应收金额", example = "Double")
    private BigDecimal totalMoney;

    @Schema(description = "支付金额", example = "Double")
    private BigDecimal payMoney;

    @Schema(description = "优惠金额", example = "Double")
    private BigDecimal discountMonery;

    @Schema(description = "待收金额", example = "Double")
    private BigDecimal uncollectMoney;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Schema(description = "付款日期", example = "Date")
    private Date payTime;

    @Schema(description = "付款标志：0=未付款，1=分期，2=付全款", example = "Integer")
    private Integer payFlag;

}
