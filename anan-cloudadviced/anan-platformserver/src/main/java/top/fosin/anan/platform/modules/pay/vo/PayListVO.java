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
 * 系统支付表(anan_pay)集合VO
 *
 * @author fosin
 * @date 2023-05-11
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "系统支付表集合VO", description = "系统支付表(anan_pay)集合VO")
public class PayListVO extends Id<Long> {
    private static final long serialVersionUID = 363788573574672700L;
    @ApiModelProperty(value = "付款机构")
    private Long organizId;

    @ApiModelProperty(value = "付款用户")
    private Long userId;

    @ApiModelProperty(value = "订单ID")
    private Long orderId;

    @ApiModelProperty(value = "发票ID")
    private Long invoiceId;

    @ApiModelProperty(value = "交易类型：0=正交易 1：负交易")
    private Integer payType;

    @ApiModelProperty(value = "应收金额")
    private Double totalMoney;

    @ApiModelProperty(value = "支付金额")
    private Double payMoney;

    @ApiModelProperty(value = "优惠金额")
    private Double discountMonery;

    @ApiModelProperty(value = "待收金额")
    private Double uncollectMoney;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "付款日期")
    private Date payTime;

    @ApiModelProperty(value = "付款标志：0=未付款，1=分期，2=付全款")
    private Integer payFlag;

}
