package top.fosin.anan.platform.modules.pay.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.entity.Id;

import java.math.BigDecimal;

/**
 * 系统支付明细表(anan_pay_detail)集合VO
 *
 * @author fosin
 * @date 2023-05-11
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "系统支付明细表(anan_pay_detail)集合VO")
public class PayDetailPageVO extends Id<Long> {
    private static final long serialVersionUID = 715513099871489158L;
    @Schema(description = "支付ID")
    private Long payId;

    @Schema(description = "付款方式")
    private Integer payway;

    @Schema(description = "付款金额")
    private BigDecimal money;

}
