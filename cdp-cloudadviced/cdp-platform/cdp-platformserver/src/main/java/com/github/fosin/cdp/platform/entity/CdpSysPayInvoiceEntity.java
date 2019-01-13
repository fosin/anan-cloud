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
 * 系统支付发票表(CdpSysPayInvoice)实体类
 *
 * @author fosin
 * @date 2018-11-18 17:28:24
 * @since 1.0.0
 */
@Data
@Entity
@DynamicUpdate
@Table(name = "cdp_sys_pay_invoice")
@ApiModel(value = "系统支付发票表实体类", description = "表(cdp_sys_pay_invoice)的对应的实体类")
public class CdpSysPayInvoiceEntity implements Serializable {
    private static final long serialVersionUID = -93980255906055088L;
    
    @Id
    @Column(name = "invoce_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "主键，系统自动生成,发票ID")
    private Long invoceId;
    
    @Basic
    @NotNull
    @Column(name = "pay_id")
    @ApiModelProperty(value = "支付ID")
    private Long payId;
    
    @Basic
    @NotBlank
    @Column(name = "invoce_no")
    @ApiModelProperty(value = "发票号码")
    private String invoceNo;
    
    @Basic
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Column(name = "invoce_time")
    @ApiModelProperty(value = "出票时间")
    private Date invoceTime;
    
    @Basic
    @NotNull
    @Column(name = "crreate_by")
    @ApiModelProperty(value = "操作人")
    private Long crreateBy;
    
}