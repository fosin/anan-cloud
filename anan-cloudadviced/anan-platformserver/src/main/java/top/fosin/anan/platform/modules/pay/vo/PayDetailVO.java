package top.fosin.anan.platform.modules.pay.vo;



import io.swagger.v3.oas.annotations.media.Schema;


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
 * @date 2023-05-11
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "系统支付明细表(anan_pay_detail)单体VO")
public class PayDetailVO extends Id<Long> {
    private static final long serialVersionUID = 212927174306286675L;
    @Schema(description = "支付ID")
    private Long payId;

    @Schema(description = "付款方式")
    private Integer payway;

    @Schema(description = "付款金额")
    private Double money;

}
