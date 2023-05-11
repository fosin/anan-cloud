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
 * 系统支付发票表(anan_pay_invoice)单体VO
 *
 * @author fosin
 * @date 2023-05-11 22:57:03
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "系统支付发票表单体VO", description = "系统支付发票表(anan_pay_invoice)单体VO")
public class PayInvoiceVO extends Id<Long> {
    private static final long serialVersionUID = 198464160629998456L;
    @ApiModelProperty(value = "支付ID")
    private Long payId;

    @ApiModelProperty(value = "发票号码")
    private String invoceNo;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "出票时间")
    private Date invoceTime;

}
