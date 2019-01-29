package com.github.fosin.cdp.platform.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;
import java.io.Serializable;
import lombok.Data;
/**
 * 系统支付明细表(CdpSysPayDetail)实体类
 *
 * @author fosin
 * @date 2019-01-28 12:50:34
 * @since 1.0.0
 */
@Data
@Entity
@DynamicUpdate
@Table(name = "cdp_sys_pay_detail")
@ApiModel(value = "系统支付明细表实体类", description = "表(cdp_sys_pay_detail)的对应的实体类")
public class CdpSysPayDetailEntity  implements Serializable {
    private static final long serialVersionUID = -23058131683087837L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "付款明细ID, 主键，一般系统自动生成")
    @Column(name = "paydetail_id", nullable = false)
    private Long paydetailId;

    @Basic
    @ApiModelProperty(value = "支付ID", required = true)
    @Column(name = "pay_id", nullable = false)
    private Long payId;

    @Basic
    @ApiModelProperty(value = "付款方式", required = true)
    @Column(name = "payway", nullable = false)
    private Integer payway;

    @Basic
    @ApiModelProperty(value = "付款金额", required = true)
    @Column(name = "money", nullable = false, precision = 12, scale = 2)
    private Double money;

}