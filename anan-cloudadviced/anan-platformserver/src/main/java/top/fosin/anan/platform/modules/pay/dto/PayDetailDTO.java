package top.fosin.anan.platform.modules.pay.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.entity.Id;

import java.math.BigDecimal;

/**
 * 系统支付明细表(anan_pay_detail)DTO
 *
 * @author fosin
 * @date 2023-05-11
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "系统支付明细表(anan_pay_detail)DTO")
public class PayDetailDTO extends Id<Long> {
    private static final long serialVersionUID = 855421642336412494L;
    @Schema(description = "支付ID", example = "Long")
    private Long payId;

    @Schema(description = "付款方式", example = "Integer")
    private Integer payway;

    @Schema(description = "付款金额", example = "Double")
    private BigDecimal money;

}
