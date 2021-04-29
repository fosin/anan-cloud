package top.fosin.anan.platform.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 系统支付明细表(AnanPayDetail)创建DTO
 *
 * @author fosin
 * @date 2019-02-19 18:14:31
 * @since 1.0.0
 */
@Data
@ApiModel(value = "系统支付明细表创建DTO", description = "表(anan_pay_detail)的对应的创建DTO")
public class AnanPayDetailCreateDto implements Serializable {
    private static final long serialVersionUID = -77766644338507723L;

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
