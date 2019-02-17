package com.github.fosin.cdp.platform.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
/**
 * 系统支付订单表(CdpPayOrder)实体类
 *
 * @author fosin
 * @date 2019-01-28 12:50:35
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DynamicUpdate
@Table(name = "cdp_pay_order")
@ApiModel(value = "系统支付订单表实体类", description = "表(cdp_pay_order)的对应的实体类")
public class CdpPayOrderEntity  extends AbstractPersistable<Long> implements Serializable {
    private static final long serialVersionUID = -88896111334285360L;

    @Basic
    @ApiModelProperty(value = "订单用户", required = true)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Basic
    @ApiModelProperty(value = "版本ID", required = true)
    @Column(name = "version_id", nullable = false)
    private Long versionId;

    @Column(name = "organiz_id", nullable = false)
    @Basic
    @NotNull(message = "机构ID不能为空!")
    @ApiModelProperty(value = "机构ID")
    private Long organizId;

    @Basic
    @ApiModelProperty(value = "版本金额", required = true)
    @Column(name = "money", nullable = false, precision = 12, scale = 2)
    private Double money;

    @Basic
    @ApiModelProperty(value = "订单日期", required = true)
    @Column(name = "order_time", nullable = false)
    private Date orderTime;

    @Basic
    @ApiModelProperty(value = "订单状态：0=新建，1=支付，2=取消，3=作废", required = true)
    @Column(name = "status", nullable = false)
    private Integer status;

    @Basic
    @ApiModelProperty(value = "支付日期")
    @Column(name = "pay_time")
    private Date payTime;

    @Basic
    @ApiModelProperty(value = "取消日期")
    @Column(name = "cancle_time")
    private Date cancleTime;

    @Basic
    @ApiModelProperty(value = "作废日期")
    @Column(name = "invalid_time")
    private Date invalidTime;

}
