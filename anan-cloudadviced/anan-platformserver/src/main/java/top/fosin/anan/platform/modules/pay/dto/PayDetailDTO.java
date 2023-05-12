package top.fosin.anan.platform.modules.pay.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.entity.Id;

/**
 * 系统支付明细表(anan_pay_detail)DTO
 *
 * @author fosin
 * @date 2023-05-11
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "系统支付明细表DTO", description = "系统支付明细表(anan_pay_detail)DTO")
public class PayDetailDTO extends Id<Long> {
    private static final long serialVersionUID = 855421642336412494L;
    @ApiModelProperty(value = "支付ID", required = true, example = "Long")
    private Long payId;

    @ApiModelProperty(value = "付款方式", required = true, example = "Integer")
    private Integer payway;

    @ApiModelProperty(value = "付款金额", required = true, example = "Double")
    private Double money;

}
