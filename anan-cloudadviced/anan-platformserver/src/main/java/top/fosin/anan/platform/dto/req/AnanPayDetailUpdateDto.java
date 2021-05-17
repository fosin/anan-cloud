package top.fosin.anan.platform.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.model.dto.IdDto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 系统支付明细表(AnanPayDetail)更新DTO
 *
 * @author fosin
 * @date 2019-02-19 18:14:31
 * @since 1.0.0
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "系统支付明细表更新DTO", description = "表(anan_pay_detail)的对应的更新DTO")
public class AnanPayDetailUpdateDto extends IdDto<Long> implements Serializable {
    private static final long serialVersionUID = -84127296586986403L;

    @NotNull(message = "支付ID" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "支付ID", required = true)
    private Long payId;

    @NotNull(message = "付款方式" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "付款方式", required = true)
    private Integer payway;

    @NotNull(message = "付款金额" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "付款金额", required = true)
    private Double money;

}
