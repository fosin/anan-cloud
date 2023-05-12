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
 * 系统支付表(anan_pay)通用查询DTO
 *
 * @author fosin
 * @date 2023-05-11
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "系统支付表通用查询DTO", description = "系统支付表(anan_pay)通用查询DTO")
public class PayQuery extends LogiSortQuery<LogiQueryRule,SortRule,Long> {
    private static final long serialVersionUID = -67216313896488426L;
    
    @ApiModelProperty(value = "付款机构", example = "Long")
    private Long organizId;

    @ApiModelProperty(value = "付款用户", example = "Long")
    private Long userId;

    @ApiModelProperty(value = "订单ID", example = "Long")
    private Long orderId;

    @ApiModelProperty(value = "发票ID", example = "Long")
    private Long invoiceId;

    @ApiModelProperty(value = "交易类型：0=正交易 1：负交易", example = "Integer")
    private Integer payType;

    @ApiModelProperty(value = "应收金额", example = "Double")
    private Double totalMoney;

    @ApiModelProperty(value = "支付金额", example = "Double")
    private Double payMoney;

    @ApiModelProperty(value = "优惠金额", example = "Double")
    private Double discountMonery;

    @ApiModelProperty(value = "待收金额", example = "Double")
    private Double uncollectMoney;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "付款日期", example = "Date")
    private Date payTime;

    @ApiModelProperty(value = "付款标志：0=未付款，1=分期，2=付全款", example = "Integer")
    private Integer payFlag;

}
