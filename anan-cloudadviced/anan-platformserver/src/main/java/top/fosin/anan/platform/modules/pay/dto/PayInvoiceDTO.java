package top.fosin.anan.platform.modules.pay.dto;


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
 * 系统支付发票表(anan_pay_invoice)DTO
 *
 * @author fosin
 * @date 2023-05-11
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "系统支付发票表DTO", description = "系统支付发票表(anan_pay_invoice)DTO")
public class PayInvoiceDTO extends Id<Long> {
    private static final long serialVersionUID = 547109070798445539L;
    @ApiModelProperty(value = "支付ID", required = true, example = "Long")
    private Long payId;

    @ApiModelProperty(value = "发票号码", required = true, example = "String")
    private String invoceNo;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "出票时间", required = true, example = "Date")
    private Date invoceTime;

    @ApiModelProperty(value = "操作人", required = true, example = "Long")
    private Long createBy;

}
