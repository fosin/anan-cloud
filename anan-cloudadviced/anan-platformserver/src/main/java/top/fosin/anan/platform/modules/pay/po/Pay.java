package top.fosin.anan.platform.modules.pay.po;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import top.fosin.anan.jpa.po.IdPO;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 支付表(AnanPay)实体类
 *
 * @author fosin
 * @date 2019-01-28 12:50:38
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DynamicUpdate
@Table(name = "anan_pay")
@Schema(description = "支付的实体类")
public class Pay extends IdPO<Long> {
    private static final long serialVersionUID = 197340387949290400L;

    @Basic
    @Schema(description = "付款用户")
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Basic
    @Schema(description = "订单序号")
    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Basic
    @Schema(description = "发票序号")
    @Column(name = "invoice_id", nullable = false)
    private Long invoiceId;

    @Basic
    @Schema(description = "交易类型：0=正交易 1：负交易")
    @Column(name = "pay_type", nullable = false)
    private Integer payType;

    @Basic
    @Schema(description = "应收金额")
    @Column(name = "total_money", nullable = false, precision = 12, scale = 2)
    private BigDecimal totalMoney;

    @Basic
    @Schema(description = "支付金额")
    @Column(name = "pay_money", nullable = false, precision = 12, scale = 2)
    private BigDecimal payMoney;

    @Basic
    @Schema(description = "优惠金额")
    @Column(name = "discount_monery", nullable = false, precision = 12, scale = 2)
    private BigDecimal discountMonery;

    @Basic
    @Schema(description = "待收金额")
    @Column(name = "uncollect_money", nullable = false, precision = 12, scale = 2)
    private BigDecimal uncollectMoney;

    @Basic
    @Schema(description = "付款时间")
    @Column(name = "pay_time", nullable = false)
    private Date payTime;

    @Basic
    @Schema(description = "付款标志：0=未付款，1=分期，2=付全款")
    @Column(name = "pay_flag", nullable = false)
    private Integer payFlag;

}
