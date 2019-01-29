package com.github.fosin.cdp.platform.entity;

import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;
import java.io.Serializable;
import lombok.Data;
/**
 * 系统支付发票表(CdpSysPayInvoice)实体类
 *
 * @author fosin
 * @date 2019-01-28 12:50:38
 * @since 1.0.0
 */
@Data
@Entity
@DynamicUpdate
@Table(name = "cdp_sys_pay_invoice")
@ApiModel(value = "系统支付发票表实体类", description = "表(cdp_sys_pay_invoice)的对应的实体类")
public class CdpSysPayInvoiceEntity  implements Serializable {
    private static final long serialVersionUID = 120666820121378212L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "发票ID, 主键，一般系统自动生成")
    @Column(name = "invoce_id", nullable = false)
    private Long invoceId;

    @Basic
    @ApiModelProperty(value = "支付ID", required = true)
    @Column(name = "pay_id", nullable = false)
    private Long payId;

    @Basic
    @ApiModelProperty(value = "发票号码", required = true)
    @Column(name = "invoce_no", nullable = false, length = 40)
    private String invoceNo;

    @Basic
    @ApiModelProperty(value = "出票时间", required = true)
    @Column(name = "invoce_time", nullable = false)
    private Date invoceTime;

    @Basic
    @ApiModelProperty(value = "操作人", required = true)
    @Column(name = "crreate_by", nullable = false)
    private Long crreateBy;

}