package top.fosin.anan.platform.modules.pay.po;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import top.fosin.anan.jpa.po.IdPO;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 支付订单表(AnanPayOrder)实体类
 *
 * @author fosin
 * @date 2019-01-28 12:50:35
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DynamicUpdate
@Table(name = "anan_pay_order")
@Schema(description = "支付订单的实体类")
public class PayOrder extends IdPO<Long> {
    private static final long serialVersionUID = -88896111334285360L;

    @Basic
    @Schema(description = "订单用户")
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Basic
    @Schema(description = "版本序号")
    @Column(name = "version_id", nullable = false)
    private Long versionId;

    @Column(name = "organiz_id", nullable = false)
    @Basic
    @Schema(description = "机构序号")
    private Long organizId;

    @Basic
    @Schema(description = "版本金额")
    @Column(name = "money", nullable = false, precision = 12, scale = 2)
    private BigDecimal money;

    @Basic
    @Schema(description = "订单时间")
    @Column(name = "order_time", nullable = false)
    private Date orderTime;

    @Basic
    @Schema(description = "订单状态：0=新建，1=支付，2=取消，3=作废")
    @Column(name = "status", nullable = false)
    private Byte status;

    @Basic
    @Schema(description = "支付时间")
    @Column(name = "pay_time")
    private Date payTime;

    @Basic
    @Schema(description = "取消时间")
    @Column(name = "cancle_time")
    private Date cancleTime;

    @Basic
    @Schema(description = "作废时间")
    @Column(name = "invalid_time")
    private Date invalidTime;

}
