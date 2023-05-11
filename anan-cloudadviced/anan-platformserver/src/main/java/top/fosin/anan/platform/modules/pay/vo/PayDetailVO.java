package top.fosin.anan.platform.modules.pay.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;

import java.util.Date;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.entity.Id;

/**
 * 系统支付明细表(anan_pay_detail)单体VO
 *
 * @author fosin
 * @date 2023-05-11 22:57:02
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "系统支付明细表单体VO", description = "系统支付明细表(anan_pay_detail)单体VO")
public class PayDetailVO extends Id<Long> {
    private static final long serialVersionUID = 212927174306286675L;
    @ApiModelProperty(value = "支付ID")
    private Long payId;

    @ApiModelProperty(value = "付款方式")
    private Integer payway;

    @ApiModelProperty(value = "付款金额")
    private Double money;

}
