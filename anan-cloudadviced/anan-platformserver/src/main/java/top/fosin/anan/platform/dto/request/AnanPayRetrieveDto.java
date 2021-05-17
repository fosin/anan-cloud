package top.fosin.anan.platform.dto.request;

import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.model.dto.QuerySortRuleDto;
import top.fosin.anan.model.module.SortRule;
import top.fosin.anan.model.module.QueryRule;

/**
 * 系统支付表(AnanPay)查询DTO
 *
 * @author fosin
 * @date 2019-02-19 18:14:31
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "系统支付表查询DTO", description = "表(anan_pay)的对应的查询DTO")
public class AnanPayRetrieveDto extends QuerySortRuleDto<QueryRule,SortRule> implements Serializable {
    private static final long serialVersionUID = 154780790568751897L;

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
