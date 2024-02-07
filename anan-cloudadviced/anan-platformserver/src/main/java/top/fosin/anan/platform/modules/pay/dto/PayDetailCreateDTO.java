package top.fosin.anan.platform.modules.pay.dto;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.valid.group.Create;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

/**
 * 系统支付明细表(anan_pay_detail)创建DTO
 *
 * @author fosin
 * @date 2023-05-11
 */
@Data
@EqualsAndHashCode
@ToString(callSuper = true)
@Schema(description = "系统支付明细表(anan_pay_detail)创建DTO")
public class PayDetailCreateDTO {
    private static final long serialVersionUID = -99469164739314029L;

    @NotNull(message = "支付序号" + "{jakarta.validation.constraints.NotNull.message}", groups = Create.class)
    @PositiveOrZero(message = "使用状态" + "{jakarta.validation.constraints.Positive.message}", groups = Create.class)
    @Schema(description = "支付序号")
    private Long payId;

    @NotNull(message = "付款方式" + "{jakarta.validation.constraints.NotNull.message}", groups = Create.class)
    @PositiveOrZero(message = "使用状态" + "{jakarta.validation.constraints.Positive.message}", groups = Create.class)
    @Schema(description = "付款方式")
    private Integer payway;

    @NotNull(message = "付款金额" + "{jakarta.validation.constraints.NotNull.message}", groups = Create.class)
    @Schema(description = "付款金额")
    private Double money;

}
