package com.github.fosin.cdp.platform.entity;

import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import com.github.fosin.cdp.util.DateTimeUtil;
/**
 * 系统支付表(CdpSysPay)实体类
 *
 * @author fosin
 * @date 2018-11-18 17:28:24
 * @since 1.0.0
 */
@Data
@Entity
@DynamicUpdate
@Table(name = "cdp_sys_pay")
@ApiModel(value = "系统支付表实体类", description = "表(cdp_sys_pay)的对应的实体类")
public class CdpSysPayEntity implements Serializable {
    private static final long serialVersionUID = -56983684877267659L;
    
    @Id
    @Column(name = "pay_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "主键，系统自动生成,支付ID")
    private Long payId;
    
    @Basic
    @NotNull
    @Column(name = "organiz_id")
    @ApiModelProperty(value = "付款机构")
    private Long organizId;
    
    @Basic
    @NotNull
    @Column(name = "user_id")
    @ApiModelProperty(value = "付款用户")
    private Long userId;
    
    @Basic
    @NotNull
    @Column(name = "order_id")
    @ApiModelProperty(value = "订单ID")
    private Long orderId;
    
    @Basic
    @NotNull
    @Column(name = "invoice_id")
    @ApiModelProperty(value = "发票ID")
    private Long invoiceId;
    
    @Basic
    @NotNull
    @Column(name = "pay_type")
    @ApiModelProperty(value = "交易类型：0=正交易 1：负交易")
    private Integer payType;
    
    @Basic
    @NotNull
    @Column(name = "total_money")
    @ApiModelProperty(value = "应收金额")
    private Double totalMoney;
    
    @Basic
    @NotNull
    @Column(name = "pay_money")
    @ApiModelProperty(value = "支付金额")
    private Double payMoney;
    
    @Basic
    @NotNull
    @Column(name = "discount_monery")
    @ApiModelProperty(value = "优惠金额")
    private Double discountMonery;
    
    @Basic
    @NotNull
    @Column(name = "uncollect_money")
    @ApiModelProperty(value = "待收金额")
    private Double uncollectMoney;
    
    @Basic
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Column(name = "pay_time")
    @ApiModelProperty(value = "付款日期")
    private Date payTime;
    
    @Basic
    @NotNull
    @Column(name = "pay_flag")
    @ApiModelProperty(value = "付款标志：0=未付款，1=分期，2=付全款")
    private Integer payFlag;
    
}