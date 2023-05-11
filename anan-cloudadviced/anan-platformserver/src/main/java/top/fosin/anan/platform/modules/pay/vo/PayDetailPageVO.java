package top.fosin.anan.platform.modules.pay.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.entity.Id;

/**
 * 系统支付明细表(anan_pay_detail)集合VO
 *
 * @author fosin
 * @date 2023-05-11 22:57:02
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "系统支付明细表集合VO", description = "系统支付明细表(anan_pay_detail)集合VO")
public class PayDetailPageVO extends Id<Long> {
    private static final long serialVersionUID = 715513099871489158L;
    @ApiModelProperty(value = "支付ID")
    private Long payId;

    @ApiModelProperty(value = "付款方式")
    private Integer payway;

    @ApiModelProperty(value = "付款金额")
    private Double money;

}
