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

/**
 * 支付明细表(AnanPayDetail)实体类
 *
 * @author fosin
 * @date 2019-01-28 12:50:34
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DynamicUpdate
@Table(name = "anan_pay_detail")
@Schema(description = "支付明细的实体类")
public class PayDetail extends IdPO<Long> {
    private static final long serialVersionUID = -23058131683087837L;

    @Basic
    @Schema(description = "支付序号")
    @Column(name = "pay_id", nullable = false)
    private Long payId;

    @Basic
    @Schema(description = "付款方式")
    @Column(name = "payway", nullable = false)
    private Integer payway;

    @Basic
    @Schema(description = "付款金额")
    @Column(name = "money", nullable = false, precision = 12, scale = 2)
    private Double money;

}
