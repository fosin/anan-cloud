package com.github.fosin.cdp.platformapi.entity;

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
 * 系统支付明细表(CdpSysPayDetail)实体类
 *
 * @author fosin
 * @date 2018-11-18 17:28:24
 * @since 1.0.0
 */
@Data
@Entity
@DynamicUpdate
@Table(name = "cdp_sys_pay_detail")
@ApiModel(value = "系统支付明细表实体类", description = "表(cdp_sys_pay_detail)的对应的实体类")
public class CdpSysPayDetailEntity implements Serializable {
    private static final long serialVersionUID = 627623954130333748L;
    
    @Id
    @Column(name = "paydetail_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "主键，系统自动生成,付款明细ID")
    private Long paydetailId;
    
    @Basic
    @NotNull
    @Column(name = "pay_id")
    @ApiModelProperty(value = "支付ID")
    private Long payId;
    
    @Basic
    @NotNull
    @Column(name = "payway")
    @ApiModelProperty(value = "付款方式")
    private Integer payway;
    
    @Basic
    @NotNull
    @Column(name = "money")
    @ApiModelProperty(value = "付款金额")
    private Double money;
    
}