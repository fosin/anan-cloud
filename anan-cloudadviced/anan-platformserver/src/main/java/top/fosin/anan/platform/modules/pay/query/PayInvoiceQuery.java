package top.fosin.anan.platform.modules.pay.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicUpdate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

import lombok.Data;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.data.entity.req.LogiSortQuery;
import top.fosin.anan.data.module.SortRule;
import top.fosin.anan.data.module.LogiQueryRule;

import java.util.Date;

/**
 * 系统支付发票表(anan_pay_invoice)通用查询DTO
 *
 * @author fosin
 * @date 2023-05-11
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "系统支付发票表通用查询DTO", description = "系统支付发票表(anan_pay_invoice)通用查询DTO")
public class PayInvoiceQuery extends LogiSortQuery<LogiQueryRule, SortRule, Long> {
    private static final long serialVersionUID = 194036415171177655L;

    @ApiModelProperty(value = "支付ID", example = "Long")
    private Long payId;

    @ApiModelProperty(value = "发票号码", example = "String")
    private String invoceNo;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "出票时间", example = "Date")
    private Date invoceTime;

}
