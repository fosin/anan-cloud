package top.fosin.anan.platform.modules.pay.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.cloudresource.grpc.service.UserGrpcServiceImpl;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.data.converter.translate.Translate2String;
import top.fosin.anan.data.entity.Id;

import java.util.Date;

/**
 * 系统支付发票表(anan_pay_invoice)集合VO
 *
 * @author fosin
 * @date 2023-05-11
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "系统支付发票表集合VO", description = "系统支付发票表(anan_pay_invoice)集合VO")
public class PayInvoicePageVO extends Id<Long> {
    private static final long serialVersionUID = 780407278285262525L;
    @ApiModelProperty(value = "支付ID")
    private Long payId;

    @ApiModelProperty(value = "发票号码")
    private String invoceNo;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "出票时间")
    private Date invoceTime;

    @Translate2String(service = UserGrpcServiceImpl.class, dicId = "")
    @ApiModelProperty(value = "操作人")
    private Long createBy;

}
