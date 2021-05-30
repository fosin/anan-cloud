package top.fosin.anan.platform.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import top.fosin.anan.jpa.entity.IdEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 系统支付发票表(AnanPayInvoice)实体类
 *
 * @author fosin
 * @date 2019-01-28 12:50:38
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DynamicUpdate
@Table(name = "anan_pay_invoice")
@ApiModel(value = "系统支付发票表实体类", description = "系统支付发票的实体类")
public class AnanPayInvoiceEntity extends IdEntity<Long> implements Serializable {
    private static final long serialVersionUID = 120666820121378212L;

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
    @Column(name = "create_by", nullable = false)
    private Long createBy;

}
