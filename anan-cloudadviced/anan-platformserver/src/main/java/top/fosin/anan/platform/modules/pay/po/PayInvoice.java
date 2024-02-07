package top.fosin.anan.platform.modules.pay.po;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import top.fosin.anan.jpa.po.IdPO;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.Date;

/**
 * 支付发票表(AnanPayInvoice)实体类
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
@Schema(description = "支付发票的实体类")
public class PayInvoice extends IdPO<Long> {
    private static final long serialVersionUID = 120666820121378212L;

    @Basic
    @Schema(description = "支付序号")
    @Column(name = "pay_id", nullable = false)
    private Long payId;

    @Basic
    @Schema(description = "发票号码")
    @Column(name = "invoce_no", nullable = false, length = 40)
    private String invoceNo;

    @Basic
    @Schema(description = "出票时间")
    @Column(name = "invoce_time", nullable = false)
    private Date invoceTime;

    @Basic
    @Schema(description = "操作人")
    @Column(name = "create_by", nullable = false)
    private Long createBy;

}
