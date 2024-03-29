package top.fosin.anan.platform.modules.pay.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import top.fosin.anan.jpa.po.IdPO;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
@ApiModel(value = "支付明细表实体类", description = "支付明细的实体类")
public class PayDetail extends IdPO<Long> {
    private static final long serialVersionUID = -23058131683087837L;

    @Basic
    @ApiModelProperty(value = "支付序号", required = true)
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
