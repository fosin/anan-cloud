package top.fosin.anan.platform.modules.pay.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import top.fosin.anan.jpa.po.IdPO;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
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
@ApiModel(value = "支付表实体类", description = "支付的实体类")
public class Pay extends IdPO<Long> {
    private static final long serialVersionUID = 197340387949290400L;

    @Basic
    @ApiModelProperty(value = "付款用户", required = true)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Basic
    @ApiModelProperty(value = "订单序号", required = true)
    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Basic
    @ApiModelProperty(value = "发票序号", required = true)
    @Column(name = "invoice_id", nullable = false)
    private Long invoiceId;

    @Basic
    @ApiModelProperty(value = "交易类型：0=正交易 1：负交易", required = true)
    @Column(name = "pay_type", nullable = false)
    private Integer payType;

    @Basic
    @ApiModelProperty(value = "应收金额", required = true)
    @Column(name = "total_money", nullable = false, precision = 12, scale = 2)
    private Double totalMoney;

    @Basic
    @ApiModelProperty(value = "支付金额", required = true)
    @Column(name = "pay_money", nullable = false, precision = 12, scale = 2)
    private Double payMoney;

    @Basic
    @ApiModelProperty(value = "优惠金额", required = true)
    @Column(name = "discount_monery", nullable = false, precision = 12, scale = 2)
    private Double discountMonery;

    @Basic
    @ApiModelProperty(value = "待收金额", required = true)
    @Column(name = "uncollect_money", nullable = false, precision = 12, scale = 2)
    private Double uncollectMoney;

    @Basic
    @ApiModelProperty(value = "付款时间", required = true)
    @Column(name = "pay_time", nullable = false)
    private Date payTime;

    @Basic
    @ApiModelProperty(value = "付款标志：0=未付款，1=分期，2=付全款", required = true)
    @Column(name = "pay_flag", nullable = false)
    private Integer payFlag;

}
