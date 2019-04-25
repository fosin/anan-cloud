package com.github.fosin.anan.platform.entity;
import com.github.fosin.anan.jpa.entity.AbstractIdJpaEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 系统支付表(AnanPay)实体类
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
@ApiModel(value = "系统支付表实体类", description = "表(anan_pay)的对应的实体类")
public class AnanPayEntity extends AbstractIdJpaEntity<Long> implements Serializable {
    private static final long serialVersionUID = 197340387949290400L;

    @Basic
    @ApiModelProperty(value = "付款用户", required = true)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Basic
    @ApiModelProperty(value = "订单ID", required = true)
    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Basic
    @ApiModelProperty(value = "发票ID", required = true)
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
    @ApiModelProperty(value = "付款日期", required = true)
    @Column(name = "pay_time", nullable = false)
    private Date payTime;

    @Basic
    @ApiModelProperty(value = "付款标志：0=未付款，1=分期，2=付全款", required = true)
    @Column(name = "pay_flag", nullable = false)
    private Integer payFlag;

}
