package com.github.fosin.cdp.platformapi.entity;

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
 * 系统支付订单表(CdpSysPayOrder)实体类
 *
 * @author fosin
 * @date 2018-11-18 17:28:24
 * @since 1.0.0
 */
@Data
@Entity
@DynamicUpdate
@Table(name = "cdp_sys_pay_order")
@ApiModel(value = "系统支付订单表实体类", description = "表(cdp_sys_pay_order)的对应的实体类")
public class CdpSysPayOrderEntity implements Serializable {
    private static final long serialVersionUID = 117971673346076302L;
    
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "主键，系统自动生成,订单ID")
    private Long orderId;
    
    @Basic
    @NotNull
    @Column(name = "organiz_id")
    @ApiModelProperty(value = "订单机构")
    private Long organizId;
    
    @Basic
    @NotNull
    @Column(name = "user_id")
    @ApiModelProperty(value = "订单用户")
    private Long userId;
    
    @Basic
    @NotNull
    @Column(name = "version_id")
    @ApiModelProperty(value = "版本ID")
    private Long versionId;
    
    @Basic
    @NotNull
    @Column(name = "money")
    @ApiModelProperty(value = "版本金额")
    private Double money;
    
    @Basic
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Column(name = "order_time")
    @ApiModelProperty(value = "订单日期")
    private Date orderTime;
    
    @Basic
    @NotNull
    @Column(name = "status")
    @ApiModelProperty(value = "订单状态：0=新建，1=支付，2=取消，3=作废")
    private Integer status;
    
    @Basic
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Column(name = "pay_time")
    @ApiModelProperty(value = "支付日期")
    private Date payTime;
    
    @Basic
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Column(name = "cancle_time")
    @ApiModelProperty(value = "取消日期")
    private Date cancleTime;
    
    @Basic
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Column(name = "invalid_time")
    @ApiModelProperty(value = "作废日期")
    private Date invalidTime;
    
}