package top.fosin.anan.platform.modules.pay.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.valid.group.Create;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

/**
 * 系统支付明细表(anan_pay_detail)创建DTO
 *
 * @author fosin
 * @date 2023-05-11
 */
@Data
@EqualsAndHashCode
@ToString(callSuper = true)
@ApiModel(value = "系统支付明细表创建DTO", description = "系统支付明细表(anan_pay_detail)创建DTO")
public class PayDetailCreateDTO {
    private static final long serialVersionUID = -99469164739314029L;

    @NotNull(message = "支付序号" + "{javax.validation.constraints.NotNull.message}", groups = Create.class)
    @PositiveOrZero(message = "使用状态" + "{javax.validation.constraints.Positive.message}", groups = Create.class)
    @ApiModelProperty(value = "支付序号", required = true)
    private Long payId;

    @NotNull(message = "付款方式" + "{javax.validation.constraints.NotNull.message}", groups = Create.class)
    @PositiveOrZero(message = "使用状态" + "{javax.validation.constraints.Positive.message}", groups = Create.class)
    @ApiModelProperty(value = "付款方式", required = true)
    private Integer payway;

    @NotNull(message = "付款金额" + "{javax.validation.constraints.NotNull.message}", groups = Create.class)
    @ApiModelProperty(value = "付款金额", required = true)
    private Double money;

}
