package top.fosin.anan.platform.dto.request;

import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.model.module.QuerySortRuleEntity;
import top.fosin.anan.model.module.SortRule;
import top.fosin.anan.model.module.QueryRule;

/**
 * 系统支付订单表(AnanPayOrder)查询DTO
 *
 * @author fosin
 * @date 2019-02-19 18:14:31
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "系统支付订单表查询DTO", description = "表(anan_pay_order)的对应的查询DTO")
public class AnanPayOrderRetrieveDto extends QuerySortRuleEntity<QueryRule,SortRule> implements Serializable {
    private static final long serialVersionUID = -15118452601033466L;

    @ApiModelProperty(value = "订单ID, 主键")
    private Long id;

    @ApiModelProperty(value = "订单机构")
    private Long organizId;

    @ApiModelProperty(value = "订单用户")
    private Long userId;

    @ApiModelProperty(value = "版本ID")
    private Long versionId;

    @ApiModelProperty(value = "版本金额")
    private Double money;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "订单日期")
    private Date orderTime;

    @ApiModelProperty(value = "订单状态：0=新建，1=支付，2=取消，3=作废")
    private Integer status;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "支付日期")
    private Date payTime;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "取消日期")
    private Date cancleTime;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "作废日期")
    private Date invalidTime;

}
