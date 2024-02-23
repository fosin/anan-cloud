package top.fosin.anan.platform.modules.pay.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.entity.Id;
import top.fosin.anan.data.valid.group.Update;

import java.math.BigDecimal;
/**
 * 系统支付明细表(anan_pay_detail)更新DTO
 *
 * @author fosin
 * @date 2023-05-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Schema(description = "系统支付明细表(anan_pay_detail)更新DTO")
public class PayDetailUpdateDTO extends Id<Long> {
    private static final long serialVersionUID = 697831036987222834L;

    @NotNull(message = "支付序号" + "{jakarta.validation.constraints.NotNull.message}", groups = Update.class)
    @PositiveOrZero(message = "使用状态" + "{jakarta.validation.constraints.Positive.message}", groups = Update.class)
    @Schema(description = "支付序号")
    private Long payId;

    @NotNull(message = "付款方式" + "{jakarta.validation.constraints.NotNull.message}", groups = Update.class)
    @PositiveOrZero(message = "使用状态" + "{jakarta.validation.constraints.Positive.message}", groups = Update.class)
    @Schema(description = "付款方式")
    private Integer payway;

    @NotNull(message = "付款金额" + "{jakarta.validation.constraints.NotNull.message}", groups = Update.class)
    @Schema(description = "付款金额")
    private BigDecimal money;

}
